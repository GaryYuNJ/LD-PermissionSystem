
--后台用户
--drop table BACKENDUSER;
CREATE TABLE BACKENDUSER    (
  	BEU_ID  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '后台用户ID', 
	BEU_NAME varchar(20) NOT NULL unique COMMENT '后台用户名称', 
    BEU_PASSWORD  varchar(20) NOT NULL COMMENT '后台用户登陆密码', 
	BEU_STATUS CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '后台用户可用状态', 
	BEU_CREATEDATE DATE  COMMENT '后台用户创建时间', 
	BEU_CREATEUSER int COMMENT '后台用户创建人', 
    PRIMARY KEY(BEU_ID)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;

--用户详情表
--drop table CUSTOMER;
CREATE TABLE CUSTOMER   (
    ID  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员ID', 
  	CID varchar(20) NOT NULL unique COMMENT '会员帐号',
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
	CCREATDATE DATE COMMENT '创建日期',
	CMAINTOR varchar(15) COMMENT '最近维护人',
	CMAINTDATE DATE COMMENT '最近维护日期',
	CDATE1 DATE COMMENT '最近定期处理的日期',
	CDATE2 DATE COMMENT '',
	CDATE3 DATE COMMENT '',
	CDATE4 DATE COMMENT '',
	CDATE5 DATE COMMENT '',
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
	INDEX (ID,CID),
    PRIMARY KEY(ID)
   )ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;
   
   
--用户组表
--drop table CGROUP;
CREATE TABLE CGROUP    (
  	CGP_ID  int(10) NOT NULL AUTO_INCREMENT COMMENT '会员组ID', 
	CGP_NAME varchar(20) NOT NULL unique COMMENT '会员组名称', 
	CGP_STATUS CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '会员组可用状态', 
	CGP_CREATEDATE DATE  COMMENT '会员组创建时间', 
	CGP_CREATEUSER int NOT NULL  COMMENT '会员组创建人', 
	CGP_NUM1 NUMERIC , 
	CGP_NUM2 NUMERIC , 
    CGP_VC1 varchar(20) , 
	CGP_VC2 varchar(20), 
	CGP_CHR1 CHAR(1), 
	CGP_CHR2 CHAR(1),
    INDEX (CGP_ID,CGP_NAME),
    PRIMARY KEY(CGP_ID)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;


--用户与用户组关系表
--drop table CGROUPREL;
CREATE TABLE CGROUPREL    (
  	CGP_ID  int(10) NOT NULL COMMENT '会员组ID', 
	CID varchar(20) NOT NULL COMMENT '会员帐号',
	CGPREL_CREATEDATE DATE  COMMENT '会员组创建时间', 
	CGPREL_CREATEUSER int NOT NULL  COMMENT '会员组创建人',
    INDEX (CGP_ID, CID),
    FOREIGN KEY (CGP_ID) REFERENCES CGROUP(CGP_ID) ON DELETE CASCADE,
    FOREIGN KEY (CID) REFERENCES CUSTOMER(CID) ON DELETE CASCADE,
    UNIQUE KEY CGPREL (CGP_ID,CID)
)ENGINE=InnoDB 	DEFAULT CHARSET=utf8;


--资源类型表
--drop table RESOURCE_TYPE;
CREATE TABLE RESOURCE_TYPE    (
  	RT_ID  int(10) NOT NULL AUTO_INCREMENT COMMENT '资源类型ID', 
	RT_NAME varchar(30) NOT NULL unique COMMENT '资源类型名称',
    PRIMARY KEY(RT_ID)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO RESOURCE_TYPE(RT_NAME) VALUES('小区门禁' );
INSERT INTO RESOURCE_TYPE(RT_NAME) VALUES( '楼栋门禁' );
INSERT INTO RESOURCE_TYPE(RT_NAME) VALUES( '楼层门禁' );
INSERT INTO RESOURCE_TYPE(RT_NAME) VALUES( '其它资源' );



--资源的楼栋信息
--drop table BUILDING;
CREATE TABLE BUILDING    (
  	B_ID int(10) NOT NULL AUTO_INCREMENT COMMENT  '楼栋ID', 
	B_NAME varchar(30) NOT NULL unique COMMENT '楼栋名称',
	 PRIMARY KEY(B_ID)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

INSERT INTO BUILDING(B_NAME) VALUES('绿地世界之窗' );
INSERT INTO BUILDING(B_NAME) VALUES('裴总办公室' );
INSERT INTO BUILDING(B_NAME) VALUES( '许总办公室' );
INSERT INTO BUILDING(B_NAME) VALUES('白总办公室' );
INSERT INTO BUILDING(B_NAME) VALUES('卜总办公室' );
INSERT INTO BUILDING(B_NAME) VALUES('绿地之窗1-1' );


--资源详情表
--drop table RESOURCE;
CREATE TABLE RESOURCE    (
  	R_ID  int(12) NOT NULL AUTO_INCREMENT COMMENT '资源ID', 
	R_NAME varchar(20) NOT NULL unique COMMENT '资源名称', 
    R_TYPE_ID int(3) NOT NULL  COMMENT '资源类型id',
	R_STATUS CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '资源可用状态', 
	R_CREATEDATE DATE  COMMENT '资源创建时间', 
	R_CREATEUSER int NOT NULL  COMMENT '资源创建人', 
    R_MAC CHAR(20)  COMMENT '设备类型资源的MAC地址', 
    R_BUILDING_ID int(3) COMMENT '设备类型资源的楼栋信息id', 
    R_FLOOR int(5)  COMMENT '设备类型资源的楼层', 
    R_PASSWORD  CHAR(20)  COMMENT '设备类型资源的密码', 
    R_SEQUENCE INT(10) NOT NULL DEFAULT 1 COMMENT '显示序列',
    R_SHAREENABLE CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '资源是否可被用户分享权限', 
	R_INT1 int(10) , 
	R_INT2 int(10) , 
	R_NUM1 NUMERIC , 
	R_NUM2 NUMERIC , 
    R_VC1 varchar(40) , 
	R_VC2 varchar(40), 
	R_CHR1 CHAR(1), 
	R_CHR2 CHAR(1),
    INDEX (R_ID,R_NAME),
    PRIMARY KEY(R_ID),
    FOREIGN KEY (R_TYPE_ID) REFERENCES RESOURCE_TYPE(RT_ID),
    FOREIGN KEY (R_BUILDING_ID) REFERENCES BUILDING(B_ID) 
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;



--资源组详情表
--drop table RGROUP;
CREATE TABLE RGROUP    (
  	RGP_ID  int(10) NOT NULL AUTO_INCREMENT COMMENT '资源组ID', 
	RGP_NAME varchar(20) NOT NULL unique COMMENT '资源组名称', 
	RGP_STATUS CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '资源组可用状态', 
	RGP_ISPUBLIC CHAR(1) DEFAULT 'N' NOT NULL  COMMENT '是否公共资源组；公共资源可被所有用户操控', 
	RGP_CREATEDATE DATE  COMMENT '资源组创建时间', 
	RGP_CREATEUSER int COMMENT '资源组创建人', 
	RGP_NUM1 NUMERIC , 
	RGP_NUM2 NUMERIC , 
    RGP_VC1 varchar(20) , 
	RGP_VC2 varchar(20), 
	RGP_CHR1 CHAR(1), 
	RGP_CHR2 CHAR(1),
    INDEX (RGP_ID,RGP_NAME),
    PRIMARY KEY(RGP_ID)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;


--资源与资源组关系表
--drop table RGROUPREL;
CREATE TABLE RGROUPREL    (
  	RGP_ID  int(10) NOT NULL COMMENT '资源组id', 
	R_ID int(12) NOT NULL COMMENT '资源id',
	CGPREL_CREATEDATE DATE  COMMENT '资源组关系创建时间', 
	CGPREL_CREATEUSER int  COMMENT '资源组关系创建人',
    INDEX (RGP_ID, R_ID),
    FOREIGN KEY (RGP_ID) REFERENCES RGROUP(RGP_ID) ON DELETE CASCADE,
    FOREIGN KEY (R_ID) REFERENCES RESOURCE(R_ID) ON DELETE CASCADE,
    UNIQUE KEY CGPREL (RGP_ID,R_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--用户与资源关系表
--drop table CR_REL;
CREATE TABLE CR_REL (
  	CID  varchar(20) NOT NULL COMMENT '会员帐号',
	R_ID  int(12) NOT NULL  COMMENT '资源ID', 
	CR_ENABLE CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '是否有权限', 
	AU_FROM_SHARED CHAR(1) DEFAULT 'N' NOT NULL  COMMENT '是否通过普通用户权限分享得到的', 
	SHARED_USER varchar(20) COMMENT '分享人帐号', 
	CRREL_CREATEDATE DATE  COMMENT '关系创建时间', 
	CRREL_CREATEUSER int  COMMENT '关系创建人id',
    INDEX (CID, R_ID),
    FOREIGN KEY (CID) REFERENCES CUSTOMER(CID) ON DELETE CASCADE,
    FOREIGN KEY (R_ID) REFERENCES RESOURCE(R_ID) ON DELETE CASCADE,
	FOREIGN KEY (SHARED_USER) REFERENCES CUSTOMER(CID) ,
    UNIQUE KEY CGPREL (CID,R_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--用户组与资源关系表
--drop table CGPR_REL;
CREATE TABLE CGPR_REL    (
  	CGP_ID  int(10) NOT NULL COMMENT '会员组ID', 
	R_ID  int(12) NOT NULL  COMMENT '资源ID', 
	CR_ENABLE CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '是否有权限', 
	CGPR_REL_CREATEDATE DATE  COMMENT '关系创建时间', 
	CGPR_REL_UPDATEDATE DATE  COMMENT '关系更新时间', 
	CGPR_REL_CREATEUSER int  COMMENT '关系创建人id',
    INDEX (CGP_ID, R_ID),
    FOREIGN KEY (CGP_ID) REFERENCES CGROUP(CGP_ID) ON DELETE CASCADE,
    FOREIGN KEY (R_ID) REFERENCES RESOURCE(R_ID) ON DELETE CASCADE,
    UNIQUE KEY CGPREL (CGP_ID,R_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--授权记录表
--drop table PERMISSION_RECORD;
CREATE TABLE PERMISSION_RECORD    (
	PR_OBJECT_TYPE int(1) NOT NULL COMMENT '授权对象；1 用户；2 用户组',
	CGP_NAME varchar(20) COMMENT '会员组名称', 
  	R_NAME varchar(20) NOT NULL COMMENT '资源名称', 
	CID varchar(20) COMMENT '会员帐号',
	PR_TYPE int(1) NOT NULL COMMENT '动作；1 授权；0 撤销权限',
	PR_CREATEDATE DATE  COMMENT '时间', 
	PR_CREATEUSER varchar(20) COMMENT '行为创建人(分享人或者后台用户名)',
    INDEX (R_NAME)
)ENGINE=InnoDB  DEFAULT CHARSET=utf8;



--资源节点详情表

--drop table RNODE;
CREATE TABLE RNODE    (
  	RN_ID  int(10) NOT NULL AUTO_INCREMENT COMMENT '资源节点ID', 
	RN_NAME varchar(20) NOT NULL unique COMMENT '资源节点名称', 
	RN_GRADE int(2) NOT NULL COMMENT '资源节点级别。根节点为0', 
	RN_INT1 int(5) , 
    RN_VC1 varchar(20) , 
	RN_CHR1 CHAR(1), 
    INDEX (RN_ID,RN_NAME),
    PRIMARY KEY(RN_ID)
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;




--资源节点父子关系表(树形关系)
--drop table RNODE_REL;
CREATE TABLE RNODE_REL    (
  	RN_ID_PARENT  int(10) NOT NULL COMMENT '资源节点ID', 
	RN_ID_CHILD int(10) NOT NULL unique COMMENT '资源节点名称.一个节点只有一个父类', 
	RN_INT1 int(5) , 
    RN_VC1 varchar(20) , 
	RN_CHR1 CHAR(1), 
    INDEX (RN_ID_PARENT,RN_ID_CHILD),
	FOREIGN KEY (RN_ID_PARENT) REFERENCES RNODE(RN_ID) ON DELETE CASCADE,
	FOREIGN KEY (RN_ID_CHILD) REFERENCES RNODE(RN_ID) ON DELETE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


--资源节点与资源关系表(一对多)
--drop table RNODE_RESOURCE_REL;
CREATE TABLE RNODE_RESOURCE_REL    (
  	RN_ID  int(10) NOT NULL COMMENT '资源节点ID', 
	R_ID  int(12) NOT NULL unique COMMENT '资源节点名称.一个资源只能挂在一个节点上', 
	RN_INT1 int(5) , 
    RN_VC1 varchar(20) , 
	RN_CHR1 CHAR(1), 
    INDEX (RN_ID,R_ID),
	FOREIGN KEY (R_ID) REFERENCES RESOURCE(R_ID) ON DELETE CASCADE,
	FOREIGN KEY (RN_ID) REFERENCES RNODE(RN_ID) ON DELETE CASCADE
)ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


