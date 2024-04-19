package com.example.ecommerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Menggunakan anotasi @ResponseStatus untuk memberikan status code BAD_REQUEST
// ResponseStatus akan memberikan status code 400 jika terjadi kesalahan pada request
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
// Membuat class BadRequestExeption yang merupakan subclass dari RuntimeException
public class BadRequestExeption extends RuntimeException{
    
    public BadRequestExeption(String message){
        super(message);
    }
}
