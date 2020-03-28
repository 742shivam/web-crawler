package com.web.crawler.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.web.crawler.dao.CrawlerBaseResponseDto;
import com.web.crawler.dao.CrawlerResponse;
import com.web.crawler.exception.CrawlerException;

@Service
public class CrawlerStartService {
	private static final int MAX_DEPTH = 2;
	private HashSet<String> links;
	private int totalImages;
	private List<CrawlerResponse> crawledList;

	public CrawlerBaseResponseDto getLinks(String url, int depth) throws Exception {
		links = new HashSet<>();
		totalImages = 0;
		crawledList = new ArrayList<>();
		createCrawlerResponse(url, depth);
		return CrawlerBaseResponseDto.builder().details(crawledList).totalLinks(crawledList.size())
				.totalImages(totalImages).build();
	}

	private void createCrawlerResponse(String url, int depth) throws Exception {
		if ((!links.contains(url) && (depth < MAX_DEPTH))) {
			try {
				if(url.isEmpty()) 
					return;
				links.add(url);
				Document doc = Jsoup.connect(url).get();
				String title = doc.title();
				Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

				Elements linksOnPage = doc.select("a[href]");
				depth++;
				totalImages += images.size();
				crawledList.add(
						CrawlerResponse.builder().imageCount(images.size()).pageLink(url).pageTitle(title).build());
				 if (linksOnPage.isEmpty()) {
					 return;
				 }
				for (Element page : linksOnPage) {
					createCrawlerResponse(page.attr("abs:href"), depth);
				}
			} catch (IOException ex) {
				throw new CrawlerException("HTTP error fetching URL", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
