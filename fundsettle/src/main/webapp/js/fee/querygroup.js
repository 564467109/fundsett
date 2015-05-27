var groupGrid;
		
$(document).ready(function () {
    var toolbar;
    toolbar = [
        '-',
        {
            text: '新增分组',
            iconCls: 'icon-add',
            handler: function () {
                window.location.href=domain+"/fee/toGroup.htm";//进入新增页面
            }
        }, 
        '-',
        {
            text: '新增分区',
            iconCls: 'icon-add',
            handler: function () {
            	var row = $("#groupGrid").datagrid("getSelected");
            	var groupId=row.listid;
            	var content={};
            	var data={};
                data.groupId=groupId;
            	content.url=domain+"/fee/isZone.htm";
            	content.call=addZone;
            	content.data=data;
            	ajaxReq(content);
            }
        }, 
        '-',
         {
            text: '修改分组',
            iconCls: 'icon-edit',
            handler: function () {
                var row = $("#groupGrid").datagrid("getSelected");
                var groupId=row.listid;
                window.location.href=domain+"/fee/toUpdateGroup.htm?groupId="+groupId;//进入修改页面
            }
        },
        '-',
         {
            text: '删除分组',
            iconCls: 'icon-remove',
            handler: function () {
                var row = $("#groupGrid").datagrid("getSelected");
                var groupId=row.listid;
                var content={};
                content.type="post";
                content.url=domain+"/fee/deleteGroup.htm";
                content.call=deleteRule;
                var data={};
                data.groupId=groupId;
                content.data=data;
                ajaxReq(content);
            }
        }
    ];

    groupGrid = $('#groupGrid').datagrid({
        url: domain+'/fee/showGroupData.htm',
        striped: true,
        toolbar: toolbar,
        sortName: '',
        sortOrder: '',
        pagination: true,
        singleSelect:true,
        pageList: [10, 20, 30],
        columns: [
            [
                
                {field: 'listid', title: '分组编号', fitColumns: true,width:77},
                {field: 'nikename', title: '分组昵称', fitColumns: true,width:196},
                {field: 'groupname', title: '分组名', fitColumns: true,width:191},
                {field: 'descr', title: '分组描述', fitColumns: true,width:300},
                {field: 'note', title: '引用情况', fitColumns: true,width:200}
            ]
        ]
    });
});
function addZone(result){
	if(result.success){
		window.location.href=domain+"/fee/toZone.htm?groupId="+result.content;
	}else{
		alert(result.msg);
	}
}
function deleteRule(result){
	alert(result.msg);
	$('#groupGrid').datagrid('reload');
}
function doSearch(){
	$('#groupGrid').datagrid('load',{
		listid: $('.listid').val(),
		groupname: $('.groupname').val(),
		nikename: $('.nikename').val(),
	});
}