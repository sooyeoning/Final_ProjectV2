package travelspot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import travelspot.DTO.ReportDTO;
import travelspot.service.CommentsServiceImpl;
import User.UserDTO;

@Controller
@RequiredArgsConstructor
@RequestMapping("/travelspot/report")
public class CommentsController {

	private final CommentsServiceImpl commentsservice;
	private final HttpSession session;

	@GetMapping
	public ModelAndView reportComments(int id, int contentId) {
		UserDTO userdto = (UserDTO) session.getAttribute("user");

		ModelAndView mv = new ModelAndView();
		mv.addObject("contentid", contentId);
		mv.addObject("commentid", id);// 신고당할 댓글 번호
		mv.addObject("reportedId", commentsservice.selectReportedId(id));// 신고당한 댓글 작성자 아이디
		mv.addObject("nickname", userdto.getNickname());// 신고자닉네임
		mv.addObject("userid", userdto.getUserid());// 신고자아이디

		mv.setViewName("travelspot/reportComments");
		return mv;
	}

	@PostMapping
	public String reportComments(ReportDTO ReportDTO) {
		commentsservice.insertReport(ReportDTO);
		return "redirect:/travelspot/post?contentId=" + ReportDTO.getContentId();
	}

}
