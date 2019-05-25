package com.lander.manager.wh.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.common.exception.ServiceException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.manager.wh.service.WhPickingdetailService;
import com.lander.wh.dao.TWhPickingdetailDao;
import com.lander.wh.pojo.TWhPickingdetail;
import com.lander.wh.pojo.TWhPickingdetailQuery;
import com.lander.wh.pojo.TWhPickingdetailQuery.Criteria;

@Service
public class WhPickingdetailServiceImpl implements WhPickingdetailService {
	@Autowired
	private TWhPickingdetailDao tWhPickingdetailDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			Long masterId) {

		TWhPickingdetailQuery query = new TWhPickingdetailQuery();
		Criteria criteria = query.createCriteria();
			criteria.andFmasteridEqualTo(masterId);
			
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhPickingdetail> list = tWhPickingdetailDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tWhPickingdetailDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TWhPickingdetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Long fid = IDUtils.genId();
		prOrderitem.setFid(fid);

		prOrderitem = (TWhPickingdetail) BizDateUtil.setDefaultProperty(prOrderitem, userId, TWhPickingdetail.class);
		
		Integer data = tWhPickingdetailDao.insert(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult update(TWhPickingdetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (prOrderitem.getFid()==null){
			throw new ServiceException("404", "要更新的对象不存在");
		}

		prOrderitem = (TWhPickingdetail) BizDateUtil.setModifyDefaultProperty(prOrderitem, userId, TWhPickingdetail.class);
		
		Integer data = tWhPickingdetailDao.updateByPrimaryKey(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult delete(long fid) {
		tWhPickingdetailDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	@Override
	public List<TWhPickingdetail> list(Long masterid) {
		TWhPickingdetailQuery query = new TWhPickingdetailQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFmasteridEqualTo(masterid);		
		
		return tWhPickingdetailDao.selectByExample(query);		
	}
}
