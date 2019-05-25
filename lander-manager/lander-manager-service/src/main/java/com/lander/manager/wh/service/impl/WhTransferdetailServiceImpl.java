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
import com.lander.manager.wh.service.WhTransferdetailService;
import com.lander.wh.dao.TWhTransferdetailDao;
import com.lander.wh.pojo.TWhTransferdetail;
import com.lander.wh.pojo.TWhTransferdetailQuery;
import com.lander.wh.pojo.TWhTransferdetailQuery.Criteria;

@Service
public class WhTransferdetailServiceImpl implements WhTransferdetailService {
	@Autowired
	private TWhTransferdetailDao tWhTransferdetailDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			Long masterId) {

		TWhTransferdetailQuery query = new TWhTransferdetailQuery();
		Criteria criteria = query.createCriteria();
			criteria.andFmasteridEqualTo(masterId);
			
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhTransferdetail> list = tWhTransferdetailDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tWhTransferdetailDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TWhTransferdetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Long fid = IDUtils.genId();
		prOrderitem.setFid(fid);

		prOrderitem = (TWhTransferdetail) BizDateUtil.setDefaultProperty(prOrderitem, userId, TWhTransferdetail.class);
		
		Integer data = tWhTransferdetailDao.insert(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult update(TWhTransferdetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (prOrderitem.getFid()==null){
			throw new ServiceException("404", "要更新的对象不存在");
		}

		prOrderitem = (TWhTransferdetail) BizDateUtil.setModifyDefaultProperty(prOrderitem, userId, TWhTransferdetail.class);
		
		Integer data = tWhTransferdetailDao.updateByPrimaryKey(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult delete(long fid) {
		tWhTransferdetailDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	@Override
	public List<TWhTransferdetail> list(Long masterid) {
		TWhTransferdetailQuery query = new TWhTransferdetailQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFmasteridEqualTo(masterid);		
		
		return tWhTransferdetailDao.selectByExample(query);		
	}
}
