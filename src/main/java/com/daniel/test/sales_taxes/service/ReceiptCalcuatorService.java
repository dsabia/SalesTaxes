package com.daniel.test.sales_taxes.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.daniel.test.sales_taxes.commons.NumberUtil;
import com.daniel.test.sales_taxes.model.Product;
import com.daniel.test.sales_taxes.model.ReceiptReport;
import com.daniel.test.sales_taxes.model.SimpleTaxable;
import com.daniel.test.sales_taxes.model.UnitaryOrder;

@Service
public class ReceiptCalcuatorService {

	@Autowired
	private TaxService taxService;
	@Autowired
	private NumberUtil nu;
	
	public ReceiptReport generateReceiptFromOrder(List<UnitaryOrder> currentOrder) {
		BigDecimal priceOrder = BigDecimal.ZERO;
		BigDecimal taxTotalOrder = BigDecimal.ZERO;
		
		for (UnitaryOrder order : currentOrder) {
			
			if(!order.isStartingOrder()){
				throw new IllegalArgumentException("Invalid order, order already processed.");
			}
			
			Product product = order.getProduct();
			
			priceOrder = nu.add(priceOrder, order.getOrderPrice());//priceOrder.add(order.getOrderPrice());
			
			BigDecimal tax = getTax(product, order.getOrderPrice());
			order.setOrderTax(tax);
			
			
			taxTotalOrder = nu.add(taxTotalOrder,tax);
		}
		
		return new ReceiptReport(priceOrder, taxTotalOrder);
	}
	
	BigDecimal getTax(SimpleTaxable taxable, BigDecimal price) {
		// 1. retrieve taxes information
		Date today = new Date();
		BigDecimal percentageTaxToApply = taxService.getTaxPercentage(taxable, today);
		
		// 2. apply formula on input price
		return nu.multiplayPercentage(price, percentageTaxToApply);
	}
}
