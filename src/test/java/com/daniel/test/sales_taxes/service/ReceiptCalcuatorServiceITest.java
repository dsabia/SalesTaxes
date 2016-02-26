package com.daniel.test.sales_taxes.service;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.daniel.test.sales_taxes.model.Product;
import com.daniel.test.sales_taxes.model.ProductType;
import com.daniel.test.sales_taxes.model.ReceiptReport;
import com.daniel.test.sales_taxes.model.UnitaryOrder;
import com.daniel.test.sales_taxes.service.ReceiptCalcuatorService;
import com.daniel.test.sales_taxes.util.AbstractTest;


public class ReceiptCalcuatorServiceITest extends AbstractTest {

	@Autowired
	private ReceiptCalcuatorService receiptCalcuatorService;
	
	@Test
	public void testGenerateReceipt_input1(){
		List<Product> products = new LinkedList<Product>();
		
		products.add( new Product("book", true, new BigDecimal("12.49"), ProductType.BOOK) );
		products.add( new Product("music CD", true, new BigDecimal("14.99"), ProductType.GENERIC) );
		products.add( new Product("chocolate bar", true, new BigDecimal("0.85"), ProductType.FOOD) );
		
		List<UnitaryOrder> currentOrder = helper.generateUnitaryOrder(products);
		ReceiptReport reportReceipt = receiptCalcuatorService.generateReceiptFromOrder(currentOrder);
		
		assertTotalAmountPrice(new BigDecimal("12.49"), currentOrder.get(0));
		assertTotalAmountPrice(new BigDecimal("16.49"), currentOrder.get(1));
		assertTotalAmountPrice(new BigDecimal("0.85"), currentOrder.get(2));
		
		helper.assertEqualsAtRound2(new BigDecimal("1.50"), reportReceipt.getTaxTotalOrder());
		helper.assertEqualsAtRound2(new BigDecimal("29.83"), reportReceipt.getTaxedOrder());
	}

	@Test
	public void testGenerateReceipt_input2(){
		List<Product> products = new LinkedList<Product>();
		
		products.add( new Product("imported box of chocolates", false, new BigDecimal("10.00"), ProductType.FOOD) );
		products.add( new Product("imported bottle of perfume", false, new BigDecimal("47.50"), ProductType.GENERIC) );
		
		
		List<UnitaryOrder> currentOrder = helper.generateUnitaryOrder(products);
		ReceiptReport reportReceipt = receiptCalcuatorService.generateReceiptFromOrder(currentOrder);
		
		assertTotalAmountPrice(new BigDecimal("10.50"), currentOrder.get(0));
		assertTotalAmountPrice(new BigDecimal("54.65"), currentOrder.get(1));
		
		helper.assertEqualsAtRound2(new BigDecimal("7.65"), reportReceipt.getTaxTotalOrder());
		helper.assertEqualsAtRound2(new BigDecimal("65.15"), reportReceipt.getTaxedOrder());
	}
	
	@Test
	public void testGenerateReceipt_input3(){
		List<Product> products = new LinkedList<Product>();
		
		products.add( new Product("imported bottle of perfume", false, new BigDecimal("27.99"), ProductType.GENERIC) );
		products.add( new Product("bottle of perfume", true, new BigDecimal("18.99"), ProductType.GENERIC) );
		products.add( new Product("packet of headache", true, new BigDecimal("9.75"), ProductType.MEDICAL) );
		products.add( new Product("box of imported chocolates", false, new BigDecimal("11.25"), ProductType.FOOD) );
		
		List<UnitaryOrder> currentOrder = helper.generateUnitaryOrder(products);
		ReceiptReport reportReceipt = receiptCalcuatorService.generateReceiptFromOrder(currentOrder);
		
		assertTotalAmountPrice(new BigDecimal("32.19"), currentOrder.get(0));
		assertTotalAmountPrice(new BigDecimal("20.89"), currentOrder.get(1));
		assertTotalAmountPrice(new BigDecimal("9.75"), currentOrder.get(2));
		assertTotalAmountPrice(new BigDecimal("11.85"), currentOrder.get(3));
		
		helper.assertEqualsAtRound2(new BigDecimal("6.70"), reportReceipt.getTaxTotalOrder());
		helper.assertEqualsAtRound2(new BigDecimal("74.68"), reportReceipt.getTaxedOrder());
	}
	
	private void assertTotalAmountPrice( BigDecimal expected, UnitaryOrder unitaryOrder) {
		helper.assertEqualsAtRound2(expected, unitaryOrder.getOrderTaxedPrice());
	}
	
}
