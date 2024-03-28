package travelspot.DTO;

import org.w3c.dom.Element;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ContentsCultureDTO extends ContentsDTO{

	public ContentsCultureDTO(int contentId, String infocenter, String chkbabycarriage, String chkcreditcard,
			String chkpet, String accomcount, String parking, String parkingfee, String restdate, String usefee,
			String discountinfo, String usetime) {
		super(contentId, infocenter, chkbabycarriage, chkcreditcard, chkpet,null, restdate, usetime, discountinfo, null, null, null, parking, parkingfee, accomcount, usefee);
	
	}

	public ContentsCultureDTO getContentsCultureDTO(Element e) {
		Integer contentId = Integer.parseInt(getValue("contentid", e));
		String accomcount = getValue("accomcountculture", e); // 문의 및 안내
		String chkbabycarriage = getValue("chkbabycarriageculture", e); // 유모차 대여
		String chkcreditcard = getValue("chkcreditcardculture", e);
		String chkpet = getValue("chkpetculture", e);
		String infocenter = getValue("infocenterculture", e);
		String parking = getValue("parkingculture", e);
		String parkingfee = getValue("parkingfee", e); // 문의 및 안내
		String restdate = getValue("restdateculture", e); // 유모차 대여
		String usefee = getValue("usefee", e);
		String discountinfo = getValue("discountinfo", e);
		String usetime = getValue("usetimeculture", e);

		return new ContentsCultureDTO(contentId, infocenter, chkbabycarriage, chkcreditcard, chkpet,
				accomcount, parking, parkingfee, restdate, usefee, discountinfo, usetime);

	}
}
