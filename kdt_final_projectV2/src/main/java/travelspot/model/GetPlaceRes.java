package travelspot.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import travelspot.DTO.PlaceDTO;

@Getter
@AllArgsConstructor
public class GetPlaceRes {
	private GetPlaceReq req;
	private List<PlaceDTO> placelist;
	private int totalCnt;

}
