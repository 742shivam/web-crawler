package com.web.crawler.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CrawlerResponse {
	
	private String pageTitle;
	private String pageLink;
	private int imageCount;
	
}
