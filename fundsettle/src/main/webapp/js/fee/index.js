var feeGrid;
		
$(document).ready(function () {
//    var toolbar;
//    toolbar = [
//        {
//            text: '查看',
//            iconCls: 'icon-tip',
//            handler: function () {
//                //这里的id是key
//                var row = $("#feeGrid").datagrid("getSelected");
//                var activeId=row.activeId;
//                var flag=0;
//                window.location.href=path+"/redPaper/doActive.htm?activeId="+activeId+"&flag="+flag;//进入查看页面
//            }
//        }, 
//        '-',
//        {
//            text: '新增',
//            iconCls: 'icon-add',
//            handler: function () {
//                window.location.href=path+"/redPaper/toAddRuleFirst.htm";//进入新增页面
//            }
//        }, 
//        '-',
//         {
//            text: '修改',
//            iconCls: 'icon-edit',
//            handler: function () {
//                var row = $("#feeGrid").datagrid("getSelected");
//                var activeId=row.activeId;
//                var flag=1;
//                window.location.href=path+"/redPaper/doActive.htm?activeId="+activeId+"&flag="+flag;//进入修改页面
//            }
//        },
//        '-',
//         {
//            text: '删除',
//            iconCls: 'icon-remove',
//            handler: function () {
//                var row = $("#feeGrid").datagrid("getSelected");
//                var activeId=row.activeId;
//                var activeKey=row.activeKey;
//                var content={};
//                content.type="post";
//                content.url=path+"/redPaper/deleteRule.htm";
//                content.call=deleteRule;
//                var data={};
//                data.activeId=activeId;
//                data.activeKey=activeKey;
//                content.data=data;
//                ajaxReq(content);
//            }
//        }
//    ];

    feeGrid = $('#feeGrid').datagrid({
        url: domain+'/fee/show.htm',
        striped: true,
//        toolbar: toolbar,
        sortName: '',
        sortOrder: '',
        pagination: true,
        singleSelect:true,
        pageList: [10, 20, 30],
        columns: [
            [
                {field:'catalogid',title:"分类编号", fitColumns: true,width:101},
                {field: 'listid', title: '分组编号', fitColumns: true,width:101},
                {field: 'groupname', title: '分组名', fitColumns: true,width:300},
                {field: 'descr', title: '分组描述', fitColumns: true,width:350}
            ]
        ]
    });
});
function deleteRule(result){
	alert(result.msg);
	$('#feeGrid').datagrid('reload');
}
function doSearch(){
	$('#feeGrid').datagrid('load',{
		listid: $('.listid').val(),
		feetype: $('.feetype').val(),
		nikename: $('.nikename').val(),
	});
}