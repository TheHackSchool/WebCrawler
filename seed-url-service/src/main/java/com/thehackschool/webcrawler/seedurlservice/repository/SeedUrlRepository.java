package com.thehackschool.webcrawler.seedurlservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thehackschool.webcrawler.seedurlservice.model.UrlsObj;

@Repository
public interface SeedUrlRepository extends JpaRepository<UrlsObj, Long> {

}
