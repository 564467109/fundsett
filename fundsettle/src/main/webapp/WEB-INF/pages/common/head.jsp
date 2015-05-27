<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String domain=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=path %>/css/sys.css"  />
<link rel="stylesheet" type="text/css" href="<%=path %>/js/plugins/webUI/jquery-easyui-1.3.5/themes/default/easyui.css"/>
<link rel="stylesheet" type="text/css" href="<%=path %>/js/plugins/webUI/jquery-easyui-1.3.5/themes/icon.css"/>
<script type="text/javascript" src="<%=path %>/js/jquery-1.7.2.js"></script>
<script type="text/javascript" src="<%=path %>/js/plugins/webUI/jquery-easyui-1.3.5/jquery.easyui.js"></script>
<script type="text/javascript" src="<%=path %>/js/plugins/webUI/jquery-easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
<script>
	var domain='<%=domain %>';
</script>
