package travelspot.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PlaceDTO {
	private int contentId;
	private Integer contentTypeId; // 관광타입 id
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

	public PlaceDTO(int contentId, String title, int areaCode, String image1, String address, double mapx, double mapy,
			String contents, String homepage) {
		this.contentId = contentId;
		this.title = title;
		this.areaCode = areaCode;
		this.image1 = image1;
		this.address = address;
		this.mapx = mapx;
		this.mapy = mapy;
		this.contents = contents;
		this.homepage = homepage;
	};

	public PlaceDTO(int contentId, String title, int areaCode, String image1, String image2, String address,
			double mapx, double mapy, String contents, String theme, String writingtime, int likecnt, int viewcnt,
			String homepage) {
		this.contentId = contentId;
		this.title = title;
		this.areaCode = areaCode;
		this.image1 = image1;
		this.image2 = image2;
		this.address = address;
		this.mapx = mapx;
		this.mapy = mapy;
		this.contents = contents;
		this.theme = theme;
		this.writingtime = writingtime;
		this.likecnt = likecnt;
		this.viewcnt = viewcnt;
		this.homepage = homepage;
	}

	public PlaceDTO(int contentId, String title, int areaCode, String image1, String image2, String address,
			double mapx, double mapy, String contents, String theme, String writingtime, int likecnt, int viewcnt) { // 테마o
		super();
		this.contentId = contentId;
		this.title = title;
		this.areaCode = areaCode;
		this.image1 = image1;
		this.image2 = image2;
		this.address = address;
		this.mapx = mapx;
		this.mapy = mapy;
		this.contents = contents;
		this.writingtime = writingtime;
		this.likecnt = likecnt;
		this.viewcnt = viewcnt;
		this.theme = theme;
	}

	public PlaceDTO(int contentId, String title, int areaCode, String image1, String image2, String address,
			double mapx, double mapy, String contents, String writingtime, int likecnt, int viewcnt) { // 테마x // x
		this.contentId = contentId;
		this.title = title;
		this.areaCode = areaCode;
		this.image1 = image1;
		this.image2 = image2;
		this.address = address;
		this.mapx = mapx;
		this.mapy = mapy;
		this.contents = contents;
		this.writingtime = writingtime;
		this.likecnt = likecnt;
		this.viewcnt = viewcnt;
	}

	// 테마별 추천코스에서 장소Id, 장소명 추출용 dto
	public PlaceDTO(int contentId, String title, String theme) {
		this.contentId = contentId;
		this.title = title;
		this.theme = theme;
		// this.contentTypeId = contentTypeId; 추천코스 아이디인 25 넘어옴
		// this.image1 = image1;
	};

	public PlaceDTO(int contentId, String title, int areaCode, String image1, String image2, String address,
			double mapx, double mapy) {
		this.contentId = contentId;
		this.title = title;
		this.areaCode = areaCode;
		this.image1 = image1;
		this.image2 = image2;
		this.address = address;
		this.mapx = mapx;
		this.mapy = mapy;
	}

	public PlaceDTO(int contentId, String title, int areaCode, String image1, String address, double mapx,
			double mapy) {
		this.contentId = contentId;
		this.title = title;
		this.areaCode = areaCode;
		this.image1 = image1;
		this.address = address;
		this.mapx = mapx;
		this.mapy = mapy;
	}

}
