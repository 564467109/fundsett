
              CREATE TABLE `FEE_SUITE` (                          
                `listid` bigint(20) NOT NULL AUTO_INCREMENT  COMMENT '主键',         
                `accountcode` varchar(255) DEFAULT '' COMMENT '计算对象，一般为基金代码',                    
                `statux` int(11) DEFAULT 0 COMMENT '状态',                
                `feetype` int(11) DEFAULT 0 COMMENT '费用类型',    
                `partner` varchar(255) DEFAULT '' COMMENT '合作商户',              
                `validdate` varchar(50) DEFAULT '20141203' COMMENT '生效日期，为yyyyMMdd类型字符串',    
                `bookid` bigint(20) DEFAULT 0 COMMENT '策略分组id',   		
                PRIMARY KEY (`listid`),
                KEY `code-fee-partner` (`accountcode`,`feetype`,`partner`), 
                KEY `bookid` (`bookid`),
                KEY `validdate` (`validdate`)
              ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ;
              
              

              CREATE TABLE `FEE_Book` (                          
                `listid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',  
                `bookid` bigint(20) DEFAULT 0 COMMENT '策略详细分组id',				
                `bookname` varchar(255) DEFAULT '' COMMENT '分组名称，由用户自己定义',                    
                `statux` int(11) DEFAULT 0 COMMENT '状态-是否有效',                
                `feetype` int(11) DEFAULT 0 COMMENT '费用类型',               
                `createdate` varchar(50) DEFAULT '20141203' COMMENT '创建日期',
                `isshow` int(11) DEFAULT 0 COMMENT '是否作为标准显示',               
                `note` varchar(500) DEFAULT '' COMMENT '作为扩展字段，如在界面展示引用情况使用，但在数据库中不对此字段予以持久化保存',                        
                PRIMARY KEY (`listid`)  ,
                KEY `feecatalog` (`feetype`),
                KEY `bookid` (`bookid`)                
              ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ;
              
             CREATE TABLE `FEE_Detail` (                          
                `listid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',  
                `bookid` bigint(20) DEFAULT '0' COMMENT '策略详细分组id',				
                `operway` varchar(255) DEFAULT '' COMMENT '受理渠道',
                `paychannelid` varchar(255) DEFAULT '' COMMENT '支付渠道',
                `paycenterid` varchar(255) DEFAULT '' COMMENT '支付网点',
                `businesscode` varchar(255) DEFAULT '' COMMENT '业务代码【如20认购22申购24赎回】',   
                `calmode` int(11) DEFAULT 0 COMMENT '计费基准（0默认计费，1-持仓市值等）', 
                `calpolicy` int(11) DEFAULT 0 COMMENT '计费策略（1固定费用，2固定比例，3-金额、份额分段）',                                                                   
                `fixval` decimal(19 ,4) DEFAULT '0.0' COMMENT '固定值（表示固定值或者固定比例）',                
                `zoneid` bigint(20) DEFAULT 0 COMMENT '费用分段编号（0表示无分段设置）',               
                `leftclose` int(11) DEFAULT 1 COMMENT '费用分段式左封闭还是右封闭',  
                PRIMARY KEY (`listid`)     ,
                KEY `dtbookid` (`bookid`),
                KEY `dtzoneid` (`zoneid`)
              ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
              
              
              CREATE TABLE `FEE_ZONECATALOG` (                          
                `listid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',  
                `feetype` int(11) DEFAULT 0 COMMENT '费用类型',				
                `ufcatalog` varchar(255) DEFAULT '' COMMENT '自定义分类',
                `nikename` varchar(255) DEFAULT '' COMMENT '昵称',
                `descr` varchar(255) DEFAULT '' COMMENT '描述',
                `note` varchar(255) DEFAULT '' COMMENT '备注',   
                `statux` int(11) DEFAULT 0 COMMENT '状态是否有效',  
                PRIMARY KEY (`listid`)  ,
                KEY `fee-cataolog` (`feetype`),
                KEY `zone-catalog` (`feetype`, `ufcatalog`)                             
              ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ;
              
              
              CREATE TABLE `FEE_ZONEGROUP` (                          
                `listid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',  
                `catalogid` bigint(20) DEFAULT 0 COMMENT '所属分类id',
                `groupname` varchar(255) DEFAULT '' COMMENT '分组名',
                `nikename` varchar(255) DEFAULT '' COMMENT '分组昵称',	
                `descr` varchar(255) DEFAULT '' COMMENT '描述信息',
                `note` varchar(500) DEFAULT '' COMMENT '作为扩展字段，如在界面展示引用情况使用，但在数据库中不对此字段予以持久化保存',   
                `statux` int(11) DEFAULT 0 COMMENT '状态-是否有效',  
                PRIMARY KEY (`listid`)  ,
                KEY `group-catalog` (`catalogid`)
              ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
              
              CREATE TABLE `FEE_ZONE` (                          
                `listid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键流水号',  
                `zoneid` bigint(20) DEFAULT 0 COMMENT '费用分区编号，与FeeDetailInfo中的zoneid相对,与ZoneGroupInfo中的listid对应',
                `upamount` varchar(255) DEFAULT '' COMMENT '上限',
                `downamount` varchar(255) DEFAULT '' COMMENT '下限',  
                `valtype` int(11) DEFAULT 0 COMMENT '设置值的类型（0-比例，1-固定值）',
                `rateval` decimal(19 ,4) DEFAULT 0 COMMENT '设置值',   
                PRIMARY KEY (`listid`)    ,
                KEY `zoneid` (`zoneid`)
              ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ;
