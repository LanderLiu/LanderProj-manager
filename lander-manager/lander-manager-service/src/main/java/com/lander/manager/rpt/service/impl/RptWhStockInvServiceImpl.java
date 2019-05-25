package com.lander.manager.rpt.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.enums.InvTypeEnum;
import com.lander.manager.rpt.service.RptWhStockInvService;
import com.lander.rpt.pojo.RptWhStockInv;
import com.lander.rpt.pojo.RptWhStockInvQuery;
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
import com.lander.wh.pojo.TWhOtherin;
import com.lander.wh.pojo.TWhOtherinQuery;
import com.lander.wh.pojo.TWhOtherindetail;
import com.lander.wh.pojo.TWhOtherindetailQuery;
import com.lander.wh.pojo.TWhOtherout;
import com.lander.wh.pojo.TWhOtheroutQuery;
import com.lander.wh.pojo.TWhOtheroutdetail;
import com.lander.wh.pojo.TWhOtheroutdetailQuery;
import com.lander.wh.pojo.TWhPeriod;
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

@Service
public class RptWhStockInvServiceImpl implements RptWhStockInvService {
	@Autowired
	private TWhOtherinDao tWhOtherinDao;
	@Autowired
	private TWhOtherindetailDao tWhOtherindetailDao;
	@Autowired
	private TWhReceiveDao tWhReceiveDao;
	@Autowired
	private TWhReceivedetailDao tWhReceivedetailDao;

	@Autowired
	private TWhOtheroutDao tWhOtheroutDao;
	@Autowired
	private TWhOtheroutdetailDao tWhOtheroutdetailDao;

	@Autowired
	private TWhPickingDao tWhPickingDao;
	@Autowired
	private TWhPickingdetailDao tWhPickingdetailDao;

	@Autowired
	private TWhPeriodDao tWhPeriodDao;

	@Autowired
	private TWhTransferinDao tWhTransferinDao;
	@Autowired
	private TWhTransferindetailDao tWhTransferindetailDao;

	@Autowired
	private TWhTransferoutDao tWhTransferoutDao;
	@Autowired
	private TWhTransferoutdetailDao tWhTransferoutdetailDao;
	
	@Autowired
	private TWhSaleDao tWhSaleDao;
	@Autowired
	private TWhSaledetailDao tWhSaledetailDao;
	
	/**
	 * 库存进销存明细表，根据给定的期间、仓库、物料列出相应的各类出入库单据
	 */
	@Override
	public EasyUIDataGridResult getList(RptWhStockInvQuery query, BigDecimal qty_init) {
		// 0.准备工作
		List<RptWhStockInv> resultList = new ArrayList<RptWhStockInv>();
		// 1.根据给定的期间、仓库，先找到所有审核或关闭状态的库存单据,循环前一步的单据，找到各单的明细，如果明细为给定物料，则加到结果列表
		// 2.获取其它入仓单的列表
		resultList.addAll(getOtherIn(query));
		// 3.获取其它出仓单的列表
		resultList.addAll(getOtherOut(query));
		// 4.获取生产领料出仓单列表
		resultList.addAll(getPicking(query));
		// 5.获取收货入仓单列表
		resultList.addAll(getReceive(query));
		// 6.获取调拨入仓单列表
		resultList.addAll(getTransferin(query));
		// 7.获取调拨出仓单列表
		resultList.addAll(getTransferout(query));
		// 8.获取销售出仓单列表
		resultList.addAll(getSale(query));
				
		// 直接在这里添加我们的排序规则
		Collections.sort(resultList, new Comparator<RptWhStockInv>() {
			public int compare(RptWhStockInv arg0, RptWhStockInv arg1) {
				return arg0.getBizDate().compareTo(arg1.getBizDate());
			}
		});

		BigDecimal qtyendTemp = qty_init;
		for (RptWhStockInv rptWhStockInv : resultList) {
			qtyendTemp = qtyendTemp.add(rptWhStockInv.getQty_in() == null ? BigDecimal.ZERO : rptWhStockInv.getQty_in())
					.subtract(rptWhStockInv.getQty_out() == null ? BigDecimal.ZERO : rptWhStockInv.getQty_out());
			rptWhStockInv.setQty_end(qtyendTemp);

		}

		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(resultList);

		result.setTotal(resultList.size());
		return result;

	}

