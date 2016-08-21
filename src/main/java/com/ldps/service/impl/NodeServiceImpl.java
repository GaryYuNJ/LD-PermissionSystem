package com.ldps.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldps.dao.NodeModelMapper;
import com.ldps.dao.NodeRelModelMapper;
import com.ldps.data.NodeTree;
import com.ldps.model.NodeModel;
import com.ldps.model.NodeRelModel;
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

	// 获取所有父节点Id
	@Override
	public List<Integer> getAllParentNodeIdsbyNodeId(Integer nodeId) {
		List<Integer> nodes = new ArrayList<Integer>();
		// 是根节点？
		if (nodeId != 0) {
			NodeModel nModel = nodeMapper.selectByPrimaryKey(nodeId);
			if (null != nModel) {
				this.getParentNodeIds(nModel.getId(), nodes);
			}
		}
		return nodes;
	}

	// 递归获取所有父节点Id
	private void getParentNodeIds(Integer nodeId, List<Integer> list) {
		if (null == list) {
			list = new ArrayList<Integer>();
		}
		if (nodeId != 0) {
			Integer parentId = getParentNodeIdbyNodeId(nodeId);
			if (null != parentId) {
				getParentNodeIds(parentId, list);
				list.add(parentId); // 放在递归方法后面，这样第一个加入的就是根节点
			}
		} else {
			return;
		}
	}

	// 获取所有父节点Model
	@Override
	public List<NodeModel> getAllParentNodeModelsbyNodeId(Integer nodeId) {
		List<NodeModel> nodeModels = new ArrayList<NodeModel>();
		// 是根节点？
		if (nodeId != 0) {
			NodeModel nModel = nodeMapper.selectByPrimaryKey(nodeId);
			if (null != nModel) {
				this.getParentNodeModels(nModel.getId(), nodeModels);
			}
		}
		return nodeModels;
	}

	// 递归获取所有父节点Model
	private void getParentNodeModels(Integer nodeId, List<NodeModel> list) {
		if (null == list) {
			list = new ArrayList<NodeModel>();
		}
		if (nodeId != 0) {
			NodeModel model = getNodeById(nodeId);
			if (null != model) {
				getParentNodeModels(model.getId(), list);
				list.add(model); // 放在递归方法后面，这样第一个加入的就是根节点
			}
		} else {
			return;
		}
	}

	// 通过resourceId获取父节点Id.
	@Override
	public Integer getParentNodeIdbyResourceId(Integer resourceId) {

		return nodeRelMapper.selectParentNodeIdbyResourceId(resourceId);
	}

	// 通过resourceId获取所有父节点idlist.
	@Override
	public List<Integer> getParentNodeIdsbyResourceId(Integer resourceId) {

		Integer nodeId = this.getParentNodeIdbyResourceId(resourceId);

		return this.getAllParentNodeIdsbyNodeId(nodeId);
	}

	// 通过resourceId获取所有父节点Model.
	@Override
	public List<NodeModel> getAllParentNodeModelsbyResourceId(Integer resourceId) {

		Integer nodeId = this.getParentNodeIdbyResourceId(resourceId);

		return this.getAllParentNodeModelsbyNodeId(nodeId);
	}

	@Override
	public List<NodeModel> getNodeByGrade(Integer grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<NodeModel> getChildNode(Integer nodeId, Integer grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer addNode(Integer parentNodeId, NodeModel nodeModel) {
		NodeModel parentNode = nodeMapper.selectByPrimaryKey(parentNodeId);
		if (null == parentNode) {
			return 0;
		} else {
			nodeModel.setGrade(parentNode.getGrade() + 1);
			Integer nodeId = nodeMapper.insert(nodeModel);
			if (null != nodeId && null != parentNode.getId()) {
				NodeRelModel nodeRelMode = new NodeRelModel();
				nodeRelMode.setIdChild(nodeId);
				nodeRelMode.setIdParent(parentNode.getId());
				nodeRelMapper.insert(nodeRelMode);
			}
			return 1;
		}
	}

	@Override
	public Integer deleteNode(Integer grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateNode(NodeModel nodeModel) {
		if (nodeModel != null) {
			return nodeMapper.updateByPrimaryKeySelective(nodeModel);
		}
		return null;
	}

	@Override
	public List<NodeTree>  getAllNodeTree() {
		return getAllChildNodeTree(nodeMapper.selectNodeByGrade(0));
	}

	private List<NodeTree> getAllChildNodeTree(List<NodeModel> listNode) {
		if (listNode == null)
			return null;
		List<NodeTree> listNodeTree = new ArrayList<NodeTree>();
		for (int i = 0; i < listNode.size(); i++) {
			NodeTree nodeTree = new NodeTree();
			nodeTree.setId(listNode.get(i).getId());
			if (listNode.get(i).getGrade() == 0) {
				nodeTree.setType("root");
			}
			nodeTree.setChildren(getAllChildNodeTree(nodeMapper
					.selectChildNode(listNode.get(i).getId(), listNode.get(i)
							.getGrade()+1)));
			listNodeTree.add(nodeTree);
		}
		return listNodeTree;

	}

}
