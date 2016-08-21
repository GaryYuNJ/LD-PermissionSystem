package com.ldps.service;

import java.util.List;

import com.ldps.data.NodeTree;
import com.ldps.model.NodeModel;

public interface INodeService {
	public NodeModel getNodeById(Integer nodeId); 
	
	public List<Integer> getAllParentNodeIdsbyNodeId(Integer nodeId);
	
	public List<Integer> getParentNodeIdsbyResourceId(Integer resourceId);

	Integer getParentNodeIdbyNodeId(Integer nodeId);

	Integer getParentNodeIdbyResourceId(Integer resourceId);

	List<NodeModel> getAllParentNodeModelsbyNodeId(Integer nodeId);

	List<NodeModel> getAllParentNodeModelsbyResourceId(Integer resourceId);
	
	//根据级别获取节点
	List<NodeModel> getNodeByGrade(Integer grade);
	
	//获取子节点
	List<NodeModel> getChildNode(Integer nodeId,Integer grade);

	//添加子节点
	Integer addNode(Integer parentNodeId,NodeModel nodeModel);
	//删除节点
	Integer deleteNode(Integer grade);
	//修改节点
	Integer updateNode(NodeModel nodeModel);
	
	List<NodeTree>  getAllNodeTree();
}
