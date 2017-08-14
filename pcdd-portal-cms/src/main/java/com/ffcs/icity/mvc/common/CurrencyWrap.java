package com.ffcs.icity.mvc.common;

import java.math.BigDecimal;

public class CurrencyWrap {

	
	public static Double add(Double value, Double addValue) {
		if (value == null) {
			value = 0D;
		}
		if (addValue == null) {
			addValue = 0D;
		}
		BigDecimal decimal = new BigDecimal(value);
		return decimal.add(new BigDecimal(addValue)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	public static Double sub(Double value, Double subValue){
		if (value == null) {
			value = 0D;
		}
		if (subValue == null) {
			subValue = 0D;
		}
		BigDecimal decimal = new BigDecimal(value);
		return decimal.subtract(new BigDecimal(subValue)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
}
