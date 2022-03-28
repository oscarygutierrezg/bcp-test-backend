package com.bcp.test.controller;

import java.net.URI;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcp.test.dto.change.ChangeDto;
import com.bcp.test.dto.change.ChangeFullDto;
import com.bcp.test.dto.change.ChangeRequest;
import com.bcp.test.service.ChangeService;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

@RestController
@RequestMapping(value = "/v1/change")
@CrossOrigin
public class ChangeController {

	@Autowired
	private ChangeService changeService;

	@PostMapping(
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			) public Single<ResponseEntity<ChangeDto>> addBook(
					@Valid @RequestBody  ChangeRequest changeRequest) {
		return changeService.create(changeRequest).subscribeOn(Schedulers.io()).map(
				s -> ResponseEntity.created(URI.create("/v1/change/" + s.getId()))
				.body(s));
	}

	@PutMapping(
			value = "/{changeId}",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public Single<ResponseEntity> updateBook(@PathVariable(value = "changeId") UUID changeId,
			@Valid @RequestBody  ChangeRequest changeRequest) {
		return changeService.update(changeId, changeRequest)
				.subscribeOn(Schedulers.io())
				.toSingle(() -> ResponseEntity.ok().build());
	}



	@GetMapping(
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public Single<ResponseEntity<Page<ChangeDto>>>  getAllChanges(Pageable pageable) {
		return changeService.getAll(pageable)
				.subscribeOn(Schedulers.io())
				.map(changeResponses -> ResponseEntity.ok().body(changeResponses));
	}

	@GetMapping(
			value = "/{changeId}",
			produces = MediaType.APPLICATION_JSON_VALUE
			)
	public  Single<ResponseEntity<ChangeFullDto>>  getChangeDetail(@PathVariable(value = "changeId") UUID changeId) {
		return changeService.show(changeId)
				.subscribeOn(Schedulers.io())
				.map(changeResponse -> ResponseEntity.ok().body(changeResponse));
	}


}
