package com.bcp.test.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bcp.test.dto.change.ChangeDto;
import com.bcp.test.dto.change.ChangeFullDto;
import com.bcp.test.dto.change.ChangeRequest;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface ChangeService {
	Single<ChangeDto> create(ChangeRequest changeRequest);

	Completable update(UUID uuid, ChangeRequest changeRequest);

	Single<Page<ChangeDto>> getAll(Pageable pageable);

	Single<ChangeFullDto> show(UUID uuid);


}
