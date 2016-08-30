

--后台用户
--drop table BACKENDUSER;
CREATE TABLE BACKEND_USER    (
  	ID  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '后台用户ID', 
	NAME varchar(20) NOT NULL unique COMMENT '后台用户名称', 
    PASSWORD  varchar(20) NOT NULL COMMENT '后台用户登陆密码', 
	STATUS CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '后台用户可用状态', 
	CREATE_DATE DATETIME  COMMENT '后台用户创建时间', 
	CREATE_USER int COMMENT '后台用户创建人', 
    PRIMARY KEY(ID)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

--用户详情表
--drop table CUSTOMER;
CREATE TABLE CUSTOMER   (
    ID  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员ID', 
  	CID varchar(20) COMMENT 'CRM的会员帐号。考虑到业务要求要先对未注册人进行授权，此时CID并不存在，所以去除NOT NULL unique 限制',
  	MOBILE varchar(20) NOT NULL unique COMMENT '手机号',
	NAME varchar(40)  COMMENT '会员名', 
    PASSWORD  varchar(20) NOT NULL COMMENT '用户登陆密码', 
    SEX CHAR(1) COMMENT '性别', 
    BIRTHDAY DATE  COMMENT '生日', 
    ADDRESS varchar(200) COMMENT '会员地址',
    EMAIL varchar(200) COMMENT '会员EMAIL',
    PIC_NAME  varchar(100) COMMENT '会员头像',
    ID_TYPE CHAR(1)   COMMENT '证件类型1  身份证 2  护照 3  驾驶证 4  警官证 5  军官证 6  其他',
    ID_NO varchar(40)  COMMENT '用户证件号码', 
    MEMBER_ID varchar(20)  COMMENT '成员号', 
    REFEREE varchar(20)  COMMENT '邀请码', 
    RELATION CHAR(1)  COMMENT '家庭账户成员信息1 成员 2是租客', 

	CTYPE varchar(4) NOT NULL  COMMENT '会员类型',
	CSTATUS CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '会员状态',
	CCURJFYE NUMERIC DEFAULT 0 COMMENT '当期积分',
	CHISJFYE NUMERIC DEFAULT 0 COMMENT '历史积分',
	CTOTJFYE NUMERIC  DEFAULT 0 COMMENT '总积分',
	CCURXFJE NUMERIC  DEFAULT 0 COMMENT '当期消费金额',
	CHISXFJE NUMERIC  DEFAULT 0 COMMENT '历史消费金额',
	CTOTXFJE NUMERIC  DEFAULT 0 COMMENT '总消费金额',
	CVIPNO varchar(20) COMMENT '申办卡号',
	CNAME varchar(40) COMMENT '申办姓名',
	CCREATOR varchar(15) COMMENT '创建人',
	CCREATDATE DATETIME COMMENT '创建日期',
	CMAINTOR varchar(15) COMMENT '最近维护人',
	CMAINTDATE DATETIME COMMENT '最近维护日期',
	CDATE1 DATETIME COMMENT '最近定期处理的日期',
	CDATE2 DATETIME COMMENT '',
	CDATE3 DATETIME COMMENT '',
	CDATE4 DATETIME COMMENT '',
	CDATE5 DATETIME COMMENT '',
	CNUM1 NUMERIC  COMMENT 'R',
	CNUM2 NUMERIC  COMMENT 'F',
	CNUM3 NUMERIC  COMMENT 'M',
	CNUM4 NUMERIC  DEFAULT 0 COMMENT '有效期内有效积分',
	CNUM5 NUMERIC  COMMENT '有效期内消费金额',
	CNUM6 NUMERIC  COMMENT '',
	CNUM7 NUMERIC  COMMENT '',
	CNUM8 NUMERIC  COMMENT '',
	CNUM9 NUMERIC  COMMENT '',
	CNUM10 NUMERIC  COMMENT '成长值',
	CVC1 varchar(20) DEFAULT '0' COMMENT '客服顾问',
	CVC2 varchar(20) COMMENT '升降级未换卡前的卡类别',
	CVC3 varchar(20) COMMENT '',
	CVC4 varchar(20) COMMENT '',
	CVC5 varchar(20) COMMENT '',
	CVC6 varchar(20) COMMENT '',
	CVC7 varchar(20) COMMENT '',
	CVC8 varchar(20) COMMENT '',
	CCHR1 CHAR(1) COMMENT '',
	CCHR2 CHAR(1) COMMENT '',
	CCHR3 CHAR(1) COMMENT '',
	CCHR4 CHAR(1) COMMENT '',
	CCHR5 CHAR(1) COMMENT '',
	CCHR6 varchar(20) COMMENT '租客所属的主成员会员号custno',
	INDEX (ID,MOBILE),
    PRIMARY KEY(ID)
   )ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
   
   
--用户组表
--drop table CGROUP;
CREATE TABLE CGROUP    (
  	ID  int(10) NOT NULL AUTO_INCREMENT COMMENT '会员组ID', 
	NAME varchar(20) NOT NULL unique COMMENT '会员组名称', 
	STATUS CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '会员组可用状态', 
	CREATE_DATE DATETIME  COMMENT '会员组创建时间', 
	CREATE_USER int NOT NULL  COMMENT '会员组创建人', 
	NUM1 NUMERIC , 
	NUM2 NUMERIC , 
    VC1 varchar(20) , 
	VC2 varchar(20), 
	CHR1 CHAR(1), 
	CHR2 CHAR(1),
    INDEX (ID,NAME),
    PRIMARY KEY(ID)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;


--用户与用户组关系表
--drop table CGROUPREL;
CREATE TABLE CGROUP_REL    (
  	CGROUP_ID  int(10) NOT NULL COMMENT '会员组ID', 
	CUSTOMER_ID  bigint(20) NOT NULL COMMENT '会员帐号',
	CREATE_DATE DATETIME  COMMENT '会员组关系创建时间', 
	CREATE_USER int NOT NULL  COMMENT '会员组关系创建人',
    INDEX (CGROUP_ID, CUSTOMER_ID),
    FOREIGN KEY (CGROUP_ID) REFERENCES CGROUP(ID) ON DELETE CASCADE,
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID) ON DELETE CASCADE,
    UNIQUE KEY CGPREL (CGROUP_ID,CUSTOMER_ID)
)ENGINE=InnoDB 	DEFAULT CHARSET=utf8;


--资源类型表
--drop table RESOURCE_TYPE;
CREATE TABLE RESOURCE_TYPE    (
  	ID  int(10) NOT NULL AUTO_INCREMENT COMMENT '资源类型ID', 
	NAME varchar(30) NOT NULL unique COMMENT '资源类型名称',
    PRIMARY KEY(ID)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO RESOURCE_TYPE(NAME) VALUES('小区门禁' );
INSERT INTO RESOURCE_TYPE(NAME) VALUES( '楼栋门禁' );
INSERT INTO RESOURCE_TYPE(NAME) VALUES( '楼层门禁' );
INSERT INTO RESOURCE_TYPE(NAME) VALUES( '其它资源' );



--资源的楼栋信息
--drop table BUILDING;
CREATE TABLE BUILDING    (
  	ID int(10) NOT NULL AUTO_INCREMENT COMMENT  '楼栋ID', 
	NAME varchar(30) NOT NULL unique COMMENT '楼栋名称',
	PRIMARY KEY(ID)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO BUILDING(NAME) VALUES('绿地世界之窗' );
INSERT INTO BUILDING(NAME) VALUES('裴总办公室' );
INSERT INTO BUILDING(NAME) VALUES( '许总办公室' );
INSERT INTO BUILDING(NAME) VALUES('白总办公室' );
INSERT INTO BUILDING(NAME) VALUES('卜总办公室' );
INSERT INTO BUILDING(NAME) VALUES('绿地之窗1-1' );


--资源详情表
--drop table RESOURCE;
CREATE TABLE RESOURCE    (
  	ID  int(12) NOT NULL AUTO_INCREMENT COMMENT '资源ID', 
	NAME varchar(20) NOT NULL  COMMENT '资源名称', 
    TYPE_ID int(3) NOT NULL  COMMENT '资源类型id',
	STATUS CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '资源可用状态', 
	CREATE_DATE DATETIME  COMMENT '资源创建时间', 
	CREATE_USER int NOT NULL  COMMENT '资源创建人', 
    --MAC CHAR(20)  COMMENT '设备类型资源的MAC地址', 
    BUILDING_ID int(3) COMMENT '设备类型资源的楼栋信息id', 
    FLOOR int(5)  COMMENT '设备类型资源的楼层', 
    --PASSWORD  CHAR(20)  COMMENT '设备类型资源的密码', 
    SEQUENCE INT(10) NOT NULL DEFAULT 1 COMMENT '显示序列',
    SHARE_ENABLE CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '资源是否可被用户分享权限',
    --MANUFACTURER_ID int(3) COMMENT '设备生产厂家，需要传给APP' ,
	DEVICE_TYPE int(2) COMMENT '设备类型：1. 通行、2. 家居、3.其它'  ,
	IS_VIRTUAL_RESOURCE  char(1) DEFAULT 'N' NOT NULL COMMENT 'Y：虚拟资源、N：真实设备资源' ,
	PERMISSION_ATTR_ID int(2) DEFAULT 3 NOT NULL comment '资源权限属性：1：公共资源；2：基础资源(授权时用：针对下层节点的资源来说，如果要使用下层节点，必须使用的上层节点)；3：私有资源；' ,
	NODE_PATH varchar(50) COMMENT '资源的节点路径'  ,
	NODE_ID INT(10) default 0  COMMENT '资源直接隶属的节点',
	R_INT1 int(10) , 
	R_INT2 int(10) , 
	NUM1 NUMERIC , 
	NUM2 NUMERIC , 
    VC1 varchar(40) , 
	VC2 varchar(40), 
	CHR1 CHAR(1), 
	CHR2 CHAR(1),
    INDEX (ID,NAME),
    PRIMARY KEY(ID),
    FOREIGN KEY (TYPE_ID) REFERENCES RESOURCE_TYPE(ID),
    FOREIGN KEY (BUILDING_ID) REFERENCES BUILDING(ID),
    FOREIGN KEY (NODE_ID) REFERENCES NODE(ID)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

--资源详情表
--drop table RESOURCE_KEY;
CREATE TABLE RESOURCE_KEY    (
  	ID  int(12) NOT NULL AUTO_INCREMENT COMMENT '设备钥匙ID', 
    MAC CHAR(20)  COMMENT '设备钥匙MAC地址', 
    PASSWORD  CHAR(20)  COMMENT '设备钥匙的密码', 
    MANUFACTURER_ID int(3) COMMENT '钥匙生产厂家' ,
	RESOURCE_ID INT(10) default 0  COMMENT '资源ID',
	R_INT1 int(10) , 
	R_INT2 int(10) , 
	NUM1 NUMERIC , 
	NUM2 NUMERIC , 
    VC1 varchar(40) , 
	VC2 varchar(40), 
	CHR1 CHAR(1), 
	CHR2 CHAR(1),
    INDEX (ID),
    PRIMARY KEY(ID),
    FOREIGN KEY (RESOURCE_ID) REFERENCES RESOURCE(ID)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

--资源组详情表
--drop table RESOURCE_GROUP;
CREATE TABLE RESOURCE_GROUP    (
  	ID  int(10) NOT NULL AUTO_INCREMENT COMMENT '资源组ID', 
	NAME varchar(20) NOT NULL unique COMMENT '资源组名称', 
	STATUS CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '资源组可用状态', 
	IS_PUBLIC CHAR(1) DEFAULT 'N' NOT NULL  COMMENT '是否公共资源组；公共资源可被所有用户操控', 
	CREATE_DATE DATETIME  COMMENT '资源组创建时间', 
	CREATE_USER int COMMENT '资源组创建人', 
	NUM1 NUMERIC , 
	NUM2 NUMERIC , 
    VC1 varchar(20) , 
	VC2 varchar(20), 
	CHR1 CHAR(1), 
	CHR2 CHAR(1),
    INDEX (ID,NAME),
    PRIMARY KEY(ID)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;


--资源与资源组关系表
--drop table RESOURCE_GROUP_REL;
CREATE TABLE RESOURCE_GROUP_REL    (
  	RGROUP_ID  int(10) NOT NULL COMMENT '资源组id', 
	RESOURCE_ID int(12) NOT NULL COMMENT '资源id',
	CREATE_DATE DATETIME  COMMENT '资源组关系创建时间', 
	CREATE_USER int  COMMENT '资源组关系创建人',
    INDEX (RGROUP_ID, RESOURCE_ID),
    FOREIGN KEY (RGROUP_ID) REFERENCES RESOURCE_GROUP(ID) ON DELETE CASCADE,
    FOREIGN KEY (RESOURCE_ID) REFERENCES RESOURCE(ID) ON DELETE CASCADE,
    UNIQUE KEY CGPREL (RGROUP_ID,RESOURCE_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--用户与资源关系表
--drop table CUSTOMER_RESOURCE_REL;
CREATE TABLE CUSTOMER_RESOURCE_REL (
  	CUSTOMER_ID  bigint(20) NOT NULL COMMENT '会员帐号',
	RESOURCE_ID  int(12) NOT NULL  COMMENT '资源ID', 
	ENABLE CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '是否有权限', 
	FROM_SHARED CHAR(1) DEFAULT 'N' NOT NULL  COMMENT '是否通过普通用户权限分享得到的', 
	START_DATE DATETIME COMMENT '起始时间',
	END_DATE DATETIME COMMENT '结束时间',
	CREATE_DATE DATETIME  COMMENT '关系创建时间', 
	CREATE_USER bigint(20) COMMENT '关系创建人id.可以是后台用户，也可以是其他会员分享',
    INDEX (CUSTOMER_ID, RESOURCE_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID) ON DELETE CASCADE,
    FOREIGN KEY (RESOURCE_ID) REFERENCES RESOURCE(ID) ON DELETE CASCADE,
    UNIQUE KEY CGPREL (CUSTOMER_ID,RESOURCE_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--用户组与资源关系表
--drop table CGROUP_RESOURCE_REL;
CREATE TABLE CGROUP_RESOURCE_REL    (
  	CGROUP_ID  int(10) NOT NULL COMMENT '会员组ID', 
	RESOURCE_ID  int(12) NOT NULL  COMMENT '资源ID', 
	ENABLE CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '是否有权限', 
	START_DATE DATETIME COMMENT '起始时间',
	END_DATE DATETIME COMMENT '结束时间',
	CREATE_DATE DATETIME  COMMENT '关系创建时间', 
	UPDATE_DATE DATETIME  COMMENT '关系更新时间', 
	CREATE_USER int  COMMENT '关系创建人id',
    INDEX (CGROUP_ID, RESOURCE_ID),
    FOREIGN KEY (CGROUP_ID) REFERENCES CGROUP(ID) ON DELETE CASCADE,
    FOREIGN KEY (RESOURCE_ID) REFERENCES RESOURCE(ID) ON DELETE CASCADE,
    UNIQUE KEY CGPREL (CGROUP_ID,RESOURCE_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
--ALTER TABLE CGROUP_RESOURCE_REL ADD START_DATE DATE;
--ALTER TABLE CGROUP_RESOURCE_REL ADD END_DATE DATE;

--授权记录表
--drop table PERMISSION_RECORD;
CREATE TABLE PERMISSION_RECORD    (
	OBJECT_TYPE int(1) NOT NULL COMMENT '授权对象；1 用户；2 用户组',
	CGROUP_NAME varchar(20) COMMENT '会员组名称', 
  	RESOURCE_NAME varchar(20) NOT NULL COMMENT '资源名称', 
	CUSTOMER_ID bigint(20) COMMENT '会员帐号',
	ACTION_TYPE int(1) NOT NULL COMMENT '动作；1 授权；0 撤销权限',
	CREATE_DATE DATETIME  COMMENT '时间', 
	CREATE_USER bigint(20) COMMENT '行为创建人(分享人或者后台用户名)',
    INDEX (RESOURCE_NAME)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;



--资源节点详情表

--drop table NODE;
CREATE TABLE NODE    (
  	ID  int(10) NOT NULL AUTO_INCREMENT COMMENT '资源节点ID', 
	NAME varchar(20) NOT NULL unique COMMENT '资源节点名称', 
	GRADE int(2) COMMENT '资源节点级别。根节点为0', 
	PARENT_ID  int(10) NOT NULL  COMMENT '父资源节点ID', 
	N_INT1 int(5) , 
    VC1 varchar(20) , 
	CHR1 CHAR(1), 
    INDEX (ID,NAME),
    PRIMARY KEY(ID),
    FOREIGN KEY (PARENT_ID) REFERENCES NODE(ID) 
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;




--资源节点父子关系表(树形关系)
--drop table NODE_REL;
--CREATE TABLE NODE_REL    (
--  	ID_PARENT  int(10) NOT NULL COMMENT '资源节点ID', 
--	ID_CHILD int(10) NOT NULL unique COMMENT '资源节点名称.一个节点只有一个父类', 
--	N_INT1 int(5) , 
--    VC1 varchar(20) , 
--	CHR1 CHAR(1), 
--    INDEX (ID_PARENT,ID_CHILD),
--	FOREIGN KEY (ID_PARENT) REFERENCES NODE(ID) ON DELETE CASCADE,
--	FOREIGN KEY (ID_CHILD) REFERENCES NODE(ID) ON DELETE CASCADE
--)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


--资源节点与资源关系表(一对多)
--drop table NODE_RESOURCE_REL;
--CREATE TABLE NODE_RESOURCE_REL    (
--  	NODE_ID  int(10) NOT NULL COMMENT '资源节点ID', 
---	RESOURCE_ID  int(12) NOT NULL unique COMMENT '资源节点名称.一个资源只能挂在一个节点上', 
--	N_INT1 int(5) , 
--    VC1 varchar(20) , 
--	CHR1 CHAR(1), 
--    INDEX (NODE_ID,RESOURCE_ID),
--	FOREIGN KEY (RESOURCE_ID) REFERENCES RESOURCE(ID) ON DELETE CASCADE,
--	FOREIGN KEY (NODE_ID) REFERENCES NODE(ID) ON DELETE CASCADE
--)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


