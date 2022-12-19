package com.thehackschool.webcrawler.seedurlservice.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import com.thehackschool.webcrawler.seedurlservice.exception.NoDataFoundException;
import com.thehackschool.webcrawler.seedurlservice.exception.RepositoryException;
import com.thehackschool.webcrawler.seedurlservice.model.UrlsObj;
import com.thehackschool.webcrawler.seedurlservice.repository.SeedUrlRepository;
import com.thehackschool.webcrawler.seedurlservice.service.SeedUrlService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class SeedUrlServiceImpl implements SeedUrlService {

	private final SeedUrlRepository seedUrlRepository;

	@Override
	@Async
	public void invoke() {
		WebClient webClient = WebClient.create("http://localhost:8080");
		List<UrlsObj> urlsObjs = seedUrlRepository.findAll();
		try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String returnedValue = webClient.post().uri("/api/v1/webcrawler/selfInvoke")
				.body(Mono.just(urlsObjs), new ParameterizedTypeReference<List<UrlsObj>>() {
				}).retrieve().bodyToMono(String.class).block();
		log.info("httpStatus code from request = {}" , returnedValue);
	}

	@Override
	public List<UrlsObj> getAllUrls() {
		List<UrlsObj> urlsObjs = seedUrlRepository.findAll();
		if (urlsObjs.isEmpty()) {
			log.info("no url Objs found");
			throw new NoDataFoundException("No data found");
		}
		return urlsObjs;
	}

	@Override
	public UrlsObj addUrl(UrlsObj urlObj) {
		UrlsObj urlsObj = seedUrlRepository.save(urlObj);
		log.info("addingUrl to the database");
		if (urlObj == null) {
			log.error("failed to save entity");
			throw new RepositoryException("Failed to save to database"); // error saving data
		}
		return urlsObj;
	}

	@Override
	public UrlsObj patchUrl(UrlsObj urlObj) {
		Optional<UrlsObj> findById = seedUrlRepository.findById(urlObj.getId());
		if (findById.isEmpty()) {
			log.error("failed to find entity in database with id {}", urlObj.getId());
			throw new NoDataFoundException("Failed to find the url by id " + urlObj.getId());
		}
		return addUrl(urlObj); // patch
	}

	@Override
	public UrlsObj deleteUrl(String id) {
		Optional<UrlsObj> urlsObjOptional = seedUrlRepository.findById(Long.parseLong(id));
		if (urlsObjOptional.isEmpty()) {
			log.error("failed to find & delete urlObj with id {} ", id);
			throw new NoDataFoundException("Failed to Delete Url with id : " + id);
		}
		seedUrlRepository.deleteById(Long.parseLong(id));
		return urlsObjOptional.get();

	}

}
