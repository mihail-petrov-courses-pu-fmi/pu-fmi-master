package org.example.services;

import org.example.steriotypes.Autowired;
import org.example.steriotypes.Injectable;

@Injectable
public class NestedLeve1 {

    @Autowired
    private NestedLevel2 nestedService;

    public String nested() {
        return this.nestedService.nested();
    }
}
