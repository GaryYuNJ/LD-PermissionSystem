package com.ldps.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldps.model.NodeModel;

public interface NodeModelMapper {
    int deleteByPrimaryKey(Integer id);

    Integer insert(NodeModel record);

    int insertSelective(NodeModel record);

    NodeModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(NodeModel record);

    int updateByPrimaryKey(NodeModel record);
    
    //根据级别获取节点
  	List<NodeModel> selectNodeByGrade(Integer grade);
  	
  	//获取子节点
  	List<NodeModel> selectChildNode(@Param("parentId")Integer parentId,@Param("grade")Integer grade);
}