var catalogGrid;
		
$(document).ready(function () {
    var toolbar;
    toolbar = [
        '-',
        {
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                window.location.href=domain+"/fee/toAdd.htm";//进入新增页面
            }
        }, 
        '-',
         {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function () {
                var row = $("#catalogGrid").datagrid("getSelected");
                var catalogId=row.listid;
                window.location.href=domain+"/fee/toUpdateCatalog.htm?catalogId="+catalogId;//进入修改页面
            }
        },
        '-',
         {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                var row = $("#catalogGrid").datagrid("getSelected");
                var catalogId=row.listid;
                var content={};
                content.type="post";
                content.url=domain+"/fee/deleteCatalog.htm";
                content.call=deleteRule;
                var data={};
                data.catalogId=catalogId;
                content.data=data;
                ajaxReq(content);
            }
        }
    ];

    catalogGrid = $('#catalogGrid').datagrid({
        url: domain+'/fee/queryCatalog.htm',
        striped: true,
        toolbar: toolbar,
        sortName: '',
        sortOrder: '',
        pagination: true,
        singleSelect:true,
        pageList: [10, 20, 30],
        columns: [
            [
                
                {field: 'listid', title: '分类编号', fitColumns: true,width:60},
                {field: 'feetype', title: '费用类型', fitColumns: true,width:115,formatter:function(value,row,index){
                	if(value==1){
                		return "销售服务费";
                	}else if(value==2){
                		return "客户维护务费";
                	}else if(value==3){
                		return "交易手续费";
                	}else if(value==4){
                		return "垫资利息费";
                	}else if(value==5){
                		return "监管费";
                	}else if(value==6){
                		return "转换费";
                	}else{
                		return "";
                	}
                }},
                {field: 'ufcatalog', title: '自定义分类', fitColumns: true,width:215},
                {field: 'nikename', title: '昵称', fitColumns: true,width:228},
                {field: 'descr', title: '描述', fitColumns: true,width:197},
                {field: 'note', title: '备注', fitColumns: true,width:206}
            ]
        ]
    });
});
function deleteRule(result){
	alert(result.msg);
	$('#catalogGrid').datagrid('reload');
}
function doSearch(){
	$('#catalogGrid').datagrid('load',{
		listid: $('.listid').val(),
		feetype: $('.feetype').val(),
		nikename: $('.nikename').val(),
	});
}