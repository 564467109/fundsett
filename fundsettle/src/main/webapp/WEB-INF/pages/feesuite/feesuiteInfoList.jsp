<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../pages/common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>费用策略列表</title>
</head>
	<body>
	<center>
		<div >
			  <div class="tabhead">
			        <h4>费用策略列表</h4>
			   费用类型:
			   <input id="feeType" class="easyui-combobox" name="feeType" data-options="valueField:'value',textField:'key',editable:false,url:'feetype.htm'" style="width:108px"/>
			   
			    商户：	
			   		<input id="partner" name="partner" class="easyui-combobox" data-options="editable:false,valueField:'key',textField:'value',url:'partner.htm'" style="width:108px" />
			   生效日期：
			   		<input id="validdate" type="text" data-options="editable:false" class="easyui-datetimebox" ></input>
			   		
			   		处理对象:
			   		<select id="accountcode" name="accountcode" class="easyui-combobox" data-options="editable:false,valueField:'value',textField:'key',url:'accountcode.htm'" style="width:100px" />
			   		<input value="筛 选" type="button" class="btnbg" style="margin:0px 0px 0px 15px;" onclick="javascript:queryData();"/>
			  </div>
			  <br/>
			  <table id="comment" bgcolor="#0000FF" cellpadding="1" cellspacing="1" border="0" width="900px">
			  <thead>
					<tr bgcolor="#FFFFFF" height="30px;">
						<th><b>编 码</b></th>
						<th><b>对 象</b></th>
						<th><b>别 名</b></th>
						<th><b>费用类型</b></th>
						<th><b>商 户</b></th>
						<th><b>生效日期</b></th>
						<th><b>操 作</b></th>
					</tr>
				</thead>
				<tbody id="listData">
					
				</tbody>
			  </table>
		</div>
		<div style="margin:15px;">
	  		<a href="<%=path %>/feesuite/redirect.htm?page=addFeesuiteInfo">新建策略</a>
	  	</div>
	  	
	  	

	<div id="dlg1" class="easyui-dialog" style="width: 800px; height: 410px; padding: 10px 20px;" closed="true" buttons="#dlg-buttons1">
      <form id="fm1" method="post">
      <jsp:include page="addfeesuite.jsp"></jsp:include>
      </form>
    </div>
	<div id="dlg-buttons1">
       <a href="javascript:void(0);" class="easyui-linkbutton" onclick="javascript:saveAllInfo();" iconcls="icon-save">保存</a>
       <a href="javascript:void(0);" class="easyui-linkbutton" onclick="javascript:$('#dlg1').dialog('close');" iconcls="icon-cancel">取消</a>
    </div>
	  	
	</center>
	</body>
<script type="text/javascript">
var mark = "";
$(function(){
	//Conditions();
	commentList("");
});
function Conditions(){
	$.ajax({
    	type : "POST",
        url : "<%=path %>/feesuite/condition.htm",
        data:"",
        dataType: "json",               //类型   
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",   
        success : function(json){
    		$("feetype option").each(function() {
                $(this).remove();   //移除原有项   
            });
    		alert(json);
			//$(json).appendTo($("feetype"));        //将返回来的项添加到下拉菜单中  
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
	    	showMsg("error:失败,status:"+XMLHttpRequest.status);
        }
    });
}


function commentList(data){
	var str = "";
	$.ajax({
    	type : "post",
        url : "<%=path %>/feesuite/query.htm",
        data:data,
        dataType : "json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(json){
			var fundInfo = json.data;
			$(fundInfo).each(function(index) {
				var val = fundInfo[index];
					str +="<tr bgcolor=\"#FFFFFF\"><td>"+val.listid+"</td><td>"+val.accountcode+"</td><td></td><td>"+val.feetypeN+"</td>"
	           			+"<td>"+val.partner+"</td><td>"+val.validdate+"</td>"
	           			+"<td><a href=\"javascript:modifyFeeSuite('"+val.listid+"','"+val.accountcode+"','"+val.feetype+"','"+val.partner+"','"+val.validdate+"','"+val.bookid+"','"+val.statux+"');\" style=\"margin:0px 0px 0px 10px;\">修改</a><a href=\"javascript:deleteSuiteInfo('"+val.listid+"');\" style=\"margin:0px 0px 0px 10px;\">删除</a></td></tr>";
			});
			$("#listData").html(str);
        },
        error:function(json){
        }
    });
}

function queryData(){
	var feeType = $("#feeType").combobox("getValue");
	var partner = $("#partner").combobox("getValue");
	var accountcode = $("#accountcode").combobox("getValue");
	var validdate = $("#validdate").datebox("getValue");
	var data = "feetype="+feeType+"&partner="+partner+"&accountcode="+accountcode+"&validdate="+validdate;
	commentList(data);
}

