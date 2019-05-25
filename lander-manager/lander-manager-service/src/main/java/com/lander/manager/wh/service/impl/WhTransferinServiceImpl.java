package com.lander.manager.wh.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.TBdWarehouseDao;
import com.lander.bd.pojo.TBdWarehouse;
import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.common.utils.StringUtil;
import com.lander.manager.bd.service.BdInvnumberService;
import com.lander.manager.sys.service.SysSetService;
import com.lander.manager.wh.service.WhTransferinService;
import com.lander.manager.wh.service.WhTransferindetailService;
import com.lander.wh.dao.TWhTransferinDao;
import com.lander.wh.dao.TWhTransferindetailDao;
import com.lander.wh.pojo.TWhTransferin;
import com.lander.wh.pojo.TWhTransferinQuery;
import com.lander.wh.pojo.TWhTransferinQuery.Criteria;
import com.lander.wh.pojo.TWhTransferindetail;
import com.lander.wh.pojo.TWhTransferindetailQuery;
import com.lander.wh.pojo.WhTransferin;
import com.lander.wh.pojo.WhTransferinQuery;
import com.lander.wh.pojo.WhTransferinVo;

@Service
public class WhTransferinServiceImpl implements WhTransferinService {
	@Autowired
	private TWhTransferinDao tWhTransferinDao;
	@Autowired
	private TWhTransferindetailDao tWhTransferindetailDao;
	@Autowired
	private WhTransferindetailService whTransferindetailService;
	@Autowired
	private SysSetService sysSetService;
	@Autowired
	private BdInvnumberService bdInvnumberService;
	@Autowired
	private TBdWarehouseDao tbdWarehouseDao;
	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			WhTransferinQuery whTransferinQuery) {

		TWhTransferinQuery query = new TWhTransferinQuery();
		Criteria criteria = query.createCriteria();
		if (!StringUtil.isEmpty(whTransferinQuery.getNumber())) {
			criteria.andFnumberLike("%" + whTransferinQuery.getNumber() + "%");
		}
		
		if (whTransferinQuery.getWarehouseId() != null && whTransferinQuery.getWarehouseId() != -1) {
			criteria.andFwarehouseidEqualTo(whTransferinQuery.getWarehouseId());
		}
		if (whTransferinQuery.getStateId() != null && whTransferinQuery.getStateId() != -1) {
			criteria.andFstateidEqualTo(whTransferinQuery.getStateId());
		}
		if (whTransferinQuery.getBizDatetimeFrom()!=null){
			criteria.andFbizdateGreaterThanOrEqualTo(whTransferinQuery.getBizDatetimeFrom());
		}
		if (whTransferinQuery.getBizDatetimeTo()!=null){
			criteria.andFbizdateLessThanOrEqualTo(whTransferinQuery.getBizDatetimeTo());
		}		
		

		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhTransferin> list = tWhTransferinDao.selectByExample(query);
		
		List<WhTransferinVo> listVo=new ArrayList<WhTransferinVo>();
		for(TWhTransferin item :list){
			WhTransferinVo vo=new WhTransferinVo();
			vo.settWhTransferin(item);
			vo.setFid(item.getFid());
			
			listVo.add(vo);
		}
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(listVo);

		result.setTotal(tWhTransferinDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TWhTransferin whTransferin, String userId) throws SysException,Exception {
		String isAutoNumber = sysSetService.getByCode("05");
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			String FLeaderStr = "WTI";
			int FCategoryId = 1;
			int flowLong = 3;
			String getFlow = bdInvnumberService.GetFlow(FLeaderStr, FCategoryId, userId, flowLong);
			whTransferin.setFnumber(getFlow);
		} else {// 不自动生成单号
			TWhTransferinQuery example = new TWhTransferinQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whTransferin.getFnumber());
			// 检查编码不可重复
			int countByExample = tWhTransferinDao.countByExample(example);
			if (countByExample > 0) {
				throw new ServiceException("400", "编码为：" + whTransferin.getFnumber() + "的调拨入仓单已经存在，编码不可重复。");
			}
		}
		Long fid = IDUtils.genId();
		whTransferin.setFid(fid);

		whTransferin = (TWhTransferin) BizDateUtil.setDefaultProperty(whTransferin, userId, TWhTransferin.class);
		whTransferin.setFstateid(0);
		Integer data = tWhTransferinDao.insert(whTransferin);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult insertWithItem(WhTransferin whTransferin, String userId) throws SysException, Exception {

		if (whTransferin.gettWhTransferindetails().size() == 0) {
			throw new SysException("410", "保存不成功,需要有一条以上订单明细。");
		}
		
		if (null != whTransferin.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whTransferin.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whTransferin.setFwarehousename(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}	
		
		whTransferin.setFitemcount(whTransferin.gettWhTransferindetails().size());

		// 先插入主表
		insert(whTransferin, userId);
		// 再插入明细
		for (TWhTransferindetail item : whTransferin.gettWhTransferindetails()) {
			item.setFmasterid(whTransferin.getFid());
			whTransferindetailService.insert(item, userId);
		}
		return LanderResult.ok();
	}

	@Override
	public LanderResult update(TWhTransferin whTransferin, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		//updateValidate(whTransferin);

		whTransferin = (TWhTransferin) BizDateUtil.setModifyDefaultProperty(whTransferin, userId, TWhTransferin.class);
		whTransferin.setFstateid(0);
		Integer data = tWhTransferinDao.updateByPrimaryKey(whTransferin);
		return LanderResult.ok(data);
	}

	/**
	 * 连同订单主表和明细一次性更新 判断明细有新的/有删除/有更改，要逐条处理
	 * @throws SysException 
	 */
	@Override
	public LanderResult updateWithItem(WhTransferin whTransferin, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException {

		LanderResult updateValidate = updateValidate(whTransferin);
		if (updateValidate.getStatus()!=200){
			return updateValidate;
		}

		TWhTransferin tWhTransferin = tWhTransferinDao.selectByPrimaryKey(whTransferin.getFid());
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号;
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			// 单号取原值
			whTransferin.setFnumber(tWhTransferin.getFnumber());
		}
		whTransferin.setFcreatedatetime(tWhTransferin.getFcreatedatetime());
		whTransferin.setFcreateman(tWhTransferin.getFcreateman());
		whTransferin.setFitemcount(whTransferin.gettWhTransferindetails().size());

		
		if (null != whTransferin.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whTransferin.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whTransferin.setFwarehousename(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}
		whTransferin = (WhTransferin) BizDateUtil.setModifyDefaultProperty(whTransferin, userId, TWhTransferin.class);
		whTransferin.setFstateid(0);
		Integer data = tWhTransferinDao.updateByPrimaryKey(whTransferin);
		// 获取已经有分录列表,为下面比较作准备
		TWhTransferindetailQuery example = new TWhTransferindetailQuery();
		com.lander.wh.pojo.TWhTransferindetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(whTransferin.getFid());
		List<TWhTransferindetail> itemList = tWhTransferindetailDao.selectByExample(example);
		List<Long> itemIdList = new ArrayList<Long>();
		for (TWhTransferindetail item : itemList) {
			itemIdList.add(item.getFid());
		}
		// 更新分录，可能增加、删除或改变某条分录的一个值
		for (TWhTransferindetail item : whTransferin.gettWhTransferindetails()) {
			// 01如果原单分录中不存在，则新增
			if (itemIdList.indexOf(item.getFid()) <= -1) {
				item.setFmasterid(whTransferin.getFid());
				whTransferindetailService.insert(item, userId);
			} else {
				// 如果原单存在的，则用新分录值更新原分录的值
				whTransferindetailService.update(item, userId);
				// 在新明细中存在，则更新后不再关注
				itemIdList.remove(itemIdList.indexOf(item.getFid()));
			}
		}
		// 删除原单存在的分录，而在新分录列表不存在的
		// itemIdList中到现在仍存在的，就是没处理过的，也就是新的明细里已经不存在的
		for (Long item : itemIdList) {
			whTransferindetailService.delete(item);
		}
		return LanderResult.ok(data);
	}

	private LanderResult updateValidate(WhTransferin whTransferin) {
		TWhTransferin tWhTransferin = tWhTransferinDao.selectByPrimaryKey(whTransferin.getFid());
		if (tWhTransferin == null) {			
			return new LanderResult(404,"调拨入仓单信息未找到，请刷新后重试。",null);
		}
		if (tWhTransferin.getFstateid()!=0&&tWhTransferin.getFstateid()!=1){
			return new LanderResult(410,"调拨入仓单必须是保存或提交状态才允许修改。",null);
		}
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号; 否则要检查单号有无重复
		if (isAutoNumber.equalsIgnoreCase("0")) {// 非自动生成单号
			TWhTransferinQuery example = new TWhTransferinQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whTransferin.getFnumber());
			criteria.andFidNotEqualTo(whTransferin.getFid());
			// 检查编码不可重复
			List<TWhTransferin> countByExample = tWhTransferinDao.selectByExample(example);
			if (countByExample.size() > 0) {
				//throw new ServiceException("400",
				//		"调拨入仓单:[" + countByExample.get(0).getFnumber() + "]的编码与您要修改的订单重复，编码不可重复。");
				
				return new LanderResult(400,"调拨入仓单:[" + countByExample.get(0).getFnumber() + "]的编码与您要修改的调拨入仓单重复，编码不可重复。",null);
			}
		}
		//在修改一张单前，先清空原有分录，所以不用做下面的较验
//		//要更新的明细，与原单内所有明细必须属于同一订单
//		long orderid = -1;
//		//各明细如果不为空，则必须来自同一订单
//		for (TWhTransferindetail item : whTransferin.gettWhTransferindetails()) {
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
//		TWhTransferindetailQuery example=new TWhTransferindetailQuery();
//		com.lander.wh.pojo.TWhTransferindetailQuery.Criteria createCriteria = example.createCriteria();
//		createCriteria.andFmasteridEqualTo(whTransferin.getFid());
//		List<TWhTransferindetail> selectByExample = tWhTransferindetailDao.selectByExample(example);
//		for (TWhTransferindetail item : selectByExample) {
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
		
		TWhTransferindetailQuery example = new TWhTransferindetailQuery();
		com.lander.wh.pojo.TWhTransferindetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(fid);

		List<TWhTransferindetail> selectByExample = tWhTransferindetailDao.selectByExample(example);
		for (TWhTransferindetail orderitem : selectByExample) {
			whTransferindetailService.delete(orderitem.getFid());
		}
		tWhTransferinDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	private LanderResult deleteValidate(long fid) throws SysException {
		TWhTransferin tWhTransferin = tWhTransferinDao.selectByPrimaryKey(fid);
		if (tWhTransferin == null) {	
			throw new SysException("400", "调拨入仓单信息未找到，请刷新后重试");			
		}
		if (tWhTransferin.getFstateid()!=0&&tWhTransferin.getFstateid()!=1){
			throw new SysException("400", "调拨入仓单必须是保存或提交状态。");		
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
				TWhTransferin selectByPrimaryKey = tWhTransferinDao.selectByPrimaryKey(id);
				if (selectByPrimaryKey == null) {
					throw new SysException("400", "审核失败，调拨入仓单信息不存在。");
				}

				// 2.单据必须是提交状态
				if (selectByPrimaryKey.getFstateid() != 1) {
					throw new SysException("400", "审核失败，调拨入仓单不是提交状态。");
				}
				// 检查单据明细
				// 3.必须有明细
				// 4.明细数量必须都大于0
				List<TWhTransferindetail> list = whTransferindetailService.list(id);
				if (list.size() == 0) {
					throw new SysException("400", "审核失败，调拨入仓单明细为空。");
				}
				for (TWhTransferindetail item : list) {
					if (item.getFqty().equals(0)) {
						throw new SysException("400", "审核失败，调拨入仓单物料数量不能为0。");
					}
				}
				// 更新状态
				selectByPrimaryKey.setFstateid(2);
				selectByPrimaryKey.setFauditdatetime(new Date());
				selectByPrimaryKey.setFauditman(userId);
				selectByPrimaryKey.setFauditmemo("");
				tWhTransferinDao.updateByPrimaryKey(selectByPrimaryKey);
				return LanderResult.ok();
	}

	@Override
	public LanderResult UnAudit(long id, String userId) throws SysException {
		// 检查单据是否符合可审核
				// 1.单据必须存在
		TWhTransferin selectByPrimaryKey = tWhTransferinDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反审核失败，调拨入仓单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 2) {
			throw new SysException("400", "反审核失败，调拨入仓单不是审核状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TWhTransferindetail> list = whTransferindetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "反审核失败，调拨入仓单明细为空。");
		}

		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhTransferinDao.updateByPrimaryKey(selectByPrimaryKey);
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
		TWhTransferin selectByPrimaryKey = tWhTransferinDao.selectByPrimaryKey(id);
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
		List<TWhTransferindetail> list = whTransferindetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "提交失败，调拨入仓单明细为空。");
		}
		for (TWhTransferindetail item : list) {
			if (item.getFqty().equals(0)) {
				throw new SysException("400", "提交失败，物料数量不能为0。");
			}
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhTransferinDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult UnPost(long id) throws SysException {
		// 检查单据是否符合可反提交
		// 1.单据必须存在
		TWhTransferin selectByPrimaryKey = tWhTransferinDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反提交失败，调拨入仓单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 1) {
			throw new SysException("400", "反提交失败，调拨入仓单不是提交状态。");
		}
		
		// 更新状态
		selectByPrimaryKey.setFstateid(0);
		tWhTransferinDao.updateByPrimaryKey(selectByPrimaryKey);
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
