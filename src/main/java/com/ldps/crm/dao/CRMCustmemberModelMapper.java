package com.ldps.crm.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.crm.model.CRMCustmemberModel;

public interface CRMCustmemberModelMapper {
    //int deleteByPrimaryKey(String cmmemid);

    //int insert(CRMCustmemberModel record);

    //int insertSelective(CRMCustmemberModel record);

    CRMCustmemberModel selectByPrimaryKey(String cmmemid);
    
    List<CRMCustmemberModel> selectByMaintdate(@Param("maintdate")Date maintdate);

    //int updateByPrimaryKeySelective(CRMCustmemberModel record);

    //int updateByPrimaryKey(CRMCustmemberModel record);
}