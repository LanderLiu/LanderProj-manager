package com.lander.manager.wh.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
import com.lander.manager.rpt.service.RptWhStockService;
import com.lander.manager.sys.service.SysSetService;
import com.lander.manager.wh.service.WhCheckService;
import com.lander.manager.wh.service.WhCheckdetailService;
import com.lander.manager.wh.service.WhMaterialinventoryService;
import com.lander.rpt.pojo.RptWhStock;
import com.lander.rpt.pojo.RptWhStockQuery;
import com.lander.wh.dao.TWhCheckDao;
import com.lander.wh.dao.TWhCheckdetailDao;
import com.lander.wh.dao.TWhMaterialinventoryDao;
import com.lander.wh.dao.TWhOtherinDao;
import com.lander.wh.dao.TWhOtherindetailDao;
import com.lander.wh.dao.TWhOtheroutDao;
import com.lander.wh.dao.TWhOtheroutdetailDao;
import com.lander.wh.dao.TWhPeriodDao;
import com.lander.wh.dao.TWhPickingDao;
import com.lander.wh.dao.TWhPickingdetailDao;
import com.lander.wh.dao.TWhReceiveDao;
import com.lander.wh.dao.TWhReceivedetailDao;
import com.lander.wh.dao.TWhSaleDao;
import com.lander.wh.dao.TWhSaledetailDao;
import com.lander.wh.dao.TWhTransferinDao;
import com.lander.wh.dao.TWhTransferindetailDao;
import com.lander.wh.dao.TWhTransferoutDao;
import com.lander.wh.dao.TWhTransferoutdetailDao;
import com.lander.wh.pojo.TWhCheck;
import com.lander.wh.pojo.TWhCheckQuery;
import com.lander.wh.pojo.TWhCheckQuery.Criteria;
import com.lander.wh.pojo.TWhCheckdetail;
import com.lander.wh.pojo.TWhCheckdetailQuery;
import com.lander.wh.pojo.TWhMaterialinventory;
import com.lander.wh.pojo.TWhMaterialinventoryQuery;
import com.lander.wh.pojo.TWhOtherin;
import com.lander.wh.pojo.TWhOtherinQuery;
import com.lander.wh.pojo.TWhOtherindetail;
import com.lander.wh.pojo.TWhOtherindetailQuery;
import com.lander.wh.pojo.TWhOtherout;
import com.lander.wh.pojo.TWhOtheroutQuery;
import com.lander.wh.pojo.TWhOtheroutdetail;
import com.lander.wh.pojo.TWhOtheroutdetailQuery;
import com.lander.wh.pojo.TWhPeriod;
import com.lander.wh.pojo.TWhPeriodQuery;
import com.lander.wh.pojo.TWhPicking;
import com.lander.wh.pojo.TWhPickingQuery;
import com.lander.wh.pojo.TWhPickingdetail;
import com.lander.wh.pojo.TWhPickingdetailQuery;
import com.lander.wh.pojo.TWhReceive;
import com.lander.wh.pojo.TWhReceiveQuery;
import com.lander.wh.pojo.TWhReceivedetail;
import com.lander.wh.pojo.TWhReceivedetailQuery;
import com.lander.wh.pojo.TWhSale;
import com.lander.wh.pojo.TWhSaleQuery;
import com.lander.wh.pojo.TWhSaledetail;
import com.lander.wh.pojo.TWhSaledetailQuery;
import com.lander.wh.pojo.TWhTransferin;
import com.lander.wh.pojo.TWhTransferinQuery;
import com.lander.wh.pojo.TWhTransferindetail;
import com.lander.wh.pojo.TWhTransferindetailQuery;
import com.lander.wh.pojo.TWhTransferout;
import com.lander.wh.pojo.TWhTransferoutQuery;
import com.lander.wh.pojo.TWhTransferoutdetail;
import com.lander.wh.pojo.TWhTransferoutdetailQuery;
import com.lander.wh.pojo.WhCheck;
import com.lander.wh.pojo.WhCheckQuery;
import com.lander.wh.pojo.WhCheckVo;

@Service
public class WhCheckServiceImpl implements WhCheckService {
	@Autowired
	private TWhCheckDao tWhCheckDao;
	@Autowired
	private TWhCheckdetailDao tWhCheckdetailDao;
	@Autowired
	private WhCheckdetailService whCheckdetailService;
	@Autowired
	private SysSetService sysSetService;
	@Autowired
	private BdInvnumberService bdInvnumberService;
	@Autowired
	private TBdWarehouseDao tbdWarehouseDao;
	@Autowired
	private TWhPeriodDao twhPeriodDao;
	@Autowired
	private TWhOtherinDao twhOtherinDao;
	@Autowired
	private TWhOtherindetailDao twhOtherindetailDao;
	@Autowired
	private TWhOtheroutDao twhOtheroutDao;
	@Autowired
	private TWhOtheroutdetailDao twhOtheroutdetailDao;

	@Autowired
	private TWhPickingDao twhPickingDao;
	@Autowired
	private TWhPickingdetailDao twhPickingdetailDao;

	@Autowired
	private TWhReceiveDao twhReceiveDao;
	@Autowired
	private TWhReceivedetailDao twhReceivedetailDao;

	@Autowired
	private TWhTransferinDao twhTransferinDao;
	@Autowired
	private TWhTransferindetailDao twhTransferindetailDao;

	@Autowired
	private TWhTransferoutDao twhTransferoutDao;
	@Autowired
	private TWhTransferoutdetailDao twhTransferoutdetailDao;

	@Autowired
	private TWhSaleDao twhSaleDao;
	@Autowired
	private TWhSaledetailDao twhSaledetailDao;

	@Autowired
	private TWhMaterialinventoryDao twhMaterialinventoryDao;
	@Autowired
	private WhMaterialinventoryService whMaterialinventoryService;
	@Autowired
	private RptWhStockService rptWhStockService;

