package com.xcx.xestore.mapper;

import com.xcx.xestore.common.pojo.Attribute;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;



@Mapper
public interface AttributeMapper {
	List<Attribute> selectAttributeListBySkuId(String skuId);
}
