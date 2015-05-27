<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="../common/head.jsp"></jsp:include>
<title>新增分区信息</title>
<script type="text/javascript" src="<%=path %>/js/fee/dozone.js"></script>
<script type="text/javascript" src="<%=path %>/js/fee/updatezone.js"></script>
<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<style type="text/css">
	.msg{
		border-color: red;
    }
</style>
</head>
<body>
	<center>
		<div>
			<span>修改分区信息</span>
			<table border="1">
				<tr>
					<th style="height:40px;width:100px">设置区域</th>
					<td style="height:40px;width:450px" colspan="2">
						<input type="text" name="zone" id="zone" style="height:26px;width: 200px;"/>
						<span id="zoneMsg" style="color:red"></span>
						<span id="zoneArr" style="display:none">${feeZonegroup.listid}</span>
					</td>
					<th style="height:40px;width:200px"><input type="button" id="produceZone" value="生成" style="height:26px;width:70px;"/></th>
				</tr>
				<tr>
					<th style="height:40px;width:100px">分类编号</th>
					<td style="height:40px;width:300px">${feeZonegroup.ufcatalog} (编号:<span id="catalogId">${feeZonegroup.catalogid}</span>)</td>
					<th style="height:40px;width:150px">分组编号</th>
					<td style="height:40px;width:200px">${feeZonegroup.groupname} (编号:<span id="groupId">${feeZonegroup.listid}</span>)</td>
				</tr>
				<tbody id="level">
				<c:forEach items="${feeZonegroup.feeZones }" var="feeZone">
					<tr>
						<td style="height:40px;width:100px" name="begin">${feeZone.downamount}</td>
						<td style="height:40px;width:100px" name="end">${feeZone.upamount}</td>
						<td style="height:40px;width:100px">
							<select style="height:28px;width:80px" name="levelType">
								<option value="1" <c:if test="${feeZone.valtype==1}">selected</c:if> >定值</option>
								<option value="0" <c:if test="${feeZone.valtype==0}">selected</c:if> >定比例</option>
							</select>
						</td>
						<td style="height:40px;width:100px"><input type="text" name="ratio" value="${feeZone.rateval}" style="width:70px;height:28px"/></td>
					</tr>
				</c:forEach>
				</tbody>
				<tr>
					<th colspan="4"><input type="button" value="修改" id="updateZone" style="height:26px;width:70px;"></th>
				</tr>
			</table>
		</div>
	</center>
</body>
</html>