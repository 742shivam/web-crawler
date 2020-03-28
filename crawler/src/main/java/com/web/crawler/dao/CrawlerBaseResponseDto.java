package com.web.crawler.dao;

import java.util.List;

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
public class CrawlerBaseResponseDto {
	private int totalLinks;
	private int totalImages;
	private List<CrawlerResponse> details;
}
