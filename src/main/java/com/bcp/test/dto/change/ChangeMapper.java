package com.bcp.test.dto.change;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mappings;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.bcp.test.model.Change;
import com.bcp.test.service.CurrencyService;

@Mapper(
		componentModel = "spring",
		nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
		unmappedTargetPolicy = ReportingPolicy.IGNORE,
		nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
		)
public abstract class ChangeMapper {

	@Autowired
	private CurrencyService currencyService;

	@Mappings({
		@Mapping(target = "currencyTo", expression = "java(getCurrencyService().findById(change.getCurrencyTo()).getCode())"),
		@Mapping(target = "currencyFrom", expression = "java(getCurrencyService().findById(change.getCurrencyFrom()).getCode())")
	})
	public abstract ChangeDto toDto(Change change);

	public abstract Change toModel(ChangeRequest change);

	public  abstract void updateModel(ChangeRequest changeRequest, @MappingTarget Change change);

	public CurrencyService getCurrencyService() {
		return currencyService;
	}

	public void setCurrencyService(CurrencyService currencyService) {
		this.currencyService = currencyService;
	}



}
