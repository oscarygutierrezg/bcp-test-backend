package com.bcp.test.model;

import lombok.*;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.slf4j.Logger;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.bcp.test.model.enums.Operation;

@Entity
@Table(name = "changes")
@Audited
@EntityListeners(AuditingEntityListener.class)

public class Change {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")	
	private UUID id;
	@Column(name = "currency_from", nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")	
	private UUID currencyFrom;
	@Column(name = "currency_to", nullable = false, columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")	
	private UUID currencyTo;
	private Double amount;
	private Double rate;
	@Column(name = "exchange_rate")
	private String exchangeRate;



	@Column(name = "created_date", updatable = false, nullable = false)
	@CreatedDate
	private long createdDate;

	@Column(name = "modified_date")
	@LastModifiedDate
	private long modifiedDate;

	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;

	@Column(name = "modified_by")
	@LastModifiedBy
	private String modifiedBy;

	@Column(name = "operation")
	private String operation;

	@Column(name = "timestamp")
	private long timestamp;

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
	}

	public Operation getOperation() {
		return Operation.parse(operation);
	}

	public void setOperation(final Operation operation) {
		this.operation = operation.getValue();
	}


	public long getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(long createdDate) {
		this.createdDate = createdDate;
	}

	public long getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public UUID getCurrencyFrom() {
		return currencyFrom;
	}
	public void setCurrencyFrom(UUID currencyFrom) {
		this.currencyFrom = currencyFrom;
	}
	public UUID getCurrencyTo() {
		return currencyTo;
	}
	public void setCurrencyTo(UUID currencyTo) {
		this.currencyTo = currencyTo;
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

	@PrePersist
	public void onPrePersist() {
		audit(Operation.INSERT);
	}

	@PreUpdate
	public void onPreUpdate() {
		audit(Operation.UPDATE);
	}


	private void audit(final Operation operation) {
		setOperation(operation);
		setTimestamp((new Date()).getTime());
	}





}
