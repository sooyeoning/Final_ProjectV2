<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="/js/jquery-3.6.4.min.js"></script>
<script src="/js/weather_modal.js"></script>
<link rel="stylesheet" href="/css/home/weather(modal).css">
</head>

<body>
 <div id="container">
        <h2>Lorem Ipsum</h2>
        <button id="btn-modal"><img src="">날씨</button>
        <div id="lorem-ipsum"></div>
    </div>
    <div id="modal" class="modal-overlay">
        <div class="modal-window">
            <div class="title">
                <h2>모달</h2>
            </div>
            <div class="close-area">X</div>
            <div class="content">
                <p>가나다라마바사 아자차카타파하</p>
                <p>가나다라마바사 아자차카타파하</p>
                <p>가나다라마바사 아자차카타파하</p>
                <p>가나다라마바사 아자차카타파하</p>
                
            </div>
        </div>
    </div>

</body>
</html>