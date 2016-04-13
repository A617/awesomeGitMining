package org.Common.vo;

import java.util.Map;

public class UserRateVO {
	private Map<String, Double> rates;

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "UserRateVO [rates=" + rates + "]";
	}

	
}
