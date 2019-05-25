package com.lander.manager.rpt.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.TBdMaterialDao;
import com.lander.bd.dao.TBdWarehouseDao;
import com.lander.bd.pojo.TBdMaterial;
import com.lander.bd.pojo.TBdMaterialQuery;
import com.lander.bd.pojo.TBdMaterialQuery.Criteria;
import com.lander.bd.pojo.TBdWarehouse;
import com.lander.common.pojo.EasyUIDataGridResult;
import com.lander.common.utils.IDUtils;
import com.lander.manager.rpt.service.RptWhStockService;
import com.lander.rpt.pojo.RptWhStock;
import com.lander.rpt.pojo.RptWhStockQuery;
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
public class RptWhStockServiceImpl implements RptWhStockService {
	@Autowired
	private TBdMaterialDao tBdMaterialDao;
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
	private TWhSaleDao tWhSaleDao;
	@Autowired
	private TWhSaledetailDao tWhSaledetailDao;
	
	@Autowired
	private TWhPeriodDao tWhPeriodDao;
	@Autowired
	private TWhMaterialinventoryDao tWhMaterialinventoryDao;

	@Autowired
	private TBdWarehouseDao tBdWarehouseDao;

	@Autowired
	private TWhTransferinDao tWhTransferinDao;
	@Autowired
	private TWhTransferindetailDao tWhTransferindetailDao;

	@Autowired
	private TWhTransferoutDao tWhTransferoutDao;
	@Autowired
	private TWhTransferoutdetailDao tWhTransferoutdetailDao;
	/**
	 * 库存进销存报表
	 */
	@Override
	public EasyUIDataGridResult getList(int page, int rows, String orderByClause, String asc,
			RptWhStockQuery rptWhStockQuery) {
		TWhPeriod tWhPeriod = tWhPeriodDao.selectByPrimaryKey(rptWhStockQuery.getPeriodId());
		TBdWarehouse selectByPrimaryKey = tBdWarehouseDao.selectByPrimaryKey(rptWhStockQuery.getWarehouseId());
		List<RptWhStock> resultList = new ArrayList<RptWhStock>();
		// 1.获取所有有效的物料
		TBdMaterialQuery tBdMaterialQuery = new TBdMaterialQuery();
		Criteria createCriteria = tBdMaterialQuery.createCriteria();
		createCriteria.andFstateidEqualTo(1);
		tBdMaterialQuery.setPageNo(page);
		tBdMaterialQuery.setPageSize(rows);
		tBdMaterialQuery.setOrderByClause(orderByClause + " " + asc);
		List<TBdMaterial> example = tBdMaterialDao.selectByExample(tBdMaterialQuery);

		for (TBdMaterial bdMaterial : example) {
			RptWhStock rptWhStock = new RptWhStock();
			rptWhStock.setFid(IDUtils.genId());
			rptWhStock.setFmaterialid(bdMaterial.getFid());
			rptWhStock.setFmaterialname(bdMaterial.getFname());
			rptWhStock.setFmaterialnumber(bdMaterial.getFnumber());
			rptWhStock.setFmaterialspecification(bdMaterial.getFspecification());
			rptWhStock.setFperiodid(rptWhStockQuery.getPeriodId());
			rptWhStock.setFperiodname(tWhPeriod.getFname());
			rptWhStock.setFperiodnumber(tWhPeriod.getFnumber());
			rptWhStock.setFwarehouseid(rptWhStockQuery.getWarehouseId());
			rptWhStock.setFwarehousename(selectByPrimaryKey.getFname());
			rptWhStock.setFwarehousenumber(selectByPrimaryKey.getFnumber());
			// 2.获取物料的期初库存(),不存的的，则置为0
			// 3.获取物料的进仓数量
			// 4.获取物料的出仓数量
			// 5.计算期末结余数量

			BigDecimal fqtyInit = getInitQty(rptWhStockQuery.getPeriodId(), rptWhStockQuery.getWarehouseId(),
					bdMaterial.getFid());
			rptWhStock.setFqtyInit(fqtyInit);
			BigDecimal fqtyIn = getInQty(rptWhStockQuery.getPeriodId(), rptWhStockQuery.getWarehouseId(),
					bdMaterial.getFid());
			rptWhStock.setFqtyIn(fqtyIn);
			BigDecimal fqtyOut = getOutQty(rptWhStockQuery.getPeriodId(), rptWhStockQuery.getWarehouseId(),
					bdMaterial.getFid());
			rptWhStock.setFqtyOut(fqtyOut);
			rptWhStock.setFqtyEnd(fqtyInit.add(fqtyIn).subtract(fqtyOut));

			resultList.add(rptWhStock);
		}

		// 创建一个返回值对象
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setRows(resultList);

		result.setTotal(tBdMaterialDao.countByExample(tBdMaterialQuery));
		return result;
	}

