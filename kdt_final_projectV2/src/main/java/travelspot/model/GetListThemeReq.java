package travelspot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GetListThemeReq {
	private String theme;
	private int page;
}
