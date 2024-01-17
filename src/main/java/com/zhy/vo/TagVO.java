package com.zhy.vo;

import com.zhy.entity.Tag;

/**
 * @author zhy
 * <p>
 * 2020年10月29日
 */
public class TagVO extends Tag {

    /**
     *
     */
    private static final long serialVersionUID = 5059212992497947120L;
    private int articles;

    public int getArticles() {
        return articles;
    }

    public void setArticles(int articles) {
        this.articles = articles;
    }


}
