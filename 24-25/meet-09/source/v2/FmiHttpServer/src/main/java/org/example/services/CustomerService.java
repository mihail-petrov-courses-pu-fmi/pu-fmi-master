package org.example.services;

import org.example.steriotypes.Autowired;
import org.example.steriotypes.Injectable;

@Injectable
public class CustomerService {

    @Autowired
    private NestedLeve1 nestedService;

    public String hello() {
        return this.nestedService.nested();
    }
}