	/**
	 * 获取给定期间、仓库、物料的其它入仓单有涉及的单明细清单 @Title:
	 * getOtherIn @Description: @param @param query @param @return @return
	 * List<RptWhStockInv> @throws
	 */
	private List<RptWhStockInv> getOtherIn(RptWhStockInvQuery query) {

		TWhPeriod tWhPeriod = tWhPeriodDao.selectByPrimaryKey(query.getPeriodId());

		List<RptWhStockInv> resultList = new ArrayList<RptWhStockInv>();

		TWhOtherinQuery example = new TWhOtherinQuery();
		com.lander.wh.pojo.TWhOtherinQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFwarehouseidEqualTo(query.getWarehouseId());
		createCriteria.andFstateidEqualTo(2);
		createCriteria.andFbizdateBetween(tWhPeriod.getFfrom(), tWhPeriod.getFto());

		List<TWhOtherin> selectByExample = tWhOtherinDao.selectByExample(example);
		for (TWhOtherin tWhOtherin : selectByExample) {
			TWhOtherindetailQuery example1 = new TWhOtherindetailQuery();
			com.lander.wh.pojo.TWhOtherindetailQuery.Criteria createCriteria2 = example1.createCriteria();
			createCriteria2.andFmasteridEqualTo(tWhOtherin.getFid());
			createCriteria2.andFmaterialidEqualTo(query.getMaterialId());
			List<TWhOtherindetail> selectByExample2 = tWhOtherindetailDao.selectByExample(example1);
			for (TWhOtherindetail tWhOtherindetail : selectByExample2) {
				RptWhStockInv rptWhStockInv = new RptWhStockInv();
				rptWhStockInv.setInvType(InvTypeEnum.OTHERIN);
				rptWhStockInv.setInvTypeName(InvTypeEnum.OTHERIN.toString());
				rptWhStockInv.setBizDate(tWhOtherin.getFbizdate());
				rptWhStockInv.setInvNumber(tWhOtherin.getFnumber());
				rptWhStockInv.setQty_in(tWhOtherindetail.getFqty());
				resultList.add(rptWhStockInv);
			}
		}
		return resultList;
	}

	/**
	 * 获取给定期间、仓库、物料的其它出仓单有涉及的单明细清单 @Title:
	 * getOtherIn @Description: @param @param query @param @return @return
	 * List<RptWhStockInv> @throws
	 */
	private List<RptWhStockInv> getOtherOut(RptWhStockInvQuery query) {

		TWhPeriod tWhPeriod = tWhPeriodDao.selectByPrimaryKey(query.getPeriodId());

		List<RptWhStockInv> resultList = new ArrayList<RptWhStockInv>();

		TWhOtheroutQuery example = new TWhOtheroutQuery();
		com.lander.wh.pojo.TWhOtheroutQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFwarehouseidEqualTo(query.getWarehouseId());
		createCriteria.andFstateidEqualTo(2);
		createCriteria.andFbizdateBetween(tWhPeriod.getFfrom(), tWhPeriod.getFto());

		List<TWhOtherout> selectByExample = tWhOtheroutDao.selectByExample(example);
		for (TWhOtherout tWhOtherout : selectByExample) {
			TWhOtheroutdetailQuery example1 = new TWhOtheroutdetailQuery();
			com.lander.wh.pojo.TWhOtheroutdetailQuery.Criteria createCriteria2 = example1.createCriteria();
			createCriteria2.andFmasteridEqualTo(tWhOtherout.getFid());
			createCriteria2.andFmaterialidEqualTo(query.getMaterialId());
			List<TWhOtheroutdetail> selectByExample2 = tWhOtheroutdetailDao.selectByExample(example1);
			for (TWhOtheroutdetail tWhOtheroutdetail : selectByExample2) {
				RptWhStockInv rptWhStockInv = new RptWhStockInv();
				rptWhStockInv.setInvType(InvTypeEnum.OTHEROUT);
				rptWhStockInv.setInvTypeName(InvTypeEnum.OTHEROUT.toString());
				rptWhStockInv.setBizDate(tWhOtherout.getFbizdate());
				rptWhStockInv.setInvNumber(tWhOtherout.getFnumber());
				rptWhStockInv.setQty_out(tWhOtheroutdetail.getFqty());
				resultList.add(rptWhStockInv);
			}
		}
		return resultList;
	}

