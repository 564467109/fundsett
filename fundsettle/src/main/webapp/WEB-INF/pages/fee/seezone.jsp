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
<script type="text/javascript">
	$(function(){
		$("#backZone").click(function(){
			window.location.href=domain+"/fee/toQueryZone.htm";
		});
	});
</script>
<style type="text/css">
	.msg{
		border-color: red;
    }
</style>
</head>
<body>
	<center>
		<div>
			<span>查看分区信息</span>
			<table border="1">
				<tr>
					<th style="height:40px;width:100px">分类编号</th>
					<td style="height:40px;width:250px">${feeZonegroup.ufcatalog} (编号:${feeZonegroup.catalogid})</td>
					<th style="height:40px;width:150px">分组编号</th>
					<td style="height:40px;width:150px">${feeZonegroup.groupname} (编号:${feeZonegroup.listid})</td>
				</tr>
				<tbody id="level">
				<c:forEach items="${feeZonegroup.feeZones }" var="feeZone">
					<tr>
						<td style="height:40px;width:100px">${feeZone.downamount}</td>
						<td style="height:40px;width:100px">${feeZone.upamount}</td>
						<td style="height:40px;width:100px">
						<c:if test="${feeZone.valtype==1}">定值</c:if>
						<c:if test="${feeZone.valtype==0}">定比例</c:if>
						</td>
						<td style="height:40px;width:100px">${feeZone.rateval}</td>
					</tr>
				</c:forEach>
				</tbody>
				<tr>
					<th colspan="4"><input type="button" value="返回" id="backZone" style="height:26px;width:70px;"></th>
				</tr>
			</table>
		</div>
	</center>
</body>
</html>