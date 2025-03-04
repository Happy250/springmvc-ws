package net.antra.restful.mobileappws.exception;

import net.antra.restful.mobileappws.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;


@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = { UserServiceException.class })
    public ResponseEntity<Object> handleUserServiceException(UserServiceException ex, WebRequest request) {
        ErrorMessage em = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(em, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<Object> handleOtherException(Exception ex, WebRequest request) {
        ErrorMessage em = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(em, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
