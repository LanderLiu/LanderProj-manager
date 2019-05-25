package com.lander.manager.bd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.TBdDeptDao;
import com.lander.bd.pojo.TBdDept;
import com.lander.bd.pojo.TBdDeptQuery;
import com.lander.bd.pojo.TBdDeptQuery.Criteria;
import com.lander.common.pojo.EasyUITreeNode;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.bd.service.BdDeptService;

@Service
public class BdDeptServiceImpl implements BdDeptService {
	@Autowired
	private TBdDeptDao tBdDeptDao;

	@Override
	public List<EasyUITreeNode> getListByParent(long parentId) {

		TBdDeptQuery query = new TBdDeptQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFparentidEqualTo(parentId);

		List<TBdDept> list = tBdDeptDao.selectByExample(query);
		// 转换成EasyUITreeNode列表
		List<EasyUITreeNode> resultList = new ArrayList<>();
		for (TBdDept bdDept : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(bdDept.getFid());
			node.setText(bdDept.getFname());
			// 如果节点下有子节点“closed”，如果没有子节点“open”
			node.setState(bdDept.getFisparent() ? "closed" : "open");
			// 添加到节点列表
			resultList.add(node);
		}
		return resultList;
	}

	@Override
	public LanderResult insert(long parentId, String name, String userId) throws Exception {
		TBdDept bdDept = new TBdDept();
		Long fid = IDUtils.genId();
		bdDept.setFid(fid);
		bdDept.setFstateid(1);
		bdDept.setFname(name);
		bdDept.setFfullname(getPath(parentId)+"/"+name);
		bdDept.setFparentid(parentId);
		bdDept.setFisparent(false);
		bdDept = (TBdDept) BizDateUtil.setDefaultProperty(bdDept, userId, TBdDept.class);

		tBdDeptDao.insert(bdDept);
		// 更新其上级为父节点
		TBdDept parentNode = tBdDeptDao.selectByPrimaryKey(parentId);
		if (null != parentNode && !parentNode.getFisparent()) {
			parentNode.setFisparent(true);
			tBdDeptDao.updateByPrimaryKey(parentNode);
		}
		return LanderResult.ok(bdDept);
	}

	@Override
	public LanderResult update(long id, String name, String userId) throws Exception {
		TBdDept tBdDept = tBdDeptDao.selectByPrimaryKey(id);
		if (tBdDept == null) {
			return LanderResult.build(404, "部门信息未找到，请刷新后重试。");
		}

		tBdDept = (TBdDept) BizDateUtil.setModifyDefaultProperty(tBdDept, userId, TBdDept.class);
		tBdDept.setFname(name);
		tBdDept.setFfullname(getPath(id));
		Integer data = tBdDeptDao.updateByPrimaryKey(tBdDept);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult delete(long fid) {
		TBdDept node = tBdDeptDao.selectByPrimaryKey(fid);
		Long parentId = node.getFparentid();
		TBdDeptQuery exampleAll=new TBdDeptQuery();
		Criteria criteriaAll=exampleAll.createCriteria();
		criteriaAll.andFparentidGreaterThan(0l);//除一级节点外，其它都有可能是子节点
		// 如果有子节点，要一起删除
		List<TBdDept> allNodes=tBdDeptDao.selectByExample(exampleAll);
		List<TBdDept> allChildNodes=treeNodeList(allNodes,fid);
		for(TBdDept childNode:allChildNodes){
			tBdDeptDao.deleteByPrimaryKey(childNode.getFid());
		}
		
		// 删除完成后，检查上级节点，无子节点，则将其改为叶节点
		TBdDept parentNode = tBdDeptDao.selectByPrimaryKey(parentId);
		TBdDeptQuery example = new TBdDeptQuery();
		Criteria criteria = example.createCriteria();
		criteria.andFparentidEqualTo(parentId);

		int countByExample = tBdDeptDao.countByExample(example);
		if (countByExample == 0) {
			parentNode.setFisparent(false);
			tBdDeptDao.updateByPrimaryKey(parentNode);
		}
		tBdDeptDao.deleteByPrimaryKey(fid);
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
	static List<TBdDept> childNodes = new ArrayList<TBdDept>();

	/**
	 * 在指定nodeList中，获取某个父节点下面的所有子节点
	 * 
	 * @param nodeList
	 * @param childNodes
	 * @return
	 */
	public static List<TBdDept> treeNodeList(List<TBdDept> nodeList, long pid) {
		for (TBdDept mu : nodeList) {
			// 遍历出父id等于参数的id，add进子节点集合
			if (mu.getFparentid() == pid) {
				// 递归遍历下一级
				treeNodeList(nodeList, mu.getFid());
				childNodes.add(mu);
			}
		}
		return childNodes;
	}

	/**
	 * 获取部门全路径
	* @Title: getPath 
	* @Description: 
	* @param @param id
	* @param @return    
	* @return String   
	* @throws
	 */
	private String getPath(long id){
		String path="";
		TBdDept node = tBdDeptDao.selectByPrimaryKey(id);
		if (node==null){
			return "";
		}else{
			path=node.getFname();
			Long fparentid = node.getFparentid();			
			while (fparentid!=null){
				TBdDept selectByPrimaryKey = tBdDeptDao.selectByPrimaryKey(fparentid);			
				if (selectByPrimaryKey!=null){
					path=selectByPrimaryKey.getFname()+"/"+path;
					fparentid=selectByPrimaryKey.getFparentid();
				}else{
					fparentid=null;
				}
				
			}
		}
		return path;
	}	
}
