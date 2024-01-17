package com.zhy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zhy.entity.Article;
import com.zhy.entity.Comment;

/**
 * @author zhy
 * <p>
 * 2020年10月25日
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> findByArticleAndLevelOrderByCreateDateDesc(Article a, String level);


}
