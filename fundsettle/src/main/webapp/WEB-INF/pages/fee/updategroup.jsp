<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<script type="text/javascript" src="<%=path %>/js/fee/updategroup.js"></script>
<script type="text/javascript" src="<%=path %>/js/fee/dogroup.js"></script>
<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
<title>添加分区分组信息</title>
</head>
<body style="height:100%">
	<center>
		<div>
			<span>修改分组信息</span>
			<form id="updateForm">
			  <table border="1">
			  	<tr>
			  		<th style="height:40px;width:100px">分类编号</th>
			  		<td style="height:40px;width:400px">
			  			<select id="catalogid" name="catalogid" style="height:26px;width:250px">
			  				<option value="">请选择</option>
			  				<c:forEach items="${feeZonecatalogs}" var="catalog">
			  					<option value="${catalog.listid}" <c:if test="${catalog.listid==feeZonegroup.catalogid}">selected</c:if> >${catalog.ufcatalog}  (编号:${catalog.listid})</option>
			  				</c:forEach>
			  			</select>
			  			<span id="catalogidMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<th style="height:40px;">分组名</th>
			  		<td style="height:40px;">
			  			<input id="groupname" name="groupname" type="text" value="${feeZonegroup.groupname}" style="height:26px;width: 300px;"/>
			  			<span id="groupnameMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<th style="height:40px;">分组昵称</th>
			  		<td style="height:40px;">
			  			<input id="nikename" name="nikename" type="text" value="${feeZonegroup.nikename}" style="height:26px;width: 300px;"/>
			  			<span id="nikenameMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<th style="height:40px;">分组描述</th>
			  		<td style="height:40px;">
			  			<input id="descr" name="descr" type="text" value="${feeZonegroup.descr}" style="height:26px;width: 300px;"/>
			  			<input id="listid" name="listid" type="text" value="${feeZonegroup.listid}" style="display:none"/>
			  			<span id="descrMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<td style="height:40px;" colspan="2">
			  			<center>
			  				<input id="updateGroup" type="button" value="修改" style="height:26px;width:70px;"/>
			  			</center>
			  		</td>
			  	</tr>
			  </table>
			 </form>
		</div>
	</center>
</body>
</html>