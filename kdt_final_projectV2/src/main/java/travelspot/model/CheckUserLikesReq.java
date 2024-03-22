package travelspot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CheckUserLikesReq {
	
	private int userId;
	private int placeId;
	
}
