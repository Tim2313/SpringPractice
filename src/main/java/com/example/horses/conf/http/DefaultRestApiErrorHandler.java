package com.example.horses.conf.http;

import com.example.horses.api.dto.RestApiErrorVO;
import com.example.horses.excepition.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultRestApiErrorHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<RestApiErrorVO> handleEntityNotFoundError(HttpServletRequest request, EntityNotFoundException e) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        RestApiErrorVO error = new RestApiErrorVO()
                .setMessage(e.getMessage())
                .setMethod(request.getMethod())
                .setStatus(httpStatus.value());
        return new ResponseEntity<>(error, httpStatus);
    }

}
