package com.daniel.test.sales_taxes;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.daniel.test.sales_taxes.model.Product;
import com.daniel.test.sales_taxes.model.ProductType;
import com.daniel.test.sales_taxes.model.ReceiptReport;
import com.daniel.test.sales_taxes.model.UnitaryOrder;
import com.daniel.test.sales_taxes.service.ReceiptCalcuatorService;

/**
 * Example implementation.
 * @author Daniel
 *
 */
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		ReceiptCalcuatorService receiptCalcuatorService = ctx.getBean(ReceiptCalcuatorService.class);
		
		List<Product> products = new ArrayList<Product>();
		
		products.add( new Product("book", true, new BigDecimal("12.49"), ProductType.BOOK) );
		products.add( new Product("music CD", true, new BigDecimal("14.99"), ProductType.GENERIC) );
		products.add( new Product("chocolate bar", true, new BigDecimal("0.85"), ProductType.FOOD) );
		
		List<UnitaryOrder> currentOrder = generateUnitaryOrder(products);
		ReceiptReport reportReceipt = receiptCalcuatorService.generateReceiptFromOrder(currentOrder);
		System.out.println("Output 1");
		printReport(currentOrder, reportReceipt);
		
		
		products = new ArrayList<Product>();
		products.add( new Product("imported box of chocolates", false, new BigDecimal("10.00"), ProductType.FOOD) );
		products.add( new Product("imported bottle of perfume", false, new BigDecimal("47.50"), ProductType.GENERIC) );
		
		currentOrder = generateUnitaryOrder(products);
		reportReceipt = receiptCalcuatorService.generateReceiptFromOrder(currentOrder);
		System.out.println("Output 2");
		printReport(currentOrder, reportReceipt);
		
		
		products = new ArrayList<Product>();
		products.add( new Product("imported bottle of perfume", false, new BigDecimal("27.99"), ProductType.GENERIC) );
		products.add( new Product("bottle of perfume", true, new BigDecimal("18.99"), ProductType.GENERIC) );
		products.add( new Product("packet of headache", true, new BigDecimal("9.75"), ProductType.MEDICAL) );
		products.add( new Product("box of imported chocolates", false, new BigDecimal("11.25"), ProductType.FOOD) );
		
		currentOrder = generateUnitaryOrder(products);
		reportReceipt = receiptCalcuatorService.generateReceiptFromOrder(currentOrder);
		System.out.println("Output 3");
		printReport(currentOrder, reportReceipt);
		
	}

	private static List<UnitaryOrder> generateUnitaryOrder(List<Product> products) {
		List<UnitaryOrder> currentOrder = new ArrayList<UnitaryOrder>();
		for (Product product : products) {
			currentOrder.add(new UnitaryOrder(product, 1f));
		}
		return currentOrder;
	}

	private static void printReport(List<UnitaryOrder> currentOrder, ReceiptReport reportReceipt) {
		System.out.println("\n");
		for (UnitaryOrder order : currentOrder) {
			System.out.println(order);
		}
		System.out.println(reportReceipt);
		System.out.println("\n");
	}
}
