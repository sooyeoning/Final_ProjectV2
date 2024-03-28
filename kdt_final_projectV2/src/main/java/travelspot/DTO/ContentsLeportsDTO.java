package travelspot.DTO;

import org.w3c.dom.Element;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ContentsLeportsDTO extends ContentsDTO{

	public ContentsLeportsDTO(int contentId, String infocenter, String chkbabycarriage, String chkcreditcard,
			String chkpet, String restdate, String usetime, String accomcount, String usefee, String parking,
			String parkingfee, String reservationinfo) {		
		super(contentId, infocenter, chkbabycarriage, chkcreditcard, chkpet, null, restdate, usetime, null, null, reservationinfo, null, parking,  parkingfee, accomcount, usefee);
	
	}

	public ContentsDTO getContentsLeportsDTO(Element e) {
		Integer contentId = Integer.parseInt(getValue("contentid", e));
		String infocenter = getValue("infocenterleports", e);
		String chkbabycarriage = getValue("chkbabycarriageleports", e);
		String chkcreditcard = getValue("chkcreditcardleports", e);
		String chkpet = getValue("chkpetleports", e);
		String restdate = getValue("restdateleports", e);
		String usetime = getValue("usetimeleports", e);
		String accomcount = getValue("accomcountleports", e);
		String usefee = getValue("usefeeleports", e);
		String parkingfee = getValue("parkingfeeleports", e);
		String reservationinfo = getValue("reservation", e);

		return new ContentsLeportsDTO(contentId, infocenter, chkbabycarriage, chkcreditcard, chkpet,
				restdate, usetime, accomcount, usefee, parkingfee, parkingfee, reservationinfo);

	}

	
}
