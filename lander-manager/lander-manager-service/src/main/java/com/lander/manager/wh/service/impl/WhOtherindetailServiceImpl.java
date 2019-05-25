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
import com.lander.manager.wh.service.WhOtherindetailService;
import com.lander.wh.dao.TWhOtherindetailDao;
import com.lander.wh.pojo.TWhOtherindetail;
import com.lander.wh.pojo.TWhOtherindetailQuery;
import com.lander.wh.pojo.TWhOtherindetailQuery.Criteria;

@Service
public class WhOtherindetailServiceImpl implements WhOtherindetailService {
	@Autowired
	private TWhOtherindetailDao tWhOtherindetailDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			Long masterId) {

		TWhOtherindetailQuery query = new TWhOtherindetailQuery();
		Criteria criteria = query.createCriteria();
			criteria.andFmasteridEqualTo(masterId);
			
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhOtherindetail> list = tWhOtherindetailDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tWhOtherindetailDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TWhOtherindetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Long fid = IDUtils.genId();
		prOrderitem.setFid(fid);

		prOrderitem = (TWhOtherindetail) BizDateUtil.setDefaultProperty(prOrderitem, userId, TWhOtherindetail.class);
		
		Integer data = tWhOtherindetailDao.insert(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult update(TWhOtherindetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (prOrderitem.getFid()==null){
			throw new ServiceException("404", "要更新的对象不存在");
		}

		prOrderitem = (TWhOtherindetail) BizDateUtil.setModifyDefaultProperty(prOrderitem, userId, TWhOtherindetail.class);
		
		Integer data = tWhOtherindetailDao.updateByPrimaryKey(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult delete(long fid) {
		tWhOtherindetailDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	@Override
	public List<TWhOtherindetail> list(Long masterid) {
		TWhOtherindetailQuery query = new TWhOtherindetailQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFmasteridEqualTo(masterid);		
		
		return tWhOtherindetailDao.selectByExample(query);		
	}
}
