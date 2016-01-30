package com.emc.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.emc.services.EmcService;

@Service("RecursionService")
public class EmcServiceRecursionImpl implements EmcService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmcServiceRecursionImpl.class);

	@Override
	public List<Long> fibonacci(int size) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("using recursive fibonacci");
		}

		List<Long> list = new ArrayList<Long>();
		for (long i = 0; i < size; i++) {
			list.add(recursive(i));
		}

		return list;
	}

	private Long recursive(Long size) {
		if (size <= 1) {
			return size;
		} else {
			return recursive(size - 1) + recursive(size - 2);
		}
	}
}