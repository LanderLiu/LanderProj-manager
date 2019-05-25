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
import com.lander.manager.wh.service.WhReceivedetailService;
import com.lander.wh.dao.TWhReceivedetailDao;
import com.lander.wh.pojo.TWhReceivedetail;
import com.lander.wh.pojo.TWhReceivedetailQuery;
import com.lander.wh.pojo.TWhReceivedetailQuery.Criteria;

@Service
public class WhReceivedetailServiceImpl implements WhReceivedetailService {
	@Autowired
	private TWhReceivedetailDao tWhReceivedetailDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			Long masterId) {

		TWhReceivedetailQuery query = new TWhReceivedetailQuery();
		Criteria criteria = query.createCriteria();
			criteria.andFmasteridEqualTo(masterId);
			
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhReceivedetail> list = tWhReceivedetailDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tWhReceivedetailDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TWhReceivedetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Long fid = IDUtils.genId();
		prOrderitem.setFid(fid);

		prOrderitem = (TWhReceivedetail) BizDateUtil.setDefaultProperty(prOrderitem, userId, TWhReceivedetail.class);
		
		Integer data = tWhReceivedetailDao.insert(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult update(TWhReceivedetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (prOrderitem.getFid()==null){
			throw new ServiceException("404", "要更新的对象不存在");
		}

		prOrderitem = (TWhReceivedetail) BizDateUtil.setModifyDefaultProperty(prOrderitem, userId, TWhReceivedetail.class);
		
		Integer data = tWhReceivedetailDao.updateByPrimaryKey(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult delete(long fid) {
		tWhReceivedetailDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	@Override
	public List<TWhReceivedetail> list(Long masterid) {
		TWhReceivedetailQuery query = new TWhReceivedetailQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFmasteridEqualTo(masterid);		
		
		return tWhReceivedetailDao.selectByExample(query);		
	}
}
