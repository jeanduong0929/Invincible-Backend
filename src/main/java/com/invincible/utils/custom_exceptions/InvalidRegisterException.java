package com.invincible.utils.custom_exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidRegisterException extends RuntimeException {
    public InvalidRegisterException(String message) {
        super(message);
    }
}
