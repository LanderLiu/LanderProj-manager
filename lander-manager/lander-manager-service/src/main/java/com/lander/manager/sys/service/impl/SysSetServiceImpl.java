package com.lander.manager.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lander.manager.sys.service.SysSetService;
import com.lander.sys.dao.TSysSetDao;
import com.lander.sys.pojo.TSysSet;
import com.lander.sys.pojo.TSysSetQuery;
import com.lander.sys.pojo.TSysSetQuery.Criteria;

@Service
public class SysSetServiceImpl implements SysSetService{
	@Autowired
    private TSysSetDao sysSetDao ;
	
	@Override
	public String getByCode(String code) {
		// 1. 创建查询条件
		TSysSetQuery query = new TSysSetQuery();
		Criteria criteria = query.createCriteria();
        criteria.andFcodeEqualTo(code); 
        // 2. 根据条件查询
        List<TSysSet> list = sysSetDao.selectByExample(query);       
        // 4. 返回结果
        return list.get(0).getFvalue();
	}
}
