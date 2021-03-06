<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class="ftitle"><b><font size="4">新增分组信息</font></b></div>
      <br/>
    <div class="fitem">
	   <label>分组名称：</label>
	   <input id="b_bookname" name="b_bookname" class="easyui-validatebox" />
	   <!-- <label>&nbsp;&nbsp;费用类型：</label>
	   <input id="b_feeType" class="easyui-combobox" name="b_feeType" data-options="valueField:'value',textField:'key',editable:false,url:'feetype.htm'"/>
	   <br/><br/> -->
	   <label style="margin:0px 0px 0px 10px;">是否有效：</label>
	   <select id="bstatux" class="easyui-combobox" name="bstatux" style="width:150px;" data-options="editable:false">
	  		<option value="0" selected="selected">是</option>
			<option value="1">否</option>
	  	</select>
	  	<label>&nbsp;&nbsp;是否显示：</label>
	  	<select id="isshow" class="easyui-combobox" name="isshow" style="width:150px;" data-options="editable:false">
	   		<option value="0" selected="selected">是</option>
			<option value="1">否</option>
	   	</select>
	</div>
    <br/>
    <div class="ftitle"><b><font size="4">详细策略信息</font></b></div>
    <br/>
    <div class="fitem">
   		<label>受理渠道：</label>
   		<select id="operway" class="easyui-combobox" name="operway" style="width:154px;" data-options="editable:false">
      		<option value="web" selected="selected">网上交易</option>
  			<option value="OTC">柜台交易</option>
       	</select>
       	<label>&nbsp;&nbsp;支付渠道：</label>
       	<input id="paychannelid" class="easyui-combobox" name="paychannelid" data-options="valueField:'key',textField:'value',editable:false,url:'paychannel.htm',onSelect: function(rec){var url = 'paycenter.htm?channelId='+rec.key;$('#paycenterid').combobox('setValue', '');$('#paycenterid').combobox('reload', url);}"/>
       	<label>&nbsp;&nbsp;支付网点：</label>
       	<input id="paycenterid" class="easyui-combobox" name="paycenterid" data-options="valueField:'id',textField:'text',editable:false"/>
    </div>
    <br/>
    <div class="fitem">
   		<label>业务类型：</label>
       	<input id="businesscode" class="easyui-combobox" name="businesscode" data-options="valueField:'key',textField:'value',editable:false,url:'business.htm'"/>
       	<label>&nbsp;&nbsp;计费基准：</label>
       	<input id="calmode" class="easyui-combobox" name="calmode" data-options="valueField:'key',textField:'value',editable:false,url:'feedatum.htm'"/>
       	<label>&nbsp;&nbsp;计费策略：</label>
       	<input id="calpolicy" class="easyui-combobox" name="calpolicy" data-options="valueField:'key',textField:'value',editable:false,url:'feepolicy.htm',onSelect: function(rec){    
            var va = rec.key;
            if('3' == va){
	            $('#zoneid').combobox('enable');
            	$('#leftclose').combobox('enable');
            }else{
            	$('#zoneid').combobox('select','');
            	$('#leftclose').combobox('setValue','');
            	$('#zoneid').combobox('disable');
            	$('#leftclose').combobox('disable');
            }}"/>
    </div>
    <br/>
    <div class="fitem">
   		<label style="margin:0px 0px 0px 12px;">固定值：</label>
       	<input id="fixval" class="easyui-numberbox" name="fixval" data-options="min:0,precision:4"/>
       	<label>&nbsp;&nbsp;费用分组：</label>
       	<input id="zoneid" class="easyui-combobox" name="zoneid" data-options="valueField:'listid',textField:'groupname',editable:false,url:'zonegroup.htm'"/>
       	<label>&nbsp;&nbsp;费用分段：</label>
       	<select id="leftclose" class="easyui-combobox" name="leftclose" style="width:154px;" data-options="editable:false">
       		<option value="1" selected="selected">右封闭</option>
   			<option value="0">左封闭</option>
       	</select>
    </div>
