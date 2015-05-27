<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String domain=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="common/head.jsp"></jsp:include>
<title></title>
</head>
<body>
	<center>
		<div class="easyui-layout" style="width:1340px;height:650px;">
			<div data-options="region:'west',split:true" title="菜单" style="width:200px;">
				<ul class="easyui-tree">
					<li>
						<span>费用分区设置</span>
						<ul>
							<li><a href="javascript:void(0);" onclick="openTab('分类列表','<%=domain %>/fee/toQueryCatalog.htm')">分区分类列表</a></li>
							<li><a href="javascript:void(0);" onclick="openTab('分组列表','<%=domain %>/fee/toGroupIndex.htm')">分区分组列表</a></li>
							<li><a href="javascript:void(0);" onclick="openTab('分组关联分区列表','<%=domain %>/fee/toQueryZone.htm')">分组关联分区列表</a></li>
							<li><a href="javascript:void(0);" onclick="openTab('分类关联分组列表','<%=domain %>/fee/index.htm')">分类关联分组列表</a></li>
						</ul>
					</li>
				</ul>
				<ul class="easyui-tree">
					<li>
						<span>费用策略设置</span>
						<ul>
							<li><a href="javascript:void(0);" onclick="openTab('策略信息列表','<%=domain %>/feesuite/redirect.htm?page=feesuiteInfoList')">策略信息列表</a></li>
							<li><a href="javascript:void(0);" onclick="openTab('策略组列表','<%=domain %>/feesuite/redirect.htm?page=suiteBookList')">策略组列表</a></li>
						</ul>
					</li>
				</ul>
			</div>
			<div id="tt" class="easyui-tabs" data-options="region:'center',title:'',tools:'#tab-tools'">
			</div>
		</div>
	</center>
	<script type="text/javascript">
		function openTab(title,url){
			if($("#tt").tabs("exists",title)){
				$("#tt").tabs("select",title);
				var current_tab = $('#tt').tabs('getSelected');
				$('#tt').tabs('update',{
					tab:current_tab,
					options : {
					content : '<iframe src="'+url+'" style="width:1100px;height:610px;"></iframe>'
					}
				});
		    }else{
				$('#tt').tabs('add',{
					title: title,
					content: '<iframe src="'+url+'" style="width:1100px;height:610px;"></iframe>',
					closable: true,
					tools:[{    
				        iconCls:'icon-mini-refresh',    
				        handler:function(){    
				        	var current_tab = $('#tt').tabs('getSelected');
							$('#tt').tabs('update',{
								tab:current_tab,
								options : {
								content : '<iframe src="'+url+'" style="width:1100px;height:610px;"></iframe>'
								}
							});
				        }    
				    }]
				});
			}
		}
	</script>
</body>
</html>