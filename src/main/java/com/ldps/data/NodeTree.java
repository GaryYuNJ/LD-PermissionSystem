package com.ldps.data;

import java.util.List;

public class NodeTree {

	private int id;
	private String text;
	private String type;
	private List<NodeTree> children ;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<NodeTree> getChildren() {
		return children;
	}
	public void setChildren(List<NodeTree> children) {
		this.children = children;
	}
	
}
