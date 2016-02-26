package com.daniel.test.sales_taxes.service;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.daniel.test.sales_taxes.service.ReceiptCalcuatorService;
import com.daniel.test.sales_taxes.util.AbstractTest;

public class ReceiptCalcuatorServiceUTest extends AbstractTest {

	private static final BigDecimal DEFAULT_PRICE_INPUT = new BigDecimal("100.00");
	private static final BigDecimal TAX_EXPECTED_NATIONAL_NO_EXEMPT = new BigDecimal("10.00");
	private static final BigDecimal TAX_EXPECTED_NATIONAL_EXEMPT = new BigDecimal("0.00");
	private static final BigDecimal TAX_EXPECTED_INTERNATIONAL_NO_EXEMPT = new BigDecimal("15.00");
	private static final BigDecimal TAX_EXPECTED_INTERNATIONAL_EXEMPT = new BigDecimal("5.00");
	
	@Autowired
	private ReceiptCalcuatorService receiptCalcuatorService;
	
	@Test
	public void testGenerateReceiptFromOrder(){
		System.out.println("TODO - maybe in integration test");
	}
	
	@Test
	public void testGetTax_national_exempt(){
		
		BigDecimal actualValue = receiptCalcuatorService.getTax(helper.generateTaxable(true, false), DEFAULT_PRICE_INPUT);
		helper.assertEqualsAtRound2(TAX_EXPECTED_NATIONAL_NO_EXEMPT, actualValue);
	}

	@Test
	public void testGetTax_national_generic(){
		BigDecimal actualValue = receiptCalcuatorService.getTax(helper.generateTaxable(true, true), DEFAULT_PRICE_INPUT);
		helper.assertEqualsAtRound2(TAX_EXPECTED_NATIONAL_EXEMPT, actualValue);
	}
	
	
	
	@Test
	public void testGetTax_international_exempt(){
		
		BigDecimal actualValue = receiptCalcuatorService.getTax(helper.generateTaxable(false, false), DEFAULT_PRICE_INPUT);
		helper.assertEqualsAtRound2(TAX_EXPECTED_INTERNATIONAL_NO_EXEMPT, actualValue);
	}

	@Test
	public void testGetTax_international_generic(){
		BigDecimal actualValue = receiptCalcuatorService.getTax(helper.generateTaxable(false, true), DEFAULT_PRICE_INPUT);
		helper.assertEqualsAtRound2(TAX_EXPECTED_INTERNATIONAL_EXEMPT, actualValue);
	}
}