function modifyFeeSuite(listid,accountcode,feetype,partner,validdate,bookid,statux){
	$('#addbook1').hide();
	$('#addbook').show();
	$('#suitebook').hide();
	$('#addbook1').hide();
	$("#dlg1").dialog("open").dialog('setTitle', '编辑策略信息');
	/* $('#b_feeType').combobox({
	    url:'feetype.htm',
	    valueField:'value',
	    textField:'key',
	    disabled:false
	});
	$('#b_feeType').combobox('select',feetype);
	$('#statux').combobox("enable");
	$('#isshow').combobox("enable"); */
	//$('#addbook').hide();
	$('#strategyId').val(listid);
	$('#strategyId').attr('disabled','disabled');
	$('#feeType1').combobox('select',feetype);
	if("6" == feetype){
		$("#businesscode").combobox("disable");
	}else{
		$("#businesscode").combobox("enable");
	}
	$('#accountcode1').combobox('select',accountcode);
	$('#accountcode1').combobox("disable");
	$('#validdate1').datebox("setValue",validdate);
	$('#bookid').combobox("select",bookid);
	$('#bookid').combobox("disable");
	$('#statux').combobox("select",statux);
}

function saveAllInfo(){
	var data = "feetype="+$('#feeType1').combobox('getValue')+"&listid="+$('#strategyId').val()+"&partner="+$('#partner1').combobox('getValue')+"&accountcode="+$('#accountcode1').combobox('getValue')+"&validdate="+$('#validdate1').datebox("getValue")+"&bookid="+$('#bookid').combobox("getValue")+"&statux="+$('#statux').combobox("getValue");
	if("0" == mark){
		var b_bookname = $("#b_bookname").val();
		var bstatux = $("#bstatux").combobox("getValue");
		var isshow = $("#isshow").combobox("getValue");
		var operway = $("#operway").combobox("getValue");
		var paychannelid = $("#paychannelid").combobox("getValue");
		var paycenterid = $("#paycenterid").combobox("getValue");
		var businesscode = $("#businesscode").combobox("getValue");
		var calmode = $("#calmode").combobox("getValue");
		var calpolicy = $("#calpolicy").combobox("getValue");
		var fixval = $("#fixval").val();
		var zoneid = $("#zoneid").combobox("getValue");
		var leftclose = $("#leftclose").combobox("getValue");
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
		if("6" != $('#feeType1').combobox('getValue')){
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
		}
		data += "&b_bookname="+b_bookname+"&operway="+operway+"&paychannelid="+paychannelid+"&paycenterid="+paycenterid+"&businesscode="+businesscode+"&calmode="+calmode+"&calpolicy="+calpolicy+"&fixval="+fixval+"&zoneid="+zoneid+"&leftclose="+leftclose+"&bstatux="+bstatux+"&isshow="+isshow+"&flag=1";
	}
	$.ajax({
    	type : "post",
        url : "<%=path %>/feesuite/modifyfeesuite.htm",
        data:data,
        dataType : "json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(json){
        	if(json.code && '0000' == json.code){
	        	$.messager.alert('提示','修改策略信息成功');
	        	$('#dlg1').dialog('close');
	        	commentList("");
        	}else{
        		$.messager.alert('提示',json.msg);
        	}
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        	$.messager.alert('失败',"error:失败,status:"+XMLHttpRequest.status);
        }
    });
}

function deleteSuiteInfo(listid){
	$.messager.confirm('确认','您确认想要删除记录吗？',function(r){    
	    if (r){    
	    	$.ajax({
	        	type : "post",
	            url : "<%=path %>/feesuite/delfeesuite.htm",
	            data:"listid="+listid,
	            dataType : "json",
	            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
	            success : function(json){
	            	if(json.code && '0000' == json.code){
	    	        	$.messager.alert('提示','删除策略信息成功');
	    	        	commentList("");
	            	}else{
	            		$.messager.alert('提示',json.msg);
	            	}
	            },
	            error:function(XMLHttpRequest, textStatus, errorThrown){
	            	$.messager.alert('失败',"error:失败,status:"+XMLHttpRequest.status);
	            }
	        }); 
	    }    
	}); 
}

function openwin(flag){
	mark = flag;
	if('0' == flag){
		$('#suitebook').show();
		$('#addbook1').show();
		$('#addbook').hide();
	}else{
		$('#suitebook').hide();
		$('#addbook1').hide();
		$('#addbook').show();
	}
}
</script>
</html>