package com.ldps.model;

import java.util.Date;
import java.util.List;

public class ResourceModel {
    private Integer id;

    private String name;

    private Integer typeId;

    private String status;

    private Date createDate;

    private Integer createUser;

    private String mac;

    private Integer buildingId; //设备类型资源的楼栋信息id

    private Integer floor;

    private String password;

    private Integer sequence;

    private String shareEnable; //资源是否可被用户分享权限

    private String nodePath; //资源的节点路径

    private String isVirtualResource; //Y：虚拟资源、N：真实设备资源

    private Integer permissionAttrId; //资源权限属性：1：公共资源；2：基础资源(授权时用：针对下层节点的资源来说，如果要使用下层节点，必须使用的上层节点)；3：私有资源；

    private Integer deviceType; //设备类型：1. 通行、2. 家居、3.其它

    private Integer manufacturerId; //设备生产厂家，需要传给APP' ,
    
    private Integer nodeId; //资源直接隶属的节点

    private Integer rInt1;

    private Integer rInt2;

    private Long num1;

    private Long num2;

    private String vc1;

    private String vc2;

    private String chr1;

    private String chr2;
    
    private Long specificUserId; //指定要查询关联情况的userId
    
    private CusResourceRelModel cusResRelModel; //指定要查询关联情况的relModel
    
    public Long getSpecificUserId() {
		return specificUserId;
	}

	public void setSpecificUserId(Long specificUserId) {
		this.specificUserId = specificUserId;
	}


	public CusResourceRelModel getCusResRelModel() {
		return cusResRelModel;
	}

	public void setCusResRelModel(CusResourceRelModel cusResRelModel) {
		this.cusResRelModel = cusResRelModel;
	}

	public Integer getNodeId() {
		return nodeId;
	}

	public void setNodeId(Integer nodeId) {
		this.nodeId = nodeId;
	}

	private List<ResourceGroupModel> resourceGroups;  
    
    public List<ResourceGroupModel> getResourceGroups() {
		return resourceGroups;
	}

	public void setResourceGroups(List<ResourceGroupModel> resourceGroups) {
		this.resourceGroups = resourceGroups;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Integer createUser) {
        this.createUser = createUser;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public Integer getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Integer buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getShareEnable() {
        return shareEnable;
    }

    public void setShareEnable(String shareEnable) {
        this.shareEnable = shareEnable == null ? null : shareEnable.trim();
    }

    public String getNodePath() {
        return nodePath;
    }

    public void setNodePath(String nodePath) {
        this.nodePath = nodePath == null ? null : nodePath.trim();
    }

    public String getIsVirtualResource() {
        return isVirtualResource;
    }

    public void setIsVirtualResource(String isVirtualResource) {
        this.isVirtualResource = isVirtualResource == null ? null : isVirtualResource.trim();
    }

    public Integer getPermissionAttrId() {
        return permissionAttrId;
    }

    public void setPermissionAttrId(Integer permissionAttrId) {
        this.permissionAttrId = permissionAttrId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(Integer manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public Integer getrInt1() {
        return rInt1;
    }

    public void setrInt1(Integer rInt1) {
        this.rInt1 = rInt1;
    }

    public Integer getrInt2() {
        return rInt2;
    }

    public void setrInt2(Integer rInt2) {
        this.rInt2 = rInt2;
    }

    public Long getNum1() {
        return num1;
    }

    public void setNum1(Long num1) {
        this.num1 = num1;
    }

    public Long getNum2() {
        return num2;
    }

    public void setNum2(Long num2) {
        this.num2 = num2;
    }

    public String getVc1() {
        return vc1;
    }

    public void setVc1(String vc1) {
        this.vc1 = vc1 == null ? null : vc1.trim();
    }

    public String getVc2() {
        return vc2;
    }

    public void setVc2(String vc2) {
        this.vc2 = vc2 == null ? null : vc2.trim();
    }

    public String getChr1() {
        return chr1;
    }

    public void setChr1(String chr1) {
        this.chr1 = chr1 == null ? null : chr1.trim();
    }

    public String getChr2() {
        return chr2;
    }

    public void setChr2(String chr2) {
        this.chr2 = chr2 == null ? null : chr2.trim();
    }
}