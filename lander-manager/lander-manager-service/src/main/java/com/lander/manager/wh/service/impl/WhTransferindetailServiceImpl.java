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
import com.lander.manager.wh.service.WhTransferindetailService;
import com.lander.wh.dao.TWhTransferindetailDao;
import com.lander.wh.pojo.TWhTransferindetail;
import com.lander.wh.pojo.TWhTransferindetailQuery;
import com.lander.wh.pojo.TWhTransferindetailQuery.Criteria;

@Service
public class WhTransferindetailServiceImpl implements WhTransferindetailService {
	@Autowired
	private TWhTransferindetailDao tWhTransferindetailDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			Long masterId) {

		TWhTransferindetailQuery query = new TWhTransferindetailQuery();
		Criteria criteria = query.createCriteria();
			criteria.andFmasteridEqualTo(masterId);
			
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhTransferindetail> list = tWhTransferindetailDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tWhTransferindetailDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TWhTransferindetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Long fid = IDUtils.genId();
		prOrderitem.setFid(fid);

		prOrderitem = (TWhTransferindetail) BizDateUtil.setDefaultProperty(prOrderitem, userId, TWhTransferindetail.class);
		
		Integer data = tWhTransferindetailDao.insert(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult update(TWhTransferindetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (prOrderitem.getFid()==null){
			throw new ServiceException("404", "要更新的对象不存在");
		}

		prOrderitem = (TWhTransferindetail) BizDateUtil.setModifyDefaultProperty(prOrderitem, userId, TWhTransferindetail.class);
		
		Integer data = tWhTransferindetailDao.updateByPrimaryKey(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult delete(long fid) {
		tWhTransferindetailDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	@Override
	public List<TWhTransferindetail> list(Long masterid) {
		TWhTransferindetailQuery query = new TWhTransferindetailQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFmasteridEqualTo(masterid);		
		
		return tWhTransferindetailDao.selectByExample(query);		
	}
}
