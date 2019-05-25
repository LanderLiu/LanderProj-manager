package com.lander.manager.sale.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.TBdCustDao;
import com.lander.bd.pojo.TBdCust;
import com.lander.common.exception.ServiceException;
import com.lander.common.exception.SysException;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.common.utils.StringUtil;
import com.lander.manager.bd.service.BdInvnumberService;
import com.lander.manager.sale.service.SaleOrderService;
import com.lander.manager.sale.service.SaleOrderitemService;
import com.lander.manager.sys.service.SysSetService;
import com.lander.sale.dao.TSaleOrderDao;
import com.lander.sale.dao.TSaleOrderitemDao;
import com.lander.sale.pojo.SaleOrder;
import com.lander.sale.pojo.SaleOrderQuery;
import com.lander.sale.pojo.TSaleOrder;
import com.lander.sale.pojo.TSaleOrderQuery;
import com.lander.sale.pojo.TSaleOrderQuery.Criteria;
import com.lander.sale.pojo.TSaleOrderitem;
import com.lander.sale.pojo.TSaleOrderitemQuery;
import com.lander.wh.dao.TWhReceiveDao;
import com.lander.wh.dao.TWhReceivedetailDao;
import com.lander.wh.pojo.TWhReceiveQuery;
import com.lander.wh.pojo.TWhReceivedetailQuery;

