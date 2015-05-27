$(function(){
	$("#addGroup").click(function(){
		if(condition.checkCatalogid()&condition.checkGroupname()&condition.checkNikename()
				&condition.checkDescr()){
			var content={};
			content.type="post";
			content.url=domain+"/fee/addGroup.htm";
			content.data=$("#addForm").serialize();
			content.call=condition.callAdd;
			ajaxReq(content);
		}
	});
});