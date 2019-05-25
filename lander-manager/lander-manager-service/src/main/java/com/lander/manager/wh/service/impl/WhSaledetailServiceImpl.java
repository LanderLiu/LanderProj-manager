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
import com.lander.manager.wh.service.WhSaledetailService;
import com.lander.wh.dao.TWhSaledetailDao;
import com.lander.wh.pojo.TWhSaledetail;
import com.lander.wh.pojo.TWhSaledetailQuery;
import com.lander.wh.pojo.TWhSaledetailQuery.Criteria;

@Service
public class WhSaledetailServiceImpl implements WhSaledetailService {
	@Autowired
	private TWhSaledetailDao tWhSaledetailDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			Long masterId) {

		TWhSaledetailQuery query = new TWhSaledetailQuery();
		Criteria criteria = query.createCriteria();
			criteria.andFmasteridEqualTo(masterId);
			
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhSaledetail> list = tWhSaledetailDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tWhSaledetailDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TWhSaledetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Long fid = IDUtils.genId();
		prOrderitem.setFid(fid);

		prOrderitem = (TWhSaledetail) BizDateUtil.setDefaultProperty(prOrderitem, userId, TWhSaledetail.class);
		
		Integer data = tWhSaledetailDao.insert(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult update(TWhSaledetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (prOrderitem.getFid()==null){
			throw new ServiceException("404", "要更新的对象不存在");
		}

		prOrderitem = (TWhSaledetail) BizDateUtil.setModifyDefaultProperty(prOrderitem, userId, TWhSaledetail.class);
		
		Integer data = tWhSaledetailDao.updateByPrimaryKey(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult delete(long fid) {
		tWhSaledetailDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	@Override
	public List<TWhSaledetail> list(Long masterid) {
		TWhSaledetailQuery query = new TWhSaledetailQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFmasteridEqualTo(masterid);		
		
		return tWhSaledetailDao.selectByExample(query);		
	}
}
