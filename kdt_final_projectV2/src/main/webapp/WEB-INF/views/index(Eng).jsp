<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 화면</title>
<script src="/js/jquery-3.6.4.min.js"></script>
<link rel="stylesheet" href="/css/reset.css">
</head>

<body>
<!-- header -->
<%@ include file="../views/home/header(Eng).jsp"%>
<!-- body -->
<%@ include file="../views/home/body(Eng).jsp"%>
<!-- weather -->
<%@ include file="../views/home/weather.jsp" %>

<!-- top button -->
<%@ include file="../views/home/topbutton.jsp"%>

<!-- footer -->
<%@ include file="../views/home/footer(Eng).jsp"%>

</body>
</html>