package travelspot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import User.UserDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import travelspot.DTO.CommentsDTO;
import travelspot.DTO.CommentsUserDTO;
import travelspot.model.PostCommentReq;
import travelspot.model.UpdateCommentReq;
import travelspot.service.CommentsServiceImpl;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/travelspot/comments")
public class CommentsRestController {
	
	private final CommentsServiceImpl commentsservice;
	private final HttpSession session;
	
	@GetMapping
	public Map<String, Object> getComments(@RequestParam int contentId){
		Map<String, Object> map = new HashMap<>();
		
		List<CommentsUserDTO> commentsList = commentsservice.getCommentsProfile(contentId);
		map.put("commentsList", commentsList);
		
		UserDTO userdto = (UserDTO)session.getAttribute("user");
		if(ObjectUtils.isEmpty(userdto)) {
			map.put("userdto", "null");
		}else {
			map.put("userdto", userdto.getNickname());
		}
		
		return map;
	}
	
	 @PostMapping("/save")
	 public void saveComments(@RequestBody PostCommentReq postCommentReq) {
		 //로그인 체크
		UserDTO userdto = (UserDTO)session.getAttribute("user");
		
		if(ObjectUtils.isEmpty(userdto) ) {
		} else {
			CommentsDTO commentsDTO = new CommentsDTO();
			commentsDTO.setContents(postCommentReq.getContents());
			commentsDTO.setPlace_id(postCommentReq.getContentId());
			commentsDTO.setRef_group(1);
			commentsDTO.setWriter(userdto.getNickname());
			commentsservice.insertComments(commentsDTO);
		}
	 }
	 
	 @DeleteMapping 
	 public void deleteComments(@RequestParam int id) {
		 commentsservice.deleteComments(id);
	 }
	 
	 @PutMapping 
	 public CommentsDTO modifyComments(@RequestParam int id) {
		 return commentsservice.getOneContent(id);
	 }
	 
	 @PostMapping("/modify_save")
	 public void modify_SaveComments(@RequestBody UpdateCommentReq updateCommentReq) {
		 commentsservice.updateComments(updateCommentReq);
	 }
	 
	 @GetMapping("/modify_cancel")
	 public void modify_CancelComments(@RequestParam int contentId) {
		
	 }
	 
	 @PostMapping(value="/reportcheck")
	 public String checkReport(int id, int contentId) {
		UserDTO userdto = (UserDTO)session.getAttribute("user"); //로그인사용자
		List<String> useridlist = commentsservice.selectUserId(id); //신고한 사람들 아이디
		log.info("댓글번호: {}", id);
		log.info("로그인 아이디: {}", userdto.getUserid());
		
		String response = "false";
		for(int i=0; i<useridlist.size(); i++) {
			log.info("신고자 아이디: {}", useridlist.get(i));
			if((useridlist.get(i)).equals(userdto.getUserid())) {//중복
				response = "true";
				break;
			}
			log.info("기존 신고 여부: {}", response);
		}
			
		return response;
	}
}
