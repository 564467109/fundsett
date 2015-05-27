<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../pages/common/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>策略分组列表</title>
</head>
	<body>
	<center>
		<div >
			  <div class="tabhead">
			        <h4>策略分组列表</h4>
			   费用类型:
			   <input id="feeType" class="easyui-combobox" name="feeType" data-options="valueField:'value',textField:'key',editable:false,url:'feetype.htm'" style="width:208px"/>
			   		分组昵称:
			   		<input id="bookname" class="easyui-validatebox" name="bookname" style="width:208px"/>
			   		<input value="筛 选" type="button" class="btnbg" style="margin:0px 0px 0px 15px;" onclick="javascript:queryData();"/>
			  </div>
			  <br/>
			  <table id="comment" bgcolor="#0000FF" cellpadding="1" cellspacing="1" border="0" width="100%">
			  <thead>
					<tr bgcolor="#FFFFFF" height="30px;">
						<th><b>编 码</b></th>
						<th><b>别 名</b></th>
						<th><b>显/隐</b></th>
						<th><b>创建日期</b></th>
						<th><b>操 作</b></th>
					</tr>
				</thead>
				<tbody id="listData">
					
				</tbody>
			  </table>
			  <div style="margin:15px;">
			  	<button id="add" onclick="javascript:openFeeBook();">新 增</button>
			  </div>
		</div>
		
	</center>
	<div id="dlg2" class="easyui-dialog" style="width: 950px; height: 450px; padding: 10px 10px;" closed="true" buttons="#dlg-buttons2">
<!--        <div class="ftitle"><b><font size="4">策略详细信息</font></b></div> -->
       <table id="detail" bgcolor="#0000FF" cellpadding="1" cellspacing="1" border="0" width="100%">
		  <thead>
			<tr bgcolor="#FFFFFF" height="30px;">
				<th><b>编 码</b></th>
				<th><b>受理渠道</b></th>
				<th><b>支付渠道</b></th>
				<th><b>支付网点</b></th>
				<th><b>业务类型</b></th>
				<th><b>计费基准</b></th>
				<th><b>计费策略</b></th>
				<th><b>固定值</b></th>
				<th><b>费用分段编号</b></th>
				<th><b>费用分段</b></th>
				<th><b>操作</b></th>
			</tr>
		</thead>
		<tbody id="listData1">
			
		</tbody>
	  </table>
   </div>
   <div id="dlg-buttons2">
        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="javascript:$('#dlg2').dialog('close');" iconcls="icon-cancel">关闭</a>
   </div>
   <jsp:include page="addFeeBookDetail1.jsp"></jsp:include>
   
   <div id="dlg3" class="easyui-dialog" style="width:820px; height: 260px; padding: 10px 20px;" closed="true" buttons="#dlg-buttons3">
      <input type="hidden" id="d_feetype"/>
      <div class="ftitle"><b><font size="4">详细策略信息</font></b></div>
      <br/>
      <div class="fitem">
      		<label>受理渠道：</label>
      		<select id="d_operway" class="easyui-combobox" name="d_operway" style="width:154px;" data-options="editable:false">
          		<option value="web" selected="selected">网上交易</option>
		    	<option value="OTC">柜台交易</option>
          	</select>
          	<label>&nbsp;&nbsp;支付渠道：</label>
          	<input id="d_paychannelid" class="easyui-combobox" name="d_paychannelid" data-options="valueField:'key',textField:'value',editable:false,url:'paychannel.htm',onSelect: function(rec){var url = 'paycenter.htm?channelId='+rec.key;$('#d_paycenterid').combobox('setValue', '');$('#d_paycenterid').combobox('reload', url);}"/>
          	<label>&nbsp;&nbsp;支付网点：</label>
          	<input id="d_paycenterid" class="easyui-combobox" style="width:158px;" name="d_paycenterid" data-options="valueField:'id',textField:'text',editable:false"/>
      </div>
      <br/>
      <div class="fitem">
      		<label>业务类型：</label>
          	<input id="d_businesscode" class="easyui-combobox" name="d_businesscode" data-options="valueField:'key',textField:'value',editable:false,url:'business.htm'" style="width:154px;"/>
          	<label>&nbsp;&nbsp;计费基准：</label>
          	<input id="d_calmode" class="easyui-combobox" name="d_calmode" data-options="valueField:'key',textField:'value',editable:false,url:'feedatum.htm'"/>
          	<label>&nbsp;&nbsp;计费策略：</label>
          	<input id="d_calpolicy" class="easyui-combobox" name="d_calpolicy" style="width:158px;" data-options="valueField:'key',textField:'value',editable:false,url:'feepolicy.htm',onSelect: function(rec){    
            var va = rec.key;
            if('3' == va){
	            $('#d_zoneid').combobox('enable');
            	$('#d_leftclose').combobox('enable');
            }else{
            	$('#d_zoneid').combobox('select','');
            	$('#d_leftclose').combobox('setValue','');
            	$('#d_zoneid').combobox('disable');
            	$('#d_leftclose').combobox('disable');
            }}"/>
      </div>
      <br/>
      <div class="fitem">
      		<label style="margin:0px 0px 0px 12px;">固定值：</label>
          	<input id="d_fixval" class="easyui-numberbox" name="d_fixval" data-options="min:0,precision:4" style="width:154px;"/>
          	<label>&nbsp;&nbsp;费用分组：</label>
          	<input id="d_zoneid" class="easyui-combobox" name="d_zoneid" data-options="valueField:'listid',textField:'groupname',editable:false,url:'zonegroup.htm'"/>
          	<label>&nbsp;&nbsp;费用分段：</label>
          	<select id="d_leftclose" class="easyui-combobox" name="d_leftclose" style="width:158px;" data-options="editable:false">
          		<option value="1" selected="selected">右封闭</option>
		    	<option value="0">左封闭</option>
          	</select>
      </div>
      </form>
     </div>
     <div id="dlg-buttons3">
   		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="javascript:saveDetails();" iconcls="icon-save">保存</a>
        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="javascript:$('#dlg3').dialog('close');" iconcls="icon-cancel">关闭</a>
   </div>
   <input type="hidden" id="iseditor" name="iseditor" value="0" />
   <input type="hidden" id="elistid" name="elistid" />
   <input type="hidden" id="d_bookid" name="d_bookid" value=""/>
   
   <div id="dlg4" class="easyui-dialog" style="width:400px; height: 130px; padding: 10px 20px;" closed="true" buttons="#dlg-buttons4">
      <div class="fitem">
      		<input type="hidden" id="copy_listId" name="copy_listId"/>
      		<input type="hidden" id="copy_feetype" name="copy_feetype"/>
      		<label>复制策略组名：</label>
      		<input id="copy_bookname" class="easyui-validatebox" name="copy_bookname" style="width:250px;"/>
      </div>
  </div>
  <div id="dlg-buttons4">
   		<a href="javascript:void(0);" class="easyui-linkbutton" onclick="javascript:saveCopyBookInfo();" iconcls="icon-save">保存</a>
        <a href="javascript:void(0);" class="easyui-linkbutton" onclick="javascript:$('#dlg4').dialog('close');" iconcls="icon-cancel">取消</a>
   </div>
