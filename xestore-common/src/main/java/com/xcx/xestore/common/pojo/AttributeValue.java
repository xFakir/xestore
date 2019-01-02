package com.xcx.xestore.common.pojo;

import lombok.Data;

@Data
public class AttributeValue {
	private String attributeValueId;
	private AttributeName attributeName;
	private String attributeValue;
	private Category category;
	
}
