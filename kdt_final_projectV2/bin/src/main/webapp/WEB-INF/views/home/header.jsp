<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/reset.css">
<link rel="stylesheet" href="/css/home/header.css">
</head>
<body>
<!-- header(main).jsp ->홈화면에만 쓰는 헤더
header.jsp -> 홈을 제외한 모든 헤더 -->
<% 
String login=null;
if((String)session.getAttribute("login")!=null){
	login=(String)session.getAttribute("login");
}
%>
<header>
<div id="shadow">
<div id="body" >
<%

if (login==null){%>
<div class="name">
<a href="/login" id="headerLoginBtn">로그인</a>
<a href="/signin" id="headerSignupBtn">회원가입</a>
<a href="/index(Eng)">English</a>
</div>
<%}else if (login!=null){%>
<div class="name">
<a href="/logout" id="headerLoginBtn">로그아웃</a>
<!-- <a href="/signin" id="headerSignupBtn">회원가입</a> -->
<a href="/index(Eng)">English</a>
</div>
<%};%>
<a href="/"><img src=../img/logo.png class="logo"></a>
<a href="/travelspot/list"><div class="menu" >여행지 추천</div></a>
<a href="/community"><div class="menu" >커뮤니티</div></a>
<a href="/mypage"><div class="menu" >마이페이지</div></a>
<a href="/FAQ"><div class="menu" >고객센터</div></a>
<a href="/FAQ"><div class="menu" >착한가게</div></a>
<br><br>
</div>
</div> 
</header>

</body>

</html>