</body>
<script type="text/javascript">

$(function(){
	commentList("");
});

function commentList(data){
	var str = "";
	$.ajax({
    	type : "post",
        url : "<%=path %>/feesuite/suitebook.htm",
        data:data,
        dataType : "json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(json){
			var fundInfo = json.data;
			$(fundInfo).each(function(index) {
				var val = fundInfo[index];
					str +="<tr bgcolor=\"#FFFFFF\" height=\"25px;\"><td>"+val.listid+"</td><td>"+val.bookname+"</td>"
						+"<td align=\"center\"><a href=\"javascript:modifyFeeBook('"+val.listid+"','"+val.feetype+"','"+val.bookname+"','"+val.status+"','"+val.show+"','"+val.note+"');\">";
					if(0 == val.show){
						str +="显";
					}else{
						str +="隐";
					}
					str +="</a></td><td>"+val.createdate+"</td><td><a href=\"javascript:queryDetail('"+val.listid+"','"+val.feetype+"');\">详情</a><a href=\"javascript:openDetails('"+val.listid+"','"+val.feetype+"');\" style=\"margin:10px;\">添加详细</a><a href=\"javascript:copyBookInfo('"+val.listid+"','"+val.feetype+"','"+val.bookname+"');\" style=\"margin:10px;\">复制该组</a></td></tr>";
			});
			$("#listData").html(str);
        },
        error:function(json){
        }
    });
}

function queryData(){
	var feeType = $("#feeType").combobox("getValue");
	var bookname = $("#bookname").val();
	var data = "feetype="+feeType+"&bookname="+bookname;
	commentList(data);
}

