package com.ldps.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.NodeModelMapper;
import com.ldps.dao.NodeRelModelMapper;
import com.ldps.model.NodeModel;
import com.ldps.service.INodeService;

@Service("nodeService")
public class NodeServiceImpl implements INodeService {

	@Resource
	private NodeModelMapper nodeMapper;
	

	@Resource
	private NodeRelModelMapper nodeRelMapper;

	@Override
	public NodeModel getNodeById(Integer id) {
		// TODO Auto-generated method stub
		return this.nodeMapper.selectByPrimaryKey(id);
	}

	@Override
	public Integer getParentNodeIdbyNodeId(Integer nodeId) {
		
		return nodeRelMapper.selectParentNodeIdbyChildNodeId(nodeId);
	}
	
	//获取所有父节点Id
	@Override
	public List<Integer> getAllParentNodeIdsbyNodeId(Integer nodeId) {
		List<Integer> nodes = new ArrayList<Integer>();
		//是根节点？
		if(nodeId != 0){
			NodeModel nModel = nodeMapper.selectByPrimaryKey(nodeId);
			if(null != nModel){
				this.getParentNodeIds(nModel.getId(), nodes);
			}
		}
		return nodes;
	}
	
	//递归获取所有父节点Id
	private void getParentNodeIds(Integer nodeId, List<Integer> list){
		if(null == list){
			list = new ArrayList<Integer>();
		}
		if(nodeId != 0){
			Integer parentId = getParentNodeIdbyNodeId(nodeId);
			if(null != parentId){
				getParentNodeIds(parentId,list);
				list.add(parentId); //放在递归方法后面，这样第一个加入的就是根节点
			}
		}else{
			return ;
		}
	}
	
	
	//获取所有父节点Model
	@Override
	public List<NodeModel> getAllParentNodeModelsbyNodeId(Integer nodeId) {
		List<NodeModel> nodeModels = new ArrayList<NodeModel>();
		//是根节点？
		if(nodeId != 0){
			NodeModel nModel = nodeMapper.selectByPrimaryKey(nodeId);
			if(null != nModel){
				this.getParentNodeModels(nModel.getId(), nodeModels);
			}
		}
		return nodeModels;
	}
	

	//递归获取所有父节点Model
	private void getParentNodeModels(Integer nodeId, List<NodeModel> list){
		if(null == list){
			list = new ArrayList<NodeModel>();
		}
		if(nodeId != 0){
			NodeModel model = getNodeById(nodeId);
			if(null != model){
				getParentNodeModels(model.getId(),list);
				list.add(model); //放在递归方法后面，这样第一个加入的就是根节点
			}
		}else{
			return ;
		}
	}
	
	//通过resourceId获取父节点Id.
	@Override
	public Integer getParentNodeIdbyResourceId(Integer resourceId) {
		
		return nodeRelMapper.selectParentNodeIdbyResourceId(resourceId);
	}
	
	
	//通过resourceId获取所有父节点idlist.
	@Override
	public List<Integer> getParentNodeIdsbyResourceId(Integer resourceId) {
		
		Integer nodeId = this.getParentNodeIdbyResourceId(resourceId);
		
		return this.getAllParentNodeIdsbyNodeId(nodeId);
	}
	
	//通过resourceId获取所有父节点Model.
	@Override
	public List<NodeModel> getAllParentNodeModelsbyResourceId(Integer resourceId) {
		
		Integer nodeId = this.getParentNodeIdbyResourceId(resourceId);
		
		return this.getAllParentNodeModelsbyNodeId(nodeId);
	}


}
