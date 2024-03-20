package travelspot;

public enum PlaceContentType {
	//식당 39, 문화시설 14, 레포츠 28, 관광지 12
	FOOD(39),
	CULTURE(14),
	LEPORTS(28),
	TOUR(12);
	
	private int code;
	
	private PlaceContentType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return this.code;
	}
}
