package com.ldps.service;

import java.util.List;

import com.ldps.data.ResourceArea;
import com.ldps.model.CustomerModel;
import com.ldps.model.ResourceModel;

public interface ICustomerService {

	//验证添加是否数据的完整性
	String addVerification(CustomerModel custoemrModel);
	
	//新增数据
	int addCustomer(CustomerModel custoemrModel);
	
	//删除数据
	int deleteCustomerByCmMemid(String cmMemid);
	
	//按照账号更新数据
	int updateCustomerByCmMemid(CustomerModel custoemrModel);

	//public CustomerModel getModelWithGroupsByCID(String cid);
	
	//获取用户可分享权限的资源列表
	/*
		不包含公共资源，不包含用户组授权，只针对用户与资源的可用关系
	*/
	List<ResourceModel> querySharableResource(Long customerId);
	
	Long getCustomerIdByMobile(String mobile);

	CustomerModel simpleSelectWithGroupsById(Long customerId);

	CustomerModel getCustomerModelByMobile(String mobile);

	List<CustomerModel> queryAllWithPageIndex(Integer startRow, Integer pageSize);
	
	Integer queryCustomerTotalCount();

	public CustomerModel UserDataByPrimaryId(Long customerId);

	public List<CustomerModel> queryByMobileAndNameWithPageIndex(String mobile,
			String userName, Integer startRow, Integer pageSize);

	public Integer queryTotalCountByMobileAndName(String mobile, String userName);

	/*
	根据 指定的BindGrpId 绑定关系，mobile，name搜索用户列表
	 */
	public List<CustomerModel>  searchWithBindGrpIdByNameAndMobile(String userGroupId,
			String mobile, String userName, Integer startRow, Integer pageSize);

	/*
	根据指定的BindGrpId 绑定关系，mobile，name 查用户总数
	 */
	Integer queryTotalCountWithBindGrpId(String userGroupId, String mobile,
			String userName);

	/*
	根据 mobile，name搜索用户列表，并加上与指定groupId的依赖关系
	 */
	public List<CustomerModel> searchWithGrpIdFlagByNameAndMobile(
			String userGroupId, String mobile, String userName,
			Integer startRow, Integer pageSize);

	/*
	根据mobile，name 查用户总数
	 */
	public Integer queryTotalCountByMobileAndUserName(
			String mobile, String userName);

	CustomerModel getUserByCmMemid(String cmMemid);
	
	List<ResourceArea> querySharableResourceArea(Long customerId);
	
	public int addTempCustomer(CustomerModel custoemrModel);
	//针对分享权限时临时添加用户
	public CustomerModel getUserByCmMemidOrMobile(String cmMemid,String mobile) ;
	
	public int updateCustomerById(CustomerModel custoemrModel);
	
}