function queryDetail(bookid,feetype){
	$("#d_bookid").val(bookid);
	queryDetailInfo(feetype);
	$("#dlg2").dialog("open").dialog('setTitle', '策略详细信息');
}

function queryDetailInfo(feetype){
	$("#d_feetype").val(feetype);
	$("#listData1").html("");
	var str = "";
	$.ajax({
    	type : "post",
        url : "<%=path %>/feesuite/suitedetail.htm",
        data:"bookid="+$("#d_bookid").val(),
        dataType : "json",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success : function(json){
			var fundInfo = json.data;
			if(fundInfo){
				$(fundInfo).each(function(index) {
					var val = fundInfo[index];
						str +="<tr bgcolor=\"#FFFFFF\" height=\"25px;\"><td>"+val.listid+"</td><td>"+val.operway+"</td><td>"+val.paychannelid+"</td>"
		           			+"<td>"+val.paycenterid+"</td><td>"+val.businesscode+"</td><td>"+val.calmode+"</td><td>"+val.calpolicy+"</td><td>"+val.fixval+"</td><td>"+val.zoneid+"</td><td>"+val.leftclose+
		           			"</td><td><a href=\"javascript:openEditor('"+val.listid+"','"+val.operway+"','"+val.paychannelid+"','"+val.paycenterid+"','"+val.businesscode+"','"+val.calmode+"','"+val.calpolicy+"','"+val.fixval+"','"+val.zoneid+"','"+val.leftclose+"');\">编辑</a></td></tr>";
				});
				$("#listData1").html(str);
			}else{
				$("#listData1").html("<tr bgcolor=\"#FFFFFF\" align=\"center\"><td colspan=\"11\">没有查询到数据</td></tr>");
			}
        },
        error:function(json){
        }
    });
}

function modifyFeeBook(listid,feetype,bookname,status,show,note){
	if(0 == show){
		show = 1;
	}else{
		show = 0;
	}
	var data = "listid="+listid+"&feetype="+feetype+"&bookname="+bookname+"&status="+status+"&show="+show+"&note="+note;
	$.ajax({
    	type : "POST",
        url : "<%=path %>/feesuite/modifyfeebook.htm",
        data:data,
        dataType: "json",               //类型   
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",   
        success : function(json){
        	if("0000" == json.code){
        		$.messager.alert('提示','修改策略分组成功');
	    		commentList("feetype="+$("#feeType").combobox("getValue")+"&bookname="+$("#bookname").val());
        	}else{
        		alert(json.msg);
        	}
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        	$.messager.alert('失败',"error:失败,status:"+XMLHttpRequest.status);
        }
    });
}

function openFeeBook(){
	$("#dlg1").dialog("open").dialog('setTitle', '新增策略组信息');
}

function saveAllInfo(){
	var feeType = $("#b_feeType").combobox("getValue");
	var b_bookname = $("#b_bookname").val();
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
	
	if(!b_bookname){
		$.messager.alert('提示','策略分组名不能为空');
		return;
	}
	if(!feeType){
		$.messager.alert('提示','请选择费用类型');
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
	if("3" == calpolicy){
		if(!zoneid){
			$.messager.alert('提示','请选择费用分组');
			return;
		}
	}
	var data = "feetype="+feeType+"&partner=&accountcode=&validdate=&bookid=&b_bookname="+b_bookname+"&operway="+operway+"&paychannelid="+paychannelid+"&paycenterid="+paycenterid+"&businesscode="+businesscode+"&calmode="+calmode+"&calpolicy="+calpolicy+"&fixval="+fixval+"&zoneid="+zoneid+"&leftclose="+leftclose+"&statux="+statux+"&isshow="+isshow;
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
    		window.location.href="<%=path %>/feesuite/redirect.htm?page=suiteBookList";
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        	$.messager.alert('失败',"error:失败,status:"+XMLHttpRequest.status);
        }
    });
}

function openDetails(bookid,feetype){
	$("#iseditor").val("0");
	$("#d_bookid").val(bookid);
	$("#d_feetype").val(feetype);
	$("#d_operway").combobox('setValue',"");
	$("#d_paychannelid").combobox('select',"@");
	$("#d_paycenterid").combobox('select',"@");
	$("#d_calmode").combobox('setValue',"");
	$("#d_calpolicy").combobox('setValue',"");
	//$("#d_zoneid").combobox('select','');
	$("#d_leftclose").combobox('setValue',"");
	$("#d_fixval").val("");
	$("#dlg3").dialog("open").dialog('setTitle', '添加策略详细信息');
	if("6" == feetype){
    	$("#d_businesscode").combobox("disable");
    }else{
    	$("#d_businesscode").combobox("enable");
    }
}

