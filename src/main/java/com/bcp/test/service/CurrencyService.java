package com.bcp.test.service;

import java.util.List;
import java.util.UUID;

import com.bcp.test.dto.currency.CurrencyDto;
import com.bcp.test.model.Currency;

import io.reactivex.Single;

public interface CurrencyService {
	public void saveByYml();
	
	public Single<List<CurrencyDto>> getAll();
	
	public Single<CurrencyDto> show(UUID uuid);
	public Currency findById(UUID uuid);
	

}