	/**
	 * 获取给定期间、仓库、物料的生产领料出仓单有涉及的单明细清单 @Title:
	 * getOtherIn @Description: @param @param query @param @return @return
	 * List<RptWhStockInv> @throws
	 */
	private List<RptWhStockInv> getPicking(RptWhStockInvQuery query) {

		TWhPeriod tWhPeriod = tWhPeriodDao.selectByPrimaryKey(query.getPeriodId());

		List<RptWhStockInv> resultList = new ArrayList<RptWhStockInv>();

		TWhPickingQuery example = new TWhPickingQuery();
		com.lander.wh.pojo.TWhPickingQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFwarehouseidEqualTo(query.getWarehouseId());
		createCriteria.andFstateidEqualTo(2);
		createCriteria.andFbizdateBetween(tWhPeriod.getFfrom(), tWhPeriod.getFto());

		List<TWhPicking> selectByExample = tWhPickingDao.selectByExample(example);
		for (TWhPicking tWhPicking : selectByExample) {
			TWhPickingdetailQuery example1 = new TWhPickingdetailQuery();
			com.lander.wh.pojo.TWhPickingdetailQuery.Criteria createCriteria2 = example1.createCriteria();
			createCriteria2.andFmasteridEqualTo(tWhPicking.getFid());
			createCriteria2.andFmaterialidEqualTo(query.getMaterialId());
			List<TWhPickingdetail> selectByExample2 = tWhPickingdetailDao.selectByExample(example1);
			for (TWhPickingdetail tWhPickingdetail : selectByExample2) {
				RptWhStockInv rptWhStockInv = new RptWhStockInv();
				rptWhStockInv.setInvType(InvTypeEnum.PICKING);
				rptWhStockInv.setInvTypeName(InvTypeEnum.PICKING.toString());
				rptWhStockInv.setBizDate(tWhPicking.getFbizdate());
				rptWhStockInv.setInvNumber(tWhPicking.getFnumber());
				rptWhStockInv.setQty_out(tWhPickingdetail.getFqty());
				resultList.add(rptWhStockInv);
			}
		}
		return resultList;
	}

