package com.zhy.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;

import com.zhy.repository.wrapper.TagWrapper;
import com.zhy.vo.TagVO;

/**
 * @author zhy
 * <p>
 * 2020年10月25日
 */
public class TagRepositoryImpl implements TagWrapper {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<TagVO> findAllDetail() {

        String sql = "select t.*,count(at.tag_id ) as articles from me_article_tag at "
                + "right join me_tag t on t.id = at.tag_id group by t.id ";

        SQLQuery query = getSession().createSQLQuery(sql);
        query.addScalar("id");
        query.addScalar("tagname");
        query.addScalar("avatar");
        query.addScalar("articles", IntegerType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(TagVO.class));
        return query.list();

    }

    @Override
    public TagVO getTagDetail(Integer tagId) {

        String sql = "select t.*,count(at.tag_id ) as articles from me_article_tag at "
                + "right join me_tag t on t.id = at.tag_id where t.id = :tagId ";

        SQLQuery query = getSession().createSQLQuery(sql);
        query.setInteger("tagId", tagId);

        query.addScalar("id");
        query.addScalar("tagname");
        query.addScalar("avatar");
        query.addScalar("articles", IntegerType.INSTANCE);

        query.setResultTransformer(Transformers.aliasToBean(TagVO.class));
        return (TagVO) query.uniqueResult();

    }


    private Session getSession() {
        return em.unwrap(Session.class);
    }

}
