

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
    ID  bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员ID。 ',
     CM_MEMID  varchar(20) COMMENT 'CRM的成员ID，对应CRM主键CMMEMID。考虑到业务要求要先对未注册人进行授权，此时CID并不存在，所以去除NOT NULL unique 限制',
	 CM_CUSTID  varchar(20) COMMENT '会员ID',
	 CM_ISOWNER  CHAR(1) COMMENT '', 
	 CM_RELATION  CHAR(1) COMMENT '与主成员关系(0主成员；1-7其它)', 
	 CM_GRADE  CHAR(1) COMMENT '级别', 
	 CM_FLAG1  CHAR(1) COMMENT '积分回馈权限', 
	 CM_FLAG2  CHAR(1) COMMENT '', 
	 CM_FLAG3  CHAR(1) COMMENT '', 
	 CM_LMYKJF  NUMERIC COMMENT '联名卡预扣积分', 
	 CM_JFXFXE  NUMERIC COMMENT '单次积分消费限额', 
	 CM_LCZHYE  NUMERIC COMMENT '零钞帐户余额', 
	 CM_N1  NUMERIC COMMENT '', 
	 CM_N2  NUMERIC COMMENT '', 
	 CM_N3  NUMERIC COMMENT '', 
	 CM_N4  NUMERIC COMMENT '', 
	 CM_N5  NUMERIC COMMENT '', 
	 CM_N6  NUMERIC COMMENT '', 
	 CM_N7  NUMERIC COMMENT '', 
	 CM_N8  NUMERIC COMMENT '', 
	 CM_N9  NUMERIC COMMENT '', 
	 CM_JFA  NUMERIC COMMENT '消费积分', 
	 CM_JFB  NUMERIC COMMENT '倍享积分', 
	 CM_JFC  NUMERIC COMMENT '温馨积分', 
	 CM_JFD  NUMERIC COMMENT '赠予积分', 
	 CM_JFE  NUMERIC COMMENT '身份积分', 
	 CM_JFF  NUMERIC COMMENT '购买积分', 
	 CM_DECJF  NUMERIC COMMENT '扣减积分', 
	 CM_TOTJF  NUMERIC COMMENT '总积分(A+B+C+D+E+F+回馈)', 
	 CM_XFJE  NUMERIC COMMENT '消费金额', 
	 CM_MAINTOR  varchar(15) COMMENT '维护人', 
	 CM_MAINTDATE  DATETIME COMMENT '维护如期', 
	 CM_NAME  varchar(100) COMMENT '姓名', 
	 CM_BIRTHDAY  DATE COMMENT '生日', 
	 CM_BIRTHTYPE  CHAR(1) COMMENT '生日类型(1-公历;2-农历)', 
	 CM_SEX  CHAR(1) COMMENT '性别', 
	 CM_ADDR  varchar(200) COMMENT '详细地址', 
	 CM_ADD1  varchar(100) COMMENT '地址（省）', 
	 CM_ADD2  varchar(100) COMMENT '地址（市）', 
	 CM_ADD3  varchar(100) COMMENT '地址（区）', 
	 CM_ADD4  varchar(100) COMMENT '地址（街道）', 
	 CM_ZIP  CHAR(6) COMMENT '邮编', 
	 CM_IDTYPE  CHAR(1) COMMENT '证件类型', 
	 CM_IDNO  varchar(20) COMMENT '证件号码', 
	 CM_LXTYPE1  CHAR(1) COMMENT '短信联系', 
	 CM_LXTYPE2  CHAR(1) COMMENT '信件联系', 
	 CM_LXTYPE3  CHAR(1) COMMENT '电邮联系', 
	 CM_LXTYPE4  CHAR(1) COMMENT '电话联系', 
	 CM_LXTYPE5  CHAR(1) COMMENT '', 
	 CM_TEL  varchar(30) COMMENT '电话号码', 
	 CM_MOBILE1  varchar(30) COMMENT '手机号码1', 
	 CM_MOBILE2  varchar(30) COMMENT '手机号码2', 
	 CM_FAX  varchar(30) COMMENT '传真', 
	 CM_EMAIL  varchar(100) COMMENT '电子邮箱', 
	 CM_ISEMPLOYEE  CHAR(1) COMMENT '是否内部员工', 
	 CM_LUNAR  varchar(8) COMMENT '农历', 
	 CM_LUNARCHN  varchar(100) COMMENT '农历中文', 
	 CM_SX  varchar(10) COMMENT '属相', 
	 CM_COMPANY  varchar(100) COMMENT '工作单位', 
	 CM_DKJF  NUMERIC DEFAULT 0 COMMENT '抵扣积分,历史积分定期处理时增加', 
	 CM_DHISJF  NUMERIC DEFAULT 0 COMMENT '用来记录积分定期处理中的减的积分，历史积分定期处理中清0', 
	 CM_CHR1  varchar(100) COMMENT '', 
	 CM_CHR2  varchar(100) COMMENT '', 
	 CM_CHR3  varchar(100) COMMENT '', 
	 CM_CHR4  varchar(100) COMMENT '', 
	 CM_CHR5  varchar(100) COMMENT '', 
	 CM_CHR6  varchar(100) COMMENT '', 
	 CM_CHR7  varchar(100) COMMENT '', 
	 CM_CHR8  varchar(100) COMMENT '', 
	 CM_CHR9  varchar(100) COMMENT '', 
	 CM_CHR10  varchar(100) COMMENT '', 
	 CM_CHR11  varchar(100) COMMENT '', 
	 CM_CHR12  varchar(100) COMMENT '', 
	 CM_CHR13  varchar(100) COMMENT '', 
	 CM_CHR14  varchar(100) COMMENT '', 
	 CM_CHR15  varchar(100) COMMENT '', 
	 CM_CHR16  varchar(100) COMMENT '', 
	 CM_CHR17  varchar(100) COMMENT '', 
	 CM_CHR18  varchar(100) COMMENT '', 
	 CM_CHR19  varchar(100) COMMENT '', 
	 CM_CHR20  varchar(100) COMMENT '', 
	 CM_CHR21  varchar(100) COMMENT '', 
	 CM_CHR22  varchar(100) COMMENT '', 
	 CM_CHR23  varchar(100) COMMENT '', 
	 CM_CHR24  varchar(100) COMMENT '', 
	 CM_CHR25  varchar(100) COMMENT '', 
	 CM_CHR26  varchar(100) COMMENT '', 
	 CM_CHR27  varchar(100) COMMENT '', 
	 CM_CHR28  varchar(100) COMMENT '', 
	 CM_CHR29  varchar(100) COMMENT '', 
	 CM_CHR30  varchar(100) COMMENT '', 
	 CM_CHR31  varchar(100) COMMENT '', 
	 CM_CHR32  varchar(100) COMMENT '', 
	 CM_CHR33  varchar(100) COMMENT '', 
	 CM_CHR34  varchar(100) COMMENT '', 
	 CM_CHR35  varchar(100) COMMENT '', 
	 CM_CHR36  varchar(100) COMMENT '', 
	 CM_CHR37  varchar(100) COMMENT '', 
	 CM_CHR38  varchar(100) COMMENT '', 
	 CM_CHR39  varchar(100) COMMENT '', 
	 CM_CHR40  varchar(100) COMMENT '', 
	 CM_CHR41  varchar(100) COMMENT '', 
	 CM_CHR42  varchar(100) COMMENT '', 
	 CM_CHR43  varchar(100) COMMENT '', 
	 CM_CHR44  varchar(100) COMMENT '', 
	 CM_CHR45  varchar(100) COMMENT '', 
	 CM_CHR46  varchar(100) COMMENT '', 
	 CM_CHR47  varchar(100) COMMENT '', 
	 CM_CHR48  varchar(100) COMMENT '', 
	 CM_CHR49  varchar(100) COMMENT ' 当前会员等级ABCDEZ', 
	 CM_CHR50  varchar(100) COMMENT 'ABCDEZ会员价值', 
	 CM_NUM1  NUMERIC COMMENT '', 
	 CM_NUM2  NUMERIC COMMENT '', 
	 CM_NUM3  NUMERIC COMMENT '', 
	 CM_NUM4  NUMERIC COMMENT '', 
	 CM_NUM5  NUMERIC COMMENT '', 
	 CM_NUM6  NUMERIC COMMENT '', 
	 CM_NUM7  NUMERIC COMMENT '', 
	 CM_NUM8  NUMERIC COMMENT '', 
	 CM_NUM9  NUMERIC COMMENT '', 
	 CM_NUM10  NUMERIC COMMENT '', 
	 CM_NUM11  NUMERIC COMMENT '', 
	 CM_NUM12  NUMERIC COMMENT '', 
	 CM_NUM13  NUMERIC COMMENT '', 
	 CM_NUM14  NUMERIC COMMENT '', 
	 CM_NUM15  NUMERIC COMMENT '', 
	 CM_NUM16  NUMERIC COMMENT '', 
	 CM_NUM17  NUMERIC COMMENT '', 
	 CM_NUM18  NUMERIC COMMENT 'R', 
	 CM_NUM19  NUMERIC COMMENT 'F', 
	 CM_NUM20  NUMERIC COMMENT 'M', 
	 CM_MKT  varchar(20) COMMENT '所属门店', 
	 CM_KHDATE  DATETIME COMMENT '开户日期', 
	 CM_XFDATE  DATETIME COMMENT '最后消费日期', 
	 CM_SJDATE  DATETIME COMMENT '最后升级日期', 
	 CM_JJDATE  DATETIME COMMENT '最后降级日期', 
	 CM_ASTRO  varchar(20) COMMENT '星座', 
	 CM_FSTDATE  DATETIME COMMENT '开卡后第一次消费日期', 
	 CM_TOKEN  varchar(30) COMMENT 'APP登录令牌', 
	 CM_TOKEN2  varchar(30) COMMENT '快递箱令牌', 
	 CM_TOKENDATE  DATETIME COMMENT 'APP最后登录日期', 
	 CM_PWD  varchar(100) COMMENT '密码', 
	 CM_SSQY  varchar(20) COMMENT '所属企业', 
	 CM_QYJS  varchar(20) COMMENT '企业角色', 
	 CM_JTJS  varchar(20) COMMENT '家庭角色', 
	 CM_PTNAME  varchar(100) COMMENT '图片名称', 
	 CM_CZZ  NUMERIC COMMENT '成长值', 
	 CM_REFEREE  varchar(20) COMMENT '推荐人id', 
    
	INDEX (ID,CM_CUSTID,CM_MOBILE1),
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

