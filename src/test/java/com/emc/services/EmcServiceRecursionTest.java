package com.emc.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.emc.EmcApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(EmcApplication.class)
public class EmcServiceRecursionTest {

	@Autowired
	@Qualifier("RecursionService")
	private EmcService emcService;
	
	@Test
	public void validFibonacciTest() {
		List<Long> list = emcService.fibonacci(5);
		List<Long> sampleList = new ArrayList<Long>();
		sampleList.add((long) 0);
		sampleList.add((long) 1);
		sampleList.add((long) 1);
		sampleList.add((long) 2);
		sampleList.add((long) 3);

		assertNotNull(list);
		assertEquals(list, sampleList);
	}

	@Test
	public void inValidFibonacciTest() {
		List<Long> list = emcService.fibonacci(-1);
		List<Long> sampleList = new ArrayList<Long>();
		sampleList.add((long) 0);
		sampleList.add((long) 1);
		sampleList.add((long) 1);
		sampleList.add((long) 2);
		sampleList.add((long) 3);

		assertNotEquals(list, sampleList);
		assertEquals(list, new ArrayList<Long>());
	}
}