	/**
	 * 获取某物料在某期间、某仓库的期初库存数量 @Title: getInitQty @Description: @param @param
	 * periodId @param @param warehouseId @param @param
	 * materialId @param @return @return BigDecimal @throws
	 */
	private BigDecimal getInitQty(Long periodId, Long warehouseId, Long materialId) {
		TWhMaterialinventoryQuery example = new TWhMaterialinventoryQuery();
		com.lander.wh.pojo.TWhMaterialinventoryQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFperiodidEqualTo(periodId);
		createCriteria.andFwarehouseidEqualTo(warehouseId);
		createCriteria.andFmaterialidEqualTo(materialId);
		List<TWhMaterialinventory> selectByExample = tWhMaterialinventoryDao.selectByExample(example);
		if (selectByExample.size() == 1) {
			return selectByExample.get(0).getFqtyInit();
		} else {
			return BigDecimal.ZERO;
		}
	}

	/**
	 * 获取一个物料在某期间、某仓库的进仓总量 @Title: getInQty @Description: @param @param
	 * periodId @param @param warehouseId @param @param
	 * materialId @param @return @return BigDecimal @throws
	 */
	private BigDecimal getInQty(Long periodId, Long warehouseId, Long materialId) {
		BigDecimal result = BigDecimal.ZERO;
		// 准备期间对象
		TWhPeriod selectByPrimaryKey = tWhPeriodDao.selectByPrimaryKey(periodId);

		result = result.add(getInQtyFromWhOtherin(selectByPrimaryKey, warehouseId, materialId));
		result = result.add(getInQtyFromWhReceive(selectByPrimaryKey, warehouseId, materialId));
		result = result.add(getInQtyFromWhTransferin(selectByPrimaryKey, warehouseId, materialId));
		return result;
	}

	/**
	 * 获取收货入仓单的进仓数量 @Title: getInQtyFromWhReceive @Description: @param @param
	 * periodInfo @param @param warehouseId @param @param
	 * materialId @param @return @return BigDecimal @throws
	 */
	private BigDecimal getInQtyFromWhReceive(TWhPeriod periodInfo, Long warehouseId, Long materialId) {
		BigDecimal result = BigDecimal.ZERO;
		TWhReceiveQuery exampleReceive = new TWhReceiveQuery();
		com.lander.wh.pojo.TWhReceiveQuery.Criteria criteriaReceive = exampleReceive.createCriteria();
		criteriaReceive.andFstateidEqualTo(2);
		criteriaReceive.andFbizdateBetween(periodInfo.getFfrom(), periodInfo.getFto());
		criteriaReceive.andFwarehouseidEqualTo(warehouseId);

		List<TWhReceive> exampleReceive2 = tWhReceiveDao.selectByExample(exampleReceive);
		for (TWhReceive tWhReceive : exampleReceive2) {
			TWhReceivedetailQuery exampleReceive3 = new TWhReceivedetailQuery();
			com.lander.wh.pojo.TWhReceivedetailQuery.Criteria criteriaReceive2 = exampleReceive3.createCriteria();
			criteriaReceive2.andFmasteridEqualTo(tWhReceive.getFid());
			criteriaReceive2.andFmaterialidEqualTo(materialId);
			List<TWhReceivedetail> exampleReceive4 = tWhReceivedetailDao.selectByExample(exampleReceive3);
			for (TWhReceivedetail tWhReceivedetail : exampleReceive4) {
				result = result.add(tWhReceivedetail.getFqty());
			}
		}
		return result;
	}