function saveDetails(){
	var iseditor = $("#iseditor").val();
	var operway = $("#d_operway").combobox("getValue");
	var paychannelid = $("#d_paychannelid").combobox("getValue");
	var paycenterid = $("#d_paycenterid").combobox("getValue");
	var businesscode = $("#d_businesscode").combobox("getValue");
	var calmode = $("#d_calmode").combobox("getValue");
	var calpolicy = $("#d_calpolicy").combobox("getValue");
	var fixval = $("#d_fixval").val();
	var zoneid = $("#d_zoneid").combobox("getValue");
	var leftclose = $("#d_leftclose").combobox("getValue");
	
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
	if("6" != $("#d_feetype").val()){
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
	var data = "bookid="+$("#d_bookid").val()+"&operway="+operway+"&paychannelid="+paychannelid+"&paycenterid="+paycenterid+"&businesscode="+businesscode+"&calmode="+calmode+"&calpolicy="+calpolicy+"&fixval="+fixval+"&zoneid="+zoneid+"&leftclose="+leftclose+"&iseditor="+iseditor+"&listid="+$("#elistid").val();
	$.ajax({
    	type : "POST",
        url : "<%=path %>/feesuite/adddetail.htm",
        data:data,
        dataType: "json",               //类型   
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",   
        success : function(json){
        	$.messager.alert('提示','操作成功');
        	$('#dlg3').dialog('close');
        	if("1" == iseditor){
        		queryDetailInfo($("#d_feetype").val());
        	}
    		//window.location.href="<%=path %>/feesuite/redirect.htm?page=suiteBookList";
        },
        error:function(XMLHttpRequest, textStatus, errorThrown){
        	$.messager.alert('失败',"error:失败,status:"+XMLHttpRequest.status);
        }
    });
}

function openEditor(listid,operway,paychannelid,paycenterid,businesscode,calmode,calpolicy,fixval,zoneid,leftclose){
	$("#iseditor").val("1");
	$("#elistid").val(listid);
	$("#dlg3").dialog("open").dialog('setTitle', '编辑策略详细信息');
	if("6" == $("#d_feetype").val()){
		$("#d_businesscode").combobox("select","");
		$("#d_businesscode").combobox("disable");
    }else{
    	$("#d_businesscode").combobox("select",businesscode);
    	$("#d_businesscode").combobox("enable");
    }
	$("#d_operway").combobox('select',operway);
	$("#d_paychannelid").combobox('select',paychannelid);
	$("#d_paycenterid").combobox('select',paycenterid);
	$("#d_calmode").combobox('select',calmode);
	$("#d_calpolicy").combobox('select',calpolicy);
	$("#d_zoneid").combobox('select',zoneid);
	$("#d_leftclose").combobox('select',leftclose);
	$("#d_fixval").val(fixval);
}

function copyBookInfo(listid,feetype,bookname){
	$("#copy_listId").val(listid);
	$("#copy_feetype").val(feetype);
	$("#copy_bookname").val("复制组名："+bookname);
	$("#dlg4").dialog("open").dialog('setTitle', '复制策略组信息');
}

function saveCopyBookInfo(){
	var listid = $("#copy_listId").val();
	var feetype= $("#copy_feetype").val();
	var cbookname = $("#copy_bookname").val();
	$.messager.confirm('确认','您确认想要'+$("#copy_bookname").val()+'及组下的全部策略吗？',function(r){    
	    if (r){    
	    	$.ajax({
	        	type : "POST",
	            url : "<%=path %>/feesuite/copyfeebook.htm",
	            data:"listid="+listid+"&feetype="+feetype+"&bookname="+cbookname,
	            dataType: "json",               //类型   
	            contentType: "application/x-www-form-urlencoded; charset=UTF-8",   
	            success : function(json){
	            	$.messager.alert('提示','操作成功');
	            	$('#dlg3').dialog('close');
	            	commentList("");
	            },
	            error:function(XMLHttpRequest, textStatus, errorThrown){
	            	$.messager.alert('失败',"error:失败,status:"+XMLHttpRequest.status);
	            }
	        });
	    }    
	});
}
</script>
</html>