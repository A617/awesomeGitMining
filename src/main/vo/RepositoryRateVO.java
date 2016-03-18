package main.vo;

import com.sun.javafx.collections.MappingChange.Map;

public class RepositoryRateVO {
	private Map<String,Integer> rates;

	public Map<String, Integer> getRates() {
		return rates;
	}

	public void setRates(Map<String, Integer> rates) {
		this.rates = rates;
	}
	
}
