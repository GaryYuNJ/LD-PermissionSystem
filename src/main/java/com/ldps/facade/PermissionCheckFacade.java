package com.ldps.facade;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.CustomerModel;
import com.ldps.model.ResourceGroupModel;
import com.ldps.model.CustomerGroupModel;
import com.ldps.model.ResourceModel;
import com.ldps.service.ICusGrpResourceRelService;
import com.ldps.service.ICusResourceRelService;
import com.ldps.service.ICustomerService;
import com.ldps.service.IResourceService;
import java.util.List;


@Service("permissionCheckFacade")
public class PermissionCheckFacade {

	@Resource
	private ICustomerService iCustomerSevice;
	@Resource
	private IResourceService iResourceService;
	@Resource
	ICusResourceRelService iCusResourceRelService;
	@Resource
	ICusGrpResourceRelService iCusGrpResourceRelService;
	
	//验证用户是否有权限操作资源
	public String verification(String cid, String mac) {
		
		String errorCode = "0";
		if(StringUtils.isEmpty(cid) || StringUtils.isEmpty(mac)){
			errorCode = "E001"; //入参有空值
		}else{
			CustomerModel customerModel = iCustomerSevice.getModelWithGroupsByCID(cid);
			if(null == customerModel){
				errorCode = "E002"; //用户不存在
			}else{
				ResourceModel rModel = iResourceService.queryWithGroupsByMAC(mac);
				if(null == rModel || "N".equals(rModel.getStatus())){
					errorCode = "E003"; //设备不可用
				}else{
					//检查资源是否属于公共资源组
					int publicFlag = 1;
					if(null ==rModel.getResourceGroups() || rModel.getResourceGroups().size() ==0){
						publicFlag = 0;
					}else{
						for(ResourceGroupModel rGroupModel : rModel.getResourceGroups()){
							//只要有一个组非公共资源，该资源就被认为不是公共资源
							if(!"Y".equals(rGroupModel.getIsPublic())){
								publicFlag = 0;
								break;
//							}else if("N".equals(rGroupModel.getStatus())){
//								publicFlag = 0;
//							}else{
//								publicFlag = 1;
							}
						}
					}
					//公共资源，直接返回0
					//非公共资源，继续验证权限
					if(publicFlag == 0){
						CusResourceRelModel cusRRModel = new CusResourceRelModel();
						cusRRModel.setCid(cid);
						cusRRModel.setResourceId(rModel.getId());
						cusRRModel = iCusResourceRelService.queryModelByCidAndResId(cusRRModel);
						//没有资源与用户的绑定记录，需要验证用户组与资源的关系
						if(null == cusRRModel){
							//用户没有绑定任何用户组
							if(null == customerModel.getCustomerGroups() || customerModel.getCustomerGroups().size() ==0){
								errorCode = "E006"; //该用户&所属用户组没有配置权限使用该资源
							}else{
								for(CustomerGroupModel cGModel : customerModel.getCustomerGroups()){
									CusGrpResourceRelModel cGrpResRelModel = new CusGrpResourceRelModel();
									cGrpResRelModel.setCgroupId(cGModel.getId());
									cGrpResRelModel.setResourceId(rModel.getId());
									cGrpResRelModel = iCusGrpResourceRelService.queryModelByCidAndResId(cGrpResRelModel);
									//只要有一个所属用户组禁用该资源，该用户就不能使用
									if("N".equals(cGrpResRelModel.getEnable())){
										errorCode = "E005"; //该用户所属用户组禁用这个资源
										break;
									}
								}
							}
						//禁用
						}else if("N".equals(cusRRModel.getEnable())){
							errorCode = "E004"; //该用户禁用这个资源
						}
					}
				}
			}
		}
		return errorCode;
	}



	public ICustomerService getiCustomerSevice() {
		return iCustomerSevice;
	}

	public void setiCustomerSevice(ICustomerService iCustomerSevice) {
		this.iCustomerSevice = iCustomerSevice;
	}

	public IResourceService getiResourceService() {
		return iResourceService;
	}

	public void setiResourceService(IResourceService iResourceService) {
		this.iResourceService = iResourceService;
	}
	
}
