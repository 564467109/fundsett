$(function(){
	$("#addCatalog").click(function(){
		if(condition.checkFeeType()&condition.checkUfcatalog()&condition.checkNikename()
				&condition.checkDescr()&condition.checkNote()){
			var content={};
			content.type="post";
			content.url=domain+"/fee/addCatalog.htm";
			content.data=$("#addForm").serialize();
			content.call=condition.callAdd;
			ajaxReq(content);
		}
	});
});