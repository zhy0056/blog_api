package com.zhy.repository.wrapper;

import java.util.List;

import com.zhy.vo.CategoryVO;

/**
 * @author zhy
 * <p>
 * 2020年10月25日
 */
public interface CategoryWrapper {

    List<CategoryVO> findAllDetail();

    CategoryVO getCategoryDetail(Integer categoryId);
}