	/**
	 * 获取给定期间、仓库、物料的其它入仓单有涉及的单明细清单 @Title:
	 * getOtherIn @Description: @param @param query @param @return @return
	 * List<RptWhStockInv> @throws
	 */
	private List<RptWhStockInv> getReceive(RptWhStockInvQuery query) {

		TWhPeriod tWhPeriod = tWhPeriodDao.selectByPrimaryKey(query.getPeriodId());

		List<RptWhStockInv> resultList = new ArrayList<RptWhStockInv>();

		TWhReceiveQuery example = new TWhReceiveQuery();
		com.lander.wh.pojo.TWhReceiveQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFwarehouseidEqualTo(query.getWarehouseId());
		createCriteria.andFstateidEqualTo(2);
		createCriteria.andFbizdateBetween(tWhPeriod.getFfrom(), tWhPeriod.getFto());

		List<TWhReceive> selectByExample = tWhReceiveDao.selectByExample(example);
		for (TWhReceive tWhReceive : selectByExample) {
			TWhReceivedetailQuery example1 = new TWhReceivedetailQuery();
			com.lander.wh.pojo.TWhReceivedetailQuery.Criteria createCriteria2 = example1.createCriteria();
			createCriteria2.andFmasteridEqualTo(tWhReceive.getFid());
			createCriteria2.andFmaterialidEqualTo(query.getMaterialId());
			List<TWhReceivedetail> selectByExample2 = tWhReceivedetailDao.selectByExample(example1);
			for (TWhReceivedetail tWhReceivedetail : selectByExample2) {
				RptWhStockInv rptWhStockInv = new RptWhStockInv();
				rptWhStockInv.setInvType(InvTypeEnum.RECEIVE);
				rptWhStockInv.setInvTypeName(InvTypeEnum.RECEIVE.toString());
				rptWhStockInv.setBizDate(tWhReceive.getFbizdate());
				rptWhStockInv.setInvNumber(tWhReceive.getFnumber());
				rptWhStockInv.setQty_in(tWhReceivedetail.getFqty());
				resultList.add(rptWhStockInv);
			}
		}
		return resultList;
	}

	/**
	 * 获取给定期间、仓库、物料的其它入仓单有涉及的单明细清单 @Title:
	 * getOtherIn @Description: @param @param query @param @return @return
	 * List<RptWhStockInv> @throws
	 */
	private List<RptWhStockInv> getTransferin(RptWhStockInvQuery query) {

		TWhPeriod tWhPeriod = tWhPeriodDao.selectByPrimaryKey(query.getPeriodId());

		List<RptWhStockInv> resultList = new ArrayList<RptWhStockInv>();

		TWhTransferinQuery example = new TWhTransferinQuery();
		com.lander.wh.pojo.TWhTransferinQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFwarehouseidEqualTo(query.getWarehouseId());
		createCriteria.andFstateidEqualTo(2);
		createCriteria.andFbizdateBetween(tWhPeriod.getFfrom(), tWhPeriod.getFto());

		List<TWhTransferin> selectByExample = tWhTransferinDao.selectByExample(example);
		for (TWhTransferin tWhTransferin : selectByExample) {
			TWhTransferindetailQuery example1 = new TWhTransferindetailQuery();
			com.lander.wh.pojo.TWhTransferindetailQuery.Criteria createCriteria2 = example1.createCriteria();
			createCriteria2.andFmasteridEqualTo(tWhTransferin.getFid());
			createCriteria2.andFmaterialidEqualTo(query.getMaterialId());
			List<TWhTransferindetail> selectByExample2 = tWhTransferindetailDao.selectByExample(example1);
			for (TWhTransferindetail tWhTransferindetail : selectByExample2) {
				RptWhStockInv rptWhStockInv = new RptWhStockInv();
				rptWhStockInv.setInvType(InvTypeEnum.TRANSFERIN);
				rptWhStockInv.setInvTypeName(InvTypeEnum.TRANSFERIN.toString());
				rptWhStockInv.setBizDate(tWhTransferin.getFbizdate());
				rptWhStockInv.setInvNumber(tWhTransferin.getFnumber());
				rptWhStockInv.setQty_in(tWhTransferindetail.getFqty());
				resultList.add(rptWhStockInv);
			}
		}
		return resultList;
	}
	
	
	/**
	 * 获取给定期间、仓库、物料的生产领料出仓单有涉及的单明细清单 @Title:
	 * getOtherIn @Description: @param @param query @param @return @return
	 * List<RptWhStockInv> @throws
	 */
	private List<RptWhStockInv> getTransferout(RptWhStockInvQuery query) {

		TWhPeriod tWhPeriod = tWhPeriodDao.selectByPrimaryKey(query.getPeriodId());

		List<RptWhStockInv> resultList = new ArrayList<RptWhStockInv>();

		TWhTransferoutQuery example = new TWhTransferoutQuery();
		com.lander.wh.pojo.TWhTransferoutQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFwarehouseidEqualTo(query.getWarehouseId());
		createCriteria.andFstateidEqualTo(2);
		createCriteria.andFbizdateBetween(tWhPeriod.getFfrom(), tWhPeriod.getFto());

		List<TWhTransferout> selectByExample = tWhTransferoutDao.selectByExample(example);
		for (TWhTransferout tWhTransferout : selectByExample) {
			TWhTransferoutdetailQuery example1 = new TWhTransferoutdetailQuery();
			com.lander.wh.pojo.TWhTransferoutdetailQuery.Criteria createCriteria2 = example1.createCriteria();
			createCriteria2.andFmasteridEqualTo(tWhTransferout.getFid());
			createCriteria2.andFmaterialidEqualTo(query.getMaterialId());
			List<TWhTransferoutdetail> selectByExample2 = tWhTransferoutdetailDao.selectByExample(example1);
			for (TWhTransferoutdetail tWhTransferoutdetail : selectByExample2) {
				RptWhStockInv rptWhStockInv = new RptWhStockInv();
				rptWhStockInv.setInvType(InvTypeEnum.TRANSFEROUT);
				rptWhStockInv.setInvTypeName(InvTypeEnum.TRANSFEROUT.toString());
				rptWhStockInv.setBizDate(tWhTransferout.getFbizdate());
				rptWhStockInv.setInvNumber(tWhTransferout.getFnumber());
				rptWhStockInv.setQty_out(tWhTransferoutdetail.getFqty());
				resultList.add(rptWhStockInv);
			}
		}
		return resultList;
	}
	