	/**
	 * 获取其它入仓单的进仓数量 @Title: getWhOtherinQty @Description: @param @param
	 * periodInfo @param @param warehouseId @param @param
	 * materialId @param @return @return BigDecimal @throws
	 */
	private BigDecimal getInQtyFromWhOtherin(TWhPeriod periodInfo, Long warehouseId, Long materialId) {
		BigDecimal result = BigDecimal.ZERO;
		// 1.获取其它入仓单的进仓数量
		TWhOtherinQuery example = new TWhOtherinQuery();
		com.lander.wh.pojo.TWhOtherinQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFstateidEqualTo(2);
		createCriteria.andFbizdateBetween(periodInfo.getFfrom(), periodInfo.getFto());
		createCriteria.andFwarehouseidEqualTo(warehouseId);

		List<TWhOtherin> example2 = tWhOtherinDao.selectByExample(example);
		for (TWhOtherin tWhOtherin : example2) {
			TWhOtherindetailQuery example3 = new TWhOtherindetailQuery();
			com.lander.wh.pojo.TWhOtherindetailQuery.Criteria createCriteria2 = example3.createCriteria();
			createCriteria2.andFmasteridEqualTo(tWhOtherin.getFid());
			createCriteria2.andFmaterialidEqualTo(materialId);
			List<TWhOtherindetail> example4 = tWhOtherindetailDao.selectByExample(example3);
			for (TWhOtherindetail tWhOtherindetail : example4) {
				result = result.add(tWhOtherindetail.getFqty());
			}
		}
		return result;
	}
	/**
	 * 获取调拨入仓单的进仓数量
	* @Title: getInQtyFromWhTransferin 
	* @Description: 
	* @param @param periodInfo
	* @param @param warehouseId
	* @param @param materialId
	* @param @return    
	* @return BigDecimal   
	* @throws
	 */
	private BigDecimal getInQtyFromWhTransferin(TWhPeriod periodInfo, Long warehouseId, Long materialId) {
		BigDecimal result = BigDecimal.ZERO;
		// 1.获取其它入仓单的进仓数量
		TWhTransferinQuery example = new TWhTransferinQuery();
		com.lander.wh.pojo.TWhTransferinQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFstateidEqualTo(2);
		createCriteria.andFbizdateBetween(periodInfo.getFfrom(), periodInfo.getFto());
		createCriteria.andFwarehouseidEqualTo(warehouseId);

		List<TWhTransferin> example2 = tWhTransferinDao.selectByExample(example);
		for (TWhTransferin tWhTransferin : example2) {
			TWhTransferindetailQuery example3 = new TWhTransferindetailQuery();
			com.lander.wh.pojo.TWhTransferindetailQuery.Criteria createCriteria2 = example3.createCriteria();
			createCriteria2.andFmasteridEqualTo(tWhTransferin.getFid());
			createCriteria2.andFmaterialidEqualTo(materialId);
			List<TWhTransferindetail> example4 = tWhTransferindetailDao.selectByExample(example3);
			for (TWhTransferindetail tWhTransferindetail : example4) {
				result = result.add(tWhTransferindetail.getFqty());
			}
		}
		return result;
	}
	/**
	 * 获取一个物料在某期间、某仓库的出仓总量 @Title: getInQty @Description: @param @param
	 * periodId @param @param warehouseId @param @param
	 * materialId @param @return @return BigDecimal @throws
	 */
	private BigDecimal getOutQty(Long periodId, Long warehouseId, Long materialId) {
		BigDecimal result = BigDecimal.ZERO;
		// 准备期间对象
		TWhPeriod selectByPrimaryKey = tWhPeriodDao.selectByPrimaryKey(periodId);

		result = result.add(getOutQtyFromWhOtherout(selectByPrimaryKey, warehouseId, materialId));
		result = result.add(getOutQtyFromWhPicking(selectByPrimaryKey, warehouseId, materialId));
		result = result.add(getOutQtyFromWhTransferout(selectByPrimaryKey, warehouseId, materialId));
		result = result.add(getOutQtyFromWhSale(selectByPrimaryKey, warehouseId, materialId));
		return result;
	}

