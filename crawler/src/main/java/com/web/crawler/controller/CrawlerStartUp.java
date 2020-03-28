package com.web.crawler.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.crawler.dao.CrawlerBaseResponseDto;
import com.web.crawler.service.CrawlerStartService;

@RequestMapping(value = "/crawler")
@RestController
public class CrawlerStartUp {

	@Autowired
	CrawlerStartService crawlerService;

	@GetMapping(value = "")
	public CrawlerBaseResponseDto crawledUrls(@RequestParam(required = true, name = "url") String url) throws Exception {
		return crawlerService.getLinks(url, 0);
	}
}
