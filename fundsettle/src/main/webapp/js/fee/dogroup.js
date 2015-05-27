var condition={
			"checkCatalogid":function(){
				var catalogid=$("#catalogid").val();
				if(catalogid==""){
					$("#catalogidMsg").html("分类编号不能为空");
					return false;
				}
				$("#catalogidMsg").html("");
				return true;
			},
			"checkGroupname":function(){
				var groupname=$("#groupname").val();
				if(groupname==""){
					$("#groupnameMsg").html("分组名不能为空");
					return false;
				}
				$("#groupnameMsg").html("");
				return true;
			},
			"checkNikename":function(){
				var nikename=$("#nikename").val();
				if(nikename==""){
					$("#nikenameMsg").html("分组昵称不能为空");
					return false;
				}
				$("#nikenameMsg").html("");
				return true;
			},
			"checkDescr":function(){
				var descr=$("#descr").val();
				if(descr==""){
					$("#descrMsg").html("分组描述不能为空");
					return false;
				}
				$("#descrMsg").html("");
				return true;
			},
			"callAdd":function(result){
				alert(result.msg);
				if(result.success){
					window.location.href=domain+"/fee/toGroupIndex.htm";
				}
			}
	};
$(function(){
	$("#catalogid").change(function(){
		condition.checkCatalogid();
	});
	$("#groupname").blur(function(){
		condition.checkGroupname();
	});
	$("#nikename").blur(function(){
		condition.checkNikename();
	});
	$("#descr").blur(function(){
		condition.checkDescr();
	})
});