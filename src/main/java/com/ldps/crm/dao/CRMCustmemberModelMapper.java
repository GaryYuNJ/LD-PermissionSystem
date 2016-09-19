package com.ldps.crm.dao;

import com.ldps.crm.model.CRMCustmemberModel;

public interface CRMCustmemberModelMapper {
    //int deleteByPrimaryKey(String cmmemid);

    //int insert(CRMCustmemberModel record);

    //int insertSelective(CRMCustmemberModel record);

    CRMCustmemberModel selectByPrimaryKey(String cmmemid);

    //int updateByPrimaryKeySelective(CRMCustmemberModel record);

    //int updateByPrimaryKey(CRMCustmemberModel record);
}