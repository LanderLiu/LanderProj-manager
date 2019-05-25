package com.lander.manager.bd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.TBdMaterialcategoryDao;
import com.lander.bd.pojo.TBdMaterialcategory;
import com.lander.bd.pojo.TBdMaterialcategoryQuery;
import com.lander.bd.pojo.TBdMaterialcategoryQuery.Criteria;
import com.lander.common.pojo.EasyUITreeNode;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.bd.service.BdMaterialcategoryService;

@Service
public class BdMaterialcategoryServiceImpl implements BdMaterialcategoryService {
	@Autowired
	private TBdMaterialcategoryDao tBdMaterialcategoryDao;

	@Override
	public List<EasyUITreeNode> getListByParent(long parentId) {

		TBdMaterialcategoryQuery query = new TBdMaterialcategoryQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFparentidEqualTo(parentId);

		List<TBdMaterialcategory> list = tBdMaterialcategoryDao.selectByExample(query);
		// 转换成EasyUITreeNode列表
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TBdMaterialcategory bdMaterialcategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(bdMaterialcategory.getFid());
			node.setText(bdMaterialcategory.getFname());
			// 如果节点下有子节点“closed”，如果没有子节点“open”
			node.setState(bdMaterialcategory.getFisparent() ? "closed" : "open");
			// 添加到节点列表
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public LanderResult insert(long parentId, String name, String userId) throws Exception {
		TBdMaterialcategory bdMaterialcategory = new TBdMaterialcategory();
		Long fid = IDUtils.genId();
		bdMaterialcategory.setFid(fid);
		bdMaterialcategory.setFstateid(1);
		bdMaterialcategory.setFname(name);
		bdMaterialcategory.setFparentid(parentId);
		bdMaterialcategory.setFisparent(false);
		bdMaterialcategory = (TBdMaterialcategory) BizDateUtil.setDefaultProperty(bdMaterialcategory, userId, TBdMaterialcategory.class);

		tBdMaterialcategoryDao.insert(bdMaterialcategory);
		// 更新其上级为父节点
		TBdMaterialcategory parentNode = tBdMaterialcategoryDao.selectByPrimaryKey(parentId);
		if (null != parentNode && !parentNode.getFisparent()) {
			parentNode.setFisparent(true);
			tBdMaterialcategoryDao.updateByPrimaryKey(parentNode);
		}
		return LanderResult.ok(bdMaterialcategory);
	}

	@Override
	public LanderResult update(long id, String name, String userId) throws Exception {
		TBdMaterialcategory tBdMaterialcategory = tBdMaterialcategoryDao.selectByPrimaryKey(id);
		if (tBdMaterialcategory == null) {
			return LanderResult.build(404, "物料类别信息未找到，请刷新后重试。");
		}

		tBdMaterialcategory = (TBdMaterialcategory) BizDateUtil.setModifyDefaultProperty(tBdMaterialcategory, userId, TBdMaterialcategory.class);
		tBdMaterialcategory.setFname(name);
		Integer data = tBdMaterialcategoryDao.updateByPrimaryKey(tBdMaterialcategory);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult delete(long fid) {
		TBdMaterialcategory node = tBdMaterialcategoryDao.selectByPrimaryKey(fid);
		Long parentId = node.getFparentid();
		TBdMaterialcategoryQuery exampleAll=new TBdMaterialcategoryQuery();
		Criteria criteriaAll=exampleAll.createCriteria();
		criteriaAll.andFparentidGreaterThan(0l);//除一级节点外，其它都有可能是子节点
		// 如果有子节点，要一起删除
		List<TBdMaterialcategory> allNodes=tBdMaterialcategoryDao.selectByExample(exampleAll);
		List<TBdMaterialcategory> allChildNodes=treeNodeList(allNodes,fid);
		for(TBdMaterialcategory childNode:allChildNodes){
			tBdMaterialcategoryDao.deleteByPrimaryKey(childNode.getFid());
		}
		
		// 删除完成后，检查上级节点，无子节点，则将其改为叶节点
		TBdMaterialcategory parentNode = tBdMaterialcategoryDao.selectByPrimaryKey(parentId);
		TBdMaterialcategoryQuery example = new TBdMaterialcategoryQuery();
		Criteria criteria = example.createCriteria();
		criteria.andFparentidEqualTo(parentId);

		int countByExample = tBdMaterialcategoryDao.countByExample(example);
		if (countByExample == 0) {
			parentNode.setFisparent(false);
			tBdMaterialcategoryDao.updateByPrimaryKey(parentNode);
		}
		tBdMaterialcategoryDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}

	@Override
	public LanderResult delete(long[] fid) {
		for (long i : fid) {
			delete(i);
		}
		return LanderResult.ok(fid.length);
	}

	// 子节点
	static List<TBdMaterialcategory> childNodes = new ArrayList<TBdMaterialcategory>();

	/**
	 * 在指定nodeList中，获取某个父节点下面的所有子节点
	 * 
	 * @param nodeList
	 * @param childNodes
	 * @return
	 */
	public static List<TBdMaterialcategory> treeNodeList(List<TBdMaterialcategory> nodeList, long pid) {
		for (TBdMaterialcategory mu : nodeList) {
			// 遍历出父id等于参数的id，add进子节点集合
			if (mu.getFparentid() == pid) {
				// 递归遍历下一级
				treeNodeList(nodeList, mu.getFid());
				childNodes.add(mu);
			}
		}
		return childNodes;
	}

}
