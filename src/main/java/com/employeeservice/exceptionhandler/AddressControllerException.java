package com.employeeservice.exceptionhandler;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.addressservice.model.ErrorResponse;
import com.employeeservice.exception.ValidationException;

@RestControllerAdvice
public class AddressControllerException {

	@ExceptionHandler(ValidationException.class)
	ResponseEntity<ErrorResponse> handlerValidationException(ValidationException exception) {
		ErrorResponse errorResponse = new ErrorResponse(new Date(), exception.getErrorCode(),
				exception.getErrorMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

	}

}
