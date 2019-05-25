package com.lander.bd.dao;

import java.util.List;

import com.lander.bd.pojo.TBdCust;

public interface BdCustDao {
	/**
	 * 名称或编码类似于 q
	* @Title: selectLike 
	* @Description: 
	* @param @param q
	* @param @return    
	* @return List<TBdMaterial>   
	* @throws
	 */
	List<TBdCust> selectLike(String q);
}
