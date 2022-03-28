package com.bcp.test.dto.change;

import java.util.UUID;

public class ChangeFullDto {
	private UUID id;
	private UUID currencyTo;
	private UUID currencyFrom;
	private Double amount;
	private Double rate;
	private String exchangeRate;
	public UUID getCurrencyTo() {
		return currencyTo;
	}
	public void setCurrencyTo(UUID currencyTo) {
		this.currencyTo = currencyTo;
	}
	public UUID getCurrencyFrom() {
		return currencyFrom;
	}
	public void setCurrencyFrom(UUID currencyFrom) {
		this.currencyFrom = currencyFrom;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public String getExchangeRate() {
		return exchangeRate;
	}
	public void setExchangeRate(String exchangeRate) {
		this.exchangeRate = exchangeRate;
	}
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	
}