	/**
	 * 获取其它出仓单的进仓数量
	* @Title: getOutQtyFromWhOtherout 
	* @Description: 
	* @param @param periodInfo
	* @param @param warehouseId
	* @param @param materialId
	* @param @return    
	* @return BigDecimal   
	* @throws
	 */
	private BigDecimal getOutQtyFromWhOtherout(TWhPeriod periodInfo, Long warehouseId, Long materialId) {
		
		BigDecimal result = BigDecimal.ZERO;
		TWhOtheroutQuery example = new TWhOtheroutQuery();
		com.lander.wh.pojo.TWhOtheroutQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFstateidEqualTo(2);
		createCriteria.andFbizdateBetween(periodInfo.getFfrom(), periodInfo.getFto());
		createCriteria.andFwarehouseidEqualTo(warehouseId);

		List<TWhOtherout> example2 = tWhOtheroutDao.selectByExample(example);
		for (TWhOtherout tWhOtherout : example2) {
			TWhOtheroutdetailQuery example3 = new TWhOtheroutdetailQuery();
			com.lander.wh.pojo.TWhOtheroutdetailQuery.Criteria createCriteria2 = example3.createCriteria();
			createCriteria2.andFmasteridEqualTo(tWhOtherout.getFid());
			createCriteria2.andFmaterialidEqualTo(materialId);
			List<TWhOtheroutdetail> example4 = tWhOtheroutdetailDao.selectByExample(example3);
			for (TWhOtheroutdetail tWhOtheroutdetail : example4) {
				result = result.add(tWhOtheroutdetail.getFqty());
			}
		}
		return result;
	}
	/**
	 * 获取其它出仓单的进仓数量
	* @Title: getOutQtyFromWhOtherout 
	* @Description: 
	* @param @param periodInfo
	* @param @param warehouseId
	* @param @param materialId
	* @param @return    
	* @return BigDecimal   
	* @throws
	 */
	private BigDecimal getOutQtyFromWhTransferout(TWhPeriod periodInfo, Long warehouseId, Long materialId) {
		
		BigDecimal result = BigDecimal.ZERO;
		TWhTransferoutQuery example = new TWhTransferoutQuery();
		com.lander.wh.pojo.TWhTransferoutQuery.Criteria createCriteria = example.createCriteria();
		createCriteria.andFstateidEqualTo(2);
		createCriteria.andFbizdateBetween(periodInfo.getFfrom(), periodInfo.getFto());
		createCriteria.andFwarehouseidEqualTo(warehouseId);

		List<TWhTransferout> example2 = tWhTransferoutDao.selectByExample(example);
		for (TWhTransferout tWhTransferout : example2) {
			TWhTransferoutdetailQuery example3 = new TWhTransferoutdetailQuery();
			com.lander.wh.pojo.TWhTransferoutdetailQuery.Criteria createCriteria2 = example3.createCriteria();
			createCriteria2.andFmasteridEqualTo(tWhTransferout.getFid());
			createCriteria2.andFmaterialidEqualTo(materialId);
			List<TWhTransferoutdetail> example4 = tWhTransferoutdetailDao.selectByExample(example3);
			for (TWhTransferoutdetail tWhTransferoutdetail : example4) {
				result = result.add(tWhTransferoutdetail.getFqty());
			}
		}
		return result;
	}
	/**
	 * 获取领料出仓单的进仓数量
	* @Title: getOutQtyFromWhPicking 
	* @Description: 
	* @param @param periodInfo
	* @param @param warehouseId
	* @param @param materialId
	* @param @return    
	* @return BigDecimal   
	* @throws
	 */
	private BigDecimal getOutQtyFromWhPicking(TWhPeriod periodInfo, Long warehouseId, Long materialId) {		
		BigDecimal result = BigDecimal.ZERO;
		TWhPickingQuery examplePicking = new TWhPickingQuery();
		com.lander.wh.pojo.TWhPickingQuery.Criteria criteriaPicking = examplePicking.createCriteria();
		criteriaPicking.andFstateidEqualTo(2);
		criteriaPicking.andFbizdateBetween(periodInfo.getFfrom(), periodInfo.getFto());
		criteriaPicking.andFwarehouseidEqualTo(warehouseId);

		List<TWhPicking> examplePicking2 = tWhPickingDao.selectByExample(examplePicking);
		for (TWhPicking tWhPicking : examplePicking2) {
			TWhPickingdetailQuery examplePicking3 = new TWhPickingdetailQuery();
			com.lander.wh.pojo.TWhPickingdetailQuery.Criteria criteriaPicking2 = examplePicking3.createCriteria();
			criteriaPicking2.andFmasteridEqualTo(tWhPicking.getFid());
			criteriaPicking2.andFmaterialidEqualTo(materialId);
			List<TWhPickingdetail> examplePicking4 = tWhPickingdetailDao.selectByExample(examplePicking3);
			for (TWhPickingdetail tWhPickingdetail : examplePicking4) {
				result = result.add(tWhPickingdetail.getFqty());
			}
		}
		return result;
	}
	/**
	 * 获取销售出仓单的进仓数量
	* @Title: getOutQtyFromWhPicking 
	* @Description: 
	* @param @param periodInfo
	* @param @param warehouseId
	* @param @param materialId
	* @param @return    
	* @return BigDecimal   
	* @throws
	 */
	private BigDecimal getOutQtyFromWhSale(TWhPeriod periodInfo, Long warehouseId, Long materialId) {		
		BigDecimal result = BigDecimal.ZERO;
		TWhSaleQuery exampleSale = new TWhSaleQuery();
		com.lander.wh.pojo.TWhSaleQuery.Criteria criteriaSale = exampleSale.createCriteria();
		criteriaSale.andFstateidEqualTo(2);
		criteriaSale.andFbizdateBetween(periodInfo.getFfrom(), periodInfo.getFto());
		criteriaSale.andFwarehouseidEqualTo(warehouseId);

		List<TWhSale> exampleSale2 = tWhSaleDao.selectByExample(exampleSale);
		for (TWhSale tWhSale : exampleSale2) {
			TWhSaledetailQuery exampleSale3 = new TWhSaledetailQuery();
			com.lander.wh.pojo.TWhSaledetailQuery.Criteria criteriaSale2 = exampleSale3.createCriteria();
			criteriaSale2.andFmasteridEqualTo(tWhSale.getFid());
			criteriaSale2.andFmaterialidEqualTo(materialId);
			List<TWhSaledetail> exampleSale4 = tWhSaledetailDao.selectByExample(exampleSale3);
			for (TWhSaledetail tWhSaledetail : exampleSale4) {
				result = result.add(tWhSaledetail.getFqty());
			}
		}
		return result;
	}
}
