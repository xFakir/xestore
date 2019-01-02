package com.xcx.xestore.mapper;

import java.util.List;

import com.xcx.xestore.common.pojo.Goods;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodsMapper {
	List<Goods> selectGoodsByCategory(String category);

	Goods selectGoodsByGoodsId(String goodsId);

	Goods selectGoodsBySkuId(String skuId);
}
