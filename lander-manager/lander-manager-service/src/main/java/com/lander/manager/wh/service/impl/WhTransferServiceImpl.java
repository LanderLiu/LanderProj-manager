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
import com.lander.manager.wh.service.WhTransferService;
import com.lander.manager.wh.service.WhTransferdetailService;
import com.lander.manager.wh.service.WhTransferinService;
import com.lander.manager.wh.service.WhTransferoutService;
import com.lander.wh.dao.TWhTransferDao;
import com.lander.wh.dao.TWhTransferdetailDao;
import com.lander.wh.dao.TWhTransferinDao;
import com.lander.wh.dao.TWhTransferoutDao;
import com.lander.wh.pojo.TWhTransfer;
import com.lander.wh.pojo.TWhTransferQuery;
import com.lander.wh.pojo.TWhTransferQuery.Criteria;
import com.lander.wh.pojo.TWhTransferdetail;
import com.lander.wh.pojo.TWhTransferdetailQuery;
import com.lander.wh.pojo.TWhTransferin;
import com.lander.wh.pojo.TWhTransferinQuery;
import com.lander.wh.pojo.TWhTransferindetail;
import com.lander.wh.pojo.TWhTransferout;
import com.lander.wh.pojo.TWhTransferoutQuery;
import com.lander.wh.pojo.TWhTransferoutdetail;
import com.lander.wh.pojo.WhTransfer;
import com.lander.wh.pojo.WhTransferQuery;
import com.lander.wh.pojo.WhTransferVo;
import com.lander.wh.pojo.WhTransferin;
import com.lander.wh.pojo.WhTransferout;

