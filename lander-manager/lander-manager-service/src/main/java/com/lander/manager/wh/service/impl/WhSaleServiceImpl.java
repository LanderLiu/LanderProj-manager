package com.lander.manager.wh.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.TBdCustDao;
import com.lander.bd.dao.TBdWarehouseDao;
import com.lander.bd.pojo.TBdCust;
import com.lander.bd.pojo.TBdWarehouse;
import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.common.utils.StringUtil;
import com.lander.manager.bd.service.BdInvnumberService;
import com.lander.manager.wh.service.WhSaleService;
import com.lander.manager.wh.service.WhSaledetailService;
import com.lander.pr.dao.TPrOrderDao;
import com.lander.pr.dao.TPrOrderitemDao;
import com.lander.pr.pojo.TPrOrder;
import com.lander.pr.pojo.TPrOrderitem;
import com.lander.manager.sys.service.SysSetService;
import com.lander.wh.dao.TWhSaleDao;
import com.lander.wh.dao.TWhSaledetailDao;
import com.lander.wh.pojo.WhSale;
import com.lander.wh.pojo.WhSaleQuery;
import com.lander.wh.pojo.WhSaleVo;
import com.lander.wh.pojo.TWhSale;
import com.lander.wh.pojo.TWhSaleQuery;
import com.lander.wh.pojo.TWhSaleQuery.Criteria;
import com.lander.wh.pojo.TWhSaledetail;
import com.lander.wh.pojo.TWhSaledetailQuery;

