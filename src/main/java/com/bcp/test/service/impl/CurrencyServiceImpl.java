package com.bcp.test.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.bcp.test.dto.currency.CurrencyDto;
import com.bcp.test.dto.currency.CurrencyMapper;
import com.bcp.test.model.Currency;
import com.bcp.test.reposiroty.CurrencyRepository;
import com.bcp.test.service.CurrencyService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.reactivex.Single;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CurrencyMapper currencyMapper;
	@Value("classpath:json/currencies.json")
	private Resource resourceFile;

	@Override
	@EventListener(ApplicationReadyEvent.class)
	public void saveByYml() {
		try {

			String jsonInput = new BufferedReader(
					new InputStreamReader(resourceFile.getInputStream(), StandardCharsets.UTF_8))
					.lines()
					.collect(Collectors.joining("\n"));
			List<Map> myObjects = objectMapper.readValue(jsonInput, new TypeReference<List<Map>>(){});
			for (Map m : myObjects) {
				for (Object key : m.keySet()) {
					Optional<Currency> optional = currencyRepository.findByCode(key.toString());
					if (!optional.isPresent()) {
						Currency c = new Currency();
						c.setCode(key.toString());
						c.setName(m.get(key).toString());;
						currencyRepository.save(c);

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Single<List<CurrencyDto>> getAll() {
		return findAllCurrenciesInRepository();
	}

	private Single<List<CurrencyDto>> findAllCurrenciesInRepository() {
		return Single.create(singleSubscriber -> {
			List<CurrencyDto> currencies = currencyRepository.findAll().stream().
					map(currencyMapper::toDto)
					.collect(Collectors.toList());;
			singleSubscriber.onSuccess(currencies);
		});
	}


	@Override
	public Single<CurrencyDto> show(UUID uuid) {
		return findCurrencyDetailInRepository(uuid);
	}

    private Single<CurrencyDto> findCurrencyDetailInRepository(UUID id) {
        return Single.create(singleSubscriber -> {
            Optional<Currency> optionalCurrency = currencyRepository.findById(id);
            if (!optionalCurrency.isPresent())
                singleSubscriber.onError(new EntityNotFoundException());
            else {
                CurrencyDto CurrencyDto = currencyMapper.toDto(optionalCurrency.get());
                singleSubscriber.onSuccess(CurrencyDto);
            }
        });
    }

	@Override
	public Currency findById(UUID uuid) {
		return currencyRepository.findById(uuid)
				.orElseThrow(() -> new EntityNotFoundException());
	}

}
