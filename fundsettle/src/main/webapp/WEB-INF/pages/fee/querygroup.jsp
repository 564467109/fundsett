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
<script type="text/javascript" src="<%=path%>/js/fee/querygroup.js"></script>
<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
<title>分组列表</title>
</head>
<body>
	<table>
  		<tbody>
  		<tr>
  		<td>分组编号：</td>
  		<td><input type="text" class="listid"/></td>
  		<td>分组名：</td>
  		<td><input type="text" class="groupname"/></td>
  		<td>分组昵称：</td>
  		<td><input type="text" class="nikename"/></td>
  		<td><input type="button" value="查询" class="btnSearch" onclick="doSearch()"></td>
  		</tr>
  		</tbody>
  	</table>
	<table id="groupGrid" fit="false"></table>
</body>
</html>