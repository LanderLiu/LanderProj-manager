package com.lander.bd.dao;

import com.lander.bd.pojo.TBdMaterialcategory;
import com.lander.bd.pojo.TBdMaterialcategoryQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TBdMaterialcategoryDao {
    int countByExample(TBdMaterialcategoryQuery example);

    int deleteByExample(TBdMaterialcategoryQuery example);

    int deleteByPrimaryKey(Long fid);

    int insert(TBdMaterialcategory record);

    int insertSelective(TBdMaterialcategory record);

    List<TBdMaterialcategory> selectByExample(TBdMaterialcategoryQuery example);

    TBdMaterialcategory selectByPrimaryKey(Long fid);

    int updateByExampleSelective(@Param("record") TBdMaterialcategory record, @Param("example") TBdMaterialcategoryQuery example);

    int updateByExample(@Param("record") TBdMaterialcategory record, @Param("example") TBdMaterialcategoryQuery example);

    int updateByPrimaryKeySelective(TBdMaterialcategory record);

    int updateByPrimaryKey(TBdMaterialcategory record);
}