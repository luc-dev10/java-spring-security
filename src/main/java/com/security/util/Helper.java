package com.security.util;

import org.springframework.stereotype.Component;

@Component
public class Helper {

    public int getPropertyInInteger(String value) {
        return value != null ? Integer.parseInt(value) : 0;
    }

}
