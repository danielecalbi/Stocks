package com.stocks.services.impl;

import com.stocks.services.CommonService;

/**
 * Class of Common Services.
 */
public class CommonServiceImpl implements CommonService {

	@Override
	public double getGeometricMean(double[] inputArray) {
		double result = 0d; 

        if (inputArray != null && inputArray.length > 0) {
        	 double geometricMean = inputArray[0];
             
             for(int i = 1; i < inputArray.length; i++) {
                 geometricMean *= inputArray[i];
             }
           
             Integer n = new Integer(inputArray.length);
             
             result = Math.pow(geometricMean, (1 / n.doubleValue()));
        }
      
        return result;
	}

}
