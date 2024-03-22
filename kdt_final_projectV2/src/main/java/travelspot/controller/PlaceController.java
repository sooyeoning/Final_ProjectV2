package travelspot.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import travelspot.DTO.PlaceContentsDTO;
import travelspot.DTO.PlaceDTO;
import travelspot.model.GetPlaceReq;
import travelspot.model.GetListThemeReq;
import travelspot.service.PlaceServiceImpl;
import travelspot.model.GetListReq;
import User.UserDTO;

@Controller
@RequiredArgsConstructor
@RequestMapping("/travelspot")
public class PlaceController {

	private final PlaceServiceImpl placeservice;
	private final HttpSession session;

	// 기본 관광지
	@RequestMapping("/list")
	public ModelAndView showList(
			@RequestParam(required = true, defaultValue = "32") int areaCode,
			@RequestParam(required = true, defaultValue = "1") int page) throws Exception {
		// 데이터 저장: apiservice.getBasicInfo(areaCode);
		// 테마별 info 저장: apiservice.getThemeInfo();

		ModelAndView mv = new ModelAndView();

		int pageindex = (page - 1) * 9; // 페이징처리 - 시작인덱스
		GetListReq req = new GetListReq(areaCode, pageindex);
		
		if(session.getAttribute("user") != null) {
			UserDTO userdto = (UserDTO) session.getAttribute("user");
			mv.addObject("userdto", userdto);
		}
		
		mv.addObject("placelist", placeservice.listPlaces(req));
		mv.addObject("totalCnt", placeservice.getTotalCnt(areaCode));
		mv.addObject("areaCode", areaCode);
		mv.addObject("page", page);
		mv.setViewName("/travelspot/list");
		return mv;
	}

	@GetMapping("/search")
	public ModelAndView searchPlace(
			@RequestParam(value = "item", required = false, defaultValue = "주소") String item,
			@RequestParam(value = "searchword", required = false, defaultValue = "강원") String searchword,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {

		// 검색 조건으로 검색한 게시글 리스트, 게시글수
		String colValue = "%"+searchword+"%";
		int limitCount = 9;
		int limitIndex = (page - 1) * limitCount;
		
		GetPlaceReq param = new GetPlaceReq(item, colValue, page, limitIndex, limitCount);
		List<PlaceDTO> placelist = placeservice.searchPlace(param);
		int totalCnt = placeservice.searchPlaceCnt(param);
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("placelist", placelist);
		mv.addObject("searchCategory", item);
		mv.addObject("totalCnt", totalCnt);
		mv.addObject("searchWord", searchword);
		mv.addObject("page", page);
		mv.setViewName("/travelspot/searchList");
		return mv;
	}
	
	
	
	@RequestMapping("/post")
	public ModelAndView showPost(@RequestParam int contentId) {
		PlaceDTO placedto = placeservice.selectPlace(contentId);

		placeservice.plusViewCount(contentId);// 조회수 증가

		ModelAndView mv = new ModelAndView();

		if(session.getAttribute("user") != null) {
			UserDTO userdto = (UserDTO) session.getAttribute("user");
			mv.addObject("userdto", userdto);
		}
		
		mv.setViewName("/travelspot/post");
		mv.addObject("placedto", placedto);

		return mv;
	}

	// 테마 관광지
	@RequestMapping("/list_theme")
	public ModelAndView showThemeMain(@RequestParam String theme,
			@RequestParam(required = true, defaultValue = "1") int page) throws Exception {
		// apiservice.getThemeBasicInfo();
		ModelAndView mv = new ModelAndView();

		int pageindex = (page - 1) * 9; // 페이징처리 - 시작인덱스
		GetListThemeReq req = new GetListThemeReq(theme, pageindex);

		if(session.getAttribute("user") != null) {
			UserDTO userdto = (UserDTO) session.getAttribute("user");
			mv.addObject("userdto", userdto);
		}
		
		mv.addObject("currentPage", pageindex);
		mv.addObject("placelist", placeservice.listThemePlaces(req)); // PlaceContentsDTO
		mv.addObject("totalCnt", placeservice.getTotalThemeCnt(theme));
		mv.addObject("page", page);
		mv.addObject("theme", theme);
		mv.setViewName("/travelspot/listTheme");
		return mv;
	}

	// 검색한 게시글 조회
	@RequestMapping("/themesearch")
	public ModelAndView searchThemePlace(
			@RequestParam(value = "item", required = false, defaultValue = "title") String item,
			@RequestParam(value = "searchword", required = false, defaultValue = "강원") String searchword,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page) {
		
		// 검색 조건으로 검색한 게시글 리스트, 게시글수
		String colValue = "%"+searchword+"%";
		int limitCount = 9;
		int limitIndex = (page - 1) * limitCount;
		
		GetPlaceReq param = new GetPlaceReq(item, colValue, page,limitIndex, limitCount);
		List<PlaceContentsDTO> placelist = placeservice.searchThemePlaces(param);
		int totalCnt = placeservice.searchThemePlacesCnt(param);

		ModelAndView mv = new ModelAndView();
		mv.addObject("placelist", placelist);
		mv.addObject("searchWord", searchword);
		mv.addObject("searchCategory", item);
		mv.addObject("totalCnt", totalCnt);
		mv.addObject("page", page);
		mv.setViewName("/travelspot/searchListTheme");
		return mv;
	}

	@RequestMapping("/themepost")
	public ModelAndView showThemePost(@RequestParam int contentId) {
		PlaceDTO placedto = placeservice.selectPlace(contentId);

		placeservice.plusViewCount(contentId);// 조회수 증가

		ModelAndView mv = new ModelAndView();

		if(session.getAttribute("user") != null) {
			UserDTO userdto = (UserDTO) session.getAttribute("user");
			mv.addObject("userdto", userdto);
		}

		mv.addObject("placedto", placedto);
		mv.setViewName("/travelspot/postTheme");
		return mv;
	}

}// controller
