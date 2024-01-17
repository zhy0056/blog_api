package com.zhy.vo;

import com.zhy.entity.Category;

/**
 * @author zhy
 * <p>
 * 2020年10月29日
 */
public class CategoryVO extends Category {

    /**
     *
     */
    private static final long serialVersionUID = -2975739216517528014L;


    private int articles;

    public int getArticles() {
        return articles;
    }

    public void setArticles(int articles) {
        this.articles = articles;
    }


}
