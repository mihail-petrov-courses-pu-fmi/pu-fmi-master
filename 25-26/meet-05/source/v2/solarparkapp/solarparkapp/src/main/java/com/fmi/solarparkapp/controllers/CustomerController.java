package com.fmi.solarparkapp.controllers;

import com.fmi.solarparkapp.http.AppResponse;
import com.fmi.solarparkapp.models.base.CustomerModel;
import com.fmi.solarparkapp.services.CustomerService;
import com.fmi.solarparkapp.util.LocalizationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private CustomerService customerService;
    private LocalizationService localizationService;

    public CustomerController(CustomerService customerService, LocalizationService localizationService) {

        this.customerService = customerService;
        this.localizationService = localizationService;
    }

//
//    GET      /customers --> върни всички къстъмъри

    @GetMapping()
    public ResponseEntity<?> fetchAllCustomers() {

        // искам да получа колекция от всички потребители, които се намират в базата ми данни.
        ArrayList<CustomerModel> collection = (ArrayList<CustomerModel>) this.customerService.fetchAllCustomers();

        return AppResponse.success()
                .withData(collection)
                .send();
    }

//    GET      /customers/:id --> върни конкретен потребител
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchCustomerById(@PathVariable int id) {

        CustomerModel model = this.customerService.fetchCustomerById(id);

        if(model == null) {
            return AppResponse.error()
                    .withCode(HttpStatus.NOT_FOUND)
                    .withMessage(this.localizationService.getMessage("api.customer.message.not_found"))
                    .send();
        }

        return AppResponse.success()
                .withData(model)
                .send();
    }


//    POST     /customers
    @PostMapping()
    public ResponseEntity<?> createNewCustomer(
            @Valid @RequestBody CustomerModel customer
            //,
           //  BindingResult bindingResult
    ) {
//        if(bindingResult.hasErrors()) {
//
//            return AppResponse.error()
//                    .withMessage(this.localizationService.getMessage("api.customer.message.create_fail"))
//                    .send();
//        }

        if(this.customerService.createNewCustomer(customer)) {

            return AppResponse.success()
                    .withMessage(this.localizationService.getMessage("api.customer.message.create_new"))
                    .send();
        }

        return AppResponse.error()
                .withMessage(this.localizationService.getMessage("api.customer.message.create_fail"))
                .send();
    }

//    PUT      /customers  --> UPDATE
    @PutMapping()
    public ResponseEntity<?> updateCustomer(@RequestBody CustomerModel customer) {

        if(this.customerService.updateCustomer(customer)) {
            return AppResponse.success()
                    .withMessage(this.localizationService.getMessage("api.customer.message.update_success"))
                    .send();
        }

        return AppResponse.error()
                .withMessage("api.customer.message.update_fail")
                .send();
    }

//    DELETE   /customers/:id  --> UPDATE
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeCustomer(@PathVariable int id) {

        if(this.customerService.deleteCustomer(id)) {
            return AppResponse.success()
                    .withMessage(this.localizationService.getMessage("api.customer.message.delete_success"))
                    .send();
        }

        return AppResponse.error()
                .withMessage("api.customer.message.delete_fail")
                .send();
    }
}
