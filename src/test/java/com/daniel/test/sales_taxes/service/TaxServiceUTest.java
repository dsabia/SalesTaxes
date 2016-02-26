package com.daniel.test.sales_taxes.service;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.daniel.test.sales_taxes.model.SimpleTaxable;
import com.daniel.test.sales_taxes.service.TaxRateList;
import com.daniel.test.sales_taxes.service.TaxService;
import com.daniel.test.sales_taxes.util.AbstractTest;


public class TaxServiceUTest extends AbstractTest {

	@Autowired
	private TaxService taxService;

	@Test
	public void testTaxPercentage_national_no_exempt() {
		SimpleTaxable taxable = helper.generateTaxable(true, false);
		Assert.assertSame(TaxRateList.NATIONAL.GENERIC_PRODUCT_TAXES, taxService.getTaxPercentage(taxable, new Date()));
	}

	@Test
	public void testTaxPercentage_national_exempt() {
		SimpleTaxable taxable = helper.generateTaxable(true, true);
		Assert.assertSame(TaxRateList.NATIONAL.EXENT_PRODUCT_TAXES, taxService.getTaxPercentage(taxable, new Date()));
	}

	@Test
	public void testTaxPercentage_international_no_exempt() {
		SimpleTaxable taxable = helper.generateTaxable(false, false);
		Assert.assertSame(TaxRateList.INTERNATIONAL.GENERIC_PRODUCT_TAXES, taxService.getTaxPercentage(taxable, new Date()));
	}

	@Test
	public void testTaxPercentage_international_exempt() {
		SimpleTaxable taxable = helper.generateTaxable(false, true);
		Assert.assertSame(TaxRateList.INTERNATIONAL.EXENT_PRODUCT_TAXES, taxService.getTaxPercentage(taxable, new Date()));
	}
}
