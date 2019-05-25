package com.lander.bd.pojo;

import java.io.Serializable;

public class BdMaterialQuery implements Serializable{
	
	public BdMaterialQuery(){
		this.categoryId=-1l;
		this.name="";
	}
	private static final long serialVersionUID = 1L;
	private String name;
	private Long categoryId;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	
}
