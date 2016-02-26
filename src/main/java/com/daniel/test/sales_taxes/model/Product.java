package com.daniel.test.sales_taxes.model;

import java.math.BigDecimal;

public class Product implements SimpleTaxable{
	
	private final String name;
	private final boolean national;
	private BigDecimal unitPrice;
	private final ProductType type;
	
	public Product(String name, boolean national, BigDecimal unitPrice, ProductType type) {
		super();
		this.name = name;
		this.national = national;
		this.unitPrice = unitPrice;
		this.type = type;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public String getName() {
		return name;
	}
	public boolean isNational() {
		return national;
	}
	public ProductType getType() {
		return type;
	}
}
