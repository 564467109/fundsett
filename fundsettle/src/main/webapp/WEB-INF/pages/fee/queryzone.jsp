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
<script type="text/javascript" src="<%=path%>/js/fee/queryzone.js"></script>
<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
<title>分组关联分区列表</title>
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
  	<div id="dd001" class="easyui-window">
  		<table border="1" style="width:350px;height:150px;">
  			<tbody>
  			<tr>
  				<td style="width:120px;">分组编号</td>
  				<td id="bh"></td>
  			</tr>
  			<tr>
  				<td>分组昵称</td>
  				<td id="nc"></td>
  			</tr>
  			<tr>
  				<td>金额</td>
  				<th><input id="je" type="text" style="width:150px;height:25px;"/><span id="jee" style="display:none"></span></th>
  			</tr>
  			<tr>
  				<td>结果</td>
  				<td id="jg"></td>
  			</tr>
  			<tr>
  				<th colspan="2"><center><input id="compute" style="width:50px;height:25px;" value="计算" type="button"/></center></th>
  			</tr>
  			</tbody>
  		</table>
  	</div>  
	<table id="zoneGrid" fit="false"></table>
</body>
</html>