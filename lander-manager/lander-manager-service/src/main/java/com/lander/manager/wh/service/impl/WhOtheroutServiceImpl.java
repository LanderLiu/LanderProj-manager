package com.lander.manager.wh.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.TBdDeptDao;
import com.lander.bd.dao.TBdWarehouseDao;
import com.lander.bd.pojo.TBdDept;
import com.lander.bd.pojo.TBdWarehouse;
import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.common.utils.StringUtil;
import com.lander.manager.bd.service.BdInvnumberService;
import com.lander.manager.wh.service.WhOtheroutService;
import com.lander.manager.wh.service.WhOtheroutdetailService;
import com.lander.manager.sys.service.SysSetService;
import com.lander.wh.dao.TWhOtheroutDao;
import com.lander.wh.dao.TWhOtheroutdetailDao;
import com.lander.wh.pojo.WhOtherout;
import com.lander.wh.pojo.WhOtheroutQuery;
import com.lander.wh.pojo.WhOtheroutVo;
import com.lander.wh.pojo.TWhOtherout;
import com.lander.wh.pojo.TWhOtheroutQuery;
import com.lander.wh.pojo.TWhOtheroutQuery.Criteria;
import com.lander.wh.pojo.TWhOtheroutdetail;
import com.lander.wh.pojo.TWhOtheroutdetailQuery;

