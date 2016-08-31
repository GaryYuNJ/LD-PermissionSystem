package com.ldps.service;

import java.util.List;

import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;

public interface ICusGrpResourceRelService {

	CusGrpResourceRelModel queryModelByCidAndResId(CusGrpResourceRelModel model);

	List<CusGrpResourceRelModel> queryByGroupIdListAndResId(List<Integer> groupIds,
			Integer resourceId);

	int disableCusGrpResPermission(Integer cusGrpId, Integer resourceId);

	int authCusGrpResPermission(CusGrpResourceRelModel cusResourceRelModel);

	int jointAuthCusGrpResPermission(
			CusGrpResourceRelModel cusGrpResourceRelModel); 

	 
}
