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
import com.lander.manager.wh.service.WhReceiveService;
import com.lander.manager.wh.service.WhReceivedetailService;
import com.lander.pr.dao.TPrOrderDao;
import com.lander.pr.dao.TPrOrderitemDao;
import com.lander.pr.pojo.TPrOrder;
import com.lander.pr.pojo.TPrOrderitem;
import com.lander.manager.sys.service.SysSetService;
import com.lander.wh.dao.TWhReceiveDao;
import com.lander.wh.dao.TWhReceivedetailDao;
import com.lander.wh.pojo.WhReceive;
import com.lander.wh.pojo.WhReceiveQuery;
import com.lander.wh.pojo.WhReceiveVo;
import com.lander.wh.pojo.TWhReceive;
import com.lander.wh.pojo.TWhReceiveQuery;
import com.lander.wh.pojo.TWhReceiveQuery.Criteria;
import com.lander.wh.pojo.TWhReceivedetail;
import com.lander.wh.pojo.TWhReceivedetailQuery;

@Service
public class WhReceiveServiceImpl implements WhReceiveService {
	@Autowired
	private TWhReceiveDao tWhReceiveDao;
	@Autowired
	private TWhReceivedetailDao tWhReceivedetailDao;
	@Autowired
	private WhReceivedetailService whReceivedetailService;
	@Autowired
	private SysSetService sysSetService;
	@Autowired
	private BdInvnumberService bdInvnumberService;
	@Autowired
	private TBdSupplyDao tbdSupplyDao;
	@Autowired
	private TBdWarehouseDao tbdWarehouseDao;
	@Autowired
	private TPrOrderitemDao tprOrderitemDao;
	@Autowired
	private TPrOrderDao tprOrderDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			WhReceiveQuery whReceiveQuery) {

		TWhReceiveQuery query = new TWhReceiveQuery();
		Criteria criteria = query.createCriteria();
		if (!StringUtil.isEmpty(whReceiveQuery.getNumber())) {
			criteria.andFnumberLike("%" + whReceiveQuery.getNumber() + "%");
		}
		if (whReceiveQuery.getSupplyId() != null && whReceiveQuery.getSupplyId() != -1) {
			criteria.andFsupplyidEqualTo(whReceiveQuery.getSupplyId());
		}
		if (whReceiveQuery.getWarehouseId() != null && whReceiveQuery.getWarehouseId() != -1) {
			criteria.andFwarehouseidEqualTo(whReceiveQuery.getWarehouseId());
		}
		if (whReceiveQuery.getStateId() != null && whReceiveQuery.getStateId() != -1) {
			criteria.andFstateidEqualTo(whReceiveQuery.getStateId());
		}
		if (whReceiveQuery.getBizDatetimeFrom()!=null){
			criteria.andFbizdateGreaterThanOrEqualTo(whReceiveQuery.getBizDatetimeFrom());
		}
		if (whReceiveQuery.getBizDatetimeTo()!=null){
			criteria.andFbizdateLessThanOrEqualTo(whReceiveQuery.getBizDatetimeTo());
		}
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhReceive> list = tWhReceiveDao.selectByExample(query);
		
		List<WhReceiveVo> listVo=new ArrayList<WhReceiveVo>();
		for(TWhReceive item :list){
			WhReceiveVo vo=new WhReceiveVo();
			vo.settWhReceive(item);
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

		result.setTotal(tWhReceiveDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TWhReceive whReceive, String userId) throws SysException,Exception {
		String isAutoNumber = sysSetService.getByCode("02");
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			String FLeaderStr = "WR";
			int FCategoryId = 1;
			int flowLong = 3;
			String getFlow = bdInvnumberService.GetFlow(FLeaderStr, FCategoryId, userId, flowLong);
			whReceive.setFnumber(getFlow);
		} else {// 不自动生成单号
			TWhReceiveQuery example = new TWhReceiveQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whReceive.getFnumber());
			// 检查编码不可重复
			int countByExample = tWhReceiveDao.countByExample(example);
			if (countByExample > 0) {
				throw new ServiceException("400", "编码为：" + whReceive.getFnumber() + "的收货单已经存在，编码不可重复。");
			}
		}
		Long fid = IDUtils.genId();
		whReceive.setFid(fid);

		whReceive = (TWhReceive) BizDateUtil.setDefaultProperty(whReceive, userId, TWhReceive.class);
		whReceive.setFstateid(0);
		Integer data = tWhReceiveDao.insert(whReceive);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult insertWithItem(WhReceive whReceive, String userId) throws SysException, Exception {

		if (whReceive.gettWhReceivedetails().size() == 0) {
			throw new SysException("410", "保存不成功,需要有一条以上订单明细。");
		}
		if (null != whReceive.getFsupplyid()) {
			TBdSupply selectByPrimaryKey = tbdSupplyDao.selectByPrimaryKey(whReceive.getFsupplyid());
			if (selectByPrimaryKey != null) {
				whReceive.setFsupplyname(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,供应商资料无效。");
			}
		}
		if (null != whReceive.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whReceive.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whReceive.setFwarehousename(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}
		long orderid = -1;
		//各明细如果不为空，则必须来自同一订单
		for (TWhReceivedetail item : whReceive.gettWhReceivedetails()) {
			if (item.getForderitemid() != null) {//如果为空，则不关联订单，不用检查
				TPrOrderitem selectByPrimaryKey = tprOrderitemDao.selectByPrimaryKey(item.getForderitemid());
				if (orderid != -1 && selectByPrimaryKey.getFmasterid() != orderid) {
					throw new SysException("410", "各明细必须来自同一订单");
				} else {
					orderid = selectByPrimaryKey.getFmasterid();
				}
			}
		}
		whReceive.setFitemcount(whReceive.gettWhReceivedetails().size());

		// 先插入主表
		insert(whReceive, userId);
		// 再插入明细
		for (TWhReceivedetail item : whReceive.gettWhReceivedetails()) {
			item.setFmasterid(whReceive.getFid());
			whReceivedetailService.insert(item, userId);
		}
		return LanderResult.ok();
	}

	@Override
	public LanderResult update(TWhReceive whReceive, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		//updateValidate(whReceive);

		whReceive = (TWhReceive) BizDateUtil.setModifyDefaultProperty(whReceive, userId, TWhReceive.class);
		whReceive.setFstateid(0);
		Integer data = tWhReceiveDao.updateByPrimaryKey(whReceive);
		return LanderResult.ok(data);
	}

	/**
	 * 连同订单主表和明细一次性更新 判断明细有新的/有删除/有更改，要逐条处理
	 * @throws SysException 
	 */
	@Override
	public LanderResult updateWithItem(WhReceive whReceive, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException {

		LanderResult updateValidate = updateValidate(whReceive);
		if (updateValidate.getStatus()!=200){
			return updateValidate;
		}

		TWhReceive tWhReceive = tWhReceiveDao.selectByPrimaryKey(whReceive.getFid());
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号;
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			// 单号取原值
			whReceive.setFnumber(tWhReceive.getFnumber());
		}
		whReceive.setFcreatedatetime(tWhReceive.getFcreatedatetime());
		whReceive.setFcreateman(tWhReceive.getFcreateman());
		whReceive.setFitemcount(whReceive.gettWhReceivedetails().size());

		if (null != whReceive.getFsupplyid()) {
			TBdSupply selectByPrimaryKey = tbdSupplyDao.selectByPrimaryKey(whReceive.getFsupplyid());
			if (selectByPrimaryKey != null) {
				whReceive.setFsupplyname(selectByPrimaryKey.getFname());
			}
		}
		if (null != whReceive.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whReceive.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whReceive.setFwarehousename(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}
		whReceive = (WhReceive) BizDateUtil.setModifyDefaultProperty(whReceive, userId, TWhReceive.class);
		whReceive.setFstateid(0);
		Integer data = tWhReceiveDao.updateByPrimaryKey(whReceive);
		// 获取已经有分录列表,为下面比较作准备
		TWhReceivedetailQuery example = new TWhReceivedetailQuery();
		com.lander.wh.pojo.TWhReceivedetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(whReceive.getFid());
		List<TWhReceivedetail> itemList = tWhReceivedetailDao.selectByExample(example);
		List<Long> itemIdList = new ArrayList<Long>();
		for (TWhReceivedetail item : itemList) {
			itemIdList.add(item.getFid());
		}
		// 更新分录，可能增加、删除或改变某条分录的一个值
		for (TWhReceivedetail item : whReceive.gettWhReceivedetails()) {
			// 01如果原单分录中不存在，则新增
			if (itemIdList.indexOf(item.getFid()) <= -1) {
				item.setFmasterid(whReceive.getFid());
				whReceivedetailService.insert(item, userId);
			} else {
				// 如果原单存在的，则用新分录值更新原分录的值
				whReceivedetailService.update(item, userId);
				// 在新明细中存在，则更新后不再关注
				itemIdList.remove(itemIdList.indexOf(item.getFid()));
			}
		}
		// 删除原单存在的分录，而在新分录列表不存在的
		// itemIdList中到现在仍存在的，就是没处理过的，也就是新的明细里已经不存在的
		for (Long item : itemIdList) {
			whReceivedetailService.delete(item);
		}
		return LanderResult.ok(data);
	}

	private LanderResult updateValidate(WhReceive whReceive) {
		TWhReceive tWhReceive = tWhReceiveDao.selectByPrimaryKey(whReceive.getFid());
		if (tWhReceive == null) {			
			return new LanderResult(404,"收货单信息未找到，请刷新后重试。",null);
		}
		if (tWhReceive.getFstateid()!=0&&tWhReceive.getFstateid()!=1){
			
		}
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号; 否则要检查单号有无重复
		if (isAutoNumber.equalsIgnoreCase("0")) {// 非自动生成单号
			TWhReceiveQuery example = new TWhReceiveQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whReceive.getFnumber());
			criteria.andFidNotEqualTo(whReceive.getFid());
			// 检查编码不可重复
			List<TWhReceive> countByExample = tWhReceiveDao.selectByExample(example);
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
//		for (TWhReceivedetail item : whReceive.gettWhReceivedetails()) {
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
//		TWhReceivedetailQuery example=new TWhReceivedetailQuery();
//		com.lander.wh.pojo.TWhReceivedetailQuery.Criteria createCriteria = example.createCriteria();
//		createCriteria.andFmasteridEqualTo(whReceive.getFid());
//		List<TWhReceivedetail> selectByExample = tWhReceivedetailDao.selectByExample(example);
//		for (TWhReceivedetail item : selectByExample) {
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
		
		TWhReceivedetailQuery example = new TWhReceivedetailQuery();
		com.lander.wh.pojo.TWhReceivedetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(fid);

		List<TWhReceivedetail> selectByExample = tWhReceivedetailDao.selectByExample(example);
		for (TWhReceivedetail orderitem : selectByExample) {
			whReceivedetailService.delete(orderitem.getFid());
		}
		tWhReceiveDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	private LanderResult deleteValidate(long fid) throws SysException {
		TWhReceive tWhReceive = tWhReceiveDao.selectByPrimaryKey(fid);
		if (tWhReceive == null) {	
			throw new SysException("400", "收货单信息未找到，请刷新后重试");			
		}
		if (tWhReceive.getFstateid()!=0&&tWhReceive.getFstateid()!=1){
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
				TWhReceive selectByPrimaryKey = tWhReceiveDao.selectByPrimaryKey(id);
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
				List<TWhReceivedetail> list = whReceivedetailService.list(id);
				if (list.size() == 0) {
					throw new SysException("400", "审核失败，收货单明细为空。");
				}
				for (TWhReceivedetail item : list) {
					if (item.getFqty().equals(0)) {
						throw new SysException("400", "审核失败，收货单物料数量不能为0。");
					}
				}
				// 更新状态
				selectByPrimaryKey.setFstateid(2);
				selectByPrimaryKey.setFauditdatetime(new Date());
				selectByPrimaryKey.setFauditman(userId);
				selectByPrimaryKey.setFauditmemo("");
				tWhReceiveDao.updateByPrimaryKey(selectByPrimaryKey);
				return LanderResult.ok();
	}

	@Override
	public LanderResult UnAudit(long id, String userId) throws SysException {
		// 检查单据是否符合可审核
				// 1.单据必须存在
		TWhReceive selectByPrimaryKey = tWhReceiveDao.selectByPrimaryKey(id);
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
		List<TWhReceivedetail> list = whReceivedetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "反审核失败，收货单明细为空。");
		}

		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhReceiveDao.updateByPrimaryKey(selectByPrimaryKey);
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
		TWhReceive selectByPrimaryKey = tWhReceiveDao.selectByPrimaryKey(id);
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
		List<TWhReceivedetail> list = whReceivedetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "提交失败，收货单明细为空。");
		}
		for (TWhReceivedetail item : list) {
			if (item.getFqty().equals(0)) {
				throw new SysException("400", "提交失败，物料数量不能为0。");
			}
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhReceiveDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult UnPost(long id) throws SysException {
		// 检查单据是否符合可反提交
		// 1.单据必须存在
		TWhReceive selectByPrimaryKey = tWhReceiveDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反提交失败，收货单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 1) {
			throw new SysException("400", "反提交失败，收货单不是提交状态。");
		}
		
		// 更新状态
		selectByPrimaryKey.setFstateid(0);
		tWhReceiveDao.updateByPrimaryKey(selectByPrimaryKey);
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
