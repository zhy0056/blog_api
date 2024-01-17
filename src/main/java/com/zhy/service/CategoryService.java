package com.zhy.service;

import java.util.List;

import com.zhy.entity.Category;
import com.zhy.vo.CategoryVO;

/**
 * @author zhy
 * <p>
 * 2020年10月25日
 */
public interface CategoryService {

    List<Category> findAll();

    Category getCategoryById(Integer id);

    Integer saveCategory(Category category);

    Integer updateCategory(Category category);

    void deleteCategoryById(Integer id);

    List<CategoryVO> findAllDetail();

    CategoryVO getCategoryDetail(Integer categoryId);

}
