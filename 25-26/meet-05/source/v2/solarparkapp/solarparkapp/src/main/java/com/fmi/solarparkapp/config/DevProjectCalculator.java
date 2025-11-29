package com.fmi.solarparkapp.config;

public class DevProjectCalculator implements ProjectCalculatorProvider{

    @Override
    public int getNumberOfProjects() {
        return 1;
    }
}
