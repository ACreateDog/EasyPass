<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>EPS</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
	<link rel="stylesheet" type="text/css" href="../css/pass.css">
	<style type="text/css">
	h1 {
	margin-top: 0px;
	padding-top: 40px;
	color: yellow;
}
	
		.success_content {
	margin:auto;
	width: 309px;
	/* height: 287px; */
	text-align:center;
	height: 70px;
	margin-top: -100px;
	z-index:0;
	opacity: 0; 
}
.success_icon{
	height: 70px;
	width: 70px;
	outline: none;
	background-image:url("../images/success.gif");
	background-size:cover;
	border-radius:35px;
	margin: auto;  
}

#msg{
	display:block;
	color: white;
	margin: auto;
}
	</style>
<script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js">

</script>
  
<script type="text/javascript">
	var goEasy = new GoEasy({appkey: 'edcd2e5d-284b-47fa-a08e-3deabedaff1e'});
	goEasy.subscribe({
		channel: 'my_channel',
		onMessage: function(message){
			var jsn = eval(message);
			console.log(jsn.content);
       	}
	});
	
	function send(ele) {
		
		var goEasy = new GoEasy({appkey: 'edcd2e5d-284b-47fa-a08e-3deabedaff1e'});
		goEasy.publish({
			channel:"my_channel",
			message:ele
			
		});
	}
</script> 
	<script type="text/javascript">
		
		function changeText(ele) {
			var val = ele.value;
			var snum     = document.getElementById("snum").value;
			var type     = document.getElementById("type").value;
			var  pram = null;
			
			if (val.length>=11) {
				initSelect();
				var ajax = new XMLHttpRequest();
				ajax.onreadystatechange = function() {
					if (ajax.readyState == 4) {
						var rlt = ajax.responseText;
						if (rlt != null) {
							document.getElementById("detail").value = rlt;	
						}
					}	
				}
			}
			pram = "snum="+ snum +"&type="+type;
			ajax.open('post','/EasyPass/LastRecordServlet');
			ajax.setRequestHeader('content-type','application/x-www-form-urlencoded');
			ajax.send(pram);
				
		}
		
		function addType(jsonData) {
			var jsonObj = JSON.parse(jsonData);
						
		}
		function changeType(element) {

			var val = element.value;
			
			var elnmt = document.getElementById("other");
			var detail = document.getElementById("detail");
			var btn = document.getElementById("btn"); 
			
			if (val == "电脑") {
				elnmt.style.display = "none";
				val += "品牌和型号";
				btn.style.backgroundImage="url(../images/cmptr.png)";
			}else if (val == "自行车") {
				elnmt.style.display = "none";
				val += "类型与颜色";
				btn.style.backgroundImage="url(../images/bike_out.png)";
			}
			else if (val ="其他") {
		
				elnmt.style.display = "block";
				val="物品型号与颜色";
				btn.style.backgroundImage="url(../images/pakege.png)";
				detail.placeholder = val;	
				return;
			}
			detail.placeholder = val;	
			var snum     = document.getElementById("snum");
			changeText(snum);
		}
		function pass() {
			var passform = document.getElementById("passForm");
			var btn      = document.getElementById("btn");
			var success  = document.getElementById("success_content_id");
			var snum     = document.getElementById("snum").value;
			var type     = document.getElementById("type").value;
			var detail = document.getElementById("detail").value;
			var other  = document.getElementById("other").value;
			var pam = "";
			var is = false;
			if (snum == "") {
				is = true;
				document.getElementById("snum").style.borderColor = "red";
			}
			if (detail == "") {
				is = true;
				document.getElementById("detail").style.borderColor = "red";
				
			}
			if (other == "" && document.getElementById("other").style.display == "block") {
				is = true;
				document.getElementById("other").style.borderColor = "red";
			}
			if(is){
				return;
			}
			send(snum);
			var ajax = new XMLHttpRequest();
			ajax.onreadystatechange = function(){
				if (ajax.readyState == 4) {
					var issuccess = ajax.responseText;
					if (issuccess == "false") {
						passSuccessfully(btn,passform,success,false);	
					}else{
						passSuccessfully(btn,passform,success,true);
					}
				}
			}
			pam = "snum="+snum+"&type="+type+"&detail="+detail+"&other="+other;
			console.log(pam);
			ajax.open("post","/EasyPass/PassServlet");
			ajax.setRequestHeader('content-type','application/x-www-form-urlencoded');
			ajax.send(pam);
			
		}
		
		/*
		
		*/
		function passSuccessfully(btn,passform,success,issuccess) {
			btn.style.border = "none";
			var icon  = document.getElementById("success_icon_id");
			var msg  = document.getElementById("msg");
			var martop = 0;
			var sucs = -70;
			
			var opa    = 1.0;
			var opasuc = 0;
			var opabtn = 1.0;
			if (issuccess) {
				icon.style.backgroundImage="url(../images/success.gif)";
				msg.innerHTML = "平安出行";
			}else{
				icon.style.backgroundImage="url(../images/fail.png)";
				msg.innerHTML = "重新尝试！";
			}
			var inter = setInterval(function(){
				martop -= 1;
				sucs   -= 2.5;
				
				opa    -= 0.01;
				opabtn -= 0.03;
				opasuc += 0.01;
				
				passform.style.marginTop = martop + "px";	
				passform.style.opacity   = opa    + "";				
				btn.style.marginTop 	 = martop + "px";
				btn.style.opacity        = opabtn + ""; 
				
				success.style.opacity    = opasuc + "";
				success.style.marginTop  = sucs   + "px";
				
				if (opa < 0) {
					clearInterval(inter);
					passform.style.display = "none";
					btn.style.display = "none";
				}
			}, 1);
		}
		function initSelect() {
			var ajax = new XMLHttpRequest();
			
			ajax.onreadystatechange = function(){
				if (ajax.readyState == 4) {
					var issuccess = ajax.responseText;
					console.log(issuccess);
				}
			}
			var snum     = document.getElementById("snum").value;
			var pam = "snum="+snum;
			
			ajax.open("post","/EasyPass/TypeSelect");
			ajax.setRequestHeader('content-type','application/x-www-form-urlencoded');
			ajax.send(pam);
			
		}
		
</script>

</head>
<body>
	<div class="pageContent">
		<div class="content" id="base">
			<div class="header">
				<h1>易行</h1>		
			</div>
			<form class="formCls" id="passForm" action ="<%=request.getContextPath() %>/PassServlet" method="post">
				<input class="snum" type="tel" onchange="changeText(this)"  id="snum" name="snum" placeholder="请输入学号"><br/>
				<select onchange="changeType(this)" class="selectType" id="type" name="type">
					<option value="自行车" selected="selected">自行车</option>
					<option value="电脑">电脑</option>
					<option value="其他">其他</option>
				</select> 
				<input class="snum" type="text" id="other" style="display: none;" name="other" placeholder="物品的名称"><br>
				<input class="snum" type="text" id="detail" name="detail" placeholder="自行车类型与颜色"><br>	
			</form>	
			<button class="submitBtn" id="btn" name="submit" onclick="pass();" value=""></button>
			
		</div>
		<div class="success_content" id="success_content_id">
			<p id="msg"></p>
			<div class="success_icon" id="success_icon_id">		
			</div>
		</div>
		<div class="footer">
		</div>	
	</div>
	
</body>
</html>