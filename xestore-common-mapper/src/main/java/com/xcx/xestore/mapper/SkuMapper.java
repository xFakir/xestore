package com.xcx.xestore.mapper;

import java.util.List;

import com.xcx.xestore.common.pojo.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface SkuMapper {
	List<Sku> selectRankSkuListByCategoryId(@Param(value = "categoryId") String categoryId);
	
	List<Sku> selectRecommondSkuListByCategoryId(String categoryId);

	List<Sku> selectSkuListByGoodsId(String goodsId);
}
