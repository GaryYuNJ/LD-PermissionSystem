package com.ldps.model;

public class NodeResourceRelModel {
    private Integer nodeId;

    private Integer resourceId;

    private Integer nInt1;

    private String vc1;

    private String chr1;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public Integer getnInt1() {
        return nInt1;
    }

    public void setnInt1(Integer nInt1) {
        this.nInt1 = nInt1;
    }

    public String getVc1() {
        return vc1;
    }

    public void setVc1(String vc1) {
        this.vc1 = vc1 == null ? null : vc1.trim();
    }

    public String getChr1() {
        return chr1;
    }

    public void setChr1(String chr1) {
        this.chr1 = chr1 == null ? null : chr1.trim();
    }
}