@Service
public class WhSaleServiceImpl implements WhSaleService {
	@Autowired
	private TWhSaleDao tWhSaleDao;
	@Autowired
	private TWhSaledetailDao tWhSaledetailDao;
	@Autowired
	private WhSaledetailService whSaledetailService;
	@Autowired
	private SysSetService sysSetService;
	@Autowired
	private BdInvnumberService bdInvnumberService;
	@Autowired
	private TBdCustDao tbdCustDao;
	@Autowired
	private TBdWarehouseDao tbdWarehouseDao;
	@Autowired
	private TPrOrderitemDao tprOrderitemDao;
	@Autowired
	private TPrOrderDao tprOrderDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			WhSaleQuery whSaleQuery) {

		TWhSaleQuery query = new TWhSaleQuery();
		Criteria criteria = query.createCriteria();
		if (!StringUtil.isEmpty(whSaleQuery.getNumber())) {
			criteria.andFnumberLike("%" + whSaleQuery.getNumber() + "%");
		}
		if (!StringUtil.isEmpty(whSaleQuery.getCustName())) {
			criteria.andFcustnameLike("%" + whSaleQuery.getCustName() + "%");
		}
		if (whSaleQuery.getWarehouseId() != null && whSaleQuery.getWarehouseId() != -1) {
			criteria.andFwarehouseidEqualTo(whSaleQuery.getWarehouseId());
		}
		if (whSaleQuery.getStateId() != null && whSaleQuery.getStateId() != -1) {
			criteria.andFstateidEqualTo(whSaleQuery.getStateId());
		}
		if (whSaleQuery.getBizDatetimeFrom()!=null){
			criteria.andFbizdateGreaterThanOrEqualTo(whSaleQuery.getBizDatetimeFrom());
		}
		if (whSaleQuery.getBizDatetimeTo()!=null){
			criteria.andFbizdateLessThanOrEqualTo(whSaleQuery.getBizDatetimeTo());
		}		

		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhSale> list = tWhSaleDao.selectByExample(query);
		
		List<WhSaleVo> listVo=new ArrayList<WhSaleVo>();
		for(TWhSale item :list){
			WhSaleVo vo=new WhSaleVo();
			vo.settWhSale(item);
			vo.setFid(item.getFid());
			if (item.getForderid()!=null&&item.getForderid()!=-1){
				TPrOrder selectByPrimaryKey = tprOrderDao.selectByPrimaryKey(item.getForderid());
				if (selectByPrimaryKey!=null){
					vo.setOrderNumber(selectByPrimaryKey.getFnumber());
				}
			}
			listVo.add(vo);
		}
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(listVo);

		result.setTotal(tWhSaleDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TWhSale whSale, String userId) throws SysException,Exception {
		String isAutoNumber = sysSetService.getByCode("02");
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			String FLeaderStr = "WR";
			int FCategoryId = 1;
			int flowLong = 3;
			String getFlow = bdInvnumberService.GetFlow(FLeaderStr, FCategoryId, userId, flowLong);
			whSale.setFnumber(getFlow);
		} else {// 不自动生成单号
			TWhSaleQuery example = new TWhSaleQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whSale.getFnumber());
			// 检查编码不可重复
			int countByExample = tWhSaleDao.countByExample(example);
			if (countByExample > 0) {
				throw new ServiceException("400", "编码为：" + whSale.getFnumber() + "的收货单已经存在，编码不可重复。");
			}
		}
		Long fid = IDUtils.genId();
		whSale.setFid(fid);

		whSale = (TWhSale) BizDateUtil.setDefaultProperty(whSale, userId, TWhSale.class);
		whSale.setFstateid(0);
		Integer data = tWhSaleDao.insert(whSale);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult insertWithItem(WhSale whSale, String userId) throws SysException, Exception {

		if (whSale.gettWhSaledetails().size() == 0) {
			throw new SysException("410", "保存不成功,需要有一条以上订单明细。");
		}
		if (null != whSale.getFcustid()) {
			TBdCust selectByPrimaryKey = tbdCustDao.selectByPrimaryKey(whSale.getFcustid());
			if (selectByPrimaryKey != null) {
				whSale.setFcustname(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,客户资料无效。");
			}
		}
		if (null != whSale.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whSale.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whSale.setFwarehousename(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}
		long orderid = -1;
		//各明细如果不为空，则必须来自同一订单
		for (TWhSaledetail item : whSale.gettWhSaledetails()) {
			if (item.getForderitemid() != null) {//如果为空，则不关联订单，不用检查
				TPrOrderitem selectByPrimaryKey = tprOrderitemDao.selectByPrimaryKey(item.getForderitemid());
				if (orderid != -1 && selectByPrimaryKey.getFmasterid() != orderid) {
					throw new SysException("410", "各明细必须来自同一订单");
				} else {
					orderid = selectByPrimaryKey.getFmasterid();
				}
			}
		}
		whSale.setFitemcount(whSale.gettWhSaledetails().size());

		// 先插入主表
		insert(whSale, userId);
		// 再插入明细
		for (TWhSaledetail item : whSale.gettWhSaledetails()) {
			item.setFmasterid(whSale.getFid());
			whSaledetailService.insert(item, userId);
		}
		return LanderResult.ok();
	}

	@Override
	public LanderResult update(TWhSale whSale, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		//updateValidate(whSale);

		whSale = (TWhSale) BizDateUtil.setModifyDefaultProperty(whSale, userId, TWhSale.class);
		whSale.setFstateid(0);
		Integer data = tWhSaleDao.updateByPrimaryKey(whSale);
		return LanderResult.ok(data);
	}

	/**
	 * 连同订单主表和明细一次性更新 判断明细有新的/有删除/有更改，要逐条处理
	 * @throws SysException 
	 */
	@Override
	public LanderResult updateWithItem(WhSale whSale, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException {

		LanderResult updateValidate = updateValidate(whSale);
		if (updateValidate.getStatus()!=200){
			return updateValidate;
		}

		TWhSale tWhSale = tWhSaleDao.selectByPrimaryKey(whSale.getFid());
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号;
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			// 单号取原值
			whSale.setFnumber(tWhSale.getFnumber());
		}
		whSale.setFcreatedatetime(tWhSale.getFcreatedatetime());
		whSale.setFcreateman(tWhSale.getFcreateman());
		whSale.setFitemcount(whSale.gettWhSaledetails().size());

		if (null != whSale.getFcustid()) {
			TBdCust selectByPrimaryKey = tbdCustDao.selectByPrimaryKey(whSale.getFcustid());
			if (selectByPrimaryKey != null) {
				whSale.setFcustname(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,客户资料无效。");
			}
		}
		if (null != whSale.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whSale.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whSale.setFwarehousename(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}
		whSale = (WhSale) BizDateUtil.setModifyDefaultProperty(whSale, userId, TWhSale.class);
		whSale.setFstateid(0);
		Integer data = tWhSaleDao.updateByPrimaryKey(whSale);
		// 获取已经有分录列表,为下面比较作准备
		TWhSaledetailQuery example = new TWhSaledetailQuery();
		com.lander.wh.pojo.TWhSaledetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(whSale.getFid());
		List<TWhSaledetail> itemList = tWhSaledetailDao.selectByExample(example);
		List<Long> itemIdList = new ArrayList<Long>();
		for (TWhSaledetail item : itemList) {
			itemIdList.add(item.getFid());
		}
		// 更新分录，可能增加、删除或改变某条分录的一个值
		for (TWhSaledetail item : whSale.gettWhSaledetails()) {
			// 01如果原单分录中不存在，则新增
			if (itemIdList.indexOf(item.getFid()) <= -1) {
				item.setFmasterid(whSale.getFid());
				whSaledetailService.insert(item, userId);
			} else {
				// 如果原单存在的，则用新分录值更新原分录的值
				whSaledetailService.update(item, userId);
				// 在新明细中存在，则更新后不再关注
				itemIdList.remove(itemIdList.indexOf(item.getFid()));
			}
		}
		// 删除原单存在的分录，而在新分录列表不存在的
		// itemIdList中到现在仍存在的，就是没处理过的，也就是新的明细里已经不存在的
		for (Long item : itemIdList) {
			whSaledetailService.delete(item);
		}
		return LanderResult.ok(data);
	}

	private LanderResult updateValidate(WhSale whSale) {
		TWhSale tWhSale = tWhSaleDao.selectByPrimaryKey(whSale.getFid());
		if (tWhSale == null) {			
			return new LanderResult(404,"收货单信息未找到，请刷新后重试。",null);
		}
		if (tWhSale.getFstateid()!=0&&tWhSale.getFstateid()!=1){
			
		}
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号; 否则要检查单号有无重复
		if (isAutoNumber.equalsIgnoreCase("0")) {// 非自动生成单号
			TWhSaleQuery example = new TWhSaleQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whSale.getFnumber());
			criteria.andFidNotEqualTo(whSale.getFid());
			// 检查编码不可重复
			List<TWhSale> countByExample = tWhSaleDao.selectByExample(example);
			if (countByExample.size() > 0) {
				//throw new ServiceException("400",
				//		"采购订单:[" + countByExample.get(0).getFnumber() + "]的编码与您要修改的订单重复，编码不可重复。");
				
				return new LanderResult(400,"采购订单:[" + countByExample.get(0).getFnumber() + "]的编码与您要修改的订单重复，编码不可重复。",null);
			}
		}
		//在修改一张单前，先清空原有分录，所以不用做下面的较验
//		//要更新的明细，与原单内所有明细必须属于同一订单
//		long orderid = -1;
//		//各明细如果不为空，则必须来自同一订单
//		for (TWhSaledetail item : whSale.gettWhSaledetails()) {
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
//		TWhSaledetailQuery example=new TWhSaledetailQuery();
//		com.lander.wh.pojo.TWhSaledetailQuery.Criteria createCriteria = example.createCriteria();
//		createCriteria.andFmasteridEqualTo(whSale.getFid());
//		List<TWhSaledetail> selectByExample = tWhSaledetailDao.selectByExample(example);
//		for (TWhSaledetail item : selectByExample) {
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
		
		TWhSaledetailQuery example = new TWhSaledetailQuery();
		com.lander.wh.pojo.TWhSaledetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(fid);

		List<TWhSaledetail> selectByExample = tWhSaledetailDao.selectByExample(example);
		for (TWhSaledetail orderitem : selectByExample) {
			whSaledetailService.delete(orderitem.getFid());
		}
		tWhSaleDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	private LanderResult deleteValidate(long fid) throws SysException {
		TWhSale tWhSale = tWhSaleDao.selectByPrimaryKey(fid);
		if (tWhSale == null) {	
			throw new SysException("400", "收货单信息未找到，请刷新后重试");			
		}
		if (tWhSale.getFstateid()!=0&&tWhSale.getFstateid()!=1){
			throw new SysException("400", "收货单必须是保存或提交状态。");		
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
				TWhSale selectByPrimaryKey = tWhSaleDao.selectByPrimaryKey(id);
				if (selectByPrimaryKey == null) {
					throw new SysException("400", "审核失败，收货单信息不存在。");
				}

				// 2.单据必须是提交状态
				if (selectByPrimaryKey.getFstateid() != 1) {
					throw new SysException("400", "审核失败，收货单不是提交状态。");
				}
				// 检查单据明细
				// 3.必须有明细
				// 4.明细数量必须都大于0
				List<TWhSaledetail> list = whSaledetailService.list(id);
				if (list.size() == 0) {
					throw new SysException("400", "审核失败，收货单明细为空。");
				}
				for (TWhSaledetail item : list) {
					if (item.getFqty().equals(0)) {
						throw new SysException("400", "审核失败，收货单物料数量不能为0。");
					}
				}
				// 更新状态
				selectByPrimaryKey.setFstateid(2);
				selectByPrimaryKey.setFauditdatetime(new Date());
				selectByPrimaryKey.setFauditman(userId);
				selectByPrimaryKey.setFauditmemo("");
				tWhSaleDao.updateByPrimaryKey(selectByPrimaryKey);
				return LanderResult.ok();
	}

	@Override
	public LanderResult UnAudit(long id, String userId) throws SysException {
		// 检查单据是否符合可审核
				// 1.单据必须存在
		TWhSale selectByPrimaryKey = tWhSaleDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反审核失败，收货单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 2) {
			throw new SysException("400", "反审核失败，收货单不是审核状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TWhSaledetail> list = whSaledetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "反审核失败，收货单明细为空。");
		}

		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhSaleDao.updateByPrimaryKey(selectByPrimaryKey);
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
		TWhSale selectByPrimaryKey = tWhSaleDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "提交失败，收货单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 0) {
			throw new SysException("400", "提交失败，收货单不是保存状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TWhSaledetail> list = whSaledetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "提交失败，收货单明细为空。");
		}
		for (TWhSaledetail item : list) {
			if (item.getFqty().equals(0)) {
				throw new SysException("400", "提交失败，物料数量不能为0。");
			}
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhSaleDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult UnPost(long id) throws SysException {
		// 检查单据是否符合可反提交
		// 1.单据必须存在
		TWhSale selectByPrimaryKey = tWhSaleDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反提交失败，收货单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 1) {
			throw new SysException("400", "反提交失败，收货单不是提交状态。");
		}
		
		// 更新状态
		selectByPrimaryKey.setFstateid(0);
		tWhSaleDao.updateByPrimaryKey(selectByPrimaryKey);
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
