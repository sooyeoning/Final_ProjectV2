<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head> 
<meta charset="UTF-8">
<title>위트</title>
<link rel="icon" href="img/favicon.png">
<link rel="stylesheet" type="text/css" href="css/reset.css" />
<link rel="stylesheet" type="text/css" href="css/writing.css" />
<script src = "ckeditor/ckeditor.js"></script>
</head>
<body>
<%@ include file="../views/home/header.jsp"%>
<section>
    <form action="boardwrite" method="POST">
    <select id="board_title" name="board_title">
    	<option value="none" selected disabled>===== 게시판 선택 =====</option>
    	<option value="여행기록">여행기록</option>
    	<option value="추천해주세요">추천해주세요</option>
    </select>
    <select id="place" name="place">
    	<option value="none" selected disabled>===== 지역 선택 =====</option>
    	<option value="인천">인천</option>
    	<option value="광주">광주</option>
    	<option value="부산">부산</option>
    	<option value="울산">울산</option>
    	<option value="강원">강원</option>
    	<option value="경기도">경기도</option>
    </select>
    <input type=text id="hashtag" name="hashtag" placeholder="#해시태그">
    <input type="hidden" id="writer" name="writer" value="${nickname }" readonly><br>
    <input type=text id="title" name=title placeholder="제목"/>
    <br/>
    <textarea id = "contents" name = "contents" ></textarea>
	<script>
		CKEDITOR.replace('contents',{filebrowserUploadUrl:'/mine/imageUpload.do'});
	</script>
    <p><input type="submit" value="글쓰기" id="submit"></p>
    </form>
</section>
<%@ include file="../views/home/footer.jsp"%>
<div style="position:fixed; bottom:1%; right:1%;">
<a href="#"><img src="../img/top.png" width="20px" height="20px"></a>
</div>
</body>
</html>