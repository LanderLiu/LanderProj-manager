package com.lander.bd.dao;

import com.lander.bd.pojo.TBdInvnumber;
import com.lander.bd.pojo.TBdInvnumberQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBdInvnumberDao {
    int countByExample(TBdInvnumberQuery example);

    int deleteByExample(TBdInvnumberQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TBdInvnumber record);

    int insertSelective(TBdInvnumber record);

    List<TBdInvnumber> selectByExample(TBdInvnumberQuery example);

    TBdInvnumber selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TBdInvnumber record, @Param("example") TBdInvnumberQuery example);

    int updateByExample(@Param("record") TBdInvnumber record, @Param("example") TBdInvnumberQuery example);

    int updateByPrimaryKeySelective(TBdInvnumber record);

    int updateByPrimaryKey(TBdInvnumber record);
}