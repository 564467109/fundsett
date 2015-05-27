function ajaxReq(content){
	if(typeof(content.type)=="undefined"||content.type==null){
		content.type="post";
	}
	$.ajax({
		"type":content.type,
		"url":content.url,
		"data":content.data,
		"success":function(result){
			result=eval("("+result+")");
			content.call(result);
		},
		error:function(){
			alert("请求失败");
		}
	});
}
function removeRepeat(arr){
	var newArr=new Array();
	for(var i=0;i<arr.length;i++){//去重
		if(newArr.length==0){
			newArr.push(arr[i]);
			continue;
		}
		var flag=true;
		for(var k=0;k<newArr.length;k++){
			if(newArr[k]==arr[i]){
				flag=false;
				continue;
			}
		}
		if(flag){
			newArr.push(arr[i]);
		}
	}
	return newArr;
}
function sortNumber(a,b){
	return a-b;
}