	/**
	 * 获取给定期间、仓库、物料的生产领料出仓单有涉及的单明细清单 @Title:
	 * getOtherIn @Description: @param @param query @param @return @return
	 * List<RptWhStockInv> @throws
	 */
	private List<RptWhStockInv> getSale(RptWhStockInvQuery query) {

		TWhPeriod tWhPeriod = tWhPeriodDao.selectByPrimaryKey(query.getPeriodId());

		List<RptWhStockInv> resultList = new ArrayList<RptWhStockInv>();

		TWhSaleQuery example = new TWhSaleQuery();
		com.lander.wh.pojo.TWhSaleQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFwarehouseidEqualTo(query.getWarehouseId());
		createCriteria.andFstateidEqualTo(2);
		createCriteria.andFbizdateBetween(tWhPeriod.getFfrom(), tWhPeriod.getFto());

		List<TWhSale> selectByExample = tWhSaleDao.selectByExample(example);
		for (TWhSale tWhSale : selectByExample) {
			TWhSaledetailQuery example1 = new TWhSaledetailQuery();
			com.lander.wh.pojo.TWhSaledetailQuery.Criteria createCriteria2 = example1.createCriteria();
			createCriteria2.andFmasteridEqualTo(tWhSale.getFid());
			createCriteria2.andFmaterialidEqualTo(query.getMaterialId());
			List<TWhSaledetail> selectByExample2 = tWhSaledetailDao.selectByExample(example1);
			for (TWhSaledetail tWhSaledetail : selectByExample2) {
				RptWhStockInv rptWhStockInv = new RptWhStockInv();
				rptWhStockInv.setInvType(InvTypeEnum.SALE);
				rptWhStockInv.setInvTypeName(InvTypeEnum.SALE.toString());
				rptWhStockInv.setBizDate(tWhSale.getFbizdate());
				rptWhStockInv.setInvNumber(tWhSale.getFnumber());
				rptWhStockInv.setQty_out(tWhSaledetail.getFqty());
				resultList.add(rptWhStockInv);
			}
		}
		return resultList;
	}
}
