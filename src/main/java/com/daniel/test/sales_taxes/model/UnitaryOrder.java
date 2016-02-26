package com.daniel.test.sales_taxes.model;

import java.math.BigDecimal;

public class UnitaryOrder {

	private Product product;
	private float quantity;
	private BigDecimal orderPrice;
	private BigDecimal orderTax;
	
	public UnitaryOrder(Product product, float quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
		init();
	}
	
	private void init() {
		BigDecimal orderedPrice = product.getUnitPrice().multiply( new BigDecimal(quantity));
		setOrderPrice(orderedPrice);
	}
	
	public BigDecimal getOrderTaxedPrice() {
		return getOrderPrice().add(getOrderTax());
	}
	
	public boolean isStartingOrder(){
		return orderTax == null;
	}

	public Product getProduct() {
		return product;
	}
	public float getQuantity() {
		return quantity;
	}
	
	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getOrderTax() {
		return orderTax;
	}

	public void setOrderTax(BigDecimal orderTax) {
		this.orderTax = orderTax;
	}

	@Override
	public String toString() {
		return "" + quantity + " " + product.getName() + " : " + orderPrice.add(orderTax);
	}
}
