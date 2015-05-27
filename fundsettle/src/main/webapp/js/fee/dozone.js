var condition={
			"checkCatalog":function(){
				var catalogId=$("#catalogId").text();
				if(catalogId==""){
					alert("请先选择分区信息后再添加");
					return false;
				}
				return true;
			},
			"checkGroupId":function(){
				var groupId=$("#groupId").text();
				if(groupId==""){
					alert("请先选择分组信息后再添加");
					return false;
				}
				return true;
			},
			"checkLevel":function(){
				var level=$("#zoneArr").text();
				if(level==""){
					alert("请先设置区域");
					return false;
				}
				return true;
			},
			"checkRatio":function(){
				var ratioObj=document.getElementsByName("ratio");
				var flag=true;
				for(var i=0;i<ratioObj.length;i++){
					var ratio=ratioObj[i];
					var reg=/^\d+(.?\d+)?$/;
					if(!reg.test(ratio.value)){
						$(ratio).addClass("msg");
						flag=false;
					}else{
						$(ratio).removeClass("msg");
					}
				}
				return flag;
			},
			"produceLevel":function(){
				var levels=$("#zone").val();
				if(levels==""){
					$("#zoneMsg").html("请先填写区域")
					return false;
				}
				var reg=/^([1-9]+[0-9]*,?)+$/;
				if(!reg.test(levels)){
					$("#zoneMsg").html("填写的字符串不符合规范")
					return false;
				}
				if(levels.charAt(levels.length-1)==','){
					levels=levels.substring(0,levels.length-1);
				}
				$("#zoneArr").text(levels);
				var level=levels.split(",");
				for(var i=0;i<level.length;i++){
					level[i]=Number(level[i]);
				}
				level.sort(sortNumber);
				var newLevel=removeRepeat(level);
				
				var levelString="";
				
				for(var i=0;i<newLevel.length;i++){
					var begin="";
					var end=newLevel[i];
					if(i==0){
						begin="^";
					}else{
						begin=newLevel[i-1];
					}
					levelString=condition.append(levelString, begin, end);
				}
				levelString=condition.append(levelString, newLevel[newLevel.length-1], "$");
				$("#level").html(levelString);
				$("#zoneMsg").html("")
				return true;
			},
			"append":function(levelString,begin,end){
				levelString+='<tr><td style="height:40px;width:100px" name="begin">'+begin+'</td>';
				levelString+='<td style="height:40px;width:100px" name="end">'+end+'</td>';
				levelString+=
						'<td style="height:40px;width:100px">'+
							'<select style="height:28px;width:80px" name="levelType"><option value="1">定值</option><option value="0">定比例</option></select>'+
						'</td>';
				levelString+='<td style="height:40px;width:100px"><input type="text" name="ratio" style="width:70px;height:28px"/></td></tr>';
				return levelString;
			},
			"callZoneAdd":function(json){
				alert(json.msg);
				if(json.success){
					window.location.href=domain+"/fee/toGroupIndex.htm";
				}
			},
			"callZoneUpdate":function(json){
				alert(json.msg);
				if(json.success){
					window.location.href=domain+"/fee/toQueryZone.htm";
				}
			}
	};