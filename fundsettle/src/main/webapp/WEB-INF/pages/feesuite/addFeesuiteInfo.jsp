<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../pages/common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加费用策略信息</title>
</head>
<body>
	<br>
	<center>
		<p>添加费用策略信息</p>
		<table bgcolor="#0000FF" border="0" cellpadding="1" cellspacing="1" width="60%">
			<tr bgcolor="#FFFFFF" height="30px;">
				<td>费用类型</td>
				<td><input id="feeType" class="easyui-combobox" name="feeType" data-options="valueField:'value',textField:'key',editable:false,url:'feetype.htm',onSelect: function(rec){var url = 'feebook.htm?feetype='+rec.value;$('#bookid').combobox('setValue', '');$('#bookid').combobox('reload', url);}" style="width:160px"/></td>
				<td colspan="2"></td>
<!-- 				<td> -->
<!-- 				<input id="strategyId" name="strategyId" style="width:160px"> -->
<!-- 				</td> -->
			</tr>
			<tr bgcolor="#FFFFFF" height="30px;">
				<td>商   户</td>
				<td><input id="partner" name="partner" class="easyui-combobox" data-options="editable:false,valueField:'key',textField:'value',url:'partner.htm'" style="width:160px" /></td>
				<td>处理对象</td>
				<td><select id="accountcode" name="accountcode" class="easyui-combobox" data-options="editable:false,valueField:'value',textField:'key',url:'accountcode.htm'" style="width:160px" /></td>
			</tr>
			<tr bgcolor="#FFFFFF" height="30px;">
				<td>生效日期</td>
				<td><input id="validdate" type="text" data-options="editable:false" class="easyui-datetimebox" style="width:160px"></input></td>
				<td>选择策略分组</td>
				<td><select id="bookid" name="bookid" class="easyui-combobox" data-options="editable:false,valueField:'listid',textField:'bookname'" style="width:160px"></select><a href="javascript:void(0);" onclick="javascript:openwin();">新增分组</a></td>
			</tr>
			<tr bgcolor="#FFFFFF" height="30px;">
				<td align="center" colspan="4"><button onclick="javascript:saveFeeSuite();">保存</button></td>
			</tr>
		</table>
	</center>
	<jsp:include page="addFeeBookDetail.jsp"></jsp:include>
</body>
<script type="text/javascript">
$(function(){
	
});
function openwin(){
	var feeType = $("#feeType").combobox("getValue");
	var partner = $("#partner").combobox("getValue");
	var accountcode = $("#accountcode").combobox("getValue");
	var validdate = $("#validdate").datebox("getValue");
	if(!feeType){
		$.messager.alert('提示','请选择费用类型');
		return;
	}
	if(!partner){
		$.messager.alert('提示','请选择商户');
		return;
	}
	if(!accountcode){
		$.messager.alert('提示','请选择处理对象');
		return;
	}
	if(!validdate){
		$.messager.alert('提示','请选择生效时间');
		return;
	}
    $("#dlg1").dialog("open").dialog('setTitle', '新增策略分组信息');
    //$("#b_feeType").val($("#feeType").combobox("getValue"));
    $("#b_feeType").combobox('loadData',$("#feeType").combobox("getData"));
    $("#b_feeType").combobox('select',$("#feeType").combobox("getValue"));
    if('6' == feeType){
    	$("#businesscode").combobox("disable");
    	$("#d_businesscode").combobox("disable");
    }else{
    	$("#businesscode").combobox("enable");
    	$("#d_businesscode").combobox("enable");
    }
    //alert($("#feeType").combobox("getData"));
    //$("#b_createdate").val($("#validdate").datebox("getValue"));
	  
}

function saveFeeSuite(){
	var feeType = $("#feeType").combobox("getValue");
// 	var strategyId = $("strategyId").val();
	var partner = $("#partner").combobox("getValue");
	var accountcode = $("#accountcode").combobox("getValue");
	var validdate = $("#validdate").datebox("getValue");
	var bookid = $("#bookid").combobox("getValue");
	if(!feeType){
		$.messager.alert('提示','请选择费用类型');
		return;
	}
	/* if(!strategyId){
		alert("策略编号不能为空");
		return;
	} */
	if(!partner){
		$.messager.alert('提示','请选择商户');
		return;
	}
	if(!accountcode){
		$.messager.alert('提示','请选择处理对象');
		return;
	}
	if(!validdate){
		$.messager.alert('提示','请选择生效时间');
		return;
	}
	if(!bookid){
		$.messager.alert('提示','请选择策略分组');
		return;
	}
	var data = "feetype="+feeType+"&partner="+partner+"&accountcode="+accountcode+"&validdate="+validdate+"&bookid="+bookid+"&flag=1";
	submitData(data);
}

