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
import com.lander.manager.wh.service.WhOtheroutdetailService;
import com.lander.wh.dao.TWhOtheroutdetailDao;
import com.lander.wh.pojo.TWhOtheroutdetail;
import com.lander.wh.pojo.TWhOtheroutdetailQuery;
import com.lander.wh.pojo.TWhOtheroutdetailQuery.Criteria;

@Service
public class WhOtheroutdetailServiceImpl implements WhOtheroutdetailService {
	@Autowired
	private TWhOtheroutdetailDao tWhOtheroutdetailDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			Long masterId) {

		TWhOtheroutdetailQuery query = new TWhOtheroutdetailQuery();
		Criteria criteria = query.createCriteria();
			criteria.andFmasteridEqualTo(masterId);
			
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhOtheroutdetail> list = tWhOtheroutdetailDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tWhOtheroutdetailDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TWhOtheroutdetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Long fid = IDUtils.genId();
		prOrderitem.setFid(fid);

		prOrderitem = (TWhOtheroutdetail) BizDateUtil.setDefaultProperty(prOrderitem, userId, TWhOtheroutdetail.class);
		
		Integer data = tWhOtheroutdetailDao.insert(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult update(TWhOtheroutdetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (prOrderitem.getFid()==null){
			throw new ServiceException("404", "要更新的对象不存在");
		}

		prOrderitem = (TWhOtheroutdetail) BizDateUtil.setModifyDefaultProperty(prOrderitem, userId, TWhOtheroutdetail.class);
		
		Integer data = tWhOtheroutdetailDao.updateByPrimaryKey(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult delete(long fid) {
		tWhOtheroutdetailDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	@Override
	public List<TWhOtheroutdetail> list(Long masterid) {
		TWhOtheroutdetailQuery query = new TWhOtheroutdetailQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFmasteridEqualTo(masterid);		
		
		return tWhOtheroutdetailDao.selectByExample(query);		
	}
}
