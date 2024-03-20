package travelspot.DTO;

public class ContentsLeportsDTO extends ContentsDTO{

	public ContentsLeportsDTO(int contentId, String infocenter, String chkbabycarriage, String chkcreditcard,
			String chkpet, String restdate, String usetime, String accomcount, String usefee, String parking,
			String parkingfee, String reservationinfo) {		
		super(contentId, infocenter, chkbabycarriage, chkcreditcard, chkpet, null, restdate, usetime, null, null, reservationinfo, null, parking,  parkingfee, accomcount, usefee);
	
	}

	
}
