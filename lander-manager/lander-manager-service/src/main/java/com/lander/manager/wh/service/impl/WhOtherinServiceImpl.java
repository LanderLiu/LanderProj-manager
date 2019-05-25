package com.lander.manager.wh.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.TBdSupplyDao;
import com.lander.bd.dao.TBdWarehouseDao;
import com.lander.bd.pojo.TBdSupply;
import com.lander.bd.pojo.TBdWarehouse;
import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.common.utils.StringUtil;
import com.lander.manager.bd.service.BdInvnumberService;
import com.lander.manager.wh.service.WhOtherinService;
import com.lander.manager.wh.service.WhOtherindetailService;
import com.lander.manager.sys.service.SysSetService;
import com.lander.wh.dao.TWhOtherinDao;
import com.lander.wh.dao.TWhOtherindetailDao;
import com.lander.wh.pojo.WhOtherin;
import com.lander.wh.pojo.WhOtherinQuery;
import com.lander.wh.pojo.WhOtherinVo;
import com.lander.wh.pojo.TWhOtherin;
import com.lander.wh.pojo.TWhOtherinQuery;
import com.lander.wh.pojo.TWhOtherinQuery.Criteria;
import com.lander.wh.pojo.TWhOtherindetail;
import com.lander.wh.pojo.TWhOtherindetailQuery;

