package com.emc.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.emc.services.EmcService;

@Service("IterationService")
public class EmcServiceIterationImpl implements EmcService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmcServiceIterationImpl.class);

	@Override
	public List<Long> fibonacci(int size) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("using iteration fibonacci");
		}

		List<Long> list = new ArrayList<Long>();
		for (long i = 0; i < size; i++) {
			list.add(iteration(i));
		}

		return list;
	}

	private Long iteration(Long size) {
		long x = 0, y = 1, z = 1;

		for (int i = 0; i < size; i++) {
			x = y;
			y = z;
			z = x + y;
		}

		return x;
	}
}