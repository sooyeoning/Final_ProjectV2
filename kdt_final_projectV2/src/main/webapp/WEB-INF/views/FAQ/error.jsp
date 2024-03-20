<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Success Alert</title>
    <script>
        // alert를 띄우는 JavaScript 코드
        window.onload = function () {
            alert("글을 조회하거나 수정할 권한이 없습니다.");
            window.location.href = "/selectFAQs"; // 글 목록 페이지로 리다이렉트
        };
    </script>
</head>
<body>
    <!-- alert를 띄우기 위한 스크립트만 포함된 페이지 -->
</body>
</html>
