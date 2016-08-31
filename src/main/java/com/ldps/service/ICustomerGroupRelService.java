package com.ldps.service;

import java.util.List;

import com.ldps.model.CusGroupRelModel;
import com.ldps.model.CustomerGroupModel;


public interface ICustomerGroupRelService {

	public int delUserGroupRelation(Long userId, Integer groupId);

	public int addUserGroupRelation(Long userId, Integer groupId);

	List<CusGroupRelModel> queryByGroupId(Integer groupId);

}
