package com.lander.bd.dao;

import java.util.List;

import com.lander.basic.dao.BaseDao;
import com.lander.bd.pojo.TBdMaterial;
import com.lander.bd.pojo.TBdMaterialQuery;

public interface BdMaterialDao extends BaseDao<TBdMaterial,TBdMaterialQuery> {
	/**
	 * 名称或编码类似于 q
	* @Title: selectLike 
	* @Description: 
	* @param @param q
	* @param @return    
	* @return List<TBdMaterial>   
	* @throws
	 */
	List<TBdMaterial> selectLike(String q);
}
