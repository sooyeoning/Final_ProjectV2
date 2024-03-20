package travelspot.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentsDTO {
	private int contentId; 
	private String infocenter; 		// 안내소
	private String chkbabycarriage; // 유모차 대여
	private String chkcreditcard; 	// 신용카드 사용가능 유무
	private String chkpet; 			// 애완동물 가능 여부
	private String kidsfacility;	// 놀이방 여부
	private String restdate; 		// 쉬는날
	private String usetime; 		// 이용시간
	private String discountinfo; 	// 할인정보
	private String firstmenu; 		// 대표메뉴
	private String reservationinfo; // 예약정보
	private String takeout; 		// 포장여부
	private String parking; 		// 주차시설
	private String parkingfee; 		// 주차비용
	private String accomcount; 		// 수용인원
	private String usefee; 			// 입장비용

}