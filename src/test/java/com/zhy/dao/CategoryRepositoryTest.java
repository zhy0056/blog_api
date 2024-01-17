package com.zhy.dao;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.zhy.BlogApiApplicationTests;
import com.zhy.repository.CategoryRepository;

public class CategoryRepositoryTest extends BlogApiApplicationTests{

	@Autowired
	private CategoryRepository	categoryRepository;

	@Test
	public void test() {
	}
}
