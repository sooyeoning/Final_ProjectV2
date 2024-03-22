$(document).ready(function(){

	//지역명 클릭시 화면 이동 및 글자색상 변경
	$(".placeprofile").click(function(){
		$(this).css("color","#2463d3"); 
		//선택된 지역명 전달
		let contentId = $(this).attr('id');
		location.href="/travelspot/themepost?contentId="+contentId//이동
	})
	$(".placeName").click(function(){
		$(this).css("color","#2463d3"); 
		let contentId = $(this).attr("id");
		location.href="/travelspot/themepost?contentId="+contentId//이동
	})//click end
	
	$(".placeLocation").click(function(){
		//$(this).css("color","#2463d3"); 
		let contentId = $(this).attr("id");
		location.href="/travelspot/themepost?contentId="+contentId//이동
		
	})
	
	if($("#selectItem").val()=="title"){		
		$("#selectItem").val("title").prop("selected", true);
	}
	if($("#selectItem").val()=="address"){		
		$("#selectItem").val("address").prop("selected", true);
	} 
	if($("#selectItem").val()=="none"){		
		$("#selectItem").val("none").prop("selected", true);
	} 
	
	
})//ready end
