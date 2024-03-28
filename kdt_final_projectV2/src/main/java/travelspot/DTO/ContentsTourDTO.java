package travelspot.DTO;

import org.w3c.dom.Element;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ContentsTourDTO extends ContentsDTO{

	public ContentsTourDTO(int contentId, String infocenter, String chkbabycarriage, String chkcreditcard,
			String chkpet, String restdate, String usetime) {
		
		super(contentId, infocenter, chkbabycarriage, chkcreditcard, chkpet, null, restdate, usetime, null, null, null, null, null, null, null, null);
	}

	public ContentsTourDTO getContentsTourDTO(Element e) {
		Integer contentId = Integer.parseInt(getValue("contentid", e));
		String infocenter = getValue("infocenter", e); // 문의 및 안내
		String chkbabycarriage = getValue("chkbabycarriage", e); // 유모차 대여
		String chkpet = getValue("chkpet", e);
		String chkcreditcard = getValue("chkcreditcard", e);
		String restdate = getValue("restdate", e);
		String usetime = getValue("usetime", e);
		
		return new ContentsTourDTO(contentId, infocenter, chkbabycarriage,chkpet,chkcreditcard,restdate, usetime);
	}
	
	
}
