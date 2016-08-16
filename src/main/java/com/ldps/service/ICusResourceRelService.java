package com.ldps.service;

import com.ldps.model.CusResourceRelModel;

public interface ICusResourceRelService {

	//根据MAC查找资源(mac不为空时使用)
	CusResourceRelModel queryModelByCidAndResId(CusResourceRelModel model); 

	 
}