INSERT INTO BUILDING(NAME) VALUES('绿地之窗C-5栋' );
INSERT INTO BUILDING(NAME) VALUES('绿地之窗C-1栋' );


--资源详情表
--drop table RESOURCE;
CREATE TABLE RESOURCE    (
  	ID  int(12) NOT NULL AUTO_INCREMENT COMMENT '资源ID', 
	NAME varchar(20) NOT NULL  unique COMMENT '资源名称', 
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
    PASSWORD  CHAR(200)  COMMENT '设备钥匙的密码', 
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


--用户与资源组关系表
--drop table CUSTOMER_RESOURCE_GROUP_REL;
CREATE TABLE CUSTOMER_RESGROUP_REL (
  	CUSTOMER_ID  bigint(20) NOT NULL COMMENT '会员帐号',
	RGROUP_ID  int(10) NOT NULL COMMENT '资源组id', 
	START_DATE DATETIME COMMENT '起始时间',
	END_DATE DATETIME COMMENT '结束时间',
	CREATE_DATE DATETIME  COMMENT '关系创建时间', 
	CREATE_USER bigint(20) COMMENT '关系创建人id.',
    INDEX (CUSTOMER_ID, RGROUP_ID),
    FOREIGN KEY (CUSTOMER_ID) REFERENCES CUSTOMER(ID) ON DELETE CASCADE,
    FOREIGN KEY (RGROUP_ID) REFERENCES RESOURCE_GROUP(ID) ON DELETE CASCADE,
    UNIQUE KEY CGPREL (CUSTOMER_ID,RGROUP_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--用户组与资源组关系表
--drop table CUSTOMER_RESOURCE_GROUP_REL;
CREATE TABLE CUSGROUP_RESGROUP_REL (
  	CGROUP_ID int(10)  NOT NULL COMMENT '会员组Id',
	RGROUP_ID  int(10) NOT NULL COMMENT '资源组id', 
	START_DATE DATETIME COMMENT '起始时间',
	END_DATE DATETIME COMMENT '结束时间',
	CREATE_DATE DATETIME  COMMENT '关系创建时间', 
	CREATE_USER bigint(20) COMMENT '关系创建人id.',
    INDEX (CGROUP_ID, RGROUP_ID),
    FOREIGN KEY (CGROUP_ID) REFERENCES CGROUP(ID) ON DELETE CASCADE,
    FOREIGN KEY (RGROUP_ID) REFERENCES RESOURCE_GROUP(ID) ON DELETE CASCADE,
    UNIQUE KEY CGPREL (CGROUP_ID,RGROUP_ID)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


--授权记录表
--drop table PERMISSION_RECORD;
CREATE TABLE PERMISSION_RECORD    (
	OBJECT_RELATION int(1) NOT NULL COMMENT '授权关系；1 用户与资源；2 用户与资源组；3 用户组与资源 ；4 用户组与资源组；5 用户组添加用户；6 资源组添加资源；取消权限操作，对应负值',
	CUSTOMER_Id bigint(20) COMMENT '会员ID', 
	RESOURCE_ID int(12) COMMENT '资源ID',
  	CGROUP_ID int(10)  COMMENT '会员组Id',
	RGROUP_ID  int(10) COMMENT '资源组id', 
	ACTION_TYPE int(1) NOT NULL COMMENT '动作；1 授权；0 撤销权限',
	START_DATE DATETIME COMMENT '如果ACTION_TYPE=1, 权限起始时间',
	END_DATE DATETIME COMMENT '如果ACTION_TYPE=1, 权限结束时间',
	CREATE_DATE DATETIME  COMMENT '时间', 
	CREATE_USER bigint(20) COMMENT '行为创建人(分享人或者后台用户名)',
    INDEX (CUSTOMER_Id,RESOURCE_ID),
    FOREIGN KEY (CUSTOMER_Id) REFERENCES CUSTOMER(ID) ON DELETE CASCADE ,
    FOREIGN KEY (RESOURCE_ID) REFERENCES RESOURCE(ID) ON DELETE CASCADE 
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


