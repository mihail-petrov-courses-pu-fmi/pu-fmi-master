package com.fmi.solarparkapp.config;

public class ProdProjectCalculator implements ProjectCalculatorProvider{

    @Override
    public int getNumberOfProjects() {
        return 5;
    }
}
