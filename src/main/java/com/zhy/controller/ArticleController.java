package com.zhy.controller;

import java.util.List;

import com.zhy.common.annotation.LogAnnotation;
import com.zhy.vo.ArticleVo;
import com.zhy.vo.PageVo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.support.spring.annotation.FastJsonFilter;
import com.alibaba.fastjson.support.spring.annotation.FastJsonView;
import com.zhy.common.constant.Base;
import com.zhy.common.constant.ResultCode;
import com.zhy.common.result.Result;
import com.zhy.entity.Article;
import com.zhy.entity.ArticleBody;
import com.zhy.entity.Tag;
import com.zhy.entity.User;
import com.zhy.service.ArticleService;
import com.zhy.service.TagService;

/**
 * 文章api
 *
 * @author zhy
 * <p>
 * 2020年10月25日
 */
@RestController
@RequestMapping(value = "/articles")
public class ArticleController {


    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @GetMapping
    @FastJsonView(
            exclude = {
                    @FastJsonFilter(clazz = Article.class, props = {"body", "category", "comments"}),
                    @FastJsonFilter(clazz = Tag.class, props = {"id", "avatar"})},
            include = {@FastJsonFilter(clazz = User.class, props = {"nickname"})})
    @LogAnnotation(module = "文章", operation = "获取所有文章")
    public Result listArticles(ArticleVo article, PageVo page) {
        System.out.println(article);
        System.out.println(page);
        List<Article> articles = articleService.listArticles(article, page);
        return Result.success(articles);
    }

    @GetMapping("/hot")
    @FastJsonView(include = {@FastJsonFilter(clazz = Article.class, props = {"id", "title"})})
    @LogAnnotation(module = "文章", operation = "获取最热文章")
    public Result listHotArticles() {
        int limit = 6;
        List<Article> articles = articleService.listHotArticles(limit);

        return Result.success(articles);
    }

    @GetMapping("/new")
    @FastJsonView(include = {@FastJsonFilter(clazz = Article.class, props = {"id", "title"})})
    @LogAnnotation(module = "文章", operation = "获取最新文章")
    public Result listNewArticles() {
        int limit = 6;
        List<Article> articles = articleService.listNewArticles(limit);

        return Result.success(articles);
    }


    @GetMapping("/{id}")
    @FastJsonView(
            exclude = {
                    @FastJsonFilter(clazz = Article.class, props = {"comments"}),
                    @FastJsonFilter(clazz = ArticleBody.class, props = {"contentHtml"})})
    @LogAnnotation(module = "文章", operation = "根据id获取文章")
    public Result getArticleById(@PathVariable("id") Integer id) {

        Result r = new Result();

        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        Article article = articleService.getArticleById(id);

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(article);
        return r;
    }

    @GetMapping("/view/{id}")
    @FastJsonView(
            exclude = {
                    @FastJsonFilter(clazz = Article.class, props = {"comments"}),
                    @FastJsonFilter(clazz = ArticleBody.class, props = {"contentHtml"}),
                    @FastJsonFilter(clazz = Tag.class, props = {"avatar"})},
            include = {@FastJsonFilter(clazz = User.class, props = {"id", "nickname", "avatar"})})
    @LogAnnotation(module = "文章", operation = "根据id获取文章，添加阅读数")
    public Result getArticleAndAddViews(@PathVariable("id") Integer id) {

        Result r = new Result();

        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        Article article = articleService.getArticleAndAddViews(id);

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(article);
        return r;
    }

    @GetMapping("/tag/{id}")
    @FastJsonView(
            exclude = {
                    @FastJsonFilter(clazz = Article.class, props = {"body", "category", "comments"}),
                    @FastJsonFilter(clazz = Tag.class, props = {"id", "avatar"})},
            include = {@FastJsonFilter(clazz = User.class, props = {"nickname"})})
    @LogAnnotation(module = "文章", operation = "根据标签获取文章")
    public Result listArticlesByTag(@PathVariable Integer id) {
        List<Article> articles = articleService.listArticlesByTag(id);

        return Result.success(articles);
    }


    @GetMapping("/category/{id}")
    @FastJsonView(
            exclude = {
                    @FastJsonFilter(clazz = Article.class, props = {"body", "category", "comments"}),
                    @FastJsonFilter(clazz = Tag.class, props = {"id", "avatar"})},
            include = {@FastJsonFilter(clazz = User.class, props = {"nickname"})})
    @LogAnnotation(module = "文章", operation = "根据分类获取文章")
    public Result listArticlesByCategory(@PathVariable Integer id) {
        List<Article> articles = articleService.listArticlesByCategory(id);

        return Result.success(articles);
    }

    @PostMapping("/publish")
    @RequiresAuthentication
    @LogAnnotation(module = "文章", operation = "发布文章")
    public Result saveArticle(@Validated @RequestBody Article article) {

        Integer articleId = articleService.publishArticle(article);

        Result r = Result.success();
        r.simple().put("articleId", articleId);
        return r;
    }

    @PostMapping("/update")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "文章", operation = "修改文章")
    public Result updateArticle(@RequestBody Article article) {
        Result r = new Result();

        if (null == article.getId()) {
            r.setResultCode(ResultCode.USER_NOT_EXIST);
            return r;
        }

        Integer articleId = articleService.updateArticle(article);

        r.setResultCode(ResultCode.SUCCESS);
        r.simple().put("articleId", articleId);
        return r;
    }

    @GetMapping("/delete/{id}")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "文章", operation = "删除文章")
    public Result deleteArticleById(@PathVariable("id") Integer id) {
        Result r = new Result();

        if (null == id) {
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        articleService.deleteArticleById(id);

        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }

    @GetMapping("/listArchives")
    @LogAnnotation(module = "文章", operation = "获取文章归档日期")
    public Result listArchives() {
        return Result.success(articleService.listArchives());
    }


}