@Service
public class WhTransferServiceImpl implements WhTransferService {
	@Autowired
	private TWhTransferDao tWhTransferDao;
	@Autowired
	private TWhTransferdetailDao tWhTransferdetailDao;
	@Autowired
	private WhTransferdetailService whTransferdetailService;
	@Autowired
	private SysSetService sysSetService;
	@Autowired
	private BdInvnumberService bdInvnumberService;
	@Autowired
	private TBdWarehouseDao tbdWarehouseDao;
	@Autowired
	private TWhTransferoutDao tWhTransferoutDao;
	@Autowired
	private WhTransferoutService whTransferoutService;
	@Autowired
	private WhTransferinService whTransferinService;
	@Autowired
	private TWhTransferinDao tWhTransferinDao;
	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			WhTransferQuery whTransferQuery) {

		TWhTransferQuery query = new TWhTransferQuery();
		Criteria criteria = query.createCriteria();
		if (!StringUtil.isEmpty(whTransferQuery.getNumber())) {
			criteria.andFnumberLike("%" + whTransferQuery.getNumber() + "%");
		}
		if (whTransferQuery.getWarehousefromId() != null && whTransferQuery.getWarehousefromId() != -1) {
			criteria.andFwarehouseidFromEqualTo(whTransferQuery.getWarehousefromId());
		}
		if (whTransferQuery.getWarehousetoId() != null && whTransferQuery.getWarehousetoId() != -1) {
			criteria.andFwarehouseidToEqualTo(whTransferQuery.getWarehousetoId());
		}
		if (whTransferQuery.getStateId() != null && whTransferQuery.getStateId() != -1) {
			criteria.andFstateidEqualTo(whTransferQuery.getStateId());
		}
		if (whTransferQuery.getBizDatetimeFrom()!=null){
			criteria.andFbizdateGreaterThanOrEqualTo(whTransferQuery.getBizDatetimeFrom());
		}
		if (whTransferQuery.getBizDatetimeTo()!=null){
			criteria.andFbizdateLessThanOrEqualTo(whTransferQuery.getBizDatetimeTo());
		}

		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhTransfer> list = tWhTransferDao.selectByExample(query);

		List<WhTransferVo> listVo = new ArrayList<WhTransferVo>();
		for (TWhTransfer item : list) {
			WhTransferVo vo = new WhTransferVo();
			vo.settWhTransfer(item);
			vo.setFid(item.getFid());

			listVo.add(vo);
		}
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(listVo);

		result.setTotal(tWhTransferDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TWhTransfer whTransfer, String userId) throws SysException, Exception {
		String isAutoNumber = sysSetService.getByCode("03");
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			String FLeaderStr = "WT";
			int FCategoryId = 1;
			int flowLong = 3;
			String getFlow = bdInvnumberService.GetFlow(FLeaderStr, FCategoryId, userId, flowLong);
			whTransfer.setFnumber(getFlow);
		} else {// 不自动生成单号
			TWhTransferQuery example = new TWhTransferQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whTransfer.getFnumber());
			// 检查编码不可重复
			int countByExample = tWhTransferDao.countByExample(example);
			if (countByExample > 0) {
				throw new ServiceException("400", "编码为：" + whTransfer.getFnumber() + "的库存调拨单已经存在，编码不可重复。");
			}
		}
		Long fid = IDUtils.genId();
		whTransfer.setFid(fid);

		whTransfer = (TWhTransfer) BizDateUtil.setDefaultProperty(whTransfer, userId, TWhTransfer.class);
		whTransfer.setFstateid(0);
		Integer data = tWhTransferDao.insert(whTransfer);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult insertWithItem(WhTransfer whTransfer, String userId) throws SysException, Exception {

		if (whTransfer.gettWhTransferdetails().size() == 0) {
			throw new SysException("410", "保存不成功,需要有一条以上订单明细。");
		}

		if (null != whTransfer.getFwarehouseidFrom()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whTransfer.getFwarehouseidFrom());
			if (selectByPrimaryKey != null) {
				whTransfer.setFwarehousenameFrom(selectByPrimaryKey.getFname());
			} else {
				throw new SysException("420", "保存不成功,调出仓资料无效。");
			}
		}
		if (null != whTransfer.getFwarehouseidTo()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whTransfer.getFwarehouseidTo());
			if (selectByPrimaryKey != null) {
				whTransfer.setFwarehousenameTo(selectByPrimaryKey.getFname());
			} else {
				throw new SysException("420", "保存不成功,调入仓资料无效。");
			}
		}
		whTransfer.setFitemcount(whTransfer.gettWhTransferdetails().size());

		// 先插入主表
		insert(whTransfer, userId);
		// 再插入明细
		for (TWhTransferdetail item : whTransfer.gettWhTransferdetails()) {
			item.setFmasterid(whTransfer.getFid());
			whTransferdetailService.insert(item, userId);
		}
		return LanderResult.ok();
	}

	@Override
	public LanderResult update(TWhTransfer whTransfer, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// updateValidate(whTransfer);

		whTransfer = (TWhTransfer) BizDateUtil.setModifyDefaultProperty(whTransfer, userId, TWhTransfer.class);
		whTransfer.setFstateid(0);
		Integer data = tWhTransferDao.updateByPrimaryKey(whTransfer);
		return LanderResult.ok(data);
	}

	/**
	 * 连同订单主表和明细一次性更新 判断明细有新的/有删除/有更改，要逐条处理
	 * 
	 * @throws SysException
	 */
	@Override
	public LanderResult updateWithItem(WhTransfer whTransfer, String userId)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, SysException {

		LanderResult updateValidate = updateValidate(whTransfer);
		if (updateValidate.getStatus() != 200) {
			return updateValidate;
		}

		TWhTransfer tWhTransfer = tWhTransferDao.selectByPrimaryKey(whTransfer.getFid());
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号;
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			// 单号取原值
			whTransfer.setFnumber(tWhTransfer.getFnumber());
		}
		whTransfer.setFcreatedatetime(tWhTransfer.getFcreatedatetime());
		whTransfer.setFcreateman(tWhTransfer.getFcreateman());
		whTransfer.setFitemcount(whTransfer.gettWhTransferdetails().size());

		if (null != whTransfer.getFwarehouseidFrom()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whTransfer.getFwarehouseidFrom());
			if (selectByPrimaryKey != null) {
				whTransfer.setFwarehousenameFrom(selectByPrimaryKey.getFname());
			} else {
				throw new SysException("420", "保存不成功,调出仓资料无效。");
			}
		}
		if (null != whTransfer.getFwarehouseidTo()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whTransfer.getFwarehouseidTo());
			if (selectByPrimaryKey != null) {
				whTransfer.setFwarehousenameTo(selectByPrimaryKey.getFname());
			} else {
				throw new SysException("420", "保存不成功,调入仓资料无效。");
			}
		}
		whTransfer = (WhTransfer) BizDateUtil.setModifyDefaultProperty(whTransfer, userId, TWhTransfer.class);
		whTransfer.setFstateid(0);
		Integer data = tWhTransferDao.updateByPrimaryKey(whTransfer);
		// 获取已经有分录列表,为下面比较作准备
		TWhTransferdetailQuery example = new TWhTransferdetailQuery();
		com.lander.wh.pojo.TWhTransferdetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(whTransfer.getFid());
		List<TWhTransferdetail> itemList = tWhTransferdetailDao.selectByExample(example);
		List<Long> itemIdList = new ArrayList<Long>();
		for (TWhTransferdetail item : itemList) {
			itemIdList.add(item.getFid());
		}
		// 更新分录，可能增加、删除或改变某条分录的一个值
		for (TWhTransferdetail item : whTransfer.gettWhTransferdetails()) {
			// 01如果原单分录中不存在，则新增
			if (itemIdList.indexOf(item.getFid()) <= -1) {
				item.setFmasterid(whTransfer.getFid());
				whTransferdetailService.insert(item, userId);
			} else {
				// 如果原单存在的，则用新分录值更新原分录的值
				whTransferdetailService.update(item, userId);
				// 在新明细中存在，则更新后不再关注
				itemIdList.remove(itemIdList.indexOf(item.getFid()));
			}
		}
		// 删除原单存在的分录，而在新分录列表不存在的
		// itemIdList中到现在仍存在的，就是没处理过的，也就是新的明细里已经不存在的
		for (Long item : itemIdList) {
			whTransferdetailService.delete(item);
		}
		return LanderResult.ok(data);
	}

	private LanderResult updateValidate(WhTransfer whTransfer) {
		TWhTransfer tWhTransfer = tWhTransferDao.selectByPrimaryKey(whTransfer.getFid());
		if (tWhTransfer == null) {
			return new LanderResult(404, "库存调拨单信息未找到，请刷新后重试。", null);
		}
		if (tWhTransfer.getFstateid() != 0 && tWhTransfer.getFstateid() != 1) {
			return new LanderResult(410, "库存调拨单必须是保存或提交状态才允许修改。", null);
		}
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号; 否则要检查单号有无重复
		if (isAutoNumber.equalsIgnoreCase("0")) {// 非自动生成单号
			TWhTransferQuery example = new TWhTransferQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whTransfer.getFnumber());
			criteria.andFidNotEqualTo(whTransfer.getFid());
			// 检查编码不可重复
			List<TWhTransfer> countByExample = tWhTransferDao.selectByExample(example);
			if (countByExample.size() > 0) {
				// throw new ServiceException("400",
				// "采购订单:[" + countByExample.get(0).getFnumber() +
				// "]的编码与您要修改的订单重复，编码不可重复。");

				return new LanderResult(400,
						"库存调拨单:[" + countByExample.get(0).getFnumber() + "]的编码与您要修改的库存调拨单重复，编码不可重复。", null);
			}
		}
		// 在修改一张单前，先清空原有分录，所以不用做下面的较验
		// //要更新的明细，与原单内所有明细必须属于同一订单
		// long orderid = -1;
		// //各明细如果不为空，则必须来自同一订单
		// for (TWhTransferdetail item : whTransfer.gettWhTransferdetails()) {
		// if (item.getForderitemid() != null) {//如果为空，则不关联订单，不用检查
		// TPrOrderitem selectByPrimaryKey =
		// tprOrderitemDao.selectByPrimaryKey(item.getForderitemid());
		// if (orderid != -1 && selectByPrimaryKey.getFmasterid() != orderid) {
		// //throw new ServiceException("410", "各明细必须来自同一订单");
		// return new LanderResult(410,"各明细必须来自同一订单",null);
		// } else {
		// orderid = selectByPrimaryKey.getFmasterid();
		// }
		// }
		// }
		// TWhTransferdetailQuery example=new TWhTransferdetailQuery();
		// com.lander.wh.pojo.TWhTransferdetailQuery.Criteria createCriteria =
		// example.createCriteria();
		// createCriteria.andFmasteridEqualTo(whTransfer.getFid());
		// List<TWhTransferdetail> selectByExample =
		// tWhTransferdetailDao.selectByExample(example);
		// for (TWhTransferdetail item : selectByExample) {
		// if (item.getForderitemid() != null) {//如果为空，则不关联订单，不用检查
		// TPrOrderitem selectByPrimaryKey =
		// tprOrderitemDao.selectByPrimaryKey(item.getForderitemid());
		// if (orderid != -1 && selectByPrimaryKey.getFmasterid() != orderid) {
		// //throw new ServiceException("410", "各明细必须来自同一订单");
		// return new LanderResult(410,"各明细必须来自同一订单",null);
		// } else {
		// orderid = selectByPrimaryKey.getFmasterid();
		// }
		// }
		// }
		return LanderResult.ok();
	}

	@Override
	public LanderResult delete(long fid) throws SysException {

		deleteValidate(fid);

		TWhTransferdetailQuery example = new TWhTransferdetailQuery();
		com.lander.wh.pojo.TWhTransferdetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(fid);

		List<TWhTransferdetail> selectByExample = tWhTransferdetailDao.selectByExample(example);
		for (TWhTransferdetail orderitem : selectByExample) {
			whTransferdetailService.delete(orderitem.getFid());
		}
		tWhTransferDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}

	private LanderResult deleteValidate(long fid) throws SysException {
		TWhTransfer tWhTransfer = tWhTransferDao.selectByPrimaryKey(fid);
		if (tWhTransfer == null) {
			throw new SysException("400", "库存调拨单信息未找到，请刷新后重试");
		}
		if (tWhTransfer.getFstateid() != 0 && tWhTransfer.getFstateid() != 1) {
			throw new SysException("400", "库存调拨单必须是保存或提交状态。");
		}

		return LanderResult.ok();
	}

	@Override
	public LanderResult delete(long[] fid) throws SysException {
		for (long i : fid) {
			delete(i);
		}
		return LanderResult.ok(fid.length);
	}

	@Override
	public LanderResult Audit(long id, String userId) throws SysException {
		// 检查单据是否符合可审核
		// 1.单据必须存在
		TWhTransfer selectByPrimaryKey = tWhTransferDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "审核失败，库存调拨单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 1) {
			throw new SysException("400", "审核失败，库存调拨单不是提交状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TWhTransferdetail> list = whTransferdetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "审核失败，库存调拨单明细为空。");
		}
		for (TWhTransferdetail item : list) {
			if (item.getFqty().equals(0)) {
				throw new SysException("400", "审核失败，库存调拨单物料数量不能为0。");
			}
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(2);
		selectByPrimaryKey.setFauditdatetime(new Date());
		selectByPrimaryKey.setFauditman(userId);
		selectByPrimaryKey.setFauditmemo("");
		tWhTransferDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult UnAudit(long id, String userId) throws SysException {
		// 检查单据是否符合可审核
		// 1.单据必须存在
		TWhTransfer selectByPrimaryKey = tWhTransferDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反审核失败，库存调拨单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 2) {
			throw new SysException("400", "反审核失败，库存调拨单不是审核状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TWhTransferdetail> list = whTransferdetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "反审核失败，库存调拨单明细为空。");
		}

		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhTransferDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult Audit(long[] fid, String userId) throws SysException {
		for (long i : fid) {
			Audit(i, userId);
		}
		return LanderResult.ok(fid.length);
	}

	@Override
	public LanderResult UnAudit(long[] fid, String userId) throws SysException {
		for (long i : fid) {
			UnAudit(i, userId);
		}
		return LanderResult.ok(fid.length);
	}

	@Override
	public LanderResult Post(long id) throws SysException {
		// 检查单据是否符合可提交
		// 1.单据必须存在
		TWhTransfer selectByPrimaryKey = tWhTransferDao.selectByPrimaryKey(id);
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
		List<TWhTransferdetail> list = whTransferdetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "提交失败，库存调拨单明细为空。");
		}
		for (TWhTransferdetail item : list) {
			if (item.getFqty().equals(0)) {
				throw new SysException("400", "提交失败，物料数量不能为0。");
			}
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhTransferDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult UnPost(long id) throws SysException {
		// 检查单据是否符合可反提交
		// 1.单据必须存在
		TWhTransfer selectByPrimaryKey = tWhTransferDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反提交失败，库存调拨单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 1) {
			throw new SysException("400", "反提交失败，库存调拨单不是提交状态。");
		}

		// 更新状态
		selectByPrimaryKey.setFstateid(0);
		tWhTransferDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult Post(long[] id) throws SysException {
		for (long i : id) {
			Post(i);
		}
		return LanderResult.ok(id.length);
	}

	@Override
	public LanderResult UnPost(long[] id) throws SysException {
		for (long i : id) {
			UnPost(i);
		}
		return LanderResult.ok(id.length);
	}

	/**
	 * 由库存调拨单下推生成调拨出仓单
	 * @throws Exception 
	 * @throws ServiceException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Override
	public LanderResult BuildTransferout(long id,String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ServiceException, Exception {
		// 检查单据是否符合下推
		//如果调拨出仓单的单据编码非自动生成，则不能由下推生成。
		String isAutoNumber = sysSetService.getByCode("04");
		if (isAutoNumber.equalsIgnoreCase("1")==false) {// 自动生成单号
			throw new SysException("400", "操作失败，下推生成调拨出库单时，调拨出库单单号必须设置为自动生成方式。");
		}
		// 1.单据必须审核状态
		TWhTransfer whTransferInfo = tWhTransferDao.selectByPrimaryKey(id);
		if (whTransferInfo == null) {
			throw new SysException("400", "操作失败，库存调拨单信息不存在，请刷新后重试。");
		}
		if (whTransferInfo.getFstateid() != 2) {
			throw new SysException("400", "操作失败，库存调拨单不是审核状态。");
		}
		TWhTransferoutQuery example = new TWhTransferoutQuery();
		com.lander.wh.pojo.TWhTransferoutQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFsourcetypeidEqualTo(1);
		createCriteria.andFtransferidEqualTo(id);
		// 2.单据不存在下游调拨出库单
		List<TWhTransferout> selectByExample = tWhTransferoutDao.selectByExample(example);
		if (selectByExample.size() > 0) {
			throw new SysException("500", "关联的调拨出仓单已经存在,单号：" + selectByExample.get(0).getFnumber() + ",不能重复生成。");
		}
		TWhTransferdetailQuery tWhTransferdetailQuery=new TWhTransferdetailQuery();
		com.lander.wh.pojo.TWhTransferdetailQuery.Criteria createCriteria2 = tWhTransferdetailQuery.createCriteria();
		createCriteria2.andFmasteridEqualTo(id);
		// 下推后是保存状态		
		
		WhTransferout whTransferout =new WhTransferout(); 
		whTransferout.setFbizdate(new Date());
		whTransferout.setFid(IDUtils.genId());		
		whTransferout.setFmemo("");
		whTransferout.setFsourcetypeid(1);
		whTransferout.setFtransferid(id);
		whTransferout.setFwarehouseid(whTransferInfo.getFwarehouseidFrom());
		whTransferout.setFwarehousename(whTransferInfo.getFwarehousenameFrom());
		
		List<TWhTransferoutdetail> tWhTransferoutdetails=new ArrayList<TWhTransferoutdetail>();
		List<TWhTransferdetail> tWhTransferdetails = tWhTransferdetailDao.selectByExample(tWhTransferdetailQuery);
		for (TWhTransferdetail tWhTransferdetail:tWhTransferdetails){
			TWhTransferoutdetail transferoutdetail=new TWhTransferoutdetail();
			transferoutdetail.setFid(IDUtils.genId());
			transferoutdetail.setFmasterid(whTransferout.getFid());
			transferoutdetail.setFmaterialid(tWhTransferdetail.getFmaterialid());
			transferoutdetail.setFmaterialname(tWhTransferdetail.getFmaterialname());
			transferoutdetail.setFmaterialnumber(tWhTransferdetail.getFmaterialnumber());
			transferoutdetail.setFmaterialspecification(tWhTransferdetail.getFmaterialspecification());
			transferoutdetail.setFmemo("");
			transferoutdetail.setFqty(tWhTransferdetail.getFqty());
			transferoutdetail.setFtransferitemid(tWhTransferdetail.getFid());
			transferoutdetail.setFunit(tWhTransferdetail.getFunit());
			tWhTransferoutdetails.add(transferoutdetail);
		}
		whTransferout.settWhTransferoutdetails(tWhTransferoutdetails);
		whTransferoutService.insertWithItem(whTransferout, userId);
		return LanderResult.ok();
	}
	
	
	
	/**
	 * 由库存调拨单下推生成调拨出仓单
	 * @throws Exception 
	 * @throws ServiceException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Override
	public LanderResult BuildTransferin(long id,String userId) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, ServiceException, Exception {
		// 检查单据是否符合下推
		//如果调拨入仓单的单据编码非自动生成，则不能由下推生成。
		String isAutoNumber = sysSetService.getByCode("05");
		if (isAutoNumber.equalsIgnoreCase("1")==false) {// 自动生成单号
			throw new SysException("400", "操作失败，下推生成调拨出库单时，调拨出库单单号必须设置为自动生成方式。");
		}
		// 1.单据必须审核状态
		TWhTransfer whTransferInfo = tWhTransferDao.selectByPrimaryKey(id);
		if (whTransferInfo == null) {
			throw new SysException("400", "操作失败，库存调拨单信息不存在，请刷新后重试。");
		}
		if (whTransferInfo.getFstateid() != 2) {
			throw new SysException("400", "操作失败，库存调拨单不是审核状态。");
		}
		TWhTransferinQuery example = new TWhTransferinQuery();
		com.lander.wh.pojo.TWhTransferinQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFsourcetypeidEqualTo(1);
		createCriteria.andFtransferidEqualTo(id);
		// 2.单据不存在下游调拨出库单
		List<TWhTransferin> selectByExample = tWhTransferinDao.selectByExample(example);
		if (selectByExample.size() > 0) {
			throw new SysException("500", "关联的调拨入仓单已经存在,单号：" + selectByExample.get(0).getFnumber() + ",不能重复生成。");
		}
		TWhTransferdetailQuery tWhTransferdetailQuery=new TWhTransferdetailQuery();
		com.lander.wh.pojo.TWhTransferdetailQuery.Criteria createCriteria2 = tWhTransferdetailQuery.createCriteria();
		createCriteria2.andFmasteridEqualTo(id);
		// 下推后是保存状态		
		
		WhTransferin whTransferin =new WhTransferin(); 
		whTransferin.setFbizdate(new Date());
		whTransferin.setFid(IDUtils.genId());		
		whTransferin.setFmemo("");
		whTransferin.setFsourcetypeid(1);
		whTransferin.setFtransferid(id);
		whTransferin.setFwarehouseid(whTransferInfo.getFwarehouseidFrom());
		whTransferin.setFwarehousename(whTransferInfo.getFwarehousenameFrom());
		
		List<TWhTransferindetail> tWhTransferindetails=new ArrayList<TWhTransferindetail>();
		List<TWhTransferdetail> tWhTransferdetails = tWhTransferdetailDao.selectByExample(tWhTransferdetailQuery);
		for (TWhTransferdetail tWhTransferdetail:tWhTransferdetails){
			TWhTransferindetail transferoutdetail=new TWhTransferindetail();
			transferoutdetail.setFid(IDUtils.genId());
			transferoutdetail.setFmasterid(whTransferin.getFid());
			transferoutdetail.setFmaterialid(tWhTransferdetail.getFmaterialid());
			transferoutdetail.setFmaterialname(tWhTransferdetail.getFmaterialname());
			transferoutdetail.setFmaterialnumber(tWhTransferdetail.getFmaterialnumber());
			transferoutdetail.setFmaterialspecification(tWhTransferdetail.getFmaterialspecification());
			transferoutdetail.setFmemo("");
			transferoutdetail.setFqty(tWhTransferdetail.getFqty());
			transferoutdetail.setFtransferitemid(tWhTransferdetail.getFid());
			transferoutdetail.setFunit(tWhTransferdetail.getFunit());
			tWhTransferindetails.add(transferoutdetail);
		}
		whTransferin.settWhTransferindetails(tWhTransferindetails);
		whTransferinService.insertWithItem(whTransferin, userId);
		return LanderResult.ok();
	}
}
