var zoneGrid;
function computeCall(result){
	if(result.success){
		var feeZone=result.content;
		var valtype=feeZone.valtype
		var rateval=feeZone.rateval;
		var msg="";
		if(valtype==0){
			var amount=$("#jee").text();
			var total=Number(amount)*rateval;
			msg+="按照定比例计算,比例:"+rateval+"  结果为:"+total;
			
		}else{
			msg+="按照定值计算,定值:"+rateval+"  结果为:"+rateval;
		}
		$("#jg").html(msg);
	}
}
$(document).ready(function () {
	$('#dd001').window('close');  // close a window  
	$("#compute").click(function(){
		var amount=$("#je").val();
		var reg=/^\d+(.?\d+)?$/;
		if(!reg.test(amount)){
			alert("金额不正确");
		}
		$("#jee").html(amount);
		var content={};
		var data={};
		data.amount=amount;
		data.groupId=$("#bh").text();
		content.data=data;
		content.url=domain+"/fee/compute.htm";
		content.call=computeCall;
		ajaxReq(content);
	});
    var toolbar;
    toolbar = [
		'-',
		{
		    text: '查看',
		    iconCls: 'icon-tip',
		    handler: function () {
		    	 var row = $("#zoneGrid").datagrid("getSelected");
	                var groupId=row.listid;
	                window.location.href=domain+"/fee/toSeeZone.htm?groupId="+groupId;//进入查看页面
		    }
		}, 
        '-',
         {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function () {
                var row = $("#zoneGrid").datagrid("getSelected");
                var groupId=row.listid;
                window.location.href=domain+"/fee/toUpdateZone.htm?groupId="+groupId;//进入修改页面
            }
        },
        '-',
         {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                var row = $("#zoneGrid").datagrid("getSelected");
                var groupId=row.listid;
                var content={};
                content.type="post";
                content.url=domain+"/fee/deleteZone.htm";
                content.call=deleteRule;
                var data={};
                data.groupId=groupId;
                content.data=data;
                ajaxReq(content);
            }
        },
        '-',
        {
           text: '计算金额',
           iconCls: 'icon-ok',
           handler: function () {
               var row = $("#zoneGrid").datagrid("getSelected");
               var groupId=row.listid;
               var nikename=row.nikename;
               $("#bh").text(groupId);
               $("#nc").text(nikename);
               $("#je").val("");
               $("#jg").text("");
               $('#dd001').dialog({    
            	    title: '计算',    
            	    width: 400,    
            	    height: 200,    
            	    closed: false,    
            	    cache: false,    
            	    modal: true   
               }); 
           }
       }
    ];

    zoneGrid = $('#zoneGrid').datagrid({
        url: domain+'/fee/queryZone.htm',
        striped: true,
        toolbar: toolbar,
        sortName: '',
        sortOrder: '',
        pagination: true,
        singleSelect:true,
        pageList: [10, 20, 30],
        columns: [
            [
                
                {field: 'listid', title: '分组编号', fitColumns: true,width:105},
                {field: 'nikename', title: '分组昵称', fitColumns: true,width:220},
                {field: 'groupname', title: '分组名', fitColumns: true,width:250},
                {field: 'descr', title: '分组描述', fitColumns: true,width:377}
            ]
        ]
    });
});
function deleteRule(result){
	alert(result.msg);
	$('#zoneGrid').datagrid('reload');
}
function doSearch(){
	$('#zoneGrid').datagrid('load',{
		listid: $('.listid').val(),
		groupname: $('.groupname').val(),
		nikename: $('.nikename').val(),
	});
}