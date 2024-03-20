console.log("연결성공");

const recordTap = document.getElementById("recordTap");
const recommendTap = document.getElementById("recommendTap");
const record = document.getElementById("record");
const recommend = document.getElementById("recommend");

//여행기록 탭 눌렀을 때
recordTap.addEventListener("click", function(e){
	e.preventDefault();
	//console.log("여행기록 클릭");
	this.classList.add("on");
	recommendTap.classList.remove("on");
	record.style.display = "block";
	recommend.style.display = "none";
});

//추천해주세요 탭 눌렀을 때
recommendTap.addEventListener("click", function(e){
	e.preventDefault();
	//console.log("추천해주세요 클릭");
	this.classList.add("on");
	recordTap.classList.remove("on");
	record.style.display = "none";
	recommend.style.display = "block";
});

//URL에 따른 셀렉트 태그 선택
document.addEventListener("DOMContentLoaded", function() {
  const urlParams = new URLSearchParams(window.location.search);
  const order = urlParams.get("order");

  if (order === "newest") {
    document.getElementById("printtype").value = "newest";
  } else {
    document.getElementById("printtype").value = "like";
  }
});

// 게시글 정렬
function handleOrderChange() {
  const selectedValue = document.getElementById("printtype").value;
  window.location.href = '/community?order=' + selectedValue;
}

// 셀렉트 태그 선택
const printTypeSelect = document.getElementById("printtype");

// 셀렉트 태그의 옵션 값이 변경될 때 실행되는 이벤트 리스너 추가
printTypeSelect.addEventListener("change", function () {
  handleOrderChange();
});
