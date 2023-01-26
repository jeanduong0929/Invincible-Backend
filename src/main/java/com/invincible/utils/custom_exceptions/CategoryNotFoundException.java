package com.invincible.utils.custom_exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(String message) {
        super(message);
    }
}
