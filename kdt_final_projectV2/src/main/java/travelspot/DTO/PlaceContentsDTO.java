package travelspot.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlaceContentsDTO {
	private int contentId;
	private int contentTypeId; //관광타입 id
	private String title;
	private int areaCode;
	private String image1;
	private String image2;
	private String address;
	private double mapx;
	private double mapy;
	private String contents;
	private String theme;
	private String writingtime;
	private int likecnt;
	private int viewcnt;
	private String homepage;
	private ContentsDTO contentsDTO; //PlaceDTO contentId = PlaceContentsDTO id
	
}