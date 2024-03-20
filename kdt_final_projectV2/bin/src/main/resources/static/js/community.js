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