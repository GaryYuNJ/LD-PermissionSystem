package com.ldps.service;

import java.util.List;

import com.ldps.model.CusGroupResGroupRelModel;
import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.PermissionRecordModel;

public interface ICusGrpResourceRelService {

	CusGrpResourceRelModel queryModelByCidAndResId(CusGrpResourceRelModel model);

	List<CusGrpResourceRelModel> queryByGroupIdListAndResId(List<Integer> groupIds,
			Integer resourceId);

	int jointAuthCusGrpResGrpPermission(
			CusGroupResGroupRelModel cusGrpResGrpRelModel);

	int jointAuthCusGrpResPermission(
			CusGrpResourceRelModel cusGrpResourceRelModel,
			PermissionRecordModel permRecordModel);

	List<CusGrpResourceRelModel> queryByCusGroupId(Integer groupId);

	int authCusGrpResPermission(CusGrpResourceRelModel cusGrpResourceRelModel,
			PermissionRecordModel permRecordModel);

	int disableCusGrpResPermission(Integer cusGrpId, Integer resourceId,
			PermissionRecordModel permRecordModel); 

	 
}