@Service
public class WhOtheroutServiceImpl implements WhOtheroutService {
	@Autowired
	private TWhOtheroutDao tWhOtheroutDao;
	@Autowired
	private TWhOtheroutdetailDao tWhOtheroutdetailDao;
	@Autowired
	private WhOtheroutdetailService whOtheroutdetailService;
	@Autowired
	private SysSetService sysSetService;
	@Autowired
	private BdInvnumberService bdInvnumberService;
	@Autowired
	private TBdDeptDao tbdDeptDao;
	@Autowired
	private TBdWarehouseDao tbdWarehouseDao;
	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			WhOtheroutQuery whOtheroutQuery) {

		TWhOtheroutQuery query = new TWhOtheroutQuery();
		Criteria criteria = query.createCriteria();
		if (!StringUtil.isEmpty(whOtheroutQuery.getNumber())) {
			criteria.andFnumberLike("%" + whOtheroutQuery.getNumber() + "%");
		}
		if (!StringUtil.isEmpty(whOtheroutQuery.getDeptname())) {
			criteria.andFdeptnameLike("%" + whOtheroutQuery.getDeptname() + "%");
		}
		if (whOtheroutQuery.getWarehouseId() != null && whOtheroutQuery.getWarehouseId() != -1) {
			criteria.andFwarehouseidEqualTo(whOtheroutQuery.getWarehouseId());
		}
		if (whOtheroutQuery.getStateId() != null && whOtheroutQuery.getStateId() != -1) {
			criteria.andFstateidEqualTo(whOtheroutQuery.getStateId());
		}
		if (whOtheroutQuery.getBizDatetimeFrom()!=null){
			criteria.andFbizdateGreaterThanOrEqualTo(whOtheroutQuery.getBizDatetimeFrom());
		}
		if (whOtheroutQuery.getBizDatetimeTo()!=null){
			criteria.andFbizdateLessThanOrEqualTo(whOtheroutQuery.getBizDatetimeTo());
		}		

		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhOtherout> list = tWhOtheroutDao.selectByExample(query);
		
		List<WhOtheroutVo> listVo=new ArrayList<WhOtheroutVo>();
		for(TWhOtherout item :list){
			WhOtheroutVo vo=new WhOtheroutVo();
			vo.settWhOtherout(item);
			vo.setFid(item.getFid());
			
			listVo.add(vo);
		}
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(listVo);

		result.setTotal(tWhOtheroutDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TWhOtherout whOtherout, String userId) throws SysException,Exception {
		String isAutoNumber = sysSetService.getByCode("02");
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			String FLeaderStr = "WU";
			int FCategoryId = 1;
			int flowLong = 3;
			String getFlow = bdInvnumberService.GetFlow(FLeaderStr, FCategoryId, userId, flowLong);
			whOtherout.setFnumber(getFlow);
		} else {// 不自动生成单号
			TWhOtheroutQuery example = new TWhOtheroutQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whOtherout.getFnumber());
			// 检查编码不可重复
			int countByExample = tWhOtheroutDao.countByExample(example);
			if (countByExample > 0) {
				throw new ServiceException("400", "编码为：" + whOtherout.getFnumber() + "的其它出仓单已经存在，编码不可重复。");
			}
		}
		Long fid = IDUtils.genId();
		whOtherout.setFid(fid);

		whOtherout = (TWhOtherout) BizDateUtil.setDefaultProperty(whOtherout, userId, TWhOtherout.class);
		whOtherout.setFstateid(0);
		Integer data = tWhOtheroutDao.insert(whOtherout);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult insertWithItem(WhOtherout whOtherout, String userId) throws SysException, Exception {

		if (whOtherout.gettWhOtheroutdetails().size() == 0) {
			throw new SysException("410", "保存不成功,需要有一条以上订单明细。");
		}
		if (null != whOtherout.getFdeptid()) {
			TBdDept selectByPrimaryKey = tbdDeptDao.selectByPrimaryKey(whOtherout.getFdeptid());
			if (selectByPrimaryKey != null) {
				whOtherout.setFdeptname(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,部门资料无效。");
			}
		}
		if (null != whOtherout.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whOtherout.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whOtherout.setFwarehousename(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}	
		
		whOtherout.setFitemcount(whOtherout.gettWhOtheroutdetails().size());

		// 先插入主表
		insert(whOtherout, userId);
		// 再插入明细
		for (TWhOtheroutdetail item : whOtherout.gettWhOtheroutdetails()) {
			item.setFmasterid(whOtherout.getFid());
			whOtheroutdetailService.insert(item, userId);
		}
		return LanderResult.ok();
	}

	@Override
	public LanderResult update(TWhOtherout whOtherout, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		//updateValidate(whOtherout);

		whOtherout = (TWhOtherout) BizDateUtil.setModifyDefaultProperty(whOtherout, userId, TWhOtherout.class);
		whOtherout.setFstateid(0);
		Integer data = tWhOtheroutDao.updateByPrimaryKey(whOtherout);
		return LanderResult.ok(data);
	}

	/**
	 * 连同订单主表和明细一次性更新 判断明细有新的/有删除/有更改，要逐条处理
	 * @throws SysException 
	 */
	@Override
	public LanderResult updateWithItem(WhOtherout whOtherout, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException {

		LanderResult updateValidate = updateValidate(whOtherout);
		if (updateValidate.getStatus()!=200){
			return updateValidate;
		}

		TWhOtherout tWhOtherout = tWhOtheroutDao.selectByPrimaryKey(whOtherout.getFid());
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号;
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			// 单号取原值
			whOtherout.setFnumber(tWhOtherout.getFnumber());
		}
		whOtherout.setFcreatedatetime(tWhOtherout.getFcreatedatetime());
		whOtherout.setFcreateman(tWhOtherout.getFcreateman());
		whOtherout.setFitemcount(whOtherout.gettWhOtheroutdetails().size());

		if (null != whOtherout.getFdeptid()) {
			TBdDept selectByPrimaryKey = tbdDeptDao.selectByPrimaryKey(whOtherout.getFdeptid());
			if (selectByPrimaryKey != null) {
				whOtherout.setFdeptname(selectByPrimaryKey.getFname());
			}
		}
		if (null != whOtherout.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whOtherout.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whOtherout.setFwarehousename(selectByPrimaryKey.getFname());
			}else{
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}
		whOtherout = (WhOtherout) BizDateUtil.setModifyDefaultProperty(whOtherout, userId, TWhOtherout.class);
		whOtherout.setFstateid(0);
		Integer data = tWhOtheroutDao.updateByPrimaryKey(whOtherout);
		// 获取已经有分录列表,为下面比较作准备
		TWhOtheroutdetailQuery example = new TWhOtheroutdetailQuery();
		com.lander.wh.pojo.TWhOtheroutdetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(whOtherout.getFid());
		List<TWhOtheroutdetail> itemList = tWhOtheroutdetailDao.selectByExample(example);
		List<Long> itemIdList = new ArrayList<Long>();
		for (TWhOtheroutdetail item : itemList) {
			itemIdList.add(item.getFid());
		}
		// 更新分录，可能增加、删除或改变某条分录的一个值
		for (TWhOtheroutdetail item : whOtherout.gettWhOtheroutdetails()) {
			// 01如果原单分录中不存在，则新增
			if (itemIdList.indexOf(item.getFid()) <= -1) {
				item.setFmasterid(whOtherout.getFid());
				whOtheroutdetailService.insert(item, userId);
			} else {
				// 如果原单存在的，则用新分录值更新原分录的值
				whOtheroutdetailService.update(item, userId);
				// 在新明细中存在，则更新后不再关注
				itemIdList.remove(itemIdList.indexOf(item.getFid()));
			}
		}
		// 删除原单存在的分录，而在新分录列表不存在的
		// itemIdList中到现在仍存在的，就是没处理过的，也就是新的明细里已经不存在的
		for (Long item : itemIdList) {
			whOtheroutdetailService.delete(item);
		}
		return LanderResult.ok(data);
	}

	private LanderResult updateValidate(WhOtherout whOtherout) {
		TWhOtherout tWhOtherout = tWhOtheroutDao.selectByPrimaryKey(whOtherout.getFid());
		if (tWhOtherout == null) {			
			return new LanderResult(404,"其它出仓单信息未找到，请刷新后重试。",null);
		}
		if (tWhOtherout.getFstateid()!=0&&tWhOtherout.getFstateid()!=1){
			return new LanderResult(410,"其它出仓单必须是保存或提交状态才允许修改。",null);
		}
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号; 否则要检查单号有无重复
		if (isAutoNumber.equalsIgnoreCase("0")) {// 非自动生成单号
			TWhOtheroutQuery example = new TWhOtheroutQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whOtherout.getFnumber());
			criteria.andFidNotEqualTo(whOtherout.getFid());
			// 检查编码不可重复
			List<TWhOtherout> countByExample = tWhOtheroutDao.selectByExample(example);
			if (countByExample.size() > 0) {
				//throw new ServiceException("400",
				//		"其它出仓单:[" + countByExample.get(0).getFnumber() + "]的编码与您要修改的订单重复，编码不可重复。");
				
				return new LanderResult(400,"其它出仓单:[" + countByExample.get(0).getFnumber() + "]的编码与您要修改的其它出仓单重复，编码不可重复。",null);
			}
		}
		//在修改一张单前，先清空原有分录，所以不用做下面的较验
//		//要更新的明细，与原单内所有明细必须属于同一订单
//		long orderid = -1;
//		//各明细如果不为空，则必须来自同一订单
//		for (TWhOtheroutdetail item : whOtherout.gettWhOtheroutdetails()) {
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
//		TWhOtheroutdetailQuery example=new TWhOtheroutdetailQuery();
//		com.lander.wh.pojo.TWhOtheroutdetailQuery.Criteria createCriteria = example.createCriteria();
//		createCriteria.andFmasteridEqualTo(whOtherout.getFid());
//		List<TWhOtheroutdetail> selectByExample = tWhOtheroutdetailDao.selectByExample(example);
//		for (TWhOtheroutdetail item : selectByExample) {
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
		
		TWhOtheroutdetailQuery example = new TWhOtheroutdetailQuery();
		com.lander.wh.pojo.TWhOtheroutdetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(fid);

		List<TWhOtheroutdetail> selectByExample = tWhOtheroutdetailDao.selectByExample(example);
		for (TWhOtheroutdetail orderitem : selectByExample) {
			whOtheroutdetailService.delete(orderitem.getFid());
		}
		tWhOtheroutDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	private LanderResult deleteValidate(long fid) throws SysException {
		TWhOtherout tWhOtherout = tWhOtheroutDao.selectByPrimaryKey(fid);
		if (tWhOtherout == null) {	
			throw new SysException("400", "其它出仓单信息未找到，请刷新后重试");			
		}
		if (tWhOtherout.getFstateid()!=0&&tWhOtherout.getFstateid()!=1){
			throw new SysException("400", "其它出仓单必须是保存或提交状态。");		
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
				TWhOtherout selectByPrimaryKey = tWhOtheroutDao.selectByPrimaryKey(id);
				if (selectByPrimaryKey == null) {
					throw new SysException("400", "审核失败，其它出仓单信息不存在。");
				}

				// 2.单据必须是提交状态
				if (selectByPrimaryKey.getFstateid() != 1) {
					throw new SysException("400", "审核失败，其它出仓单不是提交状态。");
				}
				// 检查单据明细
				// 3.必须有明细
				// 4.明细数量必须都大于0
				List<TWhOtheroutdetail> list = whOtheroutdetailService.list(id);
				if (list.size() == 0) {
					throw new SysException("400", "审核失败，其它出仓单明细为空。");
				}
				for (TWhOtheroutdetail item : list) {
					if (item.getFqty().equals(0)) {
						throw new SysException("400", "审核失败，其它出仓单物料数量不能为0。");
					}
				}
				// 更新状态
				selectByPrimaryKey.setFstateid(2);
				selectByPrimaryKey.setFauditdatetime(new Date());
				selectByPrimaryKey.setFauditman(userId);
				selectByPrimaryKey.setFauditmemo("");
				tWhOtheroutDao.updateByPrimaryKey(selectByPrimaryKey);
				return LanderResult.ok();
	}

	@Override
	public LanderResult UnAudit(long id, String userId) throws SysException {
		// 检查单据是否符合可审核
				// 1.单据必须存在
		TWhOtherout selectByPrimaryKey = tWhOtheroutDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反审核失败，其它出仓单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 2) {
			throw new SysException("400", "反审核失败，其它出仓单不是审核状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TWhOtheroutdetail> list = whOtheroutdetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "反审核失败，其它出仓单明细为空。");
		}

		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhOtheroutDao.updateByPrimaryKey(selectByPrimaryKey);
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
		TWhOtherout selectByPrimaryKey = tWhOtheroutDao.selectByPrimaryKey(id);
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
		List<TWhOtheroutdetail> list = whOtheroutdetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "提交失败，其它出仓单明细为空。");
		}
		for (TWhOtheroutdetail item : list) {
			if (item.getFqty().equals(0)) {
				throw new SysException("400", "提交失败，物料数量不能为0。");
			}
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhOtheroutDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult UnPost(long id) throws SysException {
		// 检查单据是否符合可反提交
		// 1.单据必须存在
		TWhOtherout selectByPrimaryKey = tWhOtheroutDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反提交失败，其它出仓单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 1) {
			throw new SysException("400", "反提交失败，其它出仓单不是提交状态。");
		}
		
		// 更新状态
		selectByPrimaryKey.setFstateid(0);
		tWhOtheroutDao.updateByPrimaryKey(selectByPrimaryKey);
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
