package com.daniel.test.sales_taxes.service;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.daniel.test.sales_taxes.model.SimpleTaxable;
import com.daniel.test.sales_taxes.service.TaxRateList.INTERNATIONAL;
import com.daniel.test.sales_taxes.service.TaxRateList.NATIONAL;

@Service
public class TaxService {
	
	public BigDecimal getTaxPercentage(SimpleTaxable taxable, Date today) {
		if(taxable.isNational() ){
			return taxable.getType().isTaxExemption() ? NATIONAL.EXENT_PRODUCT_TAXES : NATIONAL.GENERIC_PRODUCT_TAXES;
		}
		return taxable.getType().isTaxExemption() ? INTERNATIONAL.EXENT_PRODUCT_TAXES : INTERNATIONAL.GENERIC_PRODUCT_TAXES;
	}

}
