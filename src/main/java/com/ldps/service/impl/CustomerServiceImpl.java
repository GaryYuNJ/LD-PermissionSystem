package com.ldps.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ldps.dao.CusGroupRelModelMapper;
import com.ldps.dao.CusResourceRelModelMapper;
import com.ldps.dao.CustomerModelMapper;
import com.ldps.dao.ResourceModelMapper;
import com.ldps.data.ResourceArea;
import com.ldps.model.CusResourceRelModel;
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
	@Resource
	private CusResourceRelModelMapper cusResourceRelDao;
	
	@Override
	public List<CustomerModel> queryAllWithPageIndex(Integer startRow, Integer pageSize) {
		return customerDao.selectAllWithPageIndex(startRow, pageSize);
	}
	
	@Override
	public String addVerification(CustomerModel custoemrModel) {
		 String message="";
		 if(StringUtils.isEmpty(custoemrModel.getCmMemid())){
			 message+="成员ID不能为空;";
		 }
		 if(StringUtils.isEmpty(custoemrModel.getCmCustid())){
			 message+="会员ID不能为空;";
		 }
		 if(StringUtils.isEmpty(custoemrModel.getCmMobile1())){
			 message+="手机号不能为空;";
		 }
		return message;
	}
 
	@Override
	public int addCustomer(CustomerModel custoemrModel) {
		CustomerModel custoemrModelTemp= getUserByCmMemidOrMobile(custoemrModel.getCmMemid(),custoemrModel.getCmMobile1());
		if(custoemrModelTemp!= null){
			custoemrModel.setId(custoemrModelTemp.getId());
			return -1;//customerDao.updateByCmMemidSelective(custoemrModel);
		}
		return customerDao.insertSelective(custoemrModel);
	}

	//分享权限时如果未注册或者未同步， 临时插入后面更新
	@Override
	public int addTempCustomer(CustomerModel custoemrModel) {
		return customerDao.insertSelective(custoemrModel);
	}

	@Override
	public int deleteCustomerByCmMemid(String cmMemid) {
		return customerDao.deleteByCmMemid(cmMemid);
	}

	@Override
	public int updateCustomerByCmMemid(CustomerModel custoemrModel) {
		return customerDao.updateByCmMemidSelective(custoemrModel);
	}
	
	@Override
	public int updateCustomerById(CustomerModel custoemrModel) {
		return customerDao.updateByPrimaryKeySelective(custoemrModel);
	}

	@Override
	public CustomerModel getUserByCmMemid(String cmMemid) {
		return customerDao.simpleSelectByCmMemid(cmMemid);
	}
	
	@Override
	public CustomerModel getUserByCmMemidOrMobile(String cmMemid,String mobile) {
		return customerDao.simpleSelectByCmMemidOrMobile(cmMemid,mobile);
	}
//	
//	@Override
//	public CustomerModel getModelWithGroupsByCID(String cid) {
//		return customerDao.simpleSelectWithGroupsByCID(cid);
//	}
	
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
		return cusResourceRelDao.selectSharableResourceById(customerId);
	}
	
	
	//获取用户可分享权限的资源所属区域
	/*
		不包含公共资源
	*/
	@Override
	public List<ResourceArea> querySharableResourceArea(Long customerId) {
		return cusResourceRelDao.selectSharableResBuildingById(customerId);
	}

	@Override
	public Long getCustomerIdByMobile(String mobile) {
		// TODO Auto-generated method stub
		CustomerModel model = customerDao.selectIdByMobile(mobile);
		if(null == model){
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