@Service
public class WhOtherinServiceImpl implements WhOtherinService {
	@Autowired
	private TWhOtherinDao tWhOtherinDao;
	@Autowired
	private TWhOtherindetailDao tWhOtherindetailDao;
	@Autowired
	private WhOtherindetailService whOtherindetailService;
	@Autowired
	private SysSetService sysSetService;
	@Autowired
	private BdInvnumberService bdInvnumberService;
	@Autowired
	private TBdSupplyDao tbdSupplyDao;
	@Autowired
	private TBdWarehouseDao tbdWarehouseDao;
	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			WhOtherinQuery whOtherinQuery) {

		TWhOtherinQuery query = new TWhOtherinQuery();
		Criteria criteria = query.createCriteria();
		if (!StringUtil.isEmpty(whOtherinQuery.getNumber())) {
			criteria.andFnumberLike("%" + whOtherinQuery.getNumber() + "%");
		}
		if (whOtherinQuery.getSupplyId() != null && whOtherinQuery.getSupplyId() != -1) {
			criteria.andFsupplyidEqualTo(whOtherinQuery.getSupplyId());
		}
		if (whOtherinQuery.getWarehouseId() != null && whOtherinQuery.getWarehouseId() != -1) {
			criteria.andFwarehouseidEqualTo(whOtherinQuery.getWarehouseId());
		}
		if (whOtherinQuery.getStateId() != null && whOtherinQuery.getStateId() != -1) {
			criteria.andFstateidEqualTo(whOtherinQuery.getStateId());
		}
		if (whOtherinQuery.getBizDatetimeFrom()!=null){
			criteria.andFbizdateGreaterThanOrEqualTo(whOtherinQuery.getBizDatetimeFrom());
		}
		if (whOtherinQuery.getBizDatetimeTo()!=null){
			criteria.andFbizdateLessThanOrEqualTo(whOtherinQuery.getBizDatetimeTo());
		}

		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhOtherin> list = tWhOtherinDao.selectByExample(query);
		
		List<WhOtherinVo> listVo=new ArrayList<WhOtherinVo>();
		for(TWhOtherin item :list){
			WhOtherinVo vo=new WhOtherinVo();
			vo.settWhOtherin(item);
			vo.setFid(item.getFid());
			
			listVo.add(vo);
		}
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(listVo);

		result.setTotal(tWhOtherinDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TWhOtherin whOtherin, String userId) throws SysException,Exception {
		String isAutoNumber = sysSetService.getByCode("02");
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			String FLeaderStr = "WI";
			int FCategoryId = 1;
			int flowLong = 3;
			String getFlow = bdInvnumberService.GetFlow(FLeaderStr, FCategoryId, userId, flowLong);
			whOtherin.setFnumber(getFlow);
		} else {// 不自动生成单号
			TWhOtherinQuery example = new TWhOtherinQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whOtherin.getFnumber());
			// 检查编码不可重复
			int countByExample = tWhOtherinDao.countByExample(example);
			if (countByExample > 0) {
				throw new ServiceException("400", "编码为：" + whOtherin.getFnumber() + "的其它入仓单已经存在，编码不可重复。");
			}
		}
		Long fid = IDUtils.genId();
		whOtherin.setFid(fid);

		whOtherin = (TWhOtherin) BizDateUtil.setDefaultProperty(whOtherin, userId, TWhOtherin.class);
		whOtherin.setFstateid(0);
		Integer data = tWhOtherinDao.insert(whOtherin);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult insertWithItem(WhOtherin whOtherin, String userId) throws SysException, Exception {

		if (whOtherin.gettWhOtherindetails().size() == 0) {
			throw new SysException("410", "保存不成功,需要有一条以上订单明细。");
		}
		if (null != whOtherin.getFsupplyid()) {
			TBdSupply selectByPrimaryKey = tbdSupplyDao.selectByPrimaryKey(whOtherin.getFsupplyid());
			if (selectByPrimaryKey != null) {
				whOtherin.setFsupplyname(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,供应商资料无效。");
			}
		}
		if (null != whOtherin.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whOtherin.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whOtherin.setFwarehousename(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}	
		
		whOtherin.setFitemcount(whOtherin.gettWhOtherindetails().size());

		// 先插入主表
		insert(whOtherin, userId);
		// 再插入明细
		for (TWhOtherindetail item : whOtherin.gettWhOtherindetails()) {
			item.setFmasterid(whOtherin.getFid());
			whOtherindetailService.insert(item, userId);
		}
		return LanderResult.ok();
	}

	@Override
	public LanderResult update(TWhOtherin whOtherin, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		//updateValidate(whOtherin);

		whOtherin = (TWhOtherin) BizDateUtil.setModifyDefaultProperty(whOtherin, userId, TWhOtherin.class);
		whOtherin.setFstateid(0);
		Integer data = tWhOtherinDao.updateByPrimaryKey(whOtherin);
		return LanderResult.ok(data);
	}

	/**
	 * 连同订单主表和明细一次性更新 判断明细有新的/有删除/有更改，要逐条处理
	 * @throws SysException 
	 */
	@Override
	public LanderResult updateWithItem(WhOtherin whOtherin, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException {

		LanderResult updateValidate = updateValidate(whOtherin);
		if (updateValidate.getStatus()!=200){
			return updateValidate;
		}

		TWhOtherin tWhOtherin = tWhOtherinDao.selectByPrimaryKey(whOtherin.getFid());
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号;
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			// 单号取原值
			whOtherin.setFnumber(tWhOtherin.getFnumber());
		}
		whOtherin.setFcreatedatetime(tWhOtherin.getFcreatedatetime());
		whOtherin.setFcreateman(tWhOtherin.getFcreateman());
		whOtherin.setFitemcount(whOtherin.gettWhOtherindetails().size());

		if (null != whOtherin.getFsupplyid()) {
			TBdSupply selectByPrimaryKey = tbdSupplyDao.selectByPrimaryKey(whOtherin.getFsupplyid());
			if (selectByPrimaryKey != null) {
				whOtherin.setFsupplyname(selectByPrimaryKey.getFname());
			}
		}
		if (null != whOtherin.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whOtherin.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whOtherin.setFwarehousename(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}
		whOtherin = (WhOtherin) BizDateUtil.setModifyDefaultProperty(whOtherin, userId, TWhOtherin.class);
		whOtherin.setFstateid(0);
		Integer data = tWhOtherinDao.updateByPrimaryKey(whOtherin);
		// 获取已经有分录列表,为下面比较作准备
		TWhOtherindetailQuery example = new TWhOtherindetailQuery();
		com.lander.wh.pojo.TWhOtherindetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(whOtherin.getFid());
		List<TWhOtherindetail> itemList = tWhOtherindetailDao.selectByExample(example);
		List<Long> itemIdList = new ArrayList<Long>();
		for (TWhOtherindetail item : itemList) {
			itemIdList.add(item.getFid());
		}
		// 更新分录，可能增加、删除或改变某条分录的一个值
		for (TWhOtherindetail item : whOtherin.gettWhOtherindetails()) {
			// 01如果原单分录中不存在，则新增
			if (itemIdList.indexOf(item.getFid()) <= -1) {
				item.setFmasterid(whOtherin.getFid());
				whOtherindetailService.insert(item, userId);
			} else {
				// 如果原单存在的，则用新分录值更新原分录的值
				whOtherindetailService.update(item, userId);
				// 在新明细中存在，则更新后不再关注
				itemIdList.remove(itemIdList.indexOf(item.getFid()));
			}
		}
		// 删除原单存在的分录，而在新分录列表不存在的
		// itemIdList中到现在仍存在的，就是没处理过的，也就是新的明细里已经不存在的
		for (Long item : itemIdList) {
			whOtherindetailService.delete(item);
		}
		return LanderResult.ok(data);
	}

	private LanderResult updateValidate(WhOtherin whOtherin) {
		TWhOtherin tWhOtherin = tWhOtherinDao.selectByPrimaryKey(whOtherin.getFid());
		if (tWhOtherin == null) {			
			return new LanderResult(404,"其它入仓单信息未找到，请刷新后重试。",null);
		}
		if (tWhOtherin.getFstateid()!=0&&tWhOtherin.getFstateid()!=1){
			return new LanderResult(410,"其它入仓单必须是保存或提交状态才允许修改。",null);
		}
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号; 否则要检查单号有无重复
		if (isAutoNumber.equalsIgnoreCase("0")) {// 非自动生成单号
			TWhOtherinQuery example = new TWhOtherinQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whOtherin.getFnumber());
			criteria.andFidNotEqualTo(whOtherin.getFid());
			// 检查编码不可重复
			List<TWhOtherin> countByExample = tWhOtherinDao.selectByExample(example);
			if (countByExample.size() > 0) {
				//throw new ServiceException("400",
				//		"采购订单:[" + countByExample.get(0).getFnumber() + "]的编码与您要修改的订单重复，编码不可重复。");
				
				return new LanderResult(400,"其它入仓单:[" + countByExample.get(0).getFnumber() + "]的编码与您要修改的其它入仓单重复，编码不可重复。",null);
			}
		}
		//在修改一张单前，先清空原有分录，所以不用做下面的较验
//		//要更新的明细，与原单内所有明细必须属于同一订单
//		long orderid = -1;
//		//各明细如果不为空，则必须来自同一订单
//		for (TWhOtherindetail item : whOtherin.gettWhOtherindetails()) {
//			if (item.getForderitemid() != null) {//如果为空，则不关联订单，不用检查
//				TPrOrderitem selectByPrimaryKey = tprOrderitemDao.selectByPrimaryKey(item.getForderitemid());
//				if (orderid != -1 && selectByPrimaryKey.getFmasterid() != orderid) {
//					//throw new ServiceException("410", "各明细必须来自同一订单");
//					return new LanderResult(410,"各明细必须来自同一订单",null);
//				} else {
//					orderid = selectByPrimaryKey.getFmasterid();
//				}
//			}
//		}
//		TWhOtherindetailQuery example=new TWhOtherindetailQuery();
//		com.lander.wh.pojo.TWhOtherindetailQuery.Criteria createCriteria = example.createCriteria();
//		createCriteria.andFmasteridEqualTo(whOtherin.getFid());
//		List<TWhOtherindetail> selectByExample = tWhOtherindetailDao.selectByExample(example);
//		for (TWhOtherindetail item : selectByExample) {
//			if (item.getForderitemid() != null) {//如果为空，则不关联订单，不用检查
//				TPrOrderitem selectByPrimaryKey = tprOrderitemDao.selectByPrimaryKey(item.getForderitemid());
//				if (orderid != -1 && selectByPrimaryKey.getFmasterid() != orderid) {
//					//throw new ServiceException("410", "各明细必须来自同一订单");
//					return new LanderResult(410,"各明细必须来自同一订单",null);
//				} else {
//					orderid = selectByPrimaryKey.getFmasterid();
//				}
//			}
//		}
		return  LanderResult.ok();
	}

	@Override
	public LanderResult delete(long fid) throws SysException {
		
		deleteValidate(fid);
		
		TWhOtherindetailQuery example = new TWhOtherindetailQuery();
		com.lander.wh.pojo.TWhOtherindetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(fid);

		List<TWhOtherindetail> selectByExample = tWhOtherindetailDao.selectByExample(example);
		for (TWhOtherindetail orderitem : selectByExample) {
			whOtherindetailService.delete(orderitem.getFid());
		}
		tWhOtherinDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	private LanderResult deleteValidate(long fid) throws SysException {
		TWhOtherin tWhOtherin = tWhOtherinDao.selectByPrimaryKey(fid);
		if (tWhOtherin == null) {	
			throw new SysException("400", "其它入仓单信息未找到，请刷新后重试");			
		}
		if (tWhOtherin.getFstateid()!=0&&tWhOtherin.getFstateid()!=1){
			throw new SysException("400", "其它入仓单必须是保存或提交状态。");		
		}
		
		return  LanderResult.ok();
	}
	@Override
	public LanderResult delete(long[] fid)  throws SysException{		
		for (long i : fid) {			
			delete(i);
		}
		return LanderResult.ok(fid.length);
	}

	@Override
	public LanderResult Audit(long id, String userId) throws SysException {
		// 检查单据是否符合可审核
				// 1.单据必须存在
				TWhOtherin selectByPrimaryKey = tWhOtherinDao.selectByPrimaryKey(id);
				if (selectByPrimaryKey == null) {
					throw new SysException("400", "审核失败，其它入仓单信息不存在。");
				}

				// 2.单据必须是提交状态
				if (selectByPrimaryKey.getFstateid() != 1) {
					throw new SysException("400", "审核失败，其它入仓单不是提交状态。");
				}
				// 检查单据明细
				// 3.必须有明细
				// 4.明细数量必须都大于0
				List<TWhOtherindetail> list = whOtherindetailService.list(id);
				if (list.size() == 0) {
					throw new SysException("400", "审核失败，其它入仓单明细为空。");
				}
				for (TWhOtherindetail item : list) {
					if (item.getFqty().equals(0)) {
						throw new SysException("400", "审核失败，其它入仓单物料数量不能为0。");
					}
				}
				// 更新状态
				selectByPrimaryKey.setFstateid(2);
				selectByPrimaryKey.setFauditdatetime(new Date());
				selectByPrimaryKey.setFauditman(userId);
				selectByPrimaryKey.setFauditmemo("");
				tWhOtherinDao.updateByPrimaryKey(selectByPrimaryKey);
				return LanderResult.ok();
	}

	@Override
	public LanderResult UnAudit(long id, String userId) throws SysException {
		// 检查单据是否符合可审核
				// 1.单据必须存在
		TWhOtherin selectByPrimaryKey = tWhOtherinDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反审核失败，其它入仓单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 2) {
			throw new SysException("400", "反审核失败，其它入仓单不是审核状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TWhOtherindetail> list = whOtherindetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "反审核失败，其它入仓单明细为空。");
		}

		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhOtherinDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult Audit(long[] fid, String userId) throws SysException {
		for (long i : fid) {
			Audit(i,userId);
		}
		return LanderResult.ok(fid.length);
	}

	@Override
	public LanderResult UnAudit(long[] fid, String userId) throws SysException {
		for (long i : fid) {
			UnAudit(i,userId);
		}
		return LanderResult.ok(fid.length);
	}

	@Override
	public LanderResult Post(long id) throws SysException {
		// 检查单据是否符合可提交
		// 1.单据必须存在
		TWhOtherin selectByPrimaryKey = tWhOtherinDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "提交失败，单据信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 0) {
			throw new SysException("400", "提交失败，单据不是保存状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TWhOtherindetail> list = whOtherindetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "提交失败，其它入仓单明细为空。");
		}
		for (TWhOtherindetail item : list) {
			if (item.getFqty().equals(0)) {
				throw new SysException("400", "提交失败，物料数量不能为0。");
			}
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhOtherinDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult UnPost(long id) throws SysException {
		// 检查单据是否符合可反提交
		// 1.单据必须存在
		TWhOtherin selectByPrimaryKey = tWhOtherinDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反提交失败，其它入仓单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 1) {
			throw new SysException("400", "反提交失败，其它入仓单不是提交状态。");
		}
		
		// 更新状态
		selectByPrimaryKey.setFstateid(0);
		tWhOtherinDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
}

	@Override
	public LanderResult Post(long[] id)  throws SysException {
		for (long i : id) {
			Post(i);
		}
		return LanderResult.ok(id.length);
	}

	@Override
	public LanderResult UnPost(long[] id)  throws SysException {
		for (long i : id) {
			UnPost(i);
		}
		return LanderResult.ok(id.length);
	}
}
