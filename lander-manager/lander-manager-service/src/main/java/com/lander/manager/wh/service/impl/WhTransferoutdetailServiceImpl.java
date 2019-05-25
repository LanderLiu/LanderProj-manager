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
import com.lander.manager.wh.service.WhTransferoutdetailService;
import com.lander.wh.dao.TWhTransferoutdetailDao;
import com.lander.wh.pojo.TWhTransferoutdetail;
import com.lander.wh.pojo.TWhTransferoutdetailQuery;
import com.lander.wh.pojo.TWhTransferoutdetailQuery.Criteria;

@Service
public class WhTransferoutdetailServiceImpl implements WhTransferoutdetailService {
	@Autowired
	private TWhTransferoutdetailDao tWhTransferoutdetailDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			Long masterId) {

		TWhTransferoutdetailQuery query = new TWhTransferoutdetailQuery();
		Criteria criteria = query.createCriteria();
			criteria.andFmasteridEqualTo(masterId);
			
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhTransferoutdetail> list = tWhTransferoutdetailDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tWhTransferoutdetailDao.countByExample(query));
		return result;
	}
	@Override
	public LanderResult insert(TWhTransferoutdetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Long fid = IDUtils.genId();
		prOrderitem.setFid(fid);

		prOrderitem = (TWhTransferoutdetail) BizDateUtil.setDefaultProperty(prOrderitem, userId, TWhTransferoutdetail.class);
		
		Integer data = tWhTransferoutdetailDao.insert(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult update(TWhTransferoutdetail prOrderitem, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		if (prOrderitem.getFid()==null){
			throw new ServiceException("404", "要更新的对象不存在");
		}

		prOrderitem = (TWhTransferoutdetail) BizDateUtil.setModifyDefaultProperty(prOrderitem, userId, TWhTransferoutdetail.class);
		
		Integer data = tWhTransferoutdetailDao.updateByPrimaryKey(prOrderitem);
		return LanderResult.ok(data);
	}
	@Override
	public LanderResult delete(long fid) {
		tWhTransferoutdetailDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	@Override
	public List<TWhTransferoutdetail> list(Long masterid) {
		TWhTransferoutdetailQuery query = new TWhTransferoutdetailQuery();
		Criteria criteria = query.createCriteria();
		criteria.andFmasteridEqualTo(masterid);		
		
		return tWhTransferoutdetailDao.selectByExample(query);		
	}
}
