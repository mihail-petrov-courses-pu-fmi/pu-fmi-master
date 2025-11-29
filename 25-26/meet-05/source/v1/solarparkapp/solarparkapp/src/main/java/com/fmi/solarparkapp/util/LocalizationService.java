package com.fmi.solarparkapp.util;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

@Service
public class LocalizationService {

    // връща низ базиран върху стандартния ни локал
    public String getMessage(String key) {

        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource.getMessage(key, null, null);
    }
}
