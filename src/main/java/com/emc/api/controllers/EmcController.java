package com.emc.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emc.api.models.request.FibonacciRequest;
import com.emc.api.validators.FibonacciValidator;
import com.emc.services.EmcService;

@RestController
public class EmcController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmcController.class);

	@Autowired
	@Qualifier("IterationService")
	private EmcService emcService;

	@Autowired
	private FibonacciValidator fibonacciValidator;

	@InitBinder("fibonacciRequest")
	public void fibonacciBinder(WebDataBinder binder) {
		binder.setValidator(fibonacciValidator);
	}

	@RequestMapping(value = "/fibonacci", method = RequestMethod.POST)
	public ResponseEntity<List<Long>> getFibonacci(@Valid @RequestBody FibonacciRequest fibonacciRequest) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("Getting Fibonnacci for size: " + fibonacciRequest.getSize());
		}

		List<Long> fibList = emcService.fibonacci(fibonacciRequest.getSize());
		return new ResponseEntity<List<Long>>(fibList, HttpStatus.OK);
	}
}