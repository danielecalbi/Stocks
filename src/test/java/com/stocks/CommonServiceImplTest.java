package com.stocks;

import org.junit.Assert;
import org.junit.Test;

import com.stocks.services.CommonService;
import com.stocks.services.impl.CommonServiceImpl;

/**
 * Test class of Common Services.
 */
public class CommonServiceImplTest {

	private CommonService commonService = new CommonServiceImpl();

	@Test
	public void testGetGeometricMean() {
		Assert.assertTrue(commonService.getGeometricMean(null) == 0d);
		Assert.assertTrue(commonService.getGeometricMean(new double[] { 0d }) == 0d);
		Assert.assertTrue(Math.round(this.commonService.getGeometricMean(new double[] { 33d, 54.3d, 12d, 4.55d }) * 100) / 100d == 17.69);
		Assert.assertTrue(this.commonService.getGeometricMean(new double[] { 0d, 12.44d }) == 0d);
	}

}
