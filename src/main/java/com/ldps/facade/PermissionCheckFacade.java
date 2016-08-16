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

import java.util.ArrayList;
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
		
		String verifiMessage = "0";
		if(StringUtils.isEmpty(cid) || StringUtils.isEmpty(mac)){
			verifiMessage = "E001"; //入参有空值
		}else{
			CustomerModel customerModel = iCustomerSevice.getModelWithGroupsByCID(cid);
			if(null == customerModel){
				verifiMessage = "E002"; //用户不存在
			}else{
				//先判断设备状态
				ResourceModel rModel = iResourceService.queryWithGroupsByMAC(mac);
				if(null == rModel || "N".equals(rModel.getStatus())){
					verifiMessage = "E003"; //设备不可用
				}else{
					//检查资源是否属于公共资源组(公共资源任何会员都有权限)
					int publicFlag = 0;
					//有隶属于资源组数据
					if(null !=rModel.getResourceGroups() && rModel.getResourceGroups().size() >0){
						for(ResourceGroupModel rGroupModel : rModel.getResourceGroups()){
							//只要有一个组非公共资源（有效状态），该资源就被认为不是公共资源
							if(!"Y".equals(rGroupModel.getIsPublic()) && "Y".equals(rGroupModel.getStatus())){
								publicFlag = 0;
								break;
							}else if("Y".equals(rGroupModel.getIsPublic()) && "Y".equals(rGroupModel.getStatus())){
								publicFlag = 1;
							}
						}
					}
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
								verifiMessage = "E006"; //该用户&所属用户组没有配置权限使用该资源
							}else{
								List<Integer> groupIds = new ArrayList<Integer>();
								for(CustomerGroupModel cGModel : customerModel.getCustomerGroups()){
									groupIds.add(cGModel.getId());
								}
								//获取用户组与资源的绑定关系
								List<CusGrpResourceRelModel> cGRRModels = 
										iCusGrpResourceRelService.queryByGroupIdListAndResId(groupIds, rModel.getId());
								
								if(null == cGRRModels || cGRRModels.size() == 0){
									verifiMessage = "E007"; //所有用户组与资源都没有匹配关系
								}else{
									for(CusGrpResourceRelModel cGRRModel: cGRRModels){
										if("N".equals(cGRRModel.getEnable())){
											verifiMessage = "E005"; //该用户所属用户组禁用这个资源(只要有一个用户组禁用，就看做是禁用状态)
											break;
										}
									}
								}
							}
						//禁用
						}else if("N".equals(cusRRModel.getEnable())){
							verifiMessage = "E004"; //该用户禁用这个资源
						}
					}
				}
			}
		}
		return verifiMessage;
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
