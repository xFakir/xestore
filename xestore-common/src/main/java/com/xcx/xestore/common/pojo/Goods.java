package com.xcx.xestore.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Goods implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7950863174345227592L;
	private String goodsId;
	private String goodsName;
	private Category category;
	private Sku defaultSku;
	private String description;

	
}
