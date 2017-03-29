<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- <style>
	.left-nav{
		height: 500px;
		width: 200px;
		border: 1px black solid;  
	}
</style>
<body>
	<h1>工作区</h1>
	
 	<div class="left-nav">
 		<ul>
 			<li>查看</li>
 		</ul>
 	</div>
</body> -->



<head>
    <meta charset="UTF-8">
    <title>扇形绘制</title>
    <style>
        .shanxing{
            position: relative;
            width: 200px;
            height: 200px;
            border-radius: 100px;
            background-color: yellow;
        }
        .sx1{
            position: absolute;
            width: 200px;
            height: 200px;
            transform: rotate(0deg);
            clip: rect(0px,100px,200px,0px); /*这个clip属性用来绘制半圆，在clip的rect范围内的内容显示出来，使用clip属性，元素必须是absolute的 */
            border-radius: 100px;
            background-color: #f00;
            /*-webkit-animation: an1 2s infinite linear; */
        }


        .sx2{
            position: absolute;
            width: 200px;
            height: 200px;
            transform: rotate(0deg);
            clip: rect(0px,100px,200px,0px);
            border-radius: 100px;
            background-color: #f00;
            /*-webkit-animation: an2 2s infinite linear;*/
        }
        /*绘制一个60度扇形*/
        .shanxing1 .sx1{transform: rotate(-30deg);}
        .shanxing1 .sx2{transform: rotate(-150deg);}

        /*绘制一个85度扇形*/
        .shanxing2 .sx1{transform: rotate(-45deg);}
        .shanxing2 .sx2{transform: rotate(-140deg);}

        /*绘制一个向右扇形，90度扇形*/
        .shanxing3 .sx1{transform: rotate(45deg);}
        .shanxing3 .sx2{transform: rotate(-45deg);}

        /*绘制一个颜色扇形 */
        .shanxing4 .sx1{transform: rotate(45deg);background-color: #fff;}
        .shanxing4 .sx2{transform: rotate(-45deg);background-color: #fff;}

        /*绘制一个不同颜色半圆夹角 */
        .shanxing5 .sx1{transform: rotate(45deg);background-color: #f00;}
        .shanxing5 .sx2{transform: rotate(-45deg);background-color: #0f0;}
</style>
</head>

<body>

    扇形制作原理，底部一个纯色原形，里面2个相同颜色的半圆，可以是白色,内部半圆按一定角度变化，就可以产生出扇形效果
    <p>/*绘制一个60度扇形*/</p>
<div class="shanxing shanxing1">
    <div class="sx1"></div>
     <div class="sx2"></div>
</div>
<p>/*绘制一个85度扇形*/</p>
<div class="shanxing shanxing2">
    <div class="sx1"></div>
     <div class="sx2"></div>
</div>
<p>/*绘制一个向右扇形，90度扇形*/</p>
<div class="shanxing shanxing3">
    <div class="sx1"></div>
     <div class="sx2"></div>
</div>
<p>/*绘制一个颜色扇形 */</p>
<div class="shanxing shanxing4">
    <div class="sx1"></div>
     <div class="sx2"></div>
</div>

<p>/*绘制一个不同颜色半圆夹角 */</p>
<div class="shanxing shanxing5">
    <div class="sx1"></div>
     <div class="sx2"></div>
</div>


</body> 
</html>