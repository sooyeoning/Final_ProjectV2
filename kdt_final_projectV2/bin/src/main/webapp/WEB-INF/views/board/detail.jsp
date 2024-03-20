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
<link rel="stylesheet" type="text/css" href="css/detail.css" />
</head>
<body>
<%@ include file="../../views/home/header.jsp"%>
<section>
    <span class="main_color">${board.board_title}</span>
    <span>${board.place}</span>
    <h2>${board.title}</h2>
    <div id="infocontainer">
    	<div class="info">    
    		<div id="profile"></div>
    		<span>${board.writer}</span>
    		<span>조회수: ${board.views}</span>
    		<span>좋아요: ${board.likecount}</span>    
    		<span>${board.writingtime}</span>
    	</div>
    <div class="buttons">
    	<c:choose>
        	<c:when test="${board.writer eq nickname}">
            	<a href="/update?boardId=${board.id }">수정</a>
            	<a href="/delete?boardId=${board.id }">삭제</a>
            </c:when>
            <c:otherwise>
                <button>신고</button>
            </c:otherwise>
    	</c:choose>
    </div>
    </div>
    <div id="contents">${board.contents}</div>
</section>
<%@ include file="../../views/home/footer.jsp"%>
<div style="position:fixed; bottom:1%; right:1%;">
<a href="#"><img src="../img/top.png" width="20px" height="20px"></a>
</body>
</html>