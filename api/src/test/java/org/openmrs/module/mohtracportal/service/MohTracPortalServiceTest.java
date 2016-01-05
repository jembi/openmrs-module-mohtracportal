package org.openmrs.module.mohtracportal.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openmrs.OrderFrequency;
import org.openmrs.api.OrderService;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.test.Verifies;

public class MohTracPortalServiceTest extends BaseModuleContextSensitiveTest{
	
	private OrderService orderService = null;
	
	private MohTracPortalService mohTracPortalService = null;
	
	@Before
	public void setup() {
		orderService = Context.getOrderService();
	}
	
	@Verifies(value = "should Save a new orderfrequency if the coded concept doesn't exist and returns the newly saved one else returns the one existing", method = "persistAndOrFetchOrderFrequency(String)")
	@Test
	public void persistAndOrFetchOrderFrequency_shouldWorkAsExpected() throws Exception {
		mohTracPortalService = Context.getService(MohTracPortalService.class);
		
		OrderFrequency of = mohTracPortalService.persistAndOrFetchOrderFrequency("testing freq", 2d);
		
		Assert.assertNotNull(of);
		Assert.assertEquals(of.getName(), "testing freq");
		Assert.assertEquals(orderService.getOrderFrequencyByUuid(of.getUuid()).getUuid(), of.getUuid());
		
		OrderFrequency of2 = mohTracPortalService.persistAndOrFetchOrderFrequency("testing freq", 2d);
		
		Assert.assertNotNull(orderService.getOrderFrequencyByUuid(of2.getUuid()).getUuid());
		Assert.assertEquals(orderService.getOrderFrequencyByUuid(of.getUuid()).getUuid(), orderService.getOrderFrequencyByUuid(of2.getUuid()).getUuid());
	}
}
