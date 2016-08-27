package com.ldps.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ldps.dao.CusGroupRelModelMapper;
import com.ldps.dao.CustomerModelMapper;
import com.ldps.dao.ResourceModelMapper;
import com.ldps.model.CustomerModel;
import com.ldps.model.ResourceModel;
import com.ldps.service.ICustomerService;

@Service("iCustomerSevice")
public class CustomerServiceImpl implements ICustomerService {

	@Resource
	private CustomerModelMapper customerDao;
	
	@Resource
	private ResourceModelMapper resourceDao;
	@Resource
	private CusGroupRelModelMapper cusGroupRelDao;
	
	
	@Override
	public List<CustomerModel> queryAllWithPageIndex(Integer startRow, Integer pageSize) {
		return customerDao.selectAllWithPageIndex(startRow, pageSize);
	}
	
	@Override
	public String addVerification(CustomerModel custoemrModel) {
		 String message="";
		 if(StringUtils.isEmpty(custoemrModel.getCid())){
			 message+="会员账号不能为空;";
		 }
		 if(StringUtils.isEmpty(custoemrModel.getCtype())){
			 message+="会员类型不能为空;";
		 }
		 if(StringUtils.isEmpty(custoemrModel.getCstatus())){
			 message+="会员状态不能为空;";
		 }
		return message;
	}
 
	@Override
	public int addCustomer(CustomerModel custoemrModel) {
		if(getUserByCId(custoemrModel.getCid())!=null)
			return -1;
		return customerDao.insertSelective(custoemrModel);
	}

	@Override
	public int deleteCustomer(String cid) {
		return customerDao.deleteByCID(cid);
	}

	@Override
	public int updateCustomer(CustomerModel custoemrModel) {
		return customerDao.updateByCIDSelective(custoemrModel);
	}

	@Override
	public CustomerModel getUserByCId(String cid) {
		return customerDao.simpleSelectByCID(cid);
	}
	
	@Override
	public CustomerModel getModelWithGroupsByCID(String cid) {
		return customerDao.simpleSelectWithGroupsByCID(cid);
	}
	
	@Override
	public CustomerModel simpleSelectWithGroupsById(Long customerId) {
		return customerDao.simpleSelectWithGroupsById(customerId);
	}


	//获取用户可分享权限的资源列表
	/*
		不包含公共资源，不包含用户组授权，只针对用户与资源的可用关系
	*/
	@Override
	public List<ResourceModel> querySharableResource(Long customerId) {
		return resourceDao.selectSharableResourceById(customerId);
	}

	@Override
	public Long getCustomerIdByMobile(String mobile) {
		// TODO Auto-generated method stub
		CustomerModel model = customerDao.selectIdByMobile(mobile);
		if(null == customerDao){
			return null;
		}else{
			return model.getId();
		}
	}
	
	@Override
	public CustomerModel getCustomerModelByMobile(String mobile) {
		// TODO Auto-generated method stub
		return customerDao.simpleSelectByMobile(mobile);
	}

	@Override
	public Integer queryCustomerTotalCount() {
		// TODO Auto-generated method stub
		return customerDao.selectTotalCount();
	}

	@Override
	public CustomerModel UserDataByPrimaryId(Long customerId) {
		// TODO Auto-generated method stub
		return customerDao.selectByPrimaryKey(customerId);
	}

	@Override
	public List<CustomerModel> queryByMobileAndNameWithPageIndex(String mobile,
			String userName, Integer startRow, Integer pageSize) {
		// TODO Auto-generated method stub
		return customerDao.simpleByMobileAndNameWithPageIndex(mobile,
				userName, startRow, pageSize);
	}

	@Override
	public Integer queryTotalCountByMobileAndName(String mobile, String userName) {
		// TODO Auto-generated method stub
		return customerDao.selectTotalCountByMobileAndName(mobile, userName);
	} 

	/*
	根据 指定的BindGrpId 绑定关系，mobile，name搜索用户列表
	 */
	@Override
	public List<CustomerModel> searchWithBindGrpIdByNameAndMobile(String userGroupId,
			String mobile, String userName, Integer startRow, Integer pageSize) {

		return cusGroupRelDao.selectCusModelWithBindGrpId(userGroupId, mobile, userName,startRow,pageSize);
	}
	
	/*
	根据指定的BindGrpId 绑定关系，mobile，name 查用户总数
	 */
	@Override
	public Integer queryTotalCountWithBindGrpId(String userGroupId,
			String mobile, String userName) {

		return cusGroupRelDao.selectCusModelCountWithBindGrpId(userGroupId, mobile, userName);
	}
	/*
	根据 mobile，name搜索用户列表，并加上与指定groupId的依赖关系
	 */
	@Override
	public List<CustomerModel> searchWithGrpIdFlagByNameAndMobile(
			String userGroupId, String mobile, String userName,
			Integer startRow, Integer pageSize) {
		
		return cusGroupRelDao.selectWithGrpIdFlagByNameAndMobile(userGroupId, mobile, userName,startRow,pageSize);
	}
	/*
	根据mobile，name 查用户总数
	 */
	@Override
	public Integer queryTotalCountByMobileAndUserName(String mobile,
			String userName) {
		return cusGroupRelDao.selectCusModelCountByMobileAndUserName( mobile, userName);
	}
	
}
