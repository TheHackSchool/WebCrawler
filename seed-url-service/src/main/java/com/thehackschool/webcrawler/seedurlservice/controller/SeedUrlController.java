/**
 * 
 */
package com.thehackschool.webcrawler.seedurlservice.controller;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thehackschool.webcrawler.seedurlservice.model.UrlsObj;
import com.thehackschool.webcrawler.seedurlservice.service.SeedUrlService;

import lombok.RequiredArgsConstructor;

/**
 * @author kanishkverma
 *
 */

@RestController
@RequestMapping(value = "/api/v1/webcrawler")
@RequiredArgsConstructor
public class SeedUrlController {

	private final SeedUrlService seedUrlService;

	/**
	 * Invokes the URL Frontier service to start the URL crawl procedure.
	 * All the Url's stored in the database will be called for the crawl for now. 
	 * Later can be customized.
	 * @return
	 */
	@PostMapping
	public ResponseEntity<String> invoke() {
		seedUrlService.invoke();
		return ResponseEntity.status(200).body(null);
	}
	
	@PostMapping(value = "/selfInvoke")
	public ResponseEntity<String> dummyInvoke() {
		return ResponseEntity.status(200).body("dummy Data");
	}
	
	
	@GetMapping(value = "/seedurls/all")
	public ResponseEntity<List<UrlsObj>> getUrls() {
		List<UrlsObj> urls = seedUrlService.getAllUrls();
		return ResponseEntity.status(HttpStatusCode.valueOf(200)).body(urls);
	}

	@PostMapping(value = "/seedurls")
	public ResponseEntity<UrlsObj> addUrl(@RequestBody UrlsObj urlObj) {
		return ResponseEntity.status(200).body(seedUrlService.addUrl(urlObj));
	}

	@PatchMapping(value = "/seedurls")
	public ResponseEntity<UrlsObj> patchUrl(@RequestBody UrlsObj urlObj) {
		return ResponseEntity.status(204).body(seedUrlService.patchUrl(urlObj));
	}

	@DeleteMapping(value = "/seedurls/{id}")
	public ResponseEntity<UrlsObj> deleteUrl(@PathVariable("id") String id) {
		return ResponseEntity.status(200).body(seedUrlService.deleteUrl(id));
	}

}
