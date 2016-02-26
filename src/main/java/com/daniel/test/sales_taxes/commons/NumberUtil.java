package com.daniel.test.sales_taxes.commons;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

/**
 * Application conventional number utils
 */
@Component
public class NumberUtil {
	
	
	public final BigDecimal ONE_HUNDRED = BigDecimal.valueOf(100l);

	private MathContext MC_MAX_PRECISION = new MathContext(38, RoundingMode.HALF_EVEN);
	private MathContext MC_PRECISION_2 = new MathContext(2, RoundingMode.HALF_EVEN);
	
	public BigDecimal round2(BigDecimal d) {
		return round(d, MC_PRECISION_2);
	}
	
	private BigDecimal round(BigDecimal d, MathContext mathContext) {
		return d.round(mathContext);
	}

	public BigDecimal add(BigDecimal value1, BigDecimal value2) {
		return value1.add(value2, MC_MAX_PRECISION);
	}
	
	public BigDecimal multiply(BigDecimal value1, BigDecimal value2) {
		return value1.multiply(value2, MC_MAX_PRECISION);
	}
	
	public BigDecimal divide(BigDecimal value1, BigDecimal value2) {
		return value1.divide(value2, MC_MAX_PRECISION);
	}
	

	public BigDecimal multiplayPercentage(BigDecimal value, BigDecimal percentage) {
		return multiply(value, divide(percentage, ONE_HUNDRED));
	}
}
