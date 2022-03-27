package com.bcp.test.service.impl;

import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.bcp.test.dto.change.ChangeDto;
import com.bcp.test.dto.change.ChangeMapper;
import com.bcp.test.dto.change.ChangeRequest;
import com.bcp.test.model.Change;
import com.bcp.test.model.Currency;
import com.bcp.test.reposiroty.ChangeRepository;
import com.bcp.test.reposiroty.CurrencyRepository;
import com.bcp.test.service.AuthenticationService;
import com.bcp.test.service.ChangeService;

import io.reactivex.Completable;
import io.reactivex.Single;

@Service
public class ChangeServiceImpl implements ChangeService {

	@Autowired
	private ChangeRepository changeRepository;
	@Autowired
	private CurrencyRepository currencyRepository;
	@Autowired
	private ChangeMapper changeMapper;
	@Autowired
	private AuthenticationService authenticationService;
  


	@Override
	public Single<ChangeDto> create(ChangeRequest changeRequest) {
		User user = (User) authenticationService.getAuthentication().getPrincipal(); 
	    return saveBookToRepository(changeRequest,user.getUsername());
    }

    private Single<ChangeDto> saveBookToRepository(ChangeRequest changeRequest, String username) {
        return Single.create(singleSubscriber -> {
            Optional<Currency> optionalCurrencyFrom = currencyRepository.findById(changeRequest.getCurrencyFrom());
            Optional<Currency> optionalCurrencyTo = currencyRepository.findById(changeRequest.getCurrencyTo());
            if (!optionalCurrencyFrom.isPresent() || !optionalCurrencyTo.isPresent())
                singleSubscriber.onError(new EntityNotFoundException());
            else {
            	Change model = changeMapper.toModel(changeRequest);
            	model.setCreatedBy(username);		
                ChangeDto dto = changeMapper.toDto(changeRepository.save(model));
                singleSubscriber.onSuccess(dto);
            }
        });
    }
    
    

	@Override
	public Completable update(UUID uuid, ChangeRequest changeRequest) {
		// TODO Auto-generated method stub
		User user = (User) authenticationService.getAuthentication().getPrincipal(); 
	    return updateBookToRepository(changeRequest,uuid,user.getUsername());
	}
	
	private Completable updateBookToRepository(ChangeRequest changeRequest,UUID uuid, String username) {
        return Completable.create(completableSubscriber -> {
        	 Optional<Change> optionalChange = changeRepository.findById(uuid);
        	 Optional<Currency> optionalCurrencyFrom = currencyRepository.findById(changeRequest.getCurrencyFrom());
             Optional<Currency> optionalCurrencyTo = currencyRepository.findById(changeRequest.getCurrencyTo());
             if (!optionalChange.isPresent() || !optionalCurrencyFrom.isPresent() || !optionalCurrencyTo.isPresent())
            	 completableSubscriber.onError(new EntityNotFoundException());
             else {
            	 Change change = optionalChange.get();
                 changeMapper.updateModel(changeRequest, change);
                 change.setModifiedBy(username);	
                 changeRepository.save(change);
                completableSubscriber.onComplete();
            }
        });
    }

	@Override
	public Single<Page<ChangeDto>> getAll(Pageable pageable) {
        return findAllChangesInRepository(pageable);
    }

    private Single<Page<ChangeDto>> findAllChangesInRepository(Pageable pageable) {
        return Single.create(singleSubscriber -> {
        	Page<ChangeDto> books = changeRepository.findAll(pageable).map(changeMapper::toDto);
            singleSubscriber.onSuccess(books);
        });
    }
    
	@Override
	public Single<ChangeDto> show(UUID uuid) {
		return findChangeDetailInRepository(uuid);
	}

    private Single<ChangeDto> findChangeDetailInRepository(UUID id) {
        return Single.create(singleSubscriber -> {
            Optional<Change> optionalChange = changeRepository.findById(id);
            if (!optionalChange.isPresent())
                singleSubscriber.onError(new EntityNotFoundException());
            else {
                ChangeDto ChangeDto = changeMapper.toDto(optionalChange.get());
                singleSubscriber.onSuccess(ChangeDto);
            }
        });
    }





}
