<%@ page import="travelspot.DTO.PlaceDTO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>위트</title>
<script src="http://localhost:8099/js/jquery-3.6.4.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=9a02700d6a520b1b4d23a9886f1160e0"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
<script src="http://localhost:8099/js/travelspot_post_theme.js"></script>
<script src="http://localhost:8099/js/travelspot_comment.js"></script>

<link href="/css/import.css" rel="stylesheet" type="text/css"/>

</head>
<body>
	<%@ include file="../home/header.jsp"%>
	 
	<!-- 관광명소이름 -->
	<div class="font_title margin"> ${placedto.title}
		<img src="../img/heart (2).png" id="like" style="float:right">
		<input type="hidden" id="like_id" value="${userdto}"/>
	</div>
	
		<!-- 관광명소 메뉴바 -->
	<hr class="hrmargin">
	<div class="postmenu">
		<p class="font_content" id="images">여행지 사진 모아보기</p>
		<p class="font_content" id="info">여행지 상세정보</p>
		<p class="font_content" id="comments">여행지 한줄평 남기기</p>
	</div>
	<hr class="hrmargin">
	
	<div class="result">
		<!-- ajax 이용 결과물 출력하는 곳 -->
	</div>
	
	<div style="position:fixed; bottom:3%; right:-10%;">
		<a href="#">
			<img src="../img/top.png" width="5%" height="5%" />
		</a>
	</div>
	
</body>
</html>