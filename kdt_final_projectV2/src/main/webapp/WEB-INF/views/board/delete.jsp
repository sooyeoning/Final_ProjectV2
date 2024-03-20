<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위트</title>
<link rel="icon" href="img/favicon.png">
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<style type="text/css">
	#messageBox{
		display: flex;
		flex-direction: column;
		align-items: center;
		width: 50%;
		margin: 0 auto;
		padding-top: 10.5%;
	}
	img{
		width: 16vw;
		height: 16vw;
		margin-bottom: 40px;
	}
	h1{
		margin: 0 auto 56px;
	}
	.buttons{
		display:flex;
	}
	a{
		display:block;
		width: 10vw;
		height: 56px;
		text-align: center;
		line-height: 56px;
		border:2px solid #2463d3;
		box-sizing: border-box;
	}
	.goHome{
		margin-right: 32px;
	}
	.goCommu{}
	a:hover{
		border:none;
		color: #fff;
		background-color: #2463d3;
	}
	
</style>
</head>
<body>
<div id="messageBox">
	<img alt="휴지통 아이콘 이미지" src="img/delete.png">
	<h1>정상적으로 삭제되었습니다.</h1>
	<div class="buttons">
		<a class="goHome font_content" href="/">홈으로</a>
		<a class="goCommu font_content" href="/community">커뮤니티로</a>
	</div>
</div>
</body>
</html>
