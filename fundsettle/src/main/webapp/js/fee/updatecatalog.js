$(function(){
	$("#updateCatalog").click(function(){
		if(condition.checkFeeType()&condition.checkUfcatalog()&condition.checkNikename()
				&condition.checkDescr()&condition.checkNote()){
			var content={};
			content.type="post";
			content.url=domain+"/fee/updateCatalog.htm";
			content.data=$("#updateForm").serialize();
			content.call=condition.callAdd;
			ajaxReq(content);
		}
	});
});