@Service
public class SaleOrderServiceImpl implements SaleOrderService {
	@Autowired
	private TSaleOrderDao tSaleOrderDao;
	@Autowired
	private TSaleOrderitemDao tSaleOrderitemDao;
	@Autowired
	private SaleOrderitemService saleOrderitemService;
	@Autowired
	private SysSetService sysSetService;
	@Autowired
	private BdInvnumberService bdInvnumberService;
	@Autowired
	private TBdCustDao tbdCustDao;
	@Autowired
	private TWhReceivedetailDao twhReceivedetailDao;
	@Autowired
	private TWhReceiveDao twhReceiveDao;

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			SaleOrderQuery saleOrderQuery) {

		TSaleOrderQuery query = new TSaleOrderQuery();
		Criteria criteria = query.createCriteria();
		if (!StringUtil.isEmpty(saleOrderQuery.getNumber())) {
			criteria.andFnumberLike("%" + saleOrderQuery.getNumber() + "%");
		}
		if (!StringUtil.isEmpty(saleOrderQuery.getCustName())) {
			criteria.andFcustnameLike("%" + saleOrderQuery.getCustName() + "%");
		}
		if (saleOrderQuery.getBizDatetimeFrom() != null) {
			criteria.andFbizdatetimeGreaterThanOrEqualTo(saleOrderQuery.getBizDatetimeFrom());
		}
		if (saleOrderQuery.getBizDatetimeTo() != null) {
			criteria.andFbizdatetimeLessThanOrEqualTo(saleOrderQuery.getBizDatetimeTo());
		}
		if (saleOrderQuery.getStateId() != null && saleOrderQuery.getStateId() != -1) {
			criteria.andFstateidEqualTo(saleOrderQuery.getStateId());
		}
		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TSaleOrder> list = tSaleOrderDao.selectByExample(query);
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(list);

		result.setTotal(tSaleOrderDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TSaleOrder saleOrder, String userId) throws Exception {
		String isAutoNumber = sysSetService.getByCode("06");
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			String FLeaderStr = "SO";
			int FCategoryId = 1;
			int flowLong = 3;
			String getFlow = bdInvnumberService.GetFlow(FLeaderStr, FCategoryId, userId, flowLong);
			saleOrder.setFnumber(getFlow);
		} else {// 不自动生成单号
			TSaleOrderQuery example = new TSaleOrderQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(saleOrder.getFnumber());
			// 检查编码不可重复
			int countByExample = tSaleOrderDao.countByExample(example);
			if (countByExample > 0) {
				throw new ServiceException("400", "编码为：" + saleOrder.getFnumber() + "的销售订单已经存在，编码不可重复。");
			}
		}
		Long fid = IDUtils.genId();
		saleOrder.setFid(fid);

		saleOrder = (TSaleOrder) BizDateUtil.setDefaultProperty(saleOrder, userId, TSaleOrder.class);
		saleOrder.setFstateid(0);
		Integer data = tSaleOrderDao.insert(saleOrder);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult insertWithItem(SaleOrder saleOrder, String userId) throws Exception {

		if (saleOrder.gettSaleOrderitems().size() == 0) {
			throw new ServiceException("410", "保存不成功,需要有一条以上订单明细。");
		}
		if (null != saleOrder.getFcustid()) {
			TBdCust selectByPrimaryKey = tbdCustDao.selectByPrimaryKey(saleOrder.getFcustid());
			if (selectByPrimaryKey != null) {
				saleOrder.setFcustname(selectByPrimaryKey.getFname());
			} else {
				throw new ServiceException("420", "保存不成功,客户资料无效。");
			}
		}
		saleOrder.setFitemcount(saleOrder.gettSaleOrderitems().size());

		// 先插入主表
		insert(saleOrder, userId);
		// 再插入明细
		for (TSaleOrderitem item : saleOrder.gettSaleOrderitems()) {
			item.setFmasterid(saleOrder.getFid());
			saleOrderitemService.insert(item, userId);
		}
		return LanderResult.ok();
	}

	@Override
	public LanderResult update(TSaleOrder saleOrder, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException {

		updateValidate(saleOrder);

		saleOrder = (TSaleOrder) BizDateUtil.setModifyDefaultProperty(saleOrder, userId, TSaleOrder.class);
		saleOrder.setFstateid(1);
		Integer data = tSaleOrderDao.updateByPrimaryKey(saleOrder);
		return LanderResult.ok(data);
	}

	/**
	 * 连同订单主表和明细一次性更新 判断明细有新的/有删除/有更改，要逐条处理
	 * @throws SysException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 */
	@Override
	public LanderResult updateWithItem(SaleOrder saleOrder, String userId) throws  SysException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		updateValidate(saleOrder);
		TSaleOrder tSaleOrder = tSaleOrderDao.selectByPrimaryKey(saleOrder.getFid());
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号;
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			// 单号取原值
			saleOrder.setFnumber(tSaleOrder.getFnumber());
		}
		saleOrder.setFcreatedatetime(tSaleOrder.getFcreatedatetime());
		saleOrder.setFcreateman(tSaleOrder.getFcreateman());
		saleOrder.setFitemcount(saleOrder.gettSaleOrderitems().size());

		if (null != saleOrder.getFcustid()) {
			TBdCust selectByPrimaryKey = tbdCustDao.selectByPrimaryKey(saleOrder.getFcustid());
			if (selectByPrimaryKey != null) {
				saleOrder.setFcustname(selectByPrimaryKey.getFname());
			}
		}

		saleOrder = (SaleOrder) BizDateUtil.setModifyDefaultProperty(saleOrder, userId, TSaleOrder.class);
		saleOrder.setFstateid(0);
		Integer data = tSaleOrderDao.updateByPrimaryKey(saleOrder);
		// 获取已经有分录列表,为下面比较作准备
		TSaleOrderitemQuery example = new TSaleOrderitemQuery();
		com.lander.sale.pojo.TSaleOrderitemQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(saleOrder.getFid());
		List<TSaleOrderitem> itemList = tSaleOrderitemDao.selectByExample(example);
		List<Long> itemIdList = new ArrayList<Long>();
		for (TSaleOrderitem item : itemList) {
			itemIdList.add(item.getFid());
		}
		// 更新分录，可能增加、删除或改变某条分录的一个值
		for (TSaleOrderitem item : saleOrder.gettSaleOrderitems()) {
			// 01如果原单分录中不存在，则新增
			if (itemIdList.indexOf(item.getFid()) <= -1) {
				item.setFmasterid(saleOrder.getFid());
				saleOrderitemService.insert(item, userId);
			} else {
				// 如果原单存在的，则用新分录值更新原分录的值
				saleOrderitemService.update(item, userId);
				// 在新明细中存在，则更新后不再关注
				itemIdList.remove(itemIdList.indexOf(item.getFid()));
			}
		}
		// 删除原单存在的分录，而在新分录列表不存在的
		// itemIdList中到现在仍存在的，就是没处理过的，也就是新的明细里已经不存在的
		for (Long item : itemIdList) {
			saleOrderitemService.delete(item);
		}
		return LanderResult.ok(data);
	}

	private void updateValidate(TSaleOrder saleOrder) throws SysException {
		TSaleOrder tSaleOrder = tSaleOrderDao.selectByPrimaryKey(saleOrder.getFid());
		if (tSaleOrder == null) {
			throw new SysException("404", "销售订单信息未找到，请刷新后重试。");
		}
		if (tSaleOrder.getFstateid()!=0&&tSaleOrder.getFstateid()!=1) {
			throw new SysException("410", "只有保存或提交状态的单据才允许修改。");
		}
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号; 否则要检查单号有无重复
		if (isAutoNumber.equalsIgnoreCase("0")) {// 非自动生成单号
			TSaleOrderQuery example = new TSaleOrderQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(saleOrder.getFnumber());
			criteria.andFidNotEqualTo(saleOrder.getFid());
			// 检查编码不可重复
			List<TSaleOrder> countByExample = tSaleOrderDao.selectByExample(example);
			if (countByExample.size() > 0) {
				throw new SysException("400",
						"销售订单:[" + countByExample.get(0).getFnumber() + "]的编码与您要修改的订单重复，编码不可重复。");
			}
		}
	}

	@Override
	public LanderResult delete(long fid) throws SysException {
		deleteValidate(fid);
		TSaleOrderitemQuery example = new TSaleOrderitemQuery();
		com.lander.sale.pojo.TSaleOrderitemQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(fid);

		List<TSaleOrderitem> selectByExample = tSaleOrderitemDao.selectByExample(example);
		for (TSaleOrderitem orderitem : selectByExample) {
			saleOrderitemService.delete(orderitem.getFid());
		}
		tSaleOrderDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}
	
	private LanderResult deleteValidate(long fid) throws SysException {
		TSaleOrder tSaleOrder = tSaleOrderDao.selectByPrimaryKey(fid);
		if (tSaleOrder == null) {	
			throw new SysException("400", "订单信息未找到，请刷新后重试");			
		}
		if (tSaleOrder.getFstateid()!=0&&tSaleOrder.getFstateid()!=1){
			throw new SysException("400", "订单必须是保存或提交状态。");		
		}
		TWhReceiveQuery example=new TWhReceiveQuery();
		com.lander.wh.pojo.TWhReceiveQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andForderidEqualTo(fid);
		//如果存在下游收货单，则不允许删除
		int countByExample = twhReceiveDao.countByExample(example);
		if (countByExample>0){
			throw new SysException("410", "订单存在"+countByExample+"条下游单据，需要先清除下游单据才能删除本单。");		
		}
		return  LanderResult.ok();
	}
	
	@Override
	public LanderResult delete(long[] fid) throws SysException {
		System.out.println("*****************************进入delete(int[])***************************");
		for (long i : fid) {
			System.out.println("*****************************" + i + "***************************");
			delete(i);
		}
		return LanderResult.ok(fid.length);
	}

	@Override
	public LanderResult checkforbuild(long fid) {
		// 检查销售订单是否可用于生成收货单
		// 1.必须是审核状态
		TSaleOrder selectByPrimaryKey = tSaleOrderDao.selectByPrimaryKey(fid);
		if (selectByPrimaryKey == null) {
			return LanderResult.build(400, "订单信息不存在。");
		}
		if (selectByPrimaryKey.getFstateid() != 2) {
			return LanderResult.build(400, "订单不是审核状态。");
		}

		// 2.下查收货单，必须没有收过货,因为是整单生成，所以不允许；
		// 2.1找到订单的所有明细;
		// 2.2将每个明细在收货单中查找比对；
		TSaleOrderitemQuery example = new TSaleOrderitemQuery();
		com.lander.sale.pojo.TSaleOrderitemQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(fid);
		List<TSaleOrderitem> selectByExample = tSaleOrderitemDao.selectByExample(example);
		for (TSaleOrderitem item : selectByExample) {
			TWhReceivedetailQuery example1 = new TWhReceivedetailQuery();
			com.lander.wh.pojo.TWhReceivedetailQuery.Criteria createCriteria2 = example1.createCriteria();
			createCriteria2.andForderitemidEqualTo(item.getFid());
			int countByExample = twhReceivedetailDao.countByExample(example1);
			if (countByExample > 0) {
				return LanderResult.build(400,
						"物料：" + item.getFmaterialnumber() + "/" + item.getFmaterialname() + "已经存在收货记录，不能再以整单生成收货单。");
			}
		}
		return LanderResult.ok();
	}

	@Override
	public LanderResult Audit(long id, String userId) throws SysException {
		// 检查单据是否符合可审核
		// 1.单据必须存在
		TSaleOrder selectByPrimaryKey = tSaleOrderDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "审核失败，订单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 1) {
			throw new SysException("400", "审核失败，订单不是提交状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TSaleOrderitem> list = saleOrderitemService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "审核失败，订单明细为空。");
		}
		for (TSaleOrderitem item : list) {
			if (item.getFqty().equals(0)) {
				throw new SysException("400", "审核失败，物料数量不能为0。");
			}
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(2);
		selectByPrimaryKey.setFauditdatetime(new Date());
		selectByPrimaryKey.setFauditman(userId);
		selectByPrimaryKey.setFauditmemo("");
		tSaleOrderDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult UnAudit(long id, String userId) throws SysException {

		// 检查单据是否符合可审核
		// 1.单据必须存在
		TSaleOrder selectByPrimaryKey = tSaleOrderDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反审核失败，订单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 2) {
			throw new SysException("400", "反审核失败，订单不是审核状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TSaleOrderitem> list = saleOrderitemService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "反审核失败，订单明细为空。");
		}
		
		TWhReceiveQuery example=new TWhReceiveQuery();
		com.lander.wh.pojo.TWhReceiveQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andForderidEqualTo(id);
		//如果存在下游收货单，则不允许删除
		int countByExample = twhReceiveDao.countByExample(example);
		if (countByExample>0){
			throw new SysException("410", "订单存在"+countByExample+"条下游单据，需要先清除下游单据才能反审核本单。");		
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tSaleOrderDao.updateByPrimaryKey(selectByPrimaryKey);
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
		TSaleOrder selectByPrimaryKey = tSaleOrderDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "提交失败，订单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 0) {
			throw new SysException("400", "提交失败，订单不是保存状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TSaleOrderitem> list = saleOrderitemService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "提交失败，订单明细为空。");
		}
		for (TSaleOrderitem item : list) {
			if (item.getFqty().equals(0)) {
				throw new SysException("400", "提交失败，物料数量不能为0。");
			}
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tSaleOrderDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult UnPost(long id)  throws SysException {
		// 检查单据是否符合可反提交
		// 1.单据必须存在
		TSaleOrder selectByPrimaryKey = tSaleOrderDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反提交失败，订单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 1) {
			throw new SysException("400", "反提交失败，订单不是提交状态。");
		}
		
		// 更新状态
		selectByPrimaryKey.setFstateid(0);
		tSaleOrderDao.updateByPrimaryKey(selectByPrimaryKey);
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
