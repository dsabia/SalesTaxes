package com.daniel.test.sales_taxes.model;

public enum ProductType {

	BOOK(true), FOOD(true), MEDICAL(true), GENERIC;
	
	private boolean taxExemption = false;
	
	private ProductType() {}
	
	private ProductType(boolean exempt) {
		this.taxExemption = exempt;
	}
	
	public boolean isTaxExemption() {
		return taxExemption;
	}
	public void setTaxExemption(boolean taxExemption) {
		this.taxExemption = taxExemption;
	}
	
}
