package com.daniel.test.sales_taxes.model;

import java.math.BigDecimal;

public class ReceiptReport {
	
	private BigDecimal priceOrder;
	private BigDecimal taxTotalOrder;
	private BigDecimal taxedOrder;
	
	public ReceiptReport(BigDecimal priceOrder, BigDecimal taxTotalOrder) {
		super();
		this.priceOrder = priceOrder;
		this.taxTotalOrder = taxTotalOrder;
		this.taxedOrder = priceOrder.add(taxTotalOrder);
	}

	public BigDecimal getPriceOrder() {
		return priceOrder;
	}

	public BigDecimal getTaxTotalOrder() {
		return taxTotalOrder;
	}

	public BigDecimal getTaxedOrder() {
		return taxedOrder;
	}
	
	@Override
	//@formatter:off
	public String toString() {
		return ""
				+ "Sales Taxes: " + taxTotalOrder + "\n"
				+ "Total: " + taxedOrder;
	}
	//@formatter:on
}
