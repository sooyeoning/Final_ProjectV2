package travelspot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetPlaceReq {
	private String colName;
	private String colValue;
	private int page;
	private int limitIndex;
	private int limitCount;
	
}
