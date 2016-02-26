package com.daniel.test.sales_taxes.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

@Component
public class TaxRateList {
	
	public static class NATIONAL {
		static BigDecimal GENERIC_PRODUCT_TAXES = BigDecimal.TEN;
		static BigDecimal EXENT_PRODUCT_TAXES = BigDecimal.ZERO;
	}

	public static class INTERNATIONAL {
		static BigDecimal GENERIC_PRODUCT_TAXES = NATIONAL.GENERIC_PRODUCT_TAXES.add(INTERNATIONAL_FEE);
		static BigDecimal EXENT_PRODUCT_TAXES = NATIONAL.EXENT_PRODUCT_TAXES.add(INTERNATIONAL_FEE);
	}

	private static BigDecimal INTERNATIONAL_FEE = new BigDecimal(5);
}