function saveAllInfo(){
	var feeType = $("#feeType").combobox("getValue");
// 	var strategyId = $("strategyId").val();
	var partner = $("#partner").combobox("getValue");
	var accountcode = $("#accountcode").combobox("getValue");
	var validdate = $("#validdate").datebox("getValue");
	var bookid = $("#bookid").combobox("getValue");
	if(bookid){
		$("#bookid").combobox("setValue","");
	}
	var b_bookname = $("#b_bookname").val();
	//var b_createdate = $("#b_createdate").datebox("getValue");
	var operway = $("#operway").combobox("getValue");
	var paychannelid = $("#paychannelid").combobox("getValue");
	var paycenterid = $("#paycenterid").combobox("getValue");
	var businesscode = $("#businesscode").combobox("getValue");
	var calmode = $("#calmode").combobox("getValue");
	var calpolicy = $("#calpolicy").combobox("getValue");
	var fixval = $("#fixval").val();
	var zoneid = $("#zoneid").combobox("getValue");
	var leftclose = $("#leftclose").combobox("getValue");
	var statux = $("#statux").combobox("getValue");
	var isshow = $("#isshow").combobox("getValue");
	if(!feeType){
		$.messager.alert('提示','请选择费用类型');
		return;
	}
	/* if(!b_createdate){
		$.messager.alert('提示','请选择创建时间');
		return;
	} */
	if(!partner){
		$.messager.alert('提示','请选择商户');
		return;
	}
	if(!accountcode){
		$.messager.alert('提示','请选择处理对象');
		return;
	}
	if(!validdate){
		$.messager.alert('提示','请选择生效时间');
		return;
	}
	/* if(!bookid){
		alert("请选择策略分组");
		return;
	} */
	if(!b_bookname){
		$.messager.alert('提示','策略分组名不能为空');
		return;
	}
	if(!operway){
		$.messager.alert('提示','请选择受理渠道');
		return;
	}
	if(!paychannelid){
		$.messager.alert('提示','请选择支付渠道');
		return;
	}
	if(!paycenterid){
		$.messager.alert('提示','请选择支付网点');
		return;
	}
	if("6" != feeType){
		if(!businesscode){
			$.messager.alert('提示','请选择业务类型');
			return;
		}
	}
	if(!calmode){
		$.messager.alert('提示','请选择计费基准');
		return;
	}
	if(!calpolicy){
		$.messager.alert('提示','请选择计费策略');
		return;
	}
	if(!fixval){
		$.messager.alert('提示','固定值有误');
		return;
	}
	if('3' == calpolicy){
		if(!zoneid){
			$.messager.alert('提示','请选择费用分组');
			return;
		}
		if(!leftclose){
			$.messager.alert('提示','请选择费用分段');
			return;
		}
	}
	var data = "feetype="+feeType+"&partner="+partner+"&accountcode="+accountcode+"&validdate="+validdate+"&bookid="+bookid+"&b_bookname="+b_bookname+"&operway="+operway+"&paychannelid="+paychannelid+"&paycenterid="+paycenterid+"&businesscode="+businesscode+"&calmode="+calmode+"&calpolicy="+calpolicy+"&fixval="+fixval+"&zoneid="+zoneid+"&leftclose="+leftclose+"&statux="+statux+"&isshow="+isshow+"&flag=1";
	submitData(data);
}

function submitData(data){
	$.ajax({
    	type : "POST",
        url : "<%=path %>/feesuite/add.htm",
        data:data,
        dataType: "json",               //类型   
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",   
        success : function(json){
        	$.messager.alert('提示','添加成功');
    		window.location.href="<%=path %>/feesuite/redirect.htm?page=feesuiteInfoList";
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        	$.messager.alert('失败',"error:失败,status:"+XMLHttpRequest.status);
        }
    });
}
</script>
</html>