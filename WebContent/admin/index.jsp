<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.static.runoob.com/libs/bootstrap/3.3.7/css/bootstrap.min.css">  

<title>EPS Manager</title>
<style type="text/css">
	.main-content{
		
		/* margin: 0 auto; */
		
	}
	.left{
		float:left;
		width: 100px;
		height: 700px;
		border:1px black solid; 
	}
	
	.left button{
		width: 100px;
		height: 30px;
		background-color: green;
		border:1px black solid;
		text-align:center;
		padding-top: 10px;
		outline: none;
	}
	.left button:ACTIVE{
		background-color: white;
	}
	
	.left button:VISITED{
		background-color: white;
	}
	.left button:FOCUS{
		background-color: white;
	}
	.left button:AFTER{
		background-color: white;
	}
	.content{
		float:left;
		width: 1000px;
		height: 700px;
		border: 1px black solid;
	}
	/* .content table{
		border: 1px black solid;
		text-align: center;  
	}
	.content tr{
		border: 1px black solid;
		text-align: center;  
} */
	
</style>
<script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js">

</script>
<!-- <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.0/jquery.min.js">
</script> -->
  
<script type="text/javascript">
	var goEasy = new GoEasy({appkey: 'edcd2e5d-284b-47fa-a08e-3deabedaff1e'});
	goEasy.subscribe({
		channel: 'my_channel',
		onMessage: function(message){
			console.log(message);
			alert("通知："+message.content);
			confirm();
			/* var bd = document.getElementById("bd");
			
			bd.className = "modal-open";
			var medal = document.getElementById("myModal");
			medal.style.display="block";
			medal.className = "modal fade in"; */
       	}
	});
	
</script>     
<script type="text/javascript">
	function navClick(ele) {
		if (ele == "confirm") {
			confirm();
		}else if (ele == "skimstudent") {
			studentInfo();
		}else if (ele == "add") {
			alert(ele);
		}
	}
	function studentInfo() {
		var ajax = new XMLHttpRequest();
		var pram = null;
		
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var rsps = ajax.responseText;
				var json = JSON.parse(rsps);
				setStudentTable(json);
			}
		}
		
		pram = "type=student";
		ajax.open('post','/EasyPass/skim');
		ajax.setRequestHeader('content-type','application/x-www-form-urlencoded');
		ajax.send(pram);
	}
	function confirm() {
		var ajax = new XMLHttpRequest();
		var pram = null;
		
		ajax.onreadystatechange = function() {
			if (ajax.readyState == 4) {
				var rsps = ajax.responseText;
				
				var json = JSON.parse(rsps);
				
				setDataTable(json);
				
			}
		}
		
		pram = "type=record";
		ajax.open('post','/EasyPass/skim');
		ajax.setRequestHeader('content-type','application/x-www-form-urlencoded');
		ajax.send(pram);
	}
	function setStudentTable(json) {
		console.log(json);
		var table =	"<table class=\"table table-striped\"><th>学号</th><th>姓名</th><th>电话号码</th><th>学院</th><th>班级</th><th>身份证号</th>";
		
		for (var i = 0; i < json.length; i++) {
			var date = new Date();
			date.setTime(json[i].passtime);
			var passtime = date.toLocaleString();
			
			table += "<tr>"
						+"<td>"+json[i].snum+"</td>"
						+"<td>"+json[i].sname+"</td>"
						+"<td>"+json[i].phone+"</td>"
						+"<td>"+json[i].department+"</td>"
						+"<td>"+json[i].cls+"</td>"
						+"<td>"+json[i].cardID+"</td>"
					+"</tr>";
		}
		table += "</table>"
		document.getElementById("content").innerHTML = table;
	}
	function setDataTable(json) {
		console.log(json);
		var table =	"<table class=\"table table-striped\"><th>学号</th><th>姓名</th><th>电话号码</th><th>学院</th><th>身份证号</th><th>出行时间</th><th>物品类别</th><th>物品颜色与型号</th>";
		
		for (var i = 0; i < json.length; i++) {
			var date = new Date();
			date.setTime(json[i].passtime);
			var passtime = date.toLocaleString();
			
			table += "<tr>"
						+"<td>"+json[i].snum+"</td>"
						+"<td>"+json[i].sname+"</td>"
						+"<td>"+json[i].phone+"</td>"
						+"<td>"+json[i].department+"</td>"
						+"<td>"+json[i].cardID+"</td>"
						+"<td>"+passtime+"</td>"
						+"<td>"+json[i].type+"</td>"
						+"<td>"+json[i].thingdetail+"</td>"
					"</tr>";
		}
		table += "</table>"
		document.getElementById("content").innerHTML = table;
	}
	function skim() {
			
	}
	function add() {
		
	}
</script>
<script type="text/javascript">
/* function showAlert() {
	$(function(){
		$(".close").click(function(){
			$(".alert").alert('close');
		});
	});  
} */

</script>   
</head>
<body id="bd" onload="confirm();">
	
	<div class="main-content">
		<div class="left">
			<button  id="confirm" onclick="navClick('confirm');">通行记录</button>
			<button  id="skimstudent" onclick="navClick('skimstudent');">学生信息</button>
			<button  id="add" onclick="navClick('add');">查看</button>
		</div>
		<div id="content" class="content">
			
		</div>
	</div>
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
            </div>
            <div class="modal-body">在这里添加一些文本</div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary">提交更改</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div> 
</body>
</html>