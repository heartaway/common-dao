## DAO层编码规范

* 数据库表的创建规则

	* 表字段统一使用"_"分词的命名规则。

	* 主键统一使用bigint（21）类型，时间统一使用datetime类型。

	* 所有表的删除操作都必须是逻辑删除，避免做物理删除。
  
  	* 所有表都必须有如下四个关键字段（且这些字段都不允许空）: 
	
	```
  	CREATE TABLE 'demo_table' (
  		'id' bigint(21) unsigned NOT NULL AUTO_INCREMENT,
  		'gmt_create' datetime NOT NULL,
  		'gmt_modified' datetime NOT NULL,
  		'is_delete' tinyint(4) NOT NULL DEFAULT '0',
  	PRIMARY KEY (`id`),
	) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf-8;
	```
	如果表含有扩展属性，可以包含features字段
	```
	'features' varchar(1024) DEFAULT NULL,
	```
 
* DTO对象的声明规则

	* DTO对象的属性命名统一使用驼峰方式，并且与数据库的表字段一一对应，示例：
	
	| DTO           | Table               |
	| ------------- |:-------------:      |
	| private Long id;     	    |  id      |
	| private String name;     |  name	  |
   | private Date gmtCreate;     | gmt_create  |   
   | private Date gmtModified;     | gmt_modified  |  
   | private Integer isDelete;     | is_delete  |  
   
* sql-map文件的编写
	
	可以使用haitao-common-dao二方库中的工具类自动生成sql-map，使用方式：
	
	* 添加依赖
	
	  ```
	 <dependency>
	   	<groupId>com.sfebiz.common</groupId>
    		<artifactId>common-dao</artifactId>
    		<version>1.0.2-SNAPSHOT</version>	
    	</dependency>
	  ```
	
	* 编写DTO，创建表
	
	* 使用工具类自动生成sql-map  
	  
	  参考DaoHelper类中的genXML和genXMLWithFeature函数，完整示例请参考[测试DEMO](/src/test/java/com/sfebiz/common/dao/test/DemoTest.java)
	  
      		 
		
