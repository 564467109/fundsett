$(function(){
	$("#produceZone").click(function(){
		condition.produceLevel();
	});
	$("#addZone").click(function(){
		if(condition.checkCatalog()&condition.checkGroupId()
				&condition.checkLevel()&condition.checkRatio()){
			var beginObj=document.getElementsByName("begin");
			var endObj=document.getElementsByName("end");
			var levelTypeObj=document.getElementsByName("levelType");
			var ratioObj=document.getElementsByName("ratio");
			var zoneId=$("#groupId").text();
			var levels=new Array();
			for(var i=0;i<ratioObj.length;i++){
				var level={};
				level.zoneid=zoneId;
				level.downamount=beginObj[i].innerText;
				level.upamount=endObj[i].innerText;
				var index=levelTypeObj[i].selectedIndex;
				level.valtype=levelTypeObj[i].options[index].value;
				level.rateval=ratioObj[i].value;
				level.statux=0;
				levels.push(level);
			}
			var data=JSON.stringify(levels);
			var zone={};
			zone.zone=data;
			var content={};
			content.url=domain+"/fee/addZones.htm";
			content.data=zone;
			content.call=condition.callZoneAdd;
			ajaxReq(content);
		}
	});
});