package travelspot.DTO;

import org.w3c.dom.Element;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ContentsFoodDTO extends ContentsDTO{

	public ContentsFoodDTO(int contentId, String infocenter, String kidsfacility, String chkcreditcard, String restdate,
			String usetime, String discountinfo, String firstmenu, String reservationinfo, String takeout,
			String parking) {
		super(contentId, infocenter, null, chkcreditcard, null, kidsfacility, restdate, usetime, discountinfo, firstmenu, reservationinfo, takeout, parking, null, null, null);
		
	}

	public ContentsFoodDTO getContentsFoodDTO(Element e) {
		Integer contentId = Integer.parseInt(getValue("contentid", e));
		String infocenter = getValue("infocenterfood", e); // 문의 및 안내
		String chkcreditcard = getValue("chkcreditcardfood", e); // 신용카드
		String discountinfo = getValue("discountinfofood", e); // 할인정보
		String firstmenu = getValue("firstmenu", e); // 대표메뉴
		String reservationinfo = getValue("reservationfood", e); // 예약안내
		String restdate = getValue("restdatefood", e);// 쉬는날
		String takeout = getValue("packing", e); // 포장가능
		String parking = getValue("parkingfood", e); // 주차시설
		String kidsfacility = getValue("kidsfacility", e);// 어린이놀이방여부
		String usetime = getValue("opentimefood", e);

		return new ContentsFoodDTO(contentId, infocenter, kidsfacility, chkcreditcard, restdate,
				usetime, discountinfo, firstmenu, reservationinfo, takeout, parking);
		
	}
	
}
