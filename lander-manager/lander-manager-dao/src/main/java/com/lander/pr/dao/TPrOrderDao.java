package com.lander.pr.dao;

import com.lander.pr.pojo.TPrOrder;
import com.lander.pr.pojo.TPrOrderQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPrOrderDao {
    int countByExample(TPrOrderQuery example);

    int deleteByExample(TPrOrderQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TPrOrder record);

    int insertSelective(TPrOrder record);

    List<TPrOrder> selectByExample(TPrOrderQuery example);

    TPrOrder selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TPrOrder record, @Param("example") TPrOrderQuery example);

    int updateByExample(@Param("record") TPrOrder record, @Param("example") TPrOrderQuery example);

    int updateByPrimaryKeySelective(TPrOrder record);

    int updateByPrimaryKey(TPrOrder record);
}