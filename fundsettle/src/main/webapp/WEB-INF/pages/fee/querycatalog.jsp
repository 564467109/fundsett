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
<script type="text/javascript" src="<%=path%>/js/fee/querycatalog.js"></script>
<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
<title>分类列表</title>
</head>
<body>
	<table>
  		<tbody>
  		<tr>
  		<td>分类编号：</td>
  		<td><input type="text" name="listid" class="listid"/></td>
  		<td>费用类型：</td>
  		<td>
	  		<select class="feetype">
	  			<option value="">请选择</option>
				<c:forEach items="${feeType}" var="fee">
			  		<option value="${fee.type}">${fee.val}</option>
			  	</c:forEach>
			</select>
		</td>
  		<td>费用昵称：</td>
  		<td><input type="text" class="nikename"/></td>
  		<td><input type="button" value="查询" class="btnSearch" onclick="doSearch()"></td>
  		</tr>
  		</tbody>
  	</table>
	<table id="catalogGrid" fit="false"></table>
</body>
</html>