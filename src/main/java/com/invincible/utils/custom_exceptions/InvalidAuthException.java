package com.invincible.utils.custom_exceptions;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@NoArgsConstructor
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidAuthException extends RuntimeException {
    public InvalidAuthException(String message) {
        super(message);
    }
}
