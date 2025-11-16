package com.fmi.todoapp.services;

import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {

    private int counter = 0;

    public int getNext() {
        return ++counter;
    }
}
