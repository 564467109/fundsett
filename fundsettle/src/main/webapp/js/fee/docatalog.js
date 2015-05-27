var condition={
			"checkFeeType":function(){
				var feeType=$("#feetype").val();
				if(feeType==""){
					$("#feetypeMsg").html("费用类型不能为空");
					return false;
				}
				$("#feetypeMsg").html("");
				return true;
			},
			"checkUfcatalog":function(){
				var ufcatalog=$("#ufcatalog").val();
				if(ufcatalog==""){
					$("#ufcatalogMsg").html("自定义分类不能为空");
					return false;
				}
				$("#ufcatalogMsg").html("");
				return true;
			},
			"checkNikename":function(){
				var nikename=$("#nikename").val();
				if(nikename==""){
					$("#nikenameMsg").html("分类昵称不能为空");
					return false;
				}
				$("#nikenameMsg").html("");
				return true;
			},
			"checkDescr":function(){
				var descr=$("#descr").val();
				if(descr==""){
					$("#descrMsg").html("分类描述不能为空");
					return false;
				}
				$("#descrMsg").html("");
				return true;
			},
			"checkNote":function(){
				var note=$("#note").val();
				if(note==""){
					$("#noteMsg").html("备注不能为空");
					return false;
				}
				$("#noteMsg").html("");
				return true;
			},
			"callAdd":function(result){
				alert(result.msg);
				if(result.success){
					window.location.href=domain+"/fee/toQueryCatalog.htm";
				}
			}
	};
$(function(){
	$("#feetype").change(function(){
		condition.checkFeeType();
	});
	$("#ufcatalog").blur(function(){
		condition.checkUfcatalog();
	});
	$("#nikename").blur(function(){
		condition.checkNikename();
	});
	$("#descr").blur(function(){
		condition.checkDescr();
	})
	$("#note").blur(function(){
		condition.checkNote();
	});
});