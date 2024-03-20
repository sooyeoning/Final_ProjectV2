<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/user/findidpw.css" />
<link rel="stylesheet" href="/css/reset.css">
<head>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
</head>
<body>
<%@ include file="../home/header.jsp"%>

    <div class="container">
        <h2 class="text-center findid-heading">비밀번호 찾기</h2>
        <form id="findpw" method="post">
            <div class="form-group">
                <label for="userid"></label>
                <input type="text" class="form-control" id="userid" placeholder="아이디를 입력하세요" name="userid">
            </div>
            <div class="form-group">
                <label for="email"></label>
                <input type="text" class="form-control" id="email" placeholder="이메일을 입력하세요" name="email">
            </div>
            <button type="button" class="btn btn-default" onclick="findPw()">비밀번호 찾기</button>
        </form>
    </div>

<!-- 모달 창 -->
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>비밀번호 찾기 결과</h2>
        <div id="modalResult"></div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
//비밀번호 찾기
function findPw() {
    var userid = $("#userid").val();
    var email = $("#email").val();

    $.ajax({
        url: "/findpw",
        method: "POST",
        data: { userid: userid, email: email },
        success: function(response) {
            var modalContent = "";
            if (response.result === "success") {
                modalContent = "임시 비밀번호: " + response.userPw;
            } else if (response.result === "failure") {
                modalContent = "입력한 정보와 일치하는 사용자를 찾을 수 없습니다.";
            }

            $("#modalResult").html(modalContent);
            showModal();
        },
        error: function(xhr, status, error) {
            console.error(error);
        }
    });
}




        // 모달 창 보이기
        function showModal() {
            $("#myModal").show();
        }

        // 모달 창 닫기
        $(".close").click(function() {
            $("#myModal").hide();
        });

        // 모달 창 외부 클릭 시 닫기
        window.onclick = function(event) {
            var modal = document.getElementById("myModal");
            if (event.target === modal) {
                $("#myModal").hide();
            }
        };
</script>
</body>
</html>
