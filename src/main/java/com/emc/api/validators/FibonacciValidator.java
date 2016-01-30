package com.emc.api.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.emc.api.exceptions.BadRequestException;
import com.emc.api.models.request.FibonacciRequest;

@Component
public class FibonacciValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		return FibonacciRequest.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		FibonacciRequest fibonacciRequest = (FibonacciRequest) obj;

		if (fibonacciRequest.getSize() < 0) {
			errors.rejectValue("size", ErrorCodes.SIZE_INVALID,
					"size is lesser than 0. Please provide a positive number");
		}

		if (errors.hasErrors()) {
			throw new BadRequestException("required fields cannot be empty or invalid", errors);
		}
	}
}