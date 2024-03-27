package travelspot.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lombok.extern.slf4j.Slf4j;
import travelspot.DTO.ContentsCultureDTO;
import travelspot.DTO.ContentsDTO;
import travelspot.DTO.PlaceDTO;
import travelspot.DTO.ContentsFoodDTO;
import travelspot.DTO.ContentsLeportsDTO;
import travelspot.DTO.ContentsTourDTO;
import travelspot.mapper.PlaceMapper;
import travelspot.model.CheckPlaceExistsReq;
import travelspot.model.InsertThemeDetailReq;
import travelspot.model.PostPlaceReq;
import travelspot.model.UpdateThemeDetailReq;
import travelspot.model.UpdateThemePlaceReq;
import travelspot.model.CheckPlaceContentsExistsReq;
import travelspot.model.PostThemeBasicInfoReq;
import travelspot.model.UpdatePlaceReq;

@Service("apiservice")
@Slf4j
public class APIServiceImpl {

	String serviceKey = "A7Izc9O8PXKHKY%2FsWnQUYwt5u6dujTuf3unurHLSOEIfHLv%2F7waxlcZgPnRqpsKUO60J64lzoZ%2FYO3wW5sH1rw%3D%3D";

	StringBuilder urlBuilder;

	@Autowired
	PlaceMapper placemapper;

	public String getBasicData() throws Exception { //처음 데이터 구축용
		// 기본 관광지
		int[] areaCodes = {32,6,2,5,7,31};
		for(int i=0; i<areaCodes.length; i++) {
			getBasicInfo(areaCodes[i], "place");
		}
		// 테마별관광지
		getThemeBasicInfo("place", "contents");
		
		return "ok";
	}
	
	//@Scheduled(cron = "0 28 16 * * *")
	public void ScheduledTasksMethod() throws Exception { //매일 데이터 수정

		// 기본 관광지
		int[] areaCodes = { 32, 6, 2, 5, 7, 31 };
		for (int i = 0; i < areaCodes.length; i++) {
			getBasicInfo(areaCodes[i], "place2");
		}

		// 테마별관광지
		getThemeBasicInfo("place2", "contents2");

		// place2 테이블의 모든 데이터 -> 리스트로 가져오기 -> for문 돌면서 place 테이블 정보 변경
		List<PlaceDTO> placelist = placemapper.selectAllPlace();

		for (PlaceDTO one : placelist) {
			placemapper.copyTablePlace2(one);
		}

		// contents2 테이블의 모든 데이터 -> 리스트로 가져오기 -> for문 돌면서 contents 테이블 정보 변경
		List<ContentsDTO> contentlist = placemapper.selectAllContents();

		for (ContentsDTO one : contentlist) {
			int result = placemapper.copyThemeDetail(one);
		}
		 
	}

