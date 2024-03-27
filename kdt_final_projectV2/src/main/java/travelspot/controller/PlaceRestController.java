package travelspot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import travelspot.DTO.PlaceContentsDTO;
import travelspot.DTO.PlaceDTO;
import travelspot.service.PlaceServiceImpl;
import travelspot.model.CheckUserLikesReq;
import User.UserDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/travelspot")
public class PlaceRestController {

	private final PlaceServiceImpl placeservice;
	private final HttpSession session;

	@RequestMapping("/likes")
	public String showPostLikes(@RequestParam int contentId) {
		String response = "";

		if(session.getAttribute("user") == null) {
			response = "needLogin";
		} else {	
			UserDTO userdto = (UserDTO) session.getAttribute("user");
			CheckUserLikesReq req = new CheckUserLikesReq(userdto.getId(), contentId);
			if (placeservice.CheckPlaceLikes(req) == null || placeservice.CheckPlaceLikes(req) == 0) { // 이미 찜되어있는 게시글이면
				placeservice.likePlace(contentId); // place테이블에서 찜하기
				placeservice.insertLikes(req); // likes테이블에 정보(회원아이디,게시글아이디,찜여부) 저장
				response = "success";
			} else if (placeservice.CheckPlaceLikes(req) != null && placeservice.CheckPlaceLikes(req) == 1) { // 이미 찜되어있는
				// 게시글이면
				response = "alreadyliked";
			}
		}

		return response;
	}

	@RequestMapping("/likes/cancel")
	public String cancelPostLikes(@RequestParam int contentId) {
		String response = "";

		if(session.getAttribute("user") == null) {
			response = "needLogin";
		} else {
			UserDTO userdto = (UserDTO) session.getAttribute("user");
			CheckUserLikesReq req = new CheckUserLikesReq(userdto.getId(), contentId);

			if (placeservice.CheckPlaceLikes(req) != null && placeservice.CheckPlaceLikes(req) == 1) { // 이미 찜되어있는 게시글이면
				placeservice.cancelPlaceLike(contentId); // place테이블에서 찜취소
				placeservice.cancelLikes(req); // likes테이블에 정보(회원아이디,게시글아이디,찜여부) 저장
				response = "success";
			}
	
		}
		
		return response;

	}

	@GetMapping("/images")
	public PlaceDTO showPostImages(@RequestParam int contentId) {
		PlaceDTO placedto = placeservice.selectPlace(contentId);
		return placedto;
	}

	@GetMapping("/info")
	public PlaceDTO showPostInfo(@RequestParam int contentId) {
		PlaceDTO placeDTO = placeservice.getPlaceThemeDetail(contentId);
		return placeDTO;
	}
	
	
	@GetMapping("/themeinfo")
	public PlaceContentsDTO showPostThemeInfo(@RequestParam int contentId) {
		PlaceContentsDTO placeContentsDTO = placeservice.getPlaceContentThemeDetail(contentId);
		return placeContentsDTO;
	}

	
	
	/*
	@GetMapping("/post/themeimages")
	public PlaceDTO showPostThemeImages(@RequestParam int contentId) {
		PlaceDTO placedto = placeservice.selectPlace(contentId);
		return placedto;
	}
 
	@RequestMapping("/themepost/likes")
	public String showThemePostLikes(@RequestParam int contentId) {

		HashMap<String, Integer> map = new HashMap<>();

		UserDTO userdto = (UserDTO) session.getAttribute("user");
		map.put("user_id", userdto.getId());
		map.put("place_id", contentId);

		String response = "fail";

		if (placeservice.CheckPlaceLikes(map) == null || placeservice.CheckPlaceLikes(map) == 0) { // 이미 찜되어있는 게시글이면
			placeservice.likePlace(contentId); // place테이블에서 찜하기
			placeservice.insertLikes(map); // likes테이블에 정보(회원아이디,게시글아이디,찜여부) 저장
			response = "success";
		} else if (placeservice.CheckPlaceLikes(map) != null && placeservice.CheckPlaceLikes(map) == 1) { // 이미 찜되어있는
																											// 게시글이면
			response = "alreadyliked";
		}

		return response;

	}

	@RequestMapping("/themepost/cancelLikes")
	public String cancelThemePostLikes(@RequestParam int contentId) {

		HashMap<String, Integer> map = new HashMap<>();

		UserDTO userdto = (UserDTO) session.getAttribute("user");
		map.put("user_id", userdto.getId());
		map.put("place_id", contentId);

		String response = "fail";

		if (placeservice.CheckPlaceLikes(map) != null && placeservice.CheckPlaceLikes(map) == 1) { // 이미 찜되어있는 게시글이면
			placeservice.cancelPlaceLike(contentId); // place테이블에서 찜취소
			placeservice.cancelLikes(map); // likes테이블에 정보(회원아이디,게시글아이디,찜여부) 저장
			response = "success";
		}

		return response;

	}*/

	

}// controller
