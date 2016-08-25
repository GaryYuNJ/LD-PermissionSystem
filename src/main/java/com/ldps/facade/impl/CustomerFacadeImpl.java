package com.ldps.facade.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ldps.converter.CusResourceRelModelConverter;
import com.ldps.converter.CustomerModelConverter;
import com.ldps.converter.ResourceModelConverter;
import com.ldps.data.CusResourceRelData;
import com.ldps.data.CustomerData;
import com.ldps.data.ResourceData;
import com.ldps.facade.CustomerFacade;
import com.ldps.model.CusGrpResourceRelModel;
import com.ldps.model.CusResourceRelModel;
import com.ldps.model.CustomerModel;
import com.ldps.model.ResourceGroupModel;
import com.ldps.model.CustomerGroupModel;
import com.ldps.model.ResourceModel;
import com.ldps.service.ICusGrpResourceRelService;
import com.ldps.service.ICusResourceRelService;
import com.ldps.service.ICustomerGroupRelService;
import com.ldps.service.ICustomerGroupService;
import com.ldps.service.ICustomerService;
import com.ldps.service.IResourceService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service("customerFacade")
public class CustomerFacadeImpl implements CustomerFacade {

	private static Logger logger = Logger
			.getLogger(CustomerFacadeImpl.class);
	
	@Resource
	private ICustomerService iCustomerSevice;
	@Resource
	private IResourceService iResourceService;
	@Resource
	ICusResourceRelService iCusResourceRelService;
	@Resource
	ICusGrpResourceRelService iCusGrpResourceRelService;
	@Resource
	ResourceModelConverter resourceModelConverter;
	@Resource
	CusResourceRelModelConverter cusResourceRelModelConverter;
	@Resource
	CustomerModelConverter customerModelConverter;
	@Resource
	ICustomerGroupService iCustomerGroupService;
	@Resource
	ICustomerGroupRelService iCustomerGroupRelService;
	

	@Override
	public int delUserGroupRelation(Long userId, Integer groupId) {
		// TODO Auto-generated method stub
		return iCustomerGroupRelService.delUserGroupRelation(userId, groupId);
	}

	@Override
	public int addUserGroupRelation(Long userId, Integer groupId) {
		// TODO Auto-generated method stub
		return iCustomerGroupRelService.addUserGroupRelation(userId, groupId);
	}



	@Override
	public CustomerData getUserDataByPrimaryId(Long CustomerId) {
		// TODO Auto-generated method stub
		CustomerModel cModel = iCustomerSevice.UserDataByPrimaryId(CustomerId);
		return customerModelConverter.process(cModel,null);
	}


	@Override
	public Integer queryCustomerTotalCount() {
		// TODO Auto-generated method stub
		return iCustomerSevice.queryCustomerTotalCount();
	}
	/*
		分页查询用户列表
	 */
	@Override
	public List<CustomerData> queryAllUserListWithPageIndex(Integer startRow, Integer pageSize) {
	
		List<CustomerModel> cModels = iCustomerSevice.queryAllWithPageIndex(startRow, pageSize);
		
		return customerModelConverter.processList(cModels);
	}
	/*
		根据mobile搜索用户列表
	 */
	@Override
	public CustomerData searchUserByMobileWithPageIndex(String mobile, Integer pageNo, Integer pageSize) {
	
		CustomerModel cModel = iCustomerSevice.getCustomerModelByMobile(mobile);
	
		return customerModelConverter.process(cModel,null);
	}
	
	/*
		获取building里的公共资源
	 */
	@Override
	public List<ResourceData> queryPubResByBuildingId(Integer buildingId) {

		List<ResourceModel> rModels = iResourceService.selectValidPubResByBuildingId(buildingId);
		
		return resourceModelConverter.processList(rModels);
	}
	
	/*
	获取building里用户有权限设备
	 */
	@Override
	public List<ResourceData> queryPrivateResByBIdAndMobile(Integer buildingId,
			String mobile) {
		
		Long customerId = iCustomerSevice.getCustomerIdByMobile(mobile);
		if(null == customerId){ 
			return null;
		}
		List<ResourceModel> rModels = iResourceService.queryPrivateResByBIdAndCusId(buildingId,customerId);
		
		return resourceModelConverter.processList(rModels);
	}


