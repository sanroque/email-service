package com.sanroque.email_service.core.exceptions;

import com.sanroque.email_service.adapters.EmailSenderGateway;

public class EmailServiceException extends RuntimeException{

    public EmailServiceException(String message){
        super(message);
    }

    public EmailServiceException(String message, Throwable cause){
        super(message, cause);
    }
}
