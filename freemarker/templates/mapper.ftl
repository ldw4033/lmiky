<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" 
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="${namespace}">
	<resultMap type="${className}" id="${resultMapId}" extends="com.lmiky.jdp.database.pojo.BasePojo.basePojo">
		<#list fields as field> 
		<result column="${field}" property="${field}"/>
		</#list>
	</resultMap>

	<sql id="tableName">
		${tableName}
	</sql>
	<sql id="tableAlias">
		${className}
	</sql>

	<insert id="add" parameterType="${className}" useGeneratedKeys="true" keyColumn="id" keyProperty="id">
		insert into
		<include refid="tableName" />
		(
			 id
			<#list fields as field> 
			,${field}
			</#list>
		)
		values
		(
			 ${r"#{"}id}
			<#list fields as field> 
			,${r"#{"}${field}}
			</#list>
		)
	</insert>

	<select id="list" resultMap="${resultMapId}">
		select * from
		<include refid="tableName" /> <include refid="tableAlias" />
		<trim prefix="where">
			<include refid="common.aliasPropertiesCondition" />
		</trim>
		<include refid="common.sortCondition" />
		<include refid="common.pageCondition" />
	</select>
</mapper>