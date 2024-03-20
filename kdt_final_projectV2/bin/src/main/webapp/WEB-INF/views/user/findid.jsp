<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="/css/user/findidpw.css" />
<link rel="stylesheet" href="/css/reset.css">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../home/header.jsp"%>

    <div class="container">
        <h2 class="text-center findid-heading">아이디 찾기</h2>
        <form id="findid" method="post">
            <div class="form-group">
                <label for="email"></label>
                <input type="text" class="form-control" id="email" placeholder="이메일을 입력하세요" name="email">
            </div>
            <div class="form-group">
                <label for="phone"></label>
                <input type="text" class="form-control" id="phone" placeholder="전화번호를 입력하세요" name="phone">
            </div>
            <button type="button" class="btn btn-default" onclick="findId()">아이디 찾기</button>
        </form>
    </div>

    <!-- 모달 창 -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>아이디 찾기 검색 결과</h2>
            <div id="modalResult"></div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        // 아이디 찾기
        function findId() {
            var email = $("#email").val();
            var phone = $("#phone").val();

            $.ajax({
                url: "/findid",
                method: "POST",
                data: { email: email, phone: phone },
                success: function(response) {
                    var modalContent = "";
                    if (response.result === "success") {
                        modalContent = "아이디: " + response.userId;
                    } else {
                        modalContent = "조회된 결과가 없습니다.";
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