	//连带资源授权接口(对一个资源授权，需要连带授权上层所有基础资源(要使用授权资源的前提资源)。
	//by customerId、resourceId
	@Override
	public int jointAuthResPermissionByCusId(Long customerId,
			Integer resourceId, Date startDate, Date endDate, Long createUserId) {
		return iCusResourceRelService.jointAuthorizeResPermission(customerId, resourceId, startDate, 
				endDate, "N", createUserId);
	}

	//连带资源授权接口(对一个资源授权，需要连带授权上层所有基础资源(要使用授权资源的前提资源)。
	//by mobile、resourceKey
	@Override
	public int jointAuthResPermissionByMobile(String mobile,
			String resourceKey, Date startDate, Date endDate, Long createUserId) {
		Long customerId = iCustomerSevice.getCustomerIdByMobile(mobile);
		Integer resourceId = iResourceService.queryResourceIdByMAC(resourceKey);
		
		return iCusResourceRelService.jointAuthorizeResPermission(customerId, resourceId, startDate, 
				endDate, "N", createUserId);
	}
	
	//单个资源授权接口.by customerId、resourceId
	@Override
	public int authResPermissionByCusId(Long customerId, Integer resourceId,
			Date startDate, Date endDate, Long createUserId) {
		return iCusResourceRelService.authorizeResPermission(customerId, resourceId, startDate, 
				endDate, "N", createUserId);
	}
	
	//单个资源授权接口
	//by mobile、resourceKey
	@Override
	public int authResPermissionByMobile(String mobile, String resourceKey,
			Date startDate, Date endDate, Long createUserId) {
		Long customerId = iCustomerSevice.getCustomerIdByMobile(mobile);
		Integer resourceId = iResourceService.queryResourceIdByMAC(resourceKey);
		
		return iCusResourceRelService.authorizeResPermission(customerId, resourceId, startDate, 
				endDate, "N", createUserId);
	}


	//获取用户可分享权限的资源列表
	/*
		不包含公共资源，不包含用户组授权，只针对用户与资源的可用关系
	*/
	@Override
	public List<ResourceData> querySharableResource(String mobile) {
		
		Long customerId = iCustomerSevice.getCustomerIdByMobile(mobile);
		
		List<ResourceModel> resourceModel = iCustomerSevice.querySharableResource(customerId);
		return resourceModelConverter.processList(resourceModel);
	}
	/*
		查看用户分享出去的资源
	*/
	@Override
	public List<CusResourceRelData> queryResourceRelByShareCustomerId(String mobile) {
		
		Long customerId = iCustomerSevice.getCustomerIdByMobile(mobile);
		
		List<CusResourceRelModel> cusResourceRelModel = iCusResourceRelService.queryByShareCustomerId(customerId);
		return cusResourceRelModelConverter.processList(cusResourceRelModel);
	}
	