	// 전체 관광지 추천용: 테마 없는 기본 장소 불러오기(지역코드 이용)
	public void getBasicInfo(int areaCode, String tableName) throws Exception {

		urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/areaBasedList1");

		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("test", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "=" + URLEncoder.encode("O", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("areaCode", "UTF-8") + "="
				+ URLEncoder.encode(String.valueOf(areaCode), "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode("12", "UTF-8"));
		url(urlBuilder);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(String.valueOf(urlBuilder));

		NodeList nList = doc.getElementsByTagName("item");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;

				Integer contentId = Integer.valueOf(getValue("contentid", eElement));
				String title = getValue("title", eElement);
				int areaCode1 = Integer.valueOf(getValue("areacode", eElement));
				String image1 = getValue("firstimage", eElement);
				String address = getValue("addr1", eElement);
				double mapx = Double.valueOf(getValue("mapx", eElement));
				double mapy = Double.valueOf(getValue("mapy", eElement));

				CheckPlaceExistsReq checkPlaceExistsReq = new CheckPlaceExistsReq(contentId, tableName);
				if (placemapper.selectPlaceId(checkPlaceExistsReq).equals("0")) {
					PostPlaceReq postPlaceReq = new PostPlaceReq(contentId, title, areaCode1, image1, address, mapx, mapy, tableName);
					placemapper.insertPlaces(postPlaceReq);
					log.info("{} 테이블에 insert 완료 {}", tableName, postPlaceReq.toString());
				} else {
					UpdatePlaceReq updatePlaceReq = new UpdatePlaceReq(contentId, title, areaCode1, image1, address, mapx, mapy, tableName);
					placemapper.updatePlace(updatePlaceReq);
					log.info("{}테이블에 update 완료 {}",tableName, updatePlaceReq.toString());
				}
			} // if
		} // for
	}// test method

	// 테마별 게시글 contentId 불러오기
	public void getThemeBasicInfo(String placeTableName, String contentsTableName) throws Exception {
		
		String serviceKey = "gyesYtRw%2BO5TYGJgK%2FiI%2FFD6htVqBdnM8lz7Qp2noL4lQCWtcnA%2BWzJ9dWkBu0dMagfS1sVHzJi3Vn8CQaqM%2Fw%3D%3D";
		String[] themeList = new String[] { "friends", "couple", "alone", "family" };
		String[] cat2List = new String[] { "C0116", "C0114", "C0113", "C0112" };
		String[] cat3List = new String[] { "C01160001", "C01140001", "C01130001", "C01120001" };

		for (int i = 0; i < themeList.length; i++) {
			urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/areaBasedList1");

			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("arrange", "UTF-8") + "=" + URLEncoder.encode("O", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("cat1", "UTF-8") + "=" + URLEncoder.encode("C01", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("cat2", "UTF-8") + "=" + URLEncoder.encode(cat2List[i], "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("cat3", "UTF-8") + "=" + URLEncoder.encode(cat3List[i], "UTF-8"));

			url(urlBuilder);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(String.valueOf(urlBuilder));

			NodeList nList = doc.getElementsByTagName("item");
			ArrayList<Integer> contentIdList = new ArrayList<Integer>();
			ArrayList<Integer> contentTypeIdList = new ArrayList<Integer>();

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					int contentId = Integer.valueOf(getValue("contentid", eElement));
					Integer contentTypeId = Integer.valueOf(getValue("contenttypeid", eElement));
					contentTypeIdList.add(contentTypeId);
					contentIdList.add(contentId);

				} // if
			} // for
			log.info("getThemeBasicInfo : contentsTableName {}", contentsTableName);

			getThemeInfo(contentIdList, contentTypeIdList, themeList[i], placeTableName, contentsTableName); // 기본 정보 DB 등록 - 장소명, 테마, id
		} // url for
	}// test method

	// 테마 장소 불러오기 -> 장소 arrayList 가져와서 상세정보 insert(for문)
	public void getThemeInfo(
			ArrayList<Integer> contentIdList, ArrayList<Integer> contentTypeIdList, String theme, String placeTableName, String contentsTableName)
			throws Exception {

		for (int i = 0; i < contentIdList.size(); i++) {
			urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailInfo1");
			urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
			urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
			urlBuilder.append(
					"&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "="
					+ URLEncoder.encode(String.valueOf(contentTypeIdList.get(i)), "UTF-8"));
			urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "="
					+ URLEncoder.encode(String.valueOf(contentIdList.get(i)), "UTF-8"));

			url(urlBuilder);

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(urlBuilder.toString());

			NodeList nList = doc.getElementsByTagName("item");

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					int contentId = Integer.valueOf(getValue("subcontentid", eElement));
					String title = getValue("subname", eElement);

					// DB 해당 content 존재하는지 확인(문제)
					CheckPlaceExistsReq checkPlaceExistsReq = new CheckPlaceExistsReq(contentId, placeTableName);
					if (placemapper.selectPlaceId(checkPlaceExistsReq).equals("0")) { // 기존 데이터 없는경우
						PostThemeBasicInfoReq postThemeBasicInfoReq = 
								new PostThemeBasicInfoReq(contentId, title, theme, placeTableName);
						
						placemapper.insertThemeBasicInfo(postThemeBasicInfoReq); // 아이디, 제목, 테마 저장
						getThemePlaceDetail(contentId, placeTableName, contentsTableName); // 기본정보 불러오기: 상세정보 불러오기 포함되어 있음
					}
					CheckPlaceContentsExistsReq checkPlaceContentsExistsReq = new CheckPlaceContentsExistsReq(contentId, contentsTableName);
					log.info("getThemeInfo : contentsTableName {}", contentsTableName);
					
					if (placemapper.selectContentId(checkPlaceContentsExistsReq).equals("0")) { // contents테이블 정보 없는 경우
						getThemePlaceDetail(contentId, placeTableName, contentsTableName); // 기본정보 불러오기: 상세정보 불러오기 포함되어 있음
					}
				} // if
			} // for

		} // contentIdList for

	}// test method

	// 장소별 기본정보 불러오기
	public void getThemePlaceDetail(int placeContentId, String placeTableName, String contentsTableName) throws Exception {
		log.info("placeContentId 파라미터 확인",placeContentId);

		urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailCommon1");
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("100", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("test", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "="
				+ URLEncoder.encode(String.valueOf(placeContentId), "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("overviewYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 개요
		urlBuilder.append("&" + URLEncoder.encode("addrinfoYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 주소
		urlBuilder.append("&" + URLEncoder.encode("mapinfoYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 좌표
		urlBuilder.append("&" + URLEncoder.encode("areacodeYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 지역코드
		urlBuilder.append("&" + URLEncoder.encode("firstImageYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 대표이미지
		urlBuilder.append("&" + URLEncoder.encode("defaultYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8")); // 기본정보

		url(urlBuilder);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(String.valueOf(urlBuilder));

		NodeList nodeList = doc.getElementsByTagName("item"); // xml 태그 이름 중 <item>인 부분을 전부 가져온다(NodeList 타입)

		if (nodeList.item(0) != null) {
			Node node = nodeList.item(0); // 모든 <item> 태그 중 첫번째 node

			if (node.getNodeType() == Node.ELEMENT_NODE) { // 자식 노드가 요소일 경우에만 실행한다
				Element e = (Element) node;

				// null체크
				Integer contentId = Integer.parseInt(getValue("contentid", e)); // 게시물 id
				Integer contenttypeid = Integer.parseInt(getValue("contenttypeid", e)); // 게시물 타입 id
				String title = getValue("title", e);
				Integer areaCode = Integer.parseInt(getValue("areacode", e));
				String image1 = getValue("firstimage", e);
				String addr1 = getValue("addr1", e);
				String addr2 = getValue("addr2", e);
				Double mapx = Double.parseDouble(getValue("mapx", e));
				Double mapy = Double.parseDouble(getValue("mapy", e));
				String contents = getValue("overview", e);
				String homepage = getValue("homepage", e);

				UpdateThemePlaceReq req = new UpdateThemePlaceReq();
				String address = req.getAddress(addr1, addr2);
				placemapper.updateThemePlace(new UpdateThemePlaceReq(
					contentId, contenttypeid, title, areaCode,image1, address,mapx,mapy,contents,homepage, placeTableName));

				getThemePlaceDetailIntro(contentId, contenttypeid,contentsTableName);
			} // if
		}
	}// method

	// 장소별 상세정보 불러오기
	public void getThemePlaceDetailIntro(int placeContentId, int placeContentTypeId, String contentsTableName) throws Exception {

		urlBuilder = new StringBuilder("http://apis.data.go.kr/B551011/KorService1/detailIntro1");
		urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);
		urlBuilder.append("&" + URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("test", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("contentId", "UTF-8") + "="
				+ URLEncoder.encode(String.valueOf(placeContentId), "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("contentTypeId", "UTF-8") + "="
				+ URLEncoder.encode(String.valueOf(placeContentTypeId), "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
		url(urlBuilder);

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(String.valueOf(urlBuilder));

		NodeList nodeList = doc.getElementsByTagName("item"); // xml 태그 이름 중 <item>인 부분을 전부 가져온다(NodeList 타입)

		if (nodeList.item(0) != null) {
			Node node = nodeList.item(0); // 모든 <item> 태그 중 첫번째 node

			if (node.getNodeType() == Node.ELEMENT_NODE) { // 자식 노드가 요소일 경우에만 실행한다
				Element e = (Element) node;

				ContentsDTO contentsdto;

				if (placeContentTypeId == 12) {
					contentsdto = getContentType_12(e);
					ThemeDetail(placeContentId, contentsdto, contentsTableName);
				}
				if (placeContentTypeId == 39) {
					contentsdto = getContentType_39(e);
					ThemeDetail(placeContentId, contentsdto, contentsTableName);
				}
				if (placeContentTypeId == 14) {
					contentsdto = getContentType_14(e);
					ThemeDetail(placeContentId, contentsdto, contentsTableName);
				}
				if (placeContentTypeId == 28) {
					contentsdto = getContentType_28(e);
					ThemeDetail(placeContentId, contentsdto, contentsTableName);
				}

			} // if문

		}

	}// method

	public void ThemeDetail(int placeContentId, ContentsDTO contentsdto, String tableName) {
		CheckPlaceContentsExistsReq checkPlaceContentsExistsReq = new CheckPlaceContentsExistsReq(placeContentId, tableName);

		if (placemapper.selectContentId(checkPlaceContentsExistsReq).equals("1")) {// 기존데이터 존재
			UpdateThemeDetailReq updateThemeDetailReq = new UpdateThemeDetailReq(contentsdto,tableName);
			placemapper.updateThemeDetail(updateThemeDetailReq);
		} else {
			InsertThemeDetailReq insertThemeDetailReq = new InsertThemeDetailReq(contentsdto,tableName);
			placemapper.insertThemeDetail(insertThemeDetailReq); // contents 테이블에 기존 데이터 없을 경우
		}
	}

	public void url(StringBuilder urlBuilder) throws Exception {
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");

		log.info("URL Response code: {}" , conn.getResponseCode());
		
		BufferedReader rd;
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}

		StringBuilder sb = new StringBuilder(); // 변경 가능한 문자열: StringBuilder
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		String result = sb.toString();
	}

	public ContentsDTO getContentType_14(Element e) { // 문화시설

		Integer contentId = Integer.parseInt(getValue("contentid", e));
		String accomcount = getValue("accomcountculture", e); // 문의 및 안내
		String chkbabycarriage = getValue("chkbabycarriageculture", e); // 유모차 대여
		String chkcreditcard = getValue("chkcreditcardculture", e);
		String chkpet = getValue("chkpetculture", e);
		String infocenter = getValue("infocenterculture", e);
		String parking = getValue("parkingculture", e);
		String parkingfee = getValue("parkingfee", e); // 문의 및 안내
		String restdate = getValue("restdateculture", e); // 유모차 대여
		String usefee = getValue("usefee", e);
		String discountinfo = getValue("discountinfo", e);
		String usetime = getValue("usetimeculture", e);

		ContentsDTO Contentsdto = new ContentsCultureDTO(contentId, infocenter, chkbabycarriage, chkcreditcard, chkpet,
				accomcount, parking, parkingfee, restdate, usefee, discountinfo, usetime);

		return Contentsdto;
	}

	public ContentsDTO getContentType_28(Element e) { // 레포츠

		// null체크
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

		ContentsDTO Contentsdto = new ContentsLeportsDTO(contentId, infocenter, chkbabycarriage, chkcreditcard, chkpet,
				restdate, usetime, accomcount, usefee, parkingfee, parkingfee, reservationinfo);

		return Contentsdto;
	}

	public ContentsDTO getContentType_12(Element e) { // 관광지

		// null체크
		Integer contentId = Integer.parseInt(getValue("contentid", e));
		String infocenter = getValue("infocenter", e); // 문의 및 안내
		String chkbabycarriage = getValue("chkbabycarriage", e); // 유모차 대여
		String chkpet = getValue("chkpet", e);
		String chkcreditcard = getValue("chkcreditcard", e);
		String restdate = getValue("restdate", e);
		String usetime = getValue("usetime", e);

		ContentsDTO Contentsdto = new ContentsTourDTO(contentId, infocenter, chkbabycarriage, chkcreditcard, chkpet,
				restdate, usetime);
		return Contentsdto;
	}

	public ContentsDTO getContentType_39(Element e) { // 식당

		// null체크
		Integer contentId = Integer.parseInt(getValue("contentid", e));
		String infocenter = getValue("infocenterfood", e); // 문의 및 안내
		String chkcreditcard = getValue("chkcreditcardfood", e); // 신용카드
		String discountinfo = getValue("discountinfofood", e); // 할인정보
		String firstmenu = getValue("firstmenu", e); // 대표메뉴
		String reservationinfo = getValue("reservationfood", e); // 예약안내
		String restdate = getValue("restdatefood", e);// 쉬는날
		String takeout = getValue("packing", e); // 포장가능
		String parking = getValue("parkingfood", e); // 주차시설
		String kidsfacility = getValue("kidsfacility", e);// 어린이놀이방여부
		String usetime = getValue("opentimefood", e);

		ContentsDTO Contentsdto = new ContentsFoodDTO(contentId, infocenter, kidsfacility, chkcreditcard, restdate,
				usetime, discountinfo, firstmenu, reservationinfo, takeout, parking);
		return Contentsdto;
	}

	private static String getValue(String tag, Element element) {
		// 태그 이름이 매개변수인 노드를 찾아 > 찾은 노드에서 n번째 노드에 접근 > n번째 노드 안에 정보에 접근할 수 있는 nodelist
		String result = null;

		if (element.getElementsByTagName(tag).item(0).getChildNodes() != null) {
			NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
			// Node node = (Node) nodes.item(0);
			if ((Node) nodes.item(0) != null) {
				result = ((Node) nodes.item(0)).getNodeValue();
			}
		}
		return result;
	}

}// class
