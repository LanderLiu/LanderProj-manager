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
import com.lander.manager.wh.service.WhCheckdetailService;
import com.lander.wh.dao.TWhCheckdetailDao;
import com.lander.wh.pojo.TWhCheckdetail;
import com.lander.wh.pojo.TWhCheckdetailQuery;
import com.lander.wh.pojo.TWhCheckdetailQuery.Criteria;

@Service
public class WhCheckdetailServiceImpl implements WhCheckdetailService {
	@Autowired
	private TWhCheckdetailDao tWhCheckdetailDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			Long masterId) {

		TWhCheckdetailQuery query = new TWhCheckdetailQuery();
		Criteria criteria = query.createCriteria();
			criteria.andFmasteridEqualTo(masterId);
			
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhCheckdetail> list = tWhCheckdetailDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tWhCheckdetailDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TWhCheckdetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Long fid = IDUtils.genId();
		prOrderitem.setFid(fid);

		prOrderitem = (TWhCheckdetail) BizDateUtil.setDefaultProperty(prOrderitem, userId, TWhCheckdetail.class);
		
		Integer data = tWhCheckdetailDao.insert(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult update(TWhCheckdetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (prOrderitem.getFid()==null){
			throw new ServiceException("404", "要更新的对象不存在");
		}

		prOrderitem = (TWhCheckdetail) BizDateUtil.setModifyDefaultProperty(prOrderitem, userId, TWhCheckdetail.class);
		
		Integer data = tWhCheckdetailDao.updateByPrimaryKey(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult delete(long fid) {
		tWhCheckdetailDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	@Override
	public List<TWhCheckdetail> list(Long masterid) {
		TWhCheckdetailQuery query = new TWhCheckdetailQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFmasteridEqualTo(masterid);	
		
		query.setOrderByClause("fmaterialnumber asc");
		
		return tWhCheckdetailDao.selectByExample(query);		
	}
}