	private static final String EXCEL_XLS = "xls";
	private static final String EXCEL_XLSX = "xlsx";

	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			WhCheckQuery whCheckQuery) {

		TWhCheckQuery query = new TWhCheckQuery();
		Criteria criteria = query.createCriteria();
		if (!StringUtil.isEmpty(whCheckQuery.getNumber())) {
			criteria.andFnumberLike("%" + whCheckQuery.getNumber() + "%");
		}

		query.setPageNo(page);
		query.setPageSize(rows);
		query.setOrderByClause(orderByClause + " " + asc);

		List<TWhCheck> list = tWhCheckDao.selectByExample(query);

		List<WhCheckVo> listVo = new ArrayList<WhCheckVo>();
		for (TWhCheck item : list) {
			WhCheckVo vo = new WhCheckVo();
			vo.settWhCheck(item);
			vo.setFid(item.getFid());

			listVo.add(vo);
		}
		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(listVo);

		result.setTotal(tWhCheckDao.countByExample(query));
		return result;
	}

	@Override
	public LanderResult insert(TWhCheck whCheck, String userId) throws SysException, Exception {
		String isAutoNumber = sysSetService.getByCode("07");
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			String FLeaderStr = "CK";
			int FCategoryId = 7;
			int flowLong = 3;
			String getFlow = bdInvnumberService.GetFlow(FLeaderStr, FCategoryId, userId, flowLong);
			whCheck.setFnumber(getFlow);
		} else {// 不自动生成单号
			TWhCheckQuery example = new TWhCheckQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whCheck.getFnumber());
			// 检查编码不可重复
			int countByExample = tWhCheckDao.countByExample(example);
			if (countByExample > 0) {
				throw new ServiceException("400", "编码为：" + whCheck.getFnumber() + "的盘点单已经存在，编码不可重复。");
			}
		}
		if (whCheck.getFid() == null) {
			Long fid = IDUtils.genId();
			whCheck.setFid(fid);
		}

		whCheck = (TWhCheck) BizDateUtil.setDefaultProperty(whCheck, userId, TWhCheck.class);
		whCheck.setFstateid(0);
		Integer data = tWhCheckDao.insert(whCheck);
		return LanderResult.ok(data);
	}

	@Override
	public LanderResult insertWithItem(WhCheck whCheck, String userId) throws SysException, Exception {

		if (whCheck.gettWhCheckdetails().size() == 0) {
			throw new SysException("410", "保存不成功,需要有一条以上订单明细。");
		}

		if (null != whCheck.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whCheck.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whCheck.setFwarehousename(selectByPrimaryKey.getFname());
			} else {
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}

		whCheck.setFitemcount(whCheck.gettWhCheckdetails().size());

		// 先插入主表
		insert(whCheck, userId);
		// 再插入明细
		for (TWhCheckdetail item : whCheck.gettWhCheckdetails()) {
			item.setFmasterid(whCheck.getFid());
			whCheckdetailService.insert(item, userId);
		}
		return LanderResult.ok();
	}

	@Override
	public LanderResult update(TWhCheck whCheck, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// updateValidate(whCheck);

		whCheck = (TWhCheck) BizDateUtil.setModifyDefaultProperty(whCheck, userId, TWhCheck.class);
		whCheck.setFstateid(0);
		Integer data = tWhCheckDao.updateByPrimaryKey(whCheck);
		return LanderResult.ok(data);
	}

	/**
	 * 连同订单主表和明细一次性更新 判断明细有新的/有删除/有更改，要逐条处理
	 * 
	 * @throws SysException
	 */
	@Override
	public LanderResult updateWithItem(WhCheck whCheck, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, SysException {

		LanderResult updateValidate = updateValidate(whCheck);
		if (updateValidate.getStatus() != 200) {
			return updateValidate;
		}

		TWhCheck tWhCheck = tWhCheckDao.selectByPrimaryKey(whCheck.getFid());
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号;
		if (isAutoNumber.equalsIgnoreCase("1")) {// 自动生成单号
			// 单号取原值
			whCheck.setFnumber(tWhCheck.getFnumber());
		}
		whCheck.setFcreatedatetime(tWhCheck.getFcreatedatetime());
		whCheck.setFcreateman(tWhCheck.getFcreateman());
		whCheck.setFitemcount(whCheck.gettWhCheckdetails().size());

		if (null != whCheck.getFwarehouseid()) {
			TBdWarehouse selectByPrimaryKey = tbdWarehouseDao.selectByPrimaryKey(whCheck.getFwarehouseid());
			if (selectByPrimaryKey != null) {
				whCheck.setFwarehousename(selectByPrimaryKey.getFname());
			} else {
				throw new SysException("420", "保存不成功,仓库资料无效。");
			}
		}
		whCheck = (WhCheck) BizDateUtil.setModifyDefaultProperty(whCheck, userId, TWhCheck.class);
		whCheck.setFstateid(0);
		Integer data = tWhCheckDao.updateByPrimaryKey(whCheck);
		// 获取已经有分录列表,为下面比较作准备
		TWhCheckdetailQuery example = new TWhCheckdetailQuery();
		com.lander.wh.pojo.TWhCheckdetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(whCheck.getFid());
		List<TWhCheckdetail> itemList = tWhCheckdetailDao.selectByExample(example);
		List<Long> itemIdList = new ArrayList<Long>();
		for (TWhCheckdetail item : itemList) {
			itemIdList.add(item.getFid());
		}
		// 更新分录，可能增加、删除或改变某条分录的一个值
		for (TWhCheckdetail item : whCheck.gettWhCheckdetails()) {
			// 01如果原单分录中不存在，则新增
			if (itemIdList.indexOf(item.getFid()) <= -1) {
				item.setFmasterid(whCheck.getFid());
				whCheckdetailService.insert(item, userId);
			} else {
				// 如果原单存在的，则用新分录值更新原分录的值
				whCheckdetailService.update(item, userId);
				// 在新明细中存在，则更新后不再关注
				itemIdList.remove(itemIdList.indexOf(item.getFid()));
			}
		}
		// 删除原单存在的分录，而在新分录列表不存在的
		// itemIdList中到现在仍存在的，就是没处理过的，也就是新的明细里已经不存在的
		for (Long item : itemIdList) {
			whCheckdetailService.delete(item);
		}
		return LanderResult.ok(data);
	}

	private LanderResult updateValidate(WhCheck whCheck) {
		TWhCheck tWhCheck = tWhCheckDao.selectByPrimaryKey(whCheck.getFid());
		if (tWhCheck == null) {
			return new LanderResult(404, "盘点单信息未找到，请刷新后重试。", null);
		}
		if (tWhCheck.getFstateid() != 0 && tWhCheck.getFstateid() != 1) {

		}
		String isAutoNumber = sysSetService.getByCode("01");
		// 如果当前是自动生成单号策略，则不允许修改单号; 否则要检查单号有无重复
		if (isAutoNumber.equalsIgnoreCase("0")) {// 非自动生成单号
			TWhCheckQuery example = new TWhCheckQuery();
			Criteria criteria = example.createCriteria();
			criteria.andFnumberEqualTo(whCheck.getFnumber());
			criteria.andFidNotEqualTo(whCheck.getFid());
			// 检查编码不可重复
			List<TWhCheck> countByExample = tWhCheckDao.selectByExample(example);
			if (countByExample.size() > 0) {
				// throw new ServiceException("400",
				// "采购订单:[" + countByExample.get(0).getFnumber() +
				// "]的编码与您要修改的订单重复，编码不可重复。");

				return new LanderResult(400, "采购订单:[" + countByExample.get(0).getFnumber() + "]的编码与您要修改的订单重复，编码不可重复。",
						null);
			}
		}

		return LanderResult.ok();
	}

	@Override
	public LanderResult delete(long fid) throws SysException {

		deleteValidate(fid);

		TWhCheckdetailQuery example = new TWhCheckdetailQuery();
		com.lander.wh.pojo.TWhCheckdetailQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFmasteridEqualTo(fid);

		List<TWhCheckdetail> selectByExample = tWhCheckdetailDao.selectByExample(example);
		for (TWhCheckdetail orderitem : selectByExample) {
			whCheckdetailService.delete(orderitem.getFid());
		}
		tWhCheckDao.deleteByPrimaryKey(fid);
		return LanderResult.build(200, "删除成功。");
	}

	private LanderResult deleteValidate(long fid) throws SysException {
		TWhCheck tWhCheck = tWhCheckDao.selectByPrimaryKey(fid);
		if (tWhCheck == null) {
			throw new SysException("400", "盘点单信息未找到，请刷新后重试");
		}
		if (tWhCheck.getFstateid() != 0 && tWhCheck.getFstateid() != 1) {
			throw new SysException("400", "盘点单必须是保存或提交状态。");
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
		TWhCheck selectByPrimaryKey = tWhCheckDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "审核失败，盘点单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 1) {
			throw new SysException("400", "审核失败，盘点单不是提交状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TWhCheckdetail> list = whCheckdetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "审核失败，盘点单明细为空。");
		}
		for (TWhCheckdetail item : list) {
			if (item.getFqty().equals(0)) {
				throw new SysException("400", "审核失败，盘点单物料数量不能为0。");
			}
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(2);
		selectByPrimaryKey.setFauditdatetime(new Date());
		selectByPrimaryKey.setFauditman(userId);
		selectByPrimaryKey.setFauditmemo("");
		tWhCheckDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult UnAudit(long id, String userId) throws SysException {
		// 检查单据是否符合可审核
		// 1.单据必须存在
		TWhCheck selectByPrimaryKey = tWhCheckDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反审核失败，盘点单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 2) {
			throw new SysException("400", "反审核失败，盘点单不是审核状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TWhCheckdetail> list = whCheckdetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "反审核失败，盘点单明细为空。");
		}

		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhCheckDao.updateByPrimaryKey(selectByPrimaryKey);
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
		TWhCheck selectByPrimaryKey = tWhCheckDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "提交失败，盘点单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 0) {
			throw new SysException("400", "提交失败，盘点单不是保存状态。");
		}
		// 检查单据明细
		// 3.必须有明细
		// 4.明细数量必须都大于0
		List<TWhCheckdetail> list = whCheckdetailService.list(id);
		if (list.size() == 0) {
			throw new SysException("400", "提交失败，盘点单明细为空。");
		}
		for (TWhCheckdetail item : list) {
			if (item.getFqty().equals(0)) {
				throw new SysException("400", "提交失败，物料数量不能为0。");
			}
		}
		// 更新状态
		selectByPrimaryKey.setFstateid(1);
		tWhCheckDao.updateByPrimaryKey(selectByPrimaryKey);
		return LanderResult.ok();
	}

	@Override
	public LanderResult UnPost(long id) throws SysException {
		// 检查单据是否符合可反提交
		// 1.单据必须存在
		TWhCheck selectByPrimaryKey = tWhCheckDao.selectByPrimaryKey(id);
		if (selectByPrimaryKey == null) {
			throw new SysException("400", "反提交失败，盘点单信息不存在。");
		}

		// 2.单据必须是提交状态
		if (selectByPrimaryKey.getFstateid() != 1) {
			throw new SysException("400", "反提交失败，盘点单不是提交状态。");
		}

		// 更新状态
		selectByPrimaryKey.setFstateid(0);
		tWhCheckDao.updateByPrimaryKey(selectByPrimaryKey);
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
	 * @throws Exception
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * 			@throws SecurityException @throws
	 *             NoSuchMethodException @throws SysException 为某仓库新建盘点记录 @Title:
	 *             createForWarehouse @Description: @param @param
	 *             warehouseId @param @param
	 *             userId @param @return @param @throws
	 *             NoSuchMethodException @param @throws
	 *             SecurityException @param @throws
	 *             IllegalAccessException @param @throws
	 *             IllegalArgumentException @param @throws
	 *             InvocationTargetException @return LanderResult @throws
	 */
	public LanderResult createForWarehouse(Long warehouseId, String userId) throws Exception {
		// 1.检查仓库，必须是存在、生效中、已初始化
		TBdWarehouse tBdWarehouse = tbdWarehouseDao.selectByPrimaryKey(warehouseId);
		if (tBdWarehouse == null) {
			throw new SysException("404", "创建失败，仓库信息不存在，请刷新后再试。");
		}
		if (tBdWarehouse.getFinitstate() != 1) {
			throw new SysException("500", "创建失败，仓库【" + tBdWarehouse.getFname() + "】必须已初始化。");
		}
		if (tBdWarehouse.getFstateid() != 1) {
			throw new SysException("500", "创建失败，仓库【" + tBdWarehouse.getFname() + "】不是启用状态。");
		}
		TWhPeriodQuery example = new TWhPeriodQuery();
		com.lander.wh.pojo.TWhPeriodQuery.Criteria criteria = example.createCriteria();
		criteria.andFwarehouseidEqualTo(warehouseId);
		criteria.andFstateidEqualTo(0);// 仓库未结帐
		criteria.andFiscurrentEqualTo(1);// 是当前期间
		// 2.检查，必须有当前期间
		List<TWhPeriod> example2 = twhPeriodDao.selectByExample(example);
		if (example2.size() == 0) {
			throw new SysException("500", "创建失败，仓库【" + tBdWarehouse.getFname() + "】未找到未结帐的当前期间。");
		}
		TWhPeriod currentPeriod = example2.get(0);

		// 2.1检查，如果同一期间，同一仓库，存在未关闭的盘点单，则不能再生成新单
		TWhCheckQuery example3 = new TWhCheckQuery();
		Criteria createCriteria = example3.createCriteria();
		createCriteria.andFperiodidEqualTo(currentPeriod.getFid());
		createCriteria.andFwarehouseidEqualTo(warehouseId);
		createCriteria.andFstateidEqualTo(0);// 盘点中状态
		List<TWhCheck> selectByExample = tWhCheckDao.selectByExample(example3);
		if (selectByExample.size() > 0) {
			throw new SysException("500", "创建失败，仓库【" + tBdWarehouse.getFname() + "】在期间【" + currentPeriod.getFname()
					+ "】存在未结束的盘点任务，不能再重复生成此仓库同一期间的盘点任务。");
		}
		// 3.检查仓库当前期间所有发生业务的物料（包括在基础资料可能已经停用的），有发生业务且期初库存不存在的，则生成（数量为0）

		buildForOtherin(currentPeriod, userId);
		buildForOtherout(currentPeriod, userId);
		buildForPicking(currentPeriod, userId);
		buildForReceive(currentPeriod, userId);
		buildForTransferin(currentPeriod, userId);
		buildForTransferout(currentPeriod, userId);
		buildForSale(currentPeriod, userId);

		// 4.生成盘点单主表信息
		TWhCheck tWhCheck = new TWhCheck();
		tWhCheck.setFbizdate(new Date());
		tWhCheck.setFperiodid(currentPeriod.getFid());
		tWhCheck.setFperiodname(currentPeriod.getFname());
		tWhCheck.setFperiodnumber(currentPeriod.getFnumber());
		tWhCheck.setFwarehouseid(tBdWarehouse.getFid());
		tWhCheck.setFwarehousename(tBdWarehouse.getFname());
		Long fid = IDUtils.genId();
		tWhCheck.setFid(fid);
		insert(tWhCheck, userId);

		// 获取各物料的期末数量
		RptWhStockQuery query = new RptWhStockQuery();
		query.setPeriodId(currentPeriod.getFid());
		query.setWarehouseId(warehouseId);
		EasyUIDataGridResult whStocklist = rptWhStockService.getList(1, 999999, "fid", "asc", query);

		int count = 0;
		// 5.上一步保证期初库存表包括了所有需要的物料，以期初库存表为基准，循环计算期末帐面数量（默认实盘数量与帐页数量相同），生成明细列表
		TWhMaterialinventoryQuery example1 = new TWhMaterialinventoryQuery();
		com.lander.wh.pojo.TWhMaterialinventoryQuery.Criteria criteria2 = example1.createCriteria();
		criteria2.andFperiodidEqualTo(currentPeriod.getFid());
		List<TWhMaterialinventory> twhMaterialinventorys = twhMaterialinventoryDao.selectByExample(example1);
		for (TWhMaterialinventory tWhMaterialinventory : twhMaterialinventorys) {
			TWhCheckdetail twhCheckdetail = new TWhCheckdetail();
			twhCheckdetail.setFmasterid(fid);
			twhCheckdetail.setFmaterialid(tWhMaterialinventory.getFmaterialid());
			twhCheckdetail.setFmaterialname(tWhMaterialinventory.getFmaterialname());
			twhCheckdetail.setFmaterialnumber(tWhMaterialinventory.getFmaterialnumber());
			twhCheckdetail.setFmaterialspecification(tWhMaterialinventory.getFmaterialspecification());
			Boolean findflag = false;
			for (Object rptWhStock : whStocklist.getRows()) {
				if (((RptWhStock) rptWhStock).getFmaterialid().equals(tWhMaterialinventory.getFmaterialid())) {
					twhCheckdetail.setFqty(((RptWhStock) rptWhStock).getFqtyEnd());

					findflag = true;
					break;
				}
			}
			if (findflag == false) {
				twhCheckdetail.setFqty(BigDecimal.ZERO);
			}
			whCheckdetailService.insert(twhCheckdetail, userId);
			count++;
		}
		tWhCheck.setFitemcount(count);
		update(tWhCheck, userId);
		return LanderResult.build(200, "生成盘点记录完成，共生成" + count + "条");
	}

	/**
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * 			@throws NoSuchMethodException @param userId
	 *             创建其它入库单涉及物料的期初库存 @Title:
	 *             buildForOtherin @Description: @param @param
	 *             tWhPeriod @param @param
	 *             twhMaterialinventorys @param @return @return
	 *             LanderResult @throws
	 */
	private void buildForOtherin(TWhPeriod currentPeriod, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TWhMaterialinventoryQuery example1 = new TWhMaterialinventoryQuery();
		com.lander.wh.pojo.TWhMaterialinventoryQuery.Criteria criteria2 = example1.createCriteria();
		criteria2.andFperiodidEqualTo(currentPeriod.getFid());
		List<TWhMaterialinventory> twhMaterialinventorys = twhMaterialinventoryDao.selectByExample(example1);

		TWhOtherinQuery example = new TWhOtherinQuery();
		com.lander.wh.pojo.TWhOtherinQuery.Criteria criteria = example.createCriteria();
		criteria.andFwarehouseidEqualTo(currentPeriod.getFwarehouseid());
		criteria.andFbizdateBetween(currentPeriod.getFfrom(), currentPeriod.getFto());
		criteria.andFstateidEqualTo(2);// 审核状态的单据
		List<TWhOtherin> example2 = twhOtherinDao.selectByExample(example);
		for (TWhOtherin tWhOtherin : example2) {
			TWhOtherindetailQuery example3 = new TWhOtherindetailQuery();
			com.lander.wh.pojo.TWhOtherindetailQuery.Criteria criteria3 = example3.createCriteria();
			criteria3.andFmasteridEqualTo(tWhOtherin.getFid());
			List<TWhOtherindetail> example4 = twhOtherindetailDao.selectByExample(example3);
			for (TWhOtherindetail tWhOtherindetail : example4) {
				Boolean existflag = false;// 期初数据中是否存在此物料
				for (TWhMaterialinventory twhMaterialinventory : twhMaterialinventorys) {
					if (twhMaterialinventory.getFmaterialid().equals(tWhOtherindetail.getFmaterialid())
							&& twhMaterialinventory.getFperiodid().equals(currentPeriod.getFid())) {
						existflag = true;// 存在
						break;
					}
				}
				// 如果未找到，则在期初库中增加一个物料
				if (existflag == false) {
					TWhMaterialinventory whMaterialinventory = new TWhMaterialinventory();
					whMaterialinventory.setFmaterialid(tWhOtherindetail.getFmaterialid());
					whMaterialinventory.setFmaterialname(tWhOtherindetail.getFmaterialname());
					whMaterialinventory.setFmaterialnumber(tWhOtherindetail.getFmaterialnumber());
					whMaterialinventory.setFmaterialspecification(tWhOtherindetail.getFmaterialspecification());
					whMaterialinventory.setFperiodid(currentPeriod.getFid());
					whMaterialinventory.setFperiodname(currentPeriod.getFname());
					whMaterialinventory.setFperiodnumber(currentPeriod.getFnumber());
					whMaterialinventory.setFqtyInit(BigDecimal.ZERO);
					whMaterialinventory.setFwarehouseid(currentPeriod.getFwarehouseid());
					whMaterialinventory.setFwarehousename(currentPeriod.getFwarehousename());
					whMaterialinventory.setFwarehousenumber(currentPeriod.getFwarehousenumber());
					whMaterialinventoryService.insert(whMaterialinventory, userId);
				}
			}
		}

	}

	/**
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * 			@throws NoSuchMethodException @param userId
	 *             创建其它出库单涉及物料的期初库存 @Title:
	 *             buildForOtherin @Description: @param @param
	 *             tWhPeriod @param @param
	 *             twhMaterialinventorys @param @return @return
	 *             LanderResult @throws
	 */
	private void buildForOtherout(TWhPeriod currentPeriod, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TWhMaterialinventoryQuery example1 = new TWhMaterialinventoryQuery();
		com.lander.wh.pojo.TWhMaterialinventoryQuery.Criteria criteria2 = example1.createCriteria();
		criteria2.andFperiodidEqualTo(currentPeriod.getFid());
		List<TWhMaterialinventory> twhMaterialinventorys = twhMaterialinventoryDao.selectByExample(example1);

		TWhOtheroutQuery example = new TWhOtheroutQuery();
		com.lander.wh.pojo.TWhOtheroutQuery.Criteria criteria = example.createCriteria();
		criteria.andFwarehouseidEqualTo(currentPeriod.getFwarehouseid());
		criteria.andFbizdateBetween(currentPeriod.getFfrom(), currentPeriod.getFto());
		criteria.andFstateidEqualTo(2);// 审核状态的单据
		List<TWhOtherout> example2 = twhOtheroutDao.selectByExample(example);
		for (TWhOtherout tWhOtherout : example2) {
			TWhOtheroutdetailQuery example3 = new TWhOtheroutdetailQuery();
			com.lander.wh.pojo.TWhOtheroutdetailQuery.Criteria criteria3 = example3.createCriteria();
			criteria3.andFmasteridEqualTo(tWhOtherout.getFid());
			List<TWhOtheroutdetail> example4 = twhOtheroutdetailDao.selectByExample(example3);
			for (TWhOtheroutdetail tWhOtheroutdetail : example4) {
				Boolean existflag = false;// 期初数据中是否存在此物料
				for (TWhMaterialinventory twhMaterialinventory : twhMaterialinventorys) {
					if (twhMaterialinventory.getFmaterialid().equals(tWhOtheroutdetail.getFmaterialid())) {
						existflag = true;// 存在
						break;
					}
				}
				// 如果未找到，则在期初库中增加一个物料
				if (existflag == false) {
					TWhMaterialinventory whMaterialinventory = new TWhMaterialinventory();
					whMaterialinventory.setFmaterialid(tWhOtheroutdetail.getFmaterialid());
					whMaterialinventory.setFmaterialname(tWhOtheroutdetail.getFmaterialname());
					whMaterialinventory.setFmaterialnumber(tWhOtheroutdetail.getFmaterialnumber());
					whMaterialinventory.setFmaterialspecification(tWhOtheroutdetail.getFmaterialspecification());
					whMaterialinventory.setFperiodid(currentPeriod.getFid());
					whMaterialinventory.setFperiodname(currentPeriod.getFname());
					whMaterialinventory.setFperiodnumber(currentPeriod.getFnumber());
					whMaterialinventory.setFqtyInit(BigDecimal.ZERO);
					whMaterialinventory.setFwarehouseid(currentPeriod.getFwarehouseid());
					whMaterialinventory.setFwarehousename(currentPeriod.getFwarehousename());
					whMaterialinventory.setFwarehousenumber(currentPeriod.getFwarehousenumber());
					whMaterialinventoryService.insert(whMaterialinventory, userId);
				}
			}
		}
	}

	/**
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * 			@throws NoSuchMethodException @param userId
	 *             创建其它出库单涉及物料的期初库存 @Title:
	 *             buildForOtherin @Description: @param @param
	 *             tWhPeriod @param @param
	 *             twhMaterialinventorys @param @return @return
	 *             LanderResult @throws
	 */
	private void buildForPicking(TWhPeriod currentPeriod, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TWhMaterialinventoryQuery example1 = new TWhMaterialinventoryQuery();
		com.lander.wh.pojo.TWhMaterialinventoryQuery.Criteria criteria2 = example1.createCriteria();
		criteria2.andFperiodidEqualTo(currentPeriod.getFid());
		List<TWhMaterialinventory> twhMaterialinventorys = twhMaterialinventoryDao.selectByExample(example1);

		TWhPickingQuery example = new TWhPickingQuery();
		com.lander.wh.pojo.TWhPickingQuery.Criteria criteria = example.createCriteria();
		criteria.andFwarehouseidEqualTo(currentPeriod.getFwarehouseid());
		criteria.andFbizdateBetween(currentPeriod.getFfrom(), currentPeriod.getFto());
		criteria.andFstateidEqualTo(2);// 审核状态的单据
		List<TWhPicking> example2 = twhPickingDao.selectByExample(example);
		for (TWhPicking tWhPicking : example2) {
			TWhPickingdetailQuery example3 = new TWhPickingdetailQuery();
			com.lander.wh.pojo.TWhPickingdetailQuery.Criteria criteria3 = example3.createCriteria();
			criteria3.andFmasteridEqualTo(tWhPicking.getFid());
			List<TWhPickingdetail> example4 = twhPickingdetailDao.selectByExample(example3);
			for (TWhPickingdetail tWhPickingdetail : example4) {
				Boolean existflag = false;// 期初数据中是否存在此物料
				for (TWhMaterialinventory twhMaterialinventory : twhMaterialinventorys) {
					if (twhMaterialinventory.getFmaterialid().equals(tWhPickingdetail.getFmaterialid())) {
						existflag = true;// 存在
						break;
					}
				}
				// 如果未找到，则在期初库中增加一个物料
				if (existflag == false) {
					TWhMaterialinventory whMaterialinventory = new TWhMaterialinventory();
					whMaterialinventory.setFmaterialid(tWhPickingdetail.getFmaterialid());
					whMaterialinventory.setFmaterialname(tWhPickingdetail.getFmaterialname());
					whMaterialinventory.setFmaterialnumber(tWhPickingdetail.getFmaterialnumber());
					whMaterialinventory.setFmaterialspecification(tWhPickingdetail.getFmaterialspecification());
					whMaterialinventory.setFperiodid(currentPeriod.getFid());
					whMaterialinventory.setFperiodname(currentPeriod.getFname());
					whMaterialinventory.setFperiodnumber(currentPeriod.getFnumber());
					whMaterialinventory.setFqtyInit(BigDecimal.ZERO);
					whMaterialinventory.setFwarehouseid(currentPeriod.getFwarehouseid());
					whMaterialinventory.setFwarehousename(currentPeriod.getFwarehousename());
					whMaterialinventory.setFwarehousenumber(currentPeriod.getFwarehousenumber());
					whMaterialinventoryService.insert(whMaterialinventory, userId);
				}
			}
		}
	}

	/**
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * 			@throws NoSuchMethodException @param userId
	 *             创建其它出库单涉及物料的期初库存 @Title:
	 *             buildForOtherin @Description: @param @param
	 *             tWhPeriod @param @param
	 *             twhMaterialinventorys @param @return @return
	 *             LanderResult @throws
	 */
	private void buildForReceive(TWhPeriod currentPeriod, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TWhMaterialinventoryQuery example1 = new TWhMaterialinventoryQuery();
		com.lander.wh.pojo.TWhMaterialinventoryQuery.Criteria criteria2 = example1.createCriteria();
		criteria2.andFperiodidEqualTo(currentPeriod.getFid());
		List<TWhMaterialinventory> twhMaterialinventorys = twhMaterialinventoryDao.selectByExample(example1);

		TWhReceiveQuery example = new TWhReceiveQuery();
		com.lander.wh.pojo.TWhReceiveQuery.Criteria criteria = example.createCriteria();
		criteria.andFwarehouseidEqualTo(currentPeriod.getFwarehouseid());
		criteria.andFbizdateBetween(currentPeriod.getFfrom(), currentPeriod.getFto());
		criteria.andFstateidEqualTo(2);// 审核状态的单据
		List<TWhReceive> example2 = twhReceiveDao.selectByExample(example);
		for (TWhReceive tWhReceive : example2) {
			TWhReceivedetailQuery example3 = new TWhReceivedetailQuery();
			com.lander.wh.pojo.TWhReceivedetailQuery.Criteria criteria3 = example3.createCriteria();
			criteria3.andFmasteridEqualTo(tWhReceive.getFid());
			List<TWhReceivedetail> example4 = twhReceivedetailDao.selectByExample(example3);
			for (TWhReceivedetail tWhReceivedetail : example4) {
				Boolean existflag = false;// 期初数据中是否存在此物料
				for (TWhMaterialinventory twhMaterialinventory : twhMaterialinventorys) {
					if (twhMaterialinventory.getFmaterialid().equals(tWhReceivedetail.getFmaterialid())) {
						existflag = true;// 存在
						break;
					}
				}
				// 如果未找到，则在期初库中增加一个物料
				if (existflag == false) {
					TWhMaterialinventory whMaterialinventory = new TWhMaterialinventory();
					whMaterialinventory.setFmaterialid(tWhReceivedetail.getFmaterialid());
					whMaterialinventory.setFmaterialname(tWhReceivedetail.getFmaterialname());
					whMaterialinventory.setFmaterialnumber(tWhReceivedetail.getFmaterialnumber());
					whMaterialinventory.setFmaterialspecification(tWhReceivedetail.getFmaterialspecification());
					whMaterialinventory.setFperiodid(currentPeriod.getFid());
					whMaterialinventory.setFperiodname(currentPeriod.getFname());
					whMaterialinventory.setFperiodnumber(currentPeriod.getFnumber());
					whMaterialinventory.setFqtyInit(BigDecimal.ZERO);
					whMaterialinventory.setFwarehouseid(currentPeriod.getFwarehouseid());
					whMaterialinventory.setFwarehousename(currentPeriod.getFwarehousename());
					whMaterialinventory.setFwarehousenumber(currentPeriod.getFwarehousenumber());
					whMaterialinventoryService.insert(whMaterialinventory, userId);
				}
			}
		}
	}

	/**
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * 			@throws NoSuchMethodException @param userId
	 *             创建其它出库单涉及物料的期初库存 @Title:
	 *             buildForOtherin @Description: @param @param
	 *             tWhPeriod @param @param
	 *             twhMaterialinventorys @param @return @return
	 *             LanderResult @throws
	 */
	private void buildForTransferin(TWhPeriod currentPeriod, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TWhMaterialinventoryQuery example1 = new TWhMaterialinventoryQuery();
		com.lander.wh.pojo.TWhMaterialinventoryQuery.Criteria criteria2 = example1.createCriteria();
		criteria2.andFperiodidEqualTo(currentPeriod.getFid());
		List<TWhMaterialinventory> twhMaterialinventorys = twhMaterialinventoryDao.selectByExample(example1);

		TWhTransferinQuery example = new TWhTransferinQuery();
		com.lander.wh.pojo.TWhTransferinQuery.Criteria criteria = example.createCriteria();
		criteria.andFwarehouseidEqualTo(currentPeriod.getFwarehouseid());
		criteria.andFbizdateBetween(currentPeriod.getFfrom(), currentPeriod.getFto());
		criteria.andFstateidEqualTo(2);// 审核状态的单据
		List<TWhTransferin> example2 = twhTransferinDao.selectByExample(example);
		for (TWhTransferin tWhTransferin : example2) {
			TWhTransferindetailQuery example3 = new TWhTransferindetailQuery();
			com.lander.wh.pojo.TWhTransferindetailQuery.Criteria criteria3 = example3.createCriteria();
			criteria3.andFmasteridEqualTo(tWhTransferin.getFid());
			List<TWhTransferindetail> example4 = twhTransferindetailDao.selectByExample(example3);
			for (TWhTransferindetail tWhTransferindetail : example4) {
				Boolean existflag = false;// 期初数据中是否存在此物料
				for (TWhMaterialinventory twhMaterialinventory : twhMaterialinventorys) {
					if (twhMaterialinventory.getFmaterialid().equals(tWhTransferindetail.getFmaterialid())) {
						existflag = true;// 存在
						break;
					}
				}
				// 如果未找到，则在期初库中增加一个物料
				if (existflag == false) {
					TWhMaterialinventory whMaterialinventory = new TWhMaterialinventory();
					whMaterialinventory.setFmaterialid(tWhTransferindetail.getFmaterialid());
					whMaterialinventory.setFmaterialname(tWhTransferindetail.getFmaterialname());
					whMaterialinventory.setFmaterialnumber(tWhTransferindetail.getFmaterialnumber());
					whMaterialinventory.setFmaterialspecification(tWhTransferindetail.getFmaterialspecification());
					whMaterialinventory.setFperiodid(currentPeriod.getFid());
					whMaterialinventory.setFperiodname(currentPeriod.getFname());
					whMaterialinventory.setFperiodnumber(currentPeriod.getFnumber());
					whMaterialinventory.setFqtyInit(BigDecimal.ZERO);
					whMaterialinventory.setFwarehouseid(currentPeriod.getFwarehouseid());
					whMaterialinventory.setFwarehousename(currentPeriod.getFwarehousename());
					whMaterialinventory.setFwarehousenumber(currentPeriod.getFwarehousenumber());
					whMaterialinventoryService.insert(whMaterialinventory, userId);
				}
			}
		}
	}

	/**
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * 			@throws NoSuchMethodException @param userId
	 *             创建其它出库单涉及物料的期初库存 @Title:
	 *             buildForOtherin @Description: @param @param
	 *             tWhPeriod @param @param
	 *             twhMaterialinventorys @param @return @return
	 *             LanderResult @throws
	 */
	private void buildForTransferout(TWhPeriod currentPeriod, String userId) throws NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TWhMaterialinventoryQuery example1 = new TWhMaterialinventoryQuery();
		com.lander.wh.pojo.TWhMaterialinventoryQuery.Criteria criteria2 = example1.createCriteria();
		criteria2.andFperiodidEqualTo(currentPeriod.getFid());
		List<TWhMaterialinventory> twhMaterialinventorys = twhMaterialinventoryDao.selectByExample(example1);

		TWhTransferoutQuery example = new TWhTransferoutQuery();
		com.lander.wh.pojo.TWhTransferoutQuery.Criteria criteria = example.createCriteria();
		criteria.andFwarehouseidEqualTo(currentPeriod.getFwarehouseid());
		criteria.andFbizdateBetween(currentPeriod.getFfrom(), currentPeriod.getFto());
		criteria.andFstateidEqualTo(2);// 审核状态的单据
		List<TWhTransferout> example2 = twhTransferoutDao.selectByExample(example);
		for (TWhTransferout tWhTransferout : example2) {
			TWhTransferoutdetailQuery example3 = new TWhTransferoutdetailQuery();
			com.lander.wh.pojo.TWhTransferoutdetailQuery.Criteria criteria3 = example3.createCriteria();
			criteria3.andFmasteridEqualTo(tWhTransferout.getFid());
			List<TWhTransferoutdetail> example4 = twhTransferoutdetailDao.selectByExample(example3);
			for (TWhTransferoutdetail tWhTransferoutdetail : example4) {
				Boolean existflag = false;// 期初数据中是否存在此物料
				for (TWhMaterialinventory twhMaterialinventory : twhMaterialinventorys) {
					if (twhMaterialinventory.getFmaterialid().equals(tWhTransferoutdetail.getFmaterialid())) {
						existflag = true;// 存在
						break;
					}
				}
				// 如果未找到，则在期初库中增加一个物料
				if (existflag == false) {
					TWhMaterialinventory whMaterialinventory = new TWhMaterialinventory();
					whMaterialinventory.setFmaterialid(tWhTransferoutdetail.getFmaterialid());
					whMaterialinventory.setFmaterialname(tWhTransferoutdetail.getFmaterialname());
					whMaterialinventory.setFmaterialnumber(tWhTransferoutdetail.getFmaterialnumber());
					whMaterialinventory.setFmaterialspecification(tWhTransferoutdetail.getFmaterialspecification());
					whMaterialinventory.setFperiodid(currentPeriod.getFid());
					whMaterialinventory.setFperiodname(currentPeriod.getFname());
					whMaterialinventory.setFperiodnumber(currentPeriod.getFnumber());
					whMaterialinventory.setFqtyInit(BigDecimal.ZERO);
					whMaterialinventory.setFwarehouseid(currentPeriod.getFwarehouseid());
					whMaterialinventory.setFwarehousename(currentPeriod.getFwarehousename());
					whMaterialinventory.setFwarehousenumber(currentPeriod.getFwarehousenumber());
					whMaterialinventoryService.insert(whMaterialinventory, userId);
				}
			}
		}
	}

	/**
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * 			@throws NoSuchMethodException @param userId
	 *             创建其它出库单涉及物料的期初库存 @Title:
	 *             buildForOtherin @Description: @param @param
	 *             tWhPeriod @param @param
	 *             twhMaterialinventorys @param @return @return
	 *             LanderResult @throws
	 */
	private void buildForSale(TWhPeriod currentPeriod, String userId) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TWhMaterialinventoryQuery example1 = new TWhMaterialinventoryQuery();
		com.lander.wh.pojo.TWhMaterialinventoryQuery.Criteria criteria2 = example1.createCriteria();
		criteria2.andFperiodidEqualTo(currentPeriod.getFid());
		List<TWhMaterialinventory> twhMaterialinventorys = twhMaterialinventoryDao.selectByExample(example1);

		TWhSaleQuery example = new TWhSaleQuery();
		com.lander.wh.pojo.TWhSaleQuery.Criteria criteria = example.createCriteria();
		criteria.andFwarehouseidEqualTo(currentPeriod.getFwarehouseid());
		criteria.andFbizdateBetween(currentPeriod.getFfrom(), currentPeriod.getFto());
		criteria.andFstateidEqualTo(2);// 审核状态的单据
		List<TWhSale> example2 = twhSaleDao.selectByExample(example);
		for (TWhSale tWhSale : example2) {
			TWhSaledetailQuery example3 = new TWhSaledetailQuery();
			com.lander.wh.pojo.TWhSaledetailQuery.Criteria criteria3 = example3.createCriteria();
			criteria3.andFmasteridEqualTo(tWhSale.getFid());
			List<TWhSaledetail> example4 = twhSaledetailDao.selectByExample(example3);
			for (TWhSaledetail tWhSaledetail : example4) {
				Boolean existflag = false;// 期初数据中是否存在此物料
				for (TWhMaterialinventory twhMaterialinventory : twhMaterialinventorys) {
					if (twhMaterialinventory.getFmaterialid().equals(tWhSaledetail.getFmaterialid())) {
						existflag = true;// 存在
						break;
					}
				}
				// 如果未找到，则在期初库中增加一个物料
				if (existflag == false) {
					TWhMaterialinventory whMaterialinventory = new TWhMaterialinventory();
					whMaterialinventory.setFmaterialid(tWhSaledetail.getFmaterialid());
					whMaterialinventory.setFmaterialname(tWhSaledetail.getFmaterialname());
					whMaterialinventory.setFmaterialnumber(tWhSaledetail.getFmaterialnumber());
					whMaterialinventory.setFmaterialspecification(tWhSaledetail.getFmaterialspecification());
					whMaterialinventory.setFperiodid(currentPeriod.getFid());
					whMaterialinventory.setFperiodname(currentPeriod.getFname());
					whMaterialinventory.setFperiodnumber(currentPeriod.getFnumber());
					whMaterialinventory.setFqtyInit(BigDecimal.ZERO);
					whMaterialinventory.setFwarehouseid(currentPeriod.getFwarehouseid());
					whMaterialinventory.setFwarehousename(currentPeriod.getFwarehousename());
					whMaterialinventory.setFwarehousenumber(currentPeriod.getFwarehousenumber());
					whMaterialinventoryService.insert(whMaterialinventory, userId);
				}
			}
		}
	}
	@Override
	public LanderResult exportToExcel(Long fid, String userId) {	
		TWhCheck tWhCheck = tWhCheckDao.selectByPrimaryKey(fid);		

		TWhCheckdetailQuery example4 = new TWhCheckdetailQuery();
		com.lander.wh.pojo.TWhCheckdetailQuery.Criteria createCriteria2 = example4.createCriteria();
		createCriteria2.andFmasteridEqualTo(tWhCheck.getFid());
		List<TWhCheckdetail> selectByExample2 = tWhCheckdetailDao.selectByExample(example4);
		writeExcel(tWhCheck, selectByExample2, "d:/export.xlsx","d:/export001.xlsx");
		//download( "d:/export.xlsx",request,response);
		return LanderResult.build(200, "导出成功。");
	}

	public static void writeExcel(TWhCheck tWhCheck, List<TWhCheckdetail> whCheckDetails, String finalXlsxPathTemp, String finalXlsxPath) {
		OutputStream out = null;
		try {
			
			// 读取Excel文档
			File finalXlsxFile = new File(finalXlsxPathTemp);
			Workbook workBook = getWorkbok(finalXlsxFile);
			// sheet 对应一个工作页
			Sheet sheet = workBook.getSheetAt(0);			
			/**
			 * 删除原有数据，除了属性列
			 */
//			int rowNumber = sheet.getLastRowNum(); // 第一行从0开始算
//			System.out.println("原始数据总行数，除属性列：" + rowNumber);
//			for (int i = 1; i <= rowNumber; i++) {
//				Row row = sheet.getRow(i);
//				sheet.removeRow(row);
//			}
			// 创建文件输出流，输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPathTemp);
			workBook.write(out);
			
			//导出单头
			Row row = sheet.getRow(0);
			Cell hc1 = row.createCell(1);
			hc1.setCellValue(tWhCheck.getFnumber());
			Cell hc2 = row.createCell(3);
			hc2.setCellValue(tWhCheck.getFwarehousename());
			Cell hc3 = row.createCell(5);
			hc3.setCellValue(tWhCheck.getFperiodname());
			/**
			 * 往Excel中写新数据
			 */
			for (int j = 0; j < whCheckDetails.size(); j++) {
				TWhCheckdetail tWhCheckdetail=whCheckDetails.get(j);
				// 创建一行：从第二行开始，跳过属性列
				Row row1 = sheet.createRow(j + 3);
				Cell c1 = row1.createCell(0);
				c1.setCellValue(tWhCheckdetail.getFmaterialnumber());
				Cell c2 = row1.createCell(1);
				c2.setCellValue(tWhCheckdetail.getFmaterialname());
				Cell c3 = row1.createCell(2);
				c3.setCellValue(tWhCheckdetail.getFmaterialspecification());
				Cell c4 = row1.createCell(3);
				c4.setCellValue(tWhCheckdetail.getFunit());
				Cell c5 = row1.createCell(4);
				c5.setCellValue(tWhCheckdetail.getFqty().doubleValue());
				Cell c6 = row1.createCell(5);
				c6.setCellValue(tWhCheckdetail.getFqty().doubleValue());
			}
			// 创建文件输出流，准备输出电子表格：这个必须有，否则你在sheet上做的任何操作都不会有效
			out = new FileOutputStream(finalXlsxPath);
			workBook.write(out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.flush();
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("数据导出成功");
	}

	/**
	 * 判断Excel的版本,获取Workbook
	 * 
	 * @param in
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	private static Workbook getWorkbok(File file) throws IOException {
		
		Workbook wb = null;
		FileInputStream in = new FileInputStream(file);
		if (file.getName().endsWith(EXCEL_XLS)) { // Excel&nbsp;2003
			wb = new HSSFWorkbook(in);
		} else if (file.getName().endsWith(EXCEL_XLSX)) { // Excel 2007/2010
			wb = new XSSFWorkbook(in);
		}
		return wb;
	}
	
	private static void download(String filename,HttpServletRequest request,HttpServletResponse response){
		
		 OutputStream out = null;
	        try {

	            // 1.弹出下载框，并处理中文
	            /** 如果是从jsp页面传过来的话，就要进行中文处理，在这里action里面产生的直接可以用
	             * String filename = request.getParameter("filename");
	             */
	            /**
	             if (request.getMethod().equalsIgnoreCase("GET")) {
	             filename = new String(filename.getBytes("iso8859-1"), "utf-8");
	             }
	             */

	            response.addHeader("content-disposition", "attachment;filename="
	                    + java.net.URLEncoder.encode(filename, "utf-8"));

	            // 2.下载
	            out = response.getOutputStream();
	            String path3 = request.getSession().getServletContext().getRealPath("") + "/" + filename;

	            // inputStream：读文件，前提是这个文件必须存在，要不就会报错
	            InputStream is = new FileInputStream(path3);

	            byte[] b = new byte[4096];
	            int size = is.read(b);
	            while (size > 0) {
	                out.write(b, 0, size);
	                size = is.read(b);
	            }
	            out.close();
	            is.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	}
}
