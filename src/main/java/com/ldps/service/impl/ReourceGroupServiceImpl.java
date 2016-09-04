package com.ldps.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.ResourceGroupModelMapper;
import com.ldps.dao.ResourceGrpRelModelMapper;
import com.ldps.model.ResourceGroupModel;
import com.ldps.model.ResourceGrpRelModel;
import com.ldps.service.IResourceGroupService;
@Service("iResourceGroupService")
public class ReourceGroupServiceImpl implements IResourceGroupService {

	@Resource
	private ResourceGroupModelMapper resourceGroupModelDao;
	@Resource
	private ResourceGrpRelModelMapper resourceGrpRelModelDao;
	
	@Override
	public List<ResourceGroupModel> queryBasicResGroupByCondition(
			ResourceGroupModel model, Integer pageNo, Integer pageSize) {
		return resourceGroupModelDao.selectResouceGroupListByCondition(model, pageNo, pageSize);
	}

	@Override
	public int queryCountByCondition(ResourceGroupModel model) {
		return resourceGroupModelDao.selectCountByCondition(model);
	}

	@Override
	public int addNewGroup(ResourceGroupModel model) {
		return resourceGroupModelDao.insertSelective(model);
	}

	@Override
	public ResourceGroupModel queryReourceGroupModelById(Integer id) {
		return resourceGroupModelDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateResourceGroup(ResourceGroupModel model) {
		return resourceGroupModelDao.updateByPrimaryKeySelective(model);
	}

	@Override
	public int addResourceGroupRel(ResourceGrpRelModel model) {
		return resourceGrpRelModelDao.insertSelective(model);
	}

	@Override
	public int deleteResourceGroupRel(ResourceGrpRelModel model) {
		// TODO Auto-generated method stub
		return resourceGrpRelModelDao.deleteByPrimaryKey(model);
	}

}
