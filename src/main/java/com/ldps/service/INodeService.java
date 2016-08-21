package com.ldps.service;

import java.util.List;

import com.ldps.model.NodeModel;

public interface INodeService {
	public NodeModel getNodeById(Integer nodeId); 
	
	public List<Integer> getAllParentNodeIdsbyNodeId(Integer nodeId);
	
	public List<Integer> getParentNodeIdsbyResourceId(Integer resourceId);

	Integer getParentNodeIdbyNodeId(Integer nodeId);

	Integer getParentNodeIdbyResourceId(Integer resourceId);

	List<NodeModel> getAllParentNodeModelsbyNodeId(Integer nodeId);

	List<NodeModel> getAllParentNodeModelsbyResourceId(Integer resourceId);

}
