package travelspot.DTO;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

	public ContentsDTO getThemeContentsDTO(Element e, int placeContentTypeId) {
		ContentsDTO contentsdto = new ContentsDTO();
		if(placeContentTypeId == 12) {
			contentsdto = new ContentsTourDTO().getContentsTourDTO(e);
		}
		if (placeContentTypeId == 39) {
			contentsdto = new ContentsFoodDTO().getContentsFoodDTO(e);
		}
		if (placeContentTypeId == 14) {
			contentsdto = new ContentsCultureDTO().getContentsCultureDTO(e);
		}
		if (placeContentTypeId == 28) {
			contentsdto = new ContentsLeportsDTO().getContentsLeportsDTO(e);
		}
		return contentsdto;
	}
	
	public static String getValue(String tag, Element element) {
		// 태그 이름이 매개변수인 노드를 찾아 > 찾은 노드에서 n번째 노드에 접근 > n번째 노드 안에 정보에 접근할 수 있는 nodelist
		String result = null;

		if (element.getElementsByTagName(tag).item(0).getChildNodes() != null) {
			NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
			// Node node = (Node) nodes.item(0);
			if ((Node) nodes.item(0) != null) {
				result = ((Node) nodes.item(0)).getNodeValue();
			}
		}
		return result;
	}
}