	//验证用户是否有权限操作资源
	@Override
	public String verification(String mobile, String mac) {

		
		String verifiMessage = "0";
		if(StringUtils.isEmpty(mobile) || StringUtils.isEmpty(mac)){
			verifiMessage = "E001"; //入参有空值
		}else{
			Long customerId = iCustomerSevice.getCustomerIdByMobile(mobile);
			
			if(null == customerId){
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
						
						CusResourceRelModel cusRRModel = iCusResourceRelService.queryModelByCustomerIdAndResId(customerId,rModel.getId());
						//没有资源与用户的绑定记录，需要验证用户组与资源的关系
						if(null == cusRRModel){
							CustomerModel customerModel = iCustomerSevice.simpleSelectWithGroupsById(customerId);
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
						//权限已过期
						}else if("Y".equals(cusRRModel.getEnable()) 
								&& ((null != cusRRModel.getStartDate() && cusRRModel.getStartDate().after(new Date()) )
										|| (null != cusRRModel.getEndDate() && cusRRModel.getEndDate().before(new Date())))){
							verifiMessage = "E008"; //该用权限已过期
						}
					}
				}
			}
		}
		return verifiMessage;
	}


	//用户删除分享给其他人的权限
	@Override
	public String removeSharedResource(String fromMobile, String toMobile,
			Integer sourceKeyId) {
		
		// TODO Auto-generated method stub
		String message = "0";
		//check params
		//fromCId exist?
		//sourceKeyId exist?
		try{
			if(StringUtils.isEmpty(fromMobile) || StringUtils.isEmpty(toMobile)|| StringUtils.isEmpty(sourceKeyId)){
				message = "E001"; //入参有空值
			}else{
				CustomerModel fromCustomerModel = iCustomerSevice.getCustomerModelByMobile(fromMobile);
				//CustomerModel customerModel = iCustomerSevice.getUserByCId(fromCId);
				if(null == fromCustomerModel){
					message = "E002"; //分享用户不存在
				}else{
					//先判断设备状态
					ResourceModel rModel = iResourceService.queryModelById(sourceKeyId);
					if(null == rModel ){
						message = "E003"; //设备不存在
					}else {
						Long toCustomerId = iCustomerSevice.getCustomerIdByMobile(toMobile);
						iCusResourceRelService.removeSharedResource(fromCustomerModel.getId(),toCustomerId,sourceKeyId);
					}
				}
			}
		}catch(Exception e){
			logger.error("removeSharedResource exception. ", e);
			message = "-1"; //delete记录异常
		}
		
		return message;
	}
	
	//用户分享资源给其他人的权限
	@Override
	public String shareResource(String fromMobile, String toMobile,
			Integer sourceKeyId, String startDateStr, String endDateStr) {
		
		String message = "0";
		//check params
		//fromCId exist?
		//toCId exist?
		//sourceKeyId exist? resource sharable?
		//startDateStr valid?
		//endDateStr valid?
		//toCId already have relationship with sourceKeyId?
		
		//如果起、止时间如果为null，表示起、止无限制
		Date startDate = null;
		Date endDate = null;
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(!StringUtils.isEmpty(startDate)){
				startDate = df.parse(startDateStr);
			}
			if(!StringUtils.isEmpty(endDateStr)){
				endDate = df.parse(endDateStr);
			}
		}catch(Exception e){
			message = "E008"; //权限起、至时间格式有问题
		}
		
		if("0".equals(message)){
			try{
				if(StringUtils.isEmpty(fromMobile) || StringUtils.isEmpty(toMobile)|| StringUtils.isEmpty(sourceKeyId)){
					message = "E001"; //入参有空值
				}else{
					CustomerModel fromCustomerModel = iCustomerSevice.getCustomerModelByMobile(fromMobile);
					if(null == fromCustomerModel){
						message = "E002"; //分享用户不存在
					}else{
						CustomerModel toCustomerModel = iCustomerSevice.getCustomerModelByMobile(fromMobile);
						if(null == toCustomerModel){
							message = "E004"; //被分享用户不存在
						}else{
							//先判断设备状态
							ResourceModel rModel = iResourceService.queryModelById(sourceKeyId);
							if(null == rModel || "N".equals(rModel.getStatus())){
								message = "E003"; //设备不可用
							}else if("N".equals(rModel.getShareEnable())){
								message = "E005"; //该设备权限不可分享
							}else{
								//toCId already have relationship with sourceKeyId?
								CusResourceRelModel cusRRModel = iCusResourceRelService.queryModelByCustomerIdAndResId(toCustomerModel.getId(),rModel.getId());
								//没有记录，直接创建
								if(cusRRModel == null){
									iCusResourceRelService.shareResource(fromCustomerModel.getId(),toCustomerModel.getId(),sourceKeyId,startDate, endDate);
									
								//有记录，已被授权；
								}else if ("Y".equals(cusRRModel.getEnable())){
									message = "E006"; //被分享用户已经有权限使用该资源
								//有记录被取消权限.
								}else{
									message = "E007"; //被分享用户被管理员设为无权使用该资源
								}
							}
						}
					}
				}
			}catch(Exception e){
				logger.error("shareResource exception. ", e);
				message = "-1"; //创建记录异常
			}
		}
		
		return message;
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

	public ICusResourceRelService getiCusResourceRelService() {
		return iCusResourceRelService;
	}

	public void setiCusResourceRelService(
			ICusResourceRelService iCusResourceRelService) {
		this.iCusResourceRelService = iCusResourceRelService;
	}

	public ICusGrpResourceRelService getiCusGrpResourceRelService() {
		return iCusGrpResourceRelService;
	}

	public void setiCusGrpResourceRelService(
			ICusGrpResourceRelService iCusGrpResourceRelService) {
		this.iCusGrpResourceRelService = iCusGrpResourceRelService;
	}

	public ResourceModelConverter getResourceModelConverter() {
		return resourceModelConverter;
	}

	public void setResourceModelConverter(
			ResourceModelConverter resourceModelConverter) {
		this.resourceModelConverter = resourceModelConverter;
	}


}
