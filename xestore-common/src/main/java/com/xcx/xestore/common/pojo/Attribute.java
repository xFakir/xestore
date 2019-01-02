package com.xcx.xestore.common.pojo;

import lombok.Data;

@Data
public class Attribute {
    private String attributeId;
	private AttributeName attributeName;
	private AttributeValue attributeValue;
	private Goods goods;
	private Sku sku;


	
}
