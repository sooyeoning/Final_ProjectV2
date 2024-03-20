<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="UTF-8">
<title>위트</title>
<script src="http://localhost:8099/js/jquery-3.6.4.min.js"></script>

<link href="/css/import.css" rel="stylesheet" type="text/css" />
<script src="http://localhost:8099/js/travelspot_list_theme.js"></script>

</head>
<body>
	<%@ include file="../home/header.jsp"%>

	<%
		String[] themes = new String[] { "alone", "couple", "friends", "family" };
	%>

	<!-- 6개씩 배치-->
	<div class="themeContainer">
		<%
			for (int i = 0; i < themes.length; i++) {
		%>
		<div class="themeItem-list">
			<p class="font_content regionName" id=<%=themes[i]%>><%=themes[i]%></p>
			<div class="theme-list">
				<a href="list_theme?theme=<%=themes[i]%>&page=1"><img
					class="themeprofile" style="width: 100px; height: 100px;"
					id=<%=themes[i]%> src="/img/<%=themes[i]%>.png"></a>
			</div>
		</div>
		<%
		}
		%>
	</div>
	<br>
	<hr class="hrmargin">
	
	<!-- 검색창 -->
	<form action="/travelspot/themesearch" method="get" class="search-form">
		<select name="item" class="search-item">
			<option>장소명</option>
			<option>주소</option>
		</select> <input type="text" id="search-input" name="searchword"
			class="search-input" placeholder="검색어를 입력하세요"> <input
			type="submit" value="검색" class="search-button">
	</form>
	
	<div class="container">
		<c:forEach items="${placelist}" var="placeDTO">
			<div class="item">
				<!-- style="border: 0.3px solid #2463d3" -->
				<img class="placeprofile" src="${placeDTO.image1}" id="${placeDTO.contentId}" />
				<p class="placeName font_title" id="${placeDTO.contentId}">${placeDTO.title}</p>
				<h1 class="placeLocation font_content" id="${placeDTO.contentId}"></h1>
				<p>${placeDTO.address}</p>
				<br>
				<p style="display: inline; float: left;">조회수 ${placeDTO.viewcnt }</p>
				<p style="display: inline; float: right;">좋아요 ${placeDTO.likecnt }</p>
				<br>
			</div>
		</c:forEach>
	</div>
	
	<%
		int totalCnt = (Integer) request.getAttribute("totalCnt");
		String theme = String.valueOf(request.getAttribute("theme"));
	
		int totalPage = 0;
		if (totalCnt % 9 == 0) {
			totalPage = totalCnt / 9;
		} else {
			totalPage = (totalCnt / 9) + 1;
		}
	%>
	<div class="pages">
		<p style="font-size: 20px; display: inline-block">🔙</p>
		<%
			for (int i = 1; i <= totalPage; i++) {//10페이지 처리
		%>
		<a href="list_theme?theme=<%=theme %>&page=<%=i%>">
			<p style="font-size: 20px; display: inline-block;"><%=i%></p>&nbsp;&nbsp;
		</a>
		<%
			}
		%>
		<p style="font-size: 20px; display: inline-block;">🔜</p>

	</div>

	<!-- 스크롤: 위치 수정 필요 -->
	<div style="position: fixed; bottom: 1%; right: 1%;">
		<a href="#"><img src="../img/top.png" width="20px" height="20px"></a>
	</div>
</body>
</html>