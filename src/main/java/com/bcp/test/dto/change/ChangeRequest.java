package com.bcp.test.dto.change;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ChangeRequest {

	@NotNull(message = "CurrencyTo is mandatory")
	protected UUID currencyTo;
	@NotNull(message = "CurrencyFrom is mandatory")
	protected UUID currencyFrom;
	@NotNull(message = "Amount is mandatory")
	@Positive(message = "Amount must be positive")
	protected Double amount;
	@NotNull(message = "Rate is mandatory")
	@Positive(message = "Rate must be positive")
	protected Double rate;
	@NotBlank(message = "ExchangeRate is mandatory")
	@NotNull(message = "ExchangeRate is mandatory")
	protected String exchangeRate;
	
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
}
