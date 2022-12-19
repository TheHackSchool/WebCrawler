package com.thehackschool.webcrawler.seedurlservice.model;

import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "urls")
public class UrlsObj {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String url;
	private String category;

	@Override
	public boolean equals(Object obj) {

		if (this != obj) {
			return false;
		}

		if (getClass() != obj.getClass()) {
			return false;
		}

		final UrlsObj other = (UrlsObj) obj;
		if (!this.url.equals(other.url)) {
			return false;
		}

		if (!Objects.equals(this.category, other.category)) {
			return false;
		}

		return Objects.equals(this.id, other.id); // can be customized to not see urls
	}
}
