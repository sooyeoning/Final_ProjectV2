$(document).ready(function(){
	
	//선택된 지역명(selectedRegion)
	let urlParams = new URL(location.href).searchParams;
	let areaCode = urlParams.get('areaCode');
	
	//선택된 지역 사진 흐림 처리
	$(`img[id='${areaCode}']`).css("opacity","0.5");
	$(`img[id='${areaCode}']`).css("border","2px solid #2463d3");
	
	//클릭시 페이지 이동
	$(".placeprofile").click(function(){
		//선택된 지역명 전달
		let contentId = $(this).attr('id');
		location.href="http://localhost:8099/travelspot/post?contentId="+contentId
	});
	
	$(".placeName").click(function(){ 
		let contentId = $(this).attr("id");
		location.href="http://localhost:8099/travelspot/post?contentId="+contentId
	});
	
	$(".placeLocation").click(function(){
		let contentId = $(this).attr("id");
		location.href="http://localhost:8099/travelspot/post?contentId="+contentId//이동		
	});
	
	//마우스 올리면 색변경
	$(".placeName").hover(function(){
		$(this).css("color","#2463d3"); 
	}, function(){
		$(this).css("color","black"); 
	})//click end
	
	
	
	//선택했다가 뒤로 되돌아오면 글자 색상 원래대로, 테두리 원래대로 변경 추가
	
	
	
})//ready end