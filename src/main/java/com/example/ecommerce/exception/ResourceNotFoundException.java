package com.example.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Menggunakan anotasi @ResponseStatus untuk memberikan status code NOT_FOUND
// ResponseStatus akan memberikan status code 404 jika resource yang dicari tidak ditemukan
@ResponseStatus(code = HttpStatus.NOT_FOUND)
// Membuat class ResourceNotFoundException yang merupakan subclass dari RuntimeException
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
