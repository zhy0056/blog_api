package com.zhy.service;

import java.util.List;

import com.zhy.entity.Tag;
import com.zhy.vo.TagVO;

/**
 * @author zhy
 * <p>
 * 2020年10月25日
 */
public interface TagService {

    List<Tag> findAll();

    Tag getTagById(Integer id);

    Integer saveTag(Tag tag);

    Integer updateTag(Tag tag);

    void deleteTagById(Integer id);

    List<Tag> listHotTags(int limit);

    List<TagVO> findAllDetail();

    TagVO getTagDetail(Integer tagId);

}
