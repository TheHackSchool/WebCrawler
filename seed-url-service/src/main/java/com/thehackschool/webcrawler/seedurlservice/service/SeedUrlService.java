package com.thehackschool.webcrawler.seedurlservice.service;

import java.util.List;

import com.thehackschool.webcrawler.seedurlservice.model.UrlsObj;

public interface SeedUrlService {

	void invoke(); // invokes the URL frontier service to crawl further.

	List<UrlsObj> getAllUrls();

	UrlsObj addUrl(UrlsObj urlObj);

	UrlsObj patchUrl(UrlsObj urlObj);

	UrlsObj deleteUrl(String id);

}
