<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<table bgcolor="#0000FF" border="0" cellpadding="1" cellspacing="1" width="100%" style="margin:0px;padding:0px;">
	<tr bgcolor="#FFFFFF" height="30px;">
		<td>费用类型</td>
		<td><input id="feeType1" class="easyui-combobox" name="feeType1" data-options="valueField:'value',textField:'key',editable:false,url:'feetype.htm'" style="width:160px"/></td>
		<td>策略编号</td>
		<td><input id="strategyId" name="strategyId" style="width:160px"></td>
	</tr>
	<tr bgcolor="#FFFFFF" height="30px;">
		<td>商   户</td>
		<td><input id="partner1" name="partner1" class="easyui-combobox" data-options="editable:false,valueField:'key',textField:'value',url:'partner.htm'" style="width:160px" /></td>
		<td>处理对象</td>
		<td><select id="accountcode1" name="accountcode1" class="easyui-combobox" data-options="editable:false,valueField:'value',textField:'key',url:'accountcode.htm'" style="width:160px" /></td>
	</tr>
	<tr bgcolor="#FFFFFF" height="30px;">
		<td>生效日期</td>
		<td><input id="validdate1" type="text" data-options="editable:false" class="easyui-datetimebox" style="width:160px"></input></td>
		<td>选择策略分组</td>
		<td><select id="bookid" name="bookid" class="easyui-combobox" data-options="editable:false,valueField:'listid',textField:'bookname',url:'feebook.htm'" style="width:160px;"></select><a id="addbook" href="javascript:void(0);" onclick="javascript:openwin('0');" style="margin:5px;">新建分组</a><a id="addbook1" href="javascript:void(0);" onclick="javascript:openwin('1');" style="margin:5px;">取 消</a></td>
	</tr>
	<tr bgcolor="#FFFFFF" height="30px;">
		<td>状态</td>
		<td colspan="3">
			<select id="statux" class="easyui-combobox" name="statux" style="width:160px;" data-options="editable:false">
				<option value="0" selected="selected">正常</option>
				<option value="1">非正常</option>
			</select>
		</td>
	</tr>
</table>
<div id="suitebook">
    <jsp:include page="bookDetail.jsp"></jsp:include>
</div>