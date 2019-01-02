package com.xcx.xestore.mapper;

import java.util.List;

import com.xcx.xestore.common.pojo.GoodsImg;
import com.xcx.xestore.common.pojo.Sku;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;



@Mapper
public interface GoodsImgMapper {
	List<GoodsImg> selectGoodsImgList(@Param("list") List<Sku> skuList);
}
