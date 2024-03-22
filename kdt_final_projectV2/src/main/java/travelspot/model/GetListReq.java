package travelspot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetListReq {
	private int areaCode;
	private int page;
}
