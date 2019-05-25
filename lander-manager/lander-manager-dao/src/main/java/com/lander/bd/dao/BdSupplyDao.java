package com.lander.bd.dao;

import java.util.List;

import com.lander.bd.pojo.TBdSupply;

public interface BdSupplyDao {
	/**
	 * 名称或编码类似于 q
	* @Title: selectLike 
	* @Description: 
	* @param @param q
	* @param @return    
	* @return List<TBdMaterial>   
	* @throws
	 */
	List<TBdSupply> selectLike(String q);
}
