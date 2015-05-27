$(function(){
	$("#updateGroup").click(function(){
		if(condition.checkCatalogid()&condition.checkGroupname()&condition.checkNikename()
				&condition.checkDescr()){
			var content={};
			content.type="post";
			content.url=domain+"/fee/updateGroup.htm";
			content.data=$("#updateForm").serialize();
			content.call=condition.callAdd;
			ajaxReq(content);
		}
	});
});