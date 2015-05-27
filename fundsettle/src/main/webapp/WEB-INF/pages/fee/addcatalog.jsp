<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>添加分区分类信息</title>

<jsp:include page="../common/head.jsp"></jsp:include>
<script type="text/javascript" src="<%=path %>/js/fee/docatalog.js"></script>
<script type="text/javascript" src="<%=path %>/js/fee/addcatalog.js"></script>
<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
</head>
<body style="height:100%">
	<center>
		<div >
			<span>添加分类信息</span>
			<form id="addForm">
			  <table border="1">
			  	<tr>
			  		<th style="height:40px;width:100px">费用类型</th>
			  		<td style="height:40px;width:200px">
			  			<select id="feetype" name="feetype" style="height:26px;">
			  				<option value="">请选择</option>
			  				<c:forEach items="${feeType}" var="fee">
			  					<option value="${fee.type}">${fee.val}</option>
			  				</c:forEach>
			  			</select>
			  			<span id="feetypeMsg" style="color:red"></span>
			  		</td>
			  		<th style="height:40px;width:100px">自定义分类</th>
			  		<td style="height:40px;width:330px">
			  			<input id="ufcatalog" name="ufcatalog" type="text" style="height:26px;width: 200px;"/>
			  			<span id="ufcatalogMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<th style="height:40px;">分类昵称</th>
			  		<td style="height:40px;" colspan="3">
			  			<input id="nikename" name="nikename" type="text" style="height:26px;width: 350px;"/>
			  			<span id="nikenameMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<th style="height:40px;">分类描述</th>
			  		<td style="height:40px;" colspan="3">
			  			<input id="descr" name="descr" type="text" style="height:26px;width: 350px;"/>
			  			<span id="descrMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<th style="height:40px;">备注</th>
			  		<td style="height:40px;" colspan="3">
			  			<input id="note" name="note" type="text" style="height:26px;width: 350px;"/>
			  			<span id="noteMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<td style="height:40px;" colspan="4">
			  			<center>
			  				<input id="addCatalog" type="button" value="新增" style="height:26px;width:70px;"/>
			  			</center>
			  		</td>
			  	</tr>
			  </table>
			 </form>
		</div>
	</center>
</body>
</html>