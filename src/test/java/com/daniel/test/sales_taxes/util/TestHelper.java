package com.daniel.test.sales_taxes.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.daniel.test.sales_taxes.commons.NumberUtil;
import com.daniel.test.sales_taxes.model.Product;
import com.daniel.test.sales_taxes.model.ProductType;
import com.daniel.test.sales_taxes.model.SimpleTaxable;
import com.daniel.test.sales_taxes.model.UnitaryOrder;

@Component
public class TestHelper {
	
	@Autowired
	private NumberUtil nu;

	/**
	 * Generate an order of input products with quantity = 1
	 * @param products
	 * @return
	 */
	public List<UnitaryOrder> generateUnitaryOrder(List<Product> products) {
		List<UnitaryOrder> currentOrder = new LinkedList<UnitaryOrder>();
		for (Product product : products) {
			currentOrder.add(new UnitaryOrder(product, 1f));
		}
		return currentOrder;
	}
	
	/**
	 * 
	 * @param national
	 * @param exemption
	 * @return
	 */
	public SimpleTaxable generateTaxable(boolean national, boolean exemption) {
		ProductType type = !exemption ? ProductType.GENERIC : ProductType.MEDICAL;
		return new Product("mock name", national, BigDecimal.ZERO, type);
	}
	
	
	/**
	 * BigDecimal efficient assert equals.
	 * @param expected
	 * @param value
	 */
	public void assertEqualsAtRound2(BigDecimal expected, BigDecimal value){
		if (nu.round2(expected).compareTo(nu.round2(value)) != 0) {
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("Assertion Error: expected<").append(expected).append("> but was <").append(value).append(">");
			Assert.fail(stringBuilder.toString());
		}
	}
}
