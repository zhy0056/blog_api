package com.zhy.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.BlogApiApplicationTests;
import com.zhy.entity.Tag;

public class TagServiceTest extends BlogApiApplicationTests{

	@Autowired
	private TagService	tagService;
	
	
	
	@Test
	public void listArticlesByTagTest() {
		int limit = 3;
		List<Tag> ts = tagService.listHotTags(limit);
		
		ts.stream().forEach( t -> System.out.println(t.getTagname()));
		System.out.println(ts.size());
		
	}
}
