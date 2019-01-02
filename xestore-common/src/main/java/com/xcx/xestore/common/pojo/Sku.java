package com.xcx.xestore.common.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Sku implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8207850891748204308L;
	
	private String skuId;
	private String skuName;
	private String skuVersionName;
	private String skuAttributes;
	private Goods goods;
	private Category category;
	private SkuImg skuImg;
	private List<Attribute> attributeList;
	private Double price;
	private Integer quantity;
	private String unit;
	private Long stock;
	private Long sales;
	private Integer isDefault;

	
	
}
