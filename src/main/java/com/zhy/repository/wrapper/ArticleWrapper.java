package com.zhy.repository.wrapper;

import com.zhy.entity.Article;
import com.zhy.vo.ArticleVo;
import com.zhy.vo.PageVo;

import java.util.List;

public interface ArticleWrapper {
    List<Article> listArticles(PageVo page);

    List<Article> listArticles(ArticleVo article, PageVo page);

    List<ArticleVo> listArchives();

}
