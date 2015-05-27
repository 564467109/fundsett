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
<script type="text/javascript" src="<%=path %>/js/fee/updatecatalog.js"></script>
<script type="text/javascript" src="<%=path %>/js/fee/docatalog.js"></script>
<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
</head>
<body style="height:100%">
	<center>
		<span>修改分类信息</span>
		<div >
			<form id="updateForm">
			  <table border="1">
			  	<tr>
			  		<th style="height:40px;width:100px">费用类型</th>
			  		<td style="height:40px;width:200px">
			  			<c:if test="${flag ne true}">
			  			<select id="feetype" name="feetype" style="height:26px;">
			  				<option value="">请选择</option>
			  				<c:forEach items="${feeType}" var="fee">
			  					<option value="${fee.type}" <c:if test="${fee.type==feeZonecatalog.feetype }">selected </c:if>>${fee.val}</option>
			  				</c:forEach>
			  			</select>
			  			</c:if>
			  			<c:if test="${flag eq true}">
			  				${feeName }<input name="feetype" type="hidden" value="${feeZonecatalog.feetype }"/>
			  			</c:if>
			  			<span id="feetypeMsg" style="color:red"></span>
			  		</td>
			  		<th style="height:40px;width:100px">自定义分类</th>
			  		<td style="height:40px;width:330px">
			  			<input id="ufcatalog" name="ufcatalog" type="text" value="${feeZonecatalog.ufcatalog}" style="height:26px;width: 200px;"/>
			  			<span id="ufcatalogMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<th style="height:40px;">分类昵称</th>
			  		<td style="height:40px;" colspan="3">
			  			<input id="nikename" name="nikename" type="text" value="${feeZonecatalog.nikename}" style="height:26px;width: 350px;"/>
			  			<span id="nikenameMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<th style="height:40px;">分类描述</th>
			  		<td style="height:40px;" colspan="3">
			  			<input id="descr" name="descr" type="text" value="${feeZonecatalog.descr}" style="height:26px;width: 350px;"/>
			  			<span id="descrMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<th style="height:40px;">备注</th>
			  		<td style="height:40px;" colspan="3">
			  			<input id="note" name="note" type="text" value="${feeZonecatalog.note}" style="height:26px;width: 350px;"/>
			  			<input id="listid" name="listid" type="text" value="${feeZonecatalog.listid}" style="display:none"/>
			  			<span id="noteMsg" style="color:red"></span>
			  		</td>
			  	</tr>
			  	<tr>
			  		<td style="height:40px;" colspan="4">
			  			<center>
			  				<input id="updateCatalog" type="button" value="修改" style="height:26px;width:70px;"/>
			  			</center>
			  		</td>
			  	</tr>
			  </table>
			 </form>
		</div>
	</center>
</body>
</html>