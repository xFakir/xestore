package com.xcx.xestore.common.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Category implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -384415254981221376L;
	private String categoryId;
	private String categoryName;
	private Integer level;
	private Category parentCategory;
	private Integer sort;
	private String groupIndex;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	

	public Category getParentCategory() {
		return parentCategory;
	}

	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getGroupIndex() {
		return groupIndex;
	}

	public void setGroupIndex(String groupIndex) {
		this.groupIndex = groupIndex;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", level=" + level
				+ ", parentCategory=" + parentCategory + ", sort=" + sort + ", groupIndex=" + groupIndex + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId.hashCode();
		result = prime * result + categoryName.hashCode();
		return result;

	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof User)) {
			return false;
		}
		Category category = (Category) obj;
		return category.categoryId.equals(categoryId) 
				&& category.categoryName == categoryName;

	}

}
