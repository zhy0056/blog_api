package com.zhy.repository.wrapper;

import java.util.List;

import com.zhy.vo.TagVO;

/**
 * @author zhy
 * <p>
 * 2020年10月25日
 */
public interface TagWrapper {

    List<TagVO> findAllDetail();

    TagVO getTagDetail(Integer tagId);
}
