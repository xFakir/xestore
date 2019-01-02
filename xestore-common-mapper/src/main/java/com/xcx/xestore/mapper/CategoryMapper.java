package com.xcx.xestore.mapper;


import com.xcx.xestore.common.pojo.Category;
import com.xcx.xestore.common.pojo.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {

	List<Category> selectCategoryListByParentCategoryId(@Param(value = "categoryId") String categoryId);

	List<Sku> selectRankItemListByCategoryId(String categoryId);

	List<Category> selectSecondAndThirdCategory(@Param(value = "categoryId") String categoryId);
}
