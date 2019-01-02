package com.xcx.xestore.mapper;

import java.util.List;

import com.xcx.xestore.common.pojo.SkuImg;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface SkuImgMapper {
	List<SkuImg> selectSkuImgBySkuId(String skuId);
}
