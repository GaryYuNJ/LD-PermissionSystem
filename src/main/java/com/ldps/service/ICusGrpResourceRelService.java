package com.ldps.service;

import java.util.List;

import com.ldps.model.CusGrpResourceRelModel;

public interface ICusGrpResourceRelService {

	CusGrpResourceRelModel queryModelByCidAndResId(CusGrpResourceRelModel model);

	List<CusGrpResourceRelModel> queryByGroupIdListAndResId(List<Integer> groupIds,
			Integer resourceId); 

	 
}
