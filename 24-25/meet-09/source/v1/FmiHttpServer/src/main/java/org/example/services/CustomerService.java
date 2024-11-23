package org.example.services;

import org.example.steriotypes.Injectable;

@Injectable
public class CustomerService {

    public String hello() {
        return "Hello Customer service";
    }
}
