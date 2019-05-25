package com.lander.manager.bd.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.bd.dao.TBdInvnumberDao;
import com.lander.bd.pojo.TBdInvnumber;
import com.lander.bd.pojo.TBdInvnumberQuery;
import com.lander.bd.pojo.TBdInvnumberQuery.Criteria;
import com.lander.common.pojo.LanderResult;
import com.lander.common.utils.BizDateUtil;
import com.lander.common.utils.IDUtils;
import com.lander.common.utils.StringUtil;
import com.lander.manager.bd.service.BdInvnumberService;

@Service
public class BdInvnumberServiceImpl implements BdInvnumberService {

	@Autowired
	private TBdInvnumberDao tBdInvnumberDao;

	@Override
	public LanderResult SetFlow(String FLeaderStr, int FCategoryId, String userId) throws Exception, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		TBdInvnumberQuery bdInvnumberQuery = new TBdInvnumberQuery();
		Criteria createCriteria = bdInvnumberQuery.createCriteria();
		createCriteria.andFleaderstrEqualTo(FLeaderStr);
		createCriteria.andFcategoryidEqualTo(FCategoryId);
		List<TBdInvnumber> selectByQuery = tBdInvnumberDao.selectByExample(bdInvnumberQuery);
		if (selectByQuery.size() == 1) {
			TBdInvnumber bdInvnumber = selectByQuery.get(0);
			bdInvnumber.setFflow(bdInvnumber.getFflow() + 1);
			bdInvnumber = (TBdInvnumber) BizDateUtil.setModifyDefaultProperty(bdInvnumber, userId, TBdInvnumber.class);
			tBdInvnumberDao.updateByPrimaryKey(bdInvnumber);
			return LanderResult.build(200, "编码设置完成。");
		} else {
			TBdInvnumber bdInvnumber = new TBdInvnumber();
			bdInvnumber.setFcategoryid(FCategoryId);
			bdInvnumber.setFdescript("");
			bdInvnumber.setFflow(1);
			bdInvnumber.setFid(IDUtils.genId());
			bdInvnumber.setFleaderstr(FLeaderStr);
			bdInvnumber = (TBdInvnumber) BizDateUtil.setDefaultProperty(bdInvnumber, userId, TBdInvnumber.class);
			tBdInvnumberDao.insert(bdInvnumber);
			return LanderResult.build(200, "新编码设置完成。");
		}
	}

	@Override
	public String GetFlow(String FLeaderStr, int FCategoryId, String userId, int flowLong) throws SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, Exception {
		String flowNumber = "";
		for (int i = 0; i < flowLong; i++) {
			flowNumber += "0";
		}
		TBdInvnumberQuery example = new TBdInvnumberQuery();
		Criteria createCriteria = example.createCriteria();
		createCriteria.andFleaderstrEqualTo(FLeaderStr);
		createCriteria.andFcategoryidEqualTo(FCategoryId);
		int flow = 1;
		List<TBdInvnumber> selectByQuery = tBdInvnumberDao.selectByExample(example);
		SetFlow(FLeaderStr, FCategoryId, userId);
		if (selectByQuery.size() == 1) {
			TBdInvnumber invnumber = selectByQuery.get(0);
			flow = invnumber.getFflow() + 1;			
			return FLeaderStr + StringUtil.right(flowNumber + flow, flowLong);
		}else{
			
			return FLeaderStr + StringUtil.right(flowNumber + 1, flowLong);
		}		
	}
}
