package travelspot;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import travelspot.DTO.ContentsDTO;
import travelspot.DTO.PlaceDTO;
import travelspot.mapper.PlaceMapper;
import travelspot.service.APIServiceImpl;

@RestController
public class APIControllerTest {

	private final APIServiceImpl apiServiceImpl;

	@Autowired
	PlaceMapper placemapper;

	public APIControllerTest(APIServiceImpl apiServiceImpl) {
		this.apiServiceImpl = apiServiceImpl;
	}

	@PostMapping("/api/test")
	public String apiCallTest() throws Exception {
		/*
		 * 원본 데이터 int[] areaCodes = {32,6,2,5,7,31}; for(int i=0; i<areaCodes.length;
		 * i++) { apiServiceImpl.getBasicInfo(areaCodes[i]); }
		 * 
		 * apiServiceImpl.getThemeBasicInfo();
		
		// 기본 관광지
		int[] areaCodes = { 32, 6, 2, 5, 7, 31 };
		for (int i = 0; i < areaCodes.length; i++) {
			apiServiceImpl.getBasicInfo2(areaCodes[i]);
		}

		// 테마별관광지
		apiServiceImpl.getThemeBasicInfo2();
	 */
		// place2 테이블의 모든 데이터 -> 리스트로 가져오기 -> for문 돌면서 place 테이블 정보 변경
		List<PlaceDTO> placelist = placemapper.selectAllPlace();

		for (PlaceDTO one : placelist) {
			placemapper.copyTablePlace2(one);
		}

		// contents2 테이블의 모든 데이터 -> 리스트로 가져오기 -> for문 돌면서 contents 테이블 정보 변경
		List<ContentsDTO> contentlist = placemapper.selectAllContents();

		for (ContentsDTO one : contentlist) {
			System.out.println(one.toString());
			
			int result = placemapper.copyThemeDetail(one);
			System.out.println("변화된 행의 갯수: " + result);
		}

		return "ok";
	}

	@PostMapping("/api/themeplace")
	public String apiTestPlaceDetail2() throws Exception {
		 apiServiceImpl.getThemeBasicInfo("place2", "contents2");
		 return "ok";
	}
	
	@GetMapping("/api/getInfoTest")
	public void getBasicInfo() throws Exception {
		apiServiceImpl.getBasicInfo(32, "place2");
	}
	
	@GetMapping("/api/getInfoTest2")
	public void getBasicInfo2() throws Exception {
		apiServiceImpl.getThemeBasicInfo("place2", "contents2");
	}
	
	@GetMapping("/api/copyTable")
	public void copyTable() throws Exception {
		apiServiceImpl.ScheduledTasksMethod();
	}
}
