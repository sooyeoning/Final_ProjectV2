<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>

<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/user/login.css" />
<link rel="stylesheet" href="/css/reset.css">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<%@ include file="../home/header.jsp"%>

    <div class="container">
        <h2 class="text-center">로그인</h2>
        <form id="login" method="post" action="/login">
            <div class="form-group">
                <label for="userid"></label>
                <input type="text" class="form-control" id="userid" placeholder="아이디를 입력하세요" name="userid">
            </div>
            <div class="form-group">
                <label for="userpw"></label>
                <input type="password" class="form-control" id="userpw" placeholder="비밀번호를 입력하세요" name="userpw">
            </div>
            <button type="submit" class="btn btn-default">로그인</button>
            <div class="find-buttons">
                <a href="/findid" class="btn btn-default find-button">아이디 찾기</a>
                <a href="/findpw" class="btn btn-default find-button">비밀번호 찾기</a>
            </div>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</body>
</html>
