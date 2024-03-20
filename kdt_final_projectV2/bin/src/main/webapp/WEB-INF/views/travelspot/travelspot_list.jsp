<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>위트</title>
<script src="http://localhost:8099/js/jquery-3.6.4.min.js"></script>

<link href="/css/import.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="searchForm.css">
<script src="http://localhost:8099/js/travelspot_list.js"></script>

</head>
<body>
	<%@ include file="../home/header.jsp"%>
	
	<% 
		int[] ids = new int[]{32, 6, 2, 5, 7, 31};
		String[] titles = new String[]{"강원", "부산", "인천", "광주", "울산", "경기"};
		String[] engtitles = new String[]{"gangwon", "busan", "incheon","gwangju", "ulsan", "gyeonggi"};
	%>
	
	<!-- 6개씩 배치-->
	<div class="regionContainer">
		<% 
			for(int i=0; i<ids.length; i++){
		%>
		<div class="regionItem-list">
	 	<p class="font_content regionName" id=<%=ids[i]%>><%=titles[i] %></p>
			<div class="box-list">	
	   			<a href="list?areaCode=<%=ids[i] %>&page=1"><img class="profile" id=<%=ids[i]%> src="/img/<%=engtitles[i] %>.jpg"></a>
	  		</div>
	  	</div>
	<%} %>
	</div>  	
	<br>
	<hr class="hrmargin">
	
	<!-- 검색창 -->
	<form action="/travelspot/search" method="get" class="search-form">
		<select name="item" class="search-item">
	  		<option>장소명</option>
	      	<option>주소</option>
	   </select>
	   <input type="text" id="search-input" name="searchword" class="search-input" placeholder="검색어를 입력하세요">
	   <input type="submit" value="검색" class="search-button">
	</form>
	  
	<div class="container">
		<c:forEach items="${placelist }" var="placeDTO">
	 		<div class="item" > <!-- style="border: 0.3px solid #2463d3" -->
	 			<img class="placeprofile" src="${placeDTO.image1}" id="${placeDTO.contentId}" />
	  			<p class="placeName font_title" id="${placeDTO.contentId}">${placeDTO.title}</p>
	  			<h1 class="placeLocation font_content" id="${placeDTO.contentId}"></h1>
				<p>${placeDTO.address}</p><br>
				<p style="display:inline; float: left;">조회수 ${placeDTO.viewcnt }</p>
	  			<p style="display:inline; float:right;">좋아요 ${placeDTO.likecnt }</p>
	  			<br>
	  		</div>
		</c:forEach>
	</div>
	  
	   <%
		   int areaCode = (Integer)request.getAttribute("areaCode");
		   int totalCnt = (Integer)request.getAttribute("totalCnt");
		   int totalPage = 0;
		   if(totalCnt%9==0){
			   totalPage = totalCnt/9;
		   }else {
			   totalPage = (totalCnt/9) +1;
		   }
	   %>
	   <div class="pages">
	   		<p style="font-size: 20px; display: inline-block"> ◀ </p>
	   <% for(int i=1; i<=totalPage; i++){  %>
		   <a href="list?areaCode=<%= areaCode %>&page=<%=i %>"><font size="3px"><%=i %></font> &nbsp;&nbsp;</a>	   
	   <% } %>
	  		<p style="font-size: 20px; display: inline-block;"> ▶</p>
	  </div>
	  
	<!-- 스크롤: 위치 수정 필요 -->
	<div style="position:fixed; bottom:1%; right:1%;">
		<a href="#"><img src="../img/top.png" width="20px" height="20px"></a>
	</div>
</body>
</html>