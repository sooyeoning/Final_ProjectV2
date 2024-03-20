package FAQ;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import User.UserDTO;
import community.BoardDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
/* @RequestMapping("/FAQ") */

public class FAQController {

	@Autowired
	private FAQService faqService;

	@RequestMapping("/FAQ")
	public String FAQ() {

		return "/FAQ/contactform";
		/* 아무거나 */
	}

	@RequestMapping("/FAQ01")

	public String FAQ01() {

		return "/FAQ/contactform_FAQ01";

	}

	@RequestMapping("/FAQ02")

	public String FAQ02() {

		return "/FAQ/contactform_FAQ02";

	}

	@RequestMapping("/FAQ03")

	public String FAQ03() {

		return "/FAQ/contactform_FAQ03";

	}

	@RequestMapping("/FAQ04")

	public String FAQ04() {

		return "/FAQ/contactform_FAQ04";

	}

	@RequestMapping("/FAQBoard")
	public String FAQBoard() {

		return "/FAQ/contactBoard";
	}

	@RequestMapping("/success")
	public String success() {

		return "/FAQ/success";
	}

	@RequestMapping("/deleteSuccess")
	public String deleteSuccess() {

		return "/FAQ/deleteSuccess";
	}

	/*
	 * @RequestMapping("/answer") public String answer() {
	 * 
	 * return "/FAQ/answerForm"; }
	 */

	@RequestMapping("/updateSuccess")
	public String updateSuccess() {

		return "/FAQ/updateSuccess";
	}

	private final FAQDAO faqDAO;

	@Autowired
	public FAQController(FAQDAO faqDAO) {
		this.faqDAO = faqDAO;
	}

	@RequestMapping("/answerForm")
	public String answerForm(@RequestParam("id") int id, HttpSession session, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}

		// postId를 사용하여 데이터베이스에서 해당 글의 정보를 조회합니다.
		BoardDTO existingFAQ = faqDAO.getBoardById2(id);
		model.addAttribute("faq", existingFAQ);

		// 해당 글의 작성자와 현재 사용자가 동일한지 확인하여 권한을 체크할 수도 있습니다.
		if (user.getNickname().equals("admin")) {
			model.addAttribute("nickname", user.getNickname());
			List<FAQDTO> list = faqDAO.selectAllFAQsForAdmin(user.getNickname());
			model.addAttribute("boardListForAdmin", list);
			return "/FAQ/answerForm";
		} else if (!user.getNickname().equals(existingFAQ.getWriter())) {
			return "/FAQ/error"; // 예를 들어 수정 권한이 없을 경우 오류 페이지로 리다이렉트
		}

		return "/FAQ/answerForm";
	}

	@PostMapping("/answerFAQ")
	public String answerFAQ(@ModelAttribute BoardDTO dto, Model model, HttpSession session) {
		faqService.updateFAQ(dto);

		model.addAttribute("updateSuccess", true);

		BoardDTO updatedFAQ = faqService.getBoardById2(dto.getId());
		model.addAttribute("dto", updatedFAQ);

		return "/FAQ/contactBoard";
	}

	private static final int PAGE_SIZE = 10;

	@RequestMapping("/selectFAQsForAdmin")
	@ResponseBody
	public List<FAQDTO> selectFAQsForAdmin(@RequestParam("pageNum") int pageNum) {
		// 전체 게시물 개수를 가져옵니다.
		int totalRecords = faqService.getTotalFAQCount();

		// 전체 페이지 개수를 계산합니다.
		int totalPages = (int) Math.ceil((double) totalRecords / PAGE_SIZE);

		// 유효한 페이지 번호를 확인합니다.
		if (pageNum < 1) {
			pageNum = 1;
		} else if (pageNum > totalPages) {
			pageNum = totalPages;
		}

		// 해당 페이지의 게시물을 가져옵니다.
		List<FAQDTO> faqList = faqService.getFAQListForAdmin((pageNum - 1) * PAGE_SIZE, PAGE_SIZE);

		return faqList;
	}


	@GetMapping("/selectAllFAQsForAdmin")
	@ResponseBody
	public List<FAQDTO> selectAllFAQsForAdmin(HttpServletRequest request) {
		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		List<FAQDTO> FAQListForAdmin = faqDAO.selectAllFAQsForAdmin(user.getNickname());
		return FAQListForAdmin;
	}

	@PostMapping("/FAQ/submit")
	public String submitContactForm(HttpServletRequest request, Model model) {

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		} else if (user != null) {
			model.addAttribute("nickname", user.getNickname());
		}

		FAQDTO dto = new FAQDTO();
		String title = request.getParameter("title");
		String board_title = request.getParameter("board_title");
		String contents = request.getParameter("contents");
		String writer = user.getNickname();
		dto.setWriter(request.getParameter("writer"));

		/* String imageFileName = imageFile.getOriginalFilename(); */

		// FaqDTO 객체 생성 및 필드 설정
		FAQDTO faqDTO = new FAQDTO();
		faqDTO.setTitle(title);
		faqDTO.setBoard_title(board_title);
		faqDTO.setContents(contents);
		faqDTO.setWriter(writer);

		/*
		 * faqDTO.setImageFile(imageFile);
		 */

		// faqDAO를 통해 데이터 저장
		faqDAO.insertArticle(faqDTO);

		return "redirect:/success";
	}

	@RequestMapping("/selectFAQs")
	public String getTotalFAQBoard(HttpServletRequest request, Model model) {

		UserDTO user = (UserDTO) request.getSession().getAttribute("user");
		// user가 어떤 값을 받아오는지 출력

		if (user == null) {
			return "redirect:/login";
		} else if ("admin".equals(user.getNickname())) {
			model.addAttribute("nickname", user.getNickname());
			List<FAQDTO> list = faqDAO.selectAllFAQsForAdmin(user.getNickname());
			model.addAttribute("boardListForAdmin", list);
			return "/FAQ/contactBoard(admin)";
		} else {
			model.addAttribute("nickname", user.getNickname());
		}

		List<FAQDTO> list = faqDAO.selectAllFAQs(user.getNickname());
		model.addAttribute("boardList", list);

		return "/FAQ/contactBoard";
	}

	@RequestMapping("/deleteArticle")
	public String deleteArticle(@RequestParam("id") int id, HttpServletRequest request) {
		/*
		 * // 세션에서 현재 로그인된 사용자 정보 가져오기 UserDTO user = (UserDTO)
		 * request.getSession().getAttribute("user");
		 */
		faqService.deleteArticleById(id);
		return "redirect:/deleteSuccess"; // 삭제 후 게시판 목록 페이지로 리다이렉트

	}

	@RequestMapping("/updateForm")
	public String updateForm(@RequestParam("id") int id, HttpSession session, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}

		// postId를 사용하여 데이터베이스에서 해당 글의 정보를 조회합니다.
		BoardDTO existingFAQ = faqDAO.getBoardById2(id);
		// 해당 글의 작성자와 현재 사용자가 동일한지 확인하여 권한을 체크할 수도 있습니다.
		if (user.getNickname().equals("admin")) {
			model.addAttribute("nickname", user.getNickname());
		} else if (!user.getNickname().equals(existingFAQ.getWriter()) && !user.getNickname().equals("admin")) {
			return "redirect:/error"; // 예를 들어 수정 권한이 없을 경우 오류 페이지로 리다이렉트
		}
		model.addAttribute("faq", existingFAQ);

		return "/FAQ/updateForm";
	}

	@PostMapping("/updateFAQ")
	public String updateFAQ(@ModelAttribute BoardDTO dto, Model model, HttpSession session) {
		faqService.updateFAQ(dto);

		model.addAttribute("updateSuccess", true);

		BoardDTO updatedFAQ = faqService.getBoardById2(dto.getId());
		model.addAttribute("dto", updatedFAQ);

		return "/FAQ/contactBoard";
	}

	@RequestMapping("/detailForm")
	public String detailForm(@RequestParam("id") int id, HttpSession session, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}

		// postId를 사용하여 데이터베이스에서 해당 글의 정보를 조회합니다.
		BoardDTO existingFAQ = faqDAO.getBoardById2(id);
		model.addAttribute("faq", existingFAQ);

		// 해당 글의 작성자와 현재 사용자가 동일한지 확인하여 권한을 체크할 수도 있습니다.
		if (user.getNickname().equals("admin")) {
			model.addAttribute("nickname", user.getNickname());
			List<FAQDTO> list = faqDAO.selectAllFAQsForAdmin(user.getNickname());
			model.addAttribute("boardListForAdmin", list);
			return "/FAQ/detailForm";
		} else if (!user.getNickname().equals(existingFAQ.getWriter())) {
			return "/FAQ/error"; // 예를 들어 수정 권한이 없을 경우 오류 페이지로 리다이렉트
		}

		return "/FAQ/detailForm";
	}

	@RequestMapping("/viewForm01")
	public String viewForm01(@RequestParam("id") int id, HttpSession session, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}

		// postId를 사용하여 데이터베이스에서 해당 글의 정보를 조회합니다.
		BoardDTO existingFAQ = faqDAO.getBoardById2(id);
		// 해당 글의 작성자와 현재 사용자가 동일한지 확인하여 권한을 체크할 수도 있습니다.
		if (user.getNickname().equals("admin")) {
			model.addAttribute("nickname", user.getNickname());
		} else if (!user.getNickname().equals(existingFAQ.getWriter()) && !user.getNickname().equals("admin")) {
			return "redirect:/error"; // 예를 들어 수정 권한이 없을 경우 오류 페이지로 리다이렉트
		}
		model.addAttribute("faq", existingFAQ);

		return "/FAQ/contactform_FAQ01";
	}

	@RequestMapping("/viewForm02")
	public String viewForm02(@RequestParam("id") int id, HttpSession session, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}

		// postId를 사용하여 데이터베이스에서 해당 글의 정보를 조회합니다.
		BoardDTO existingFAQ = faqDAO.getBoardById2(id);
		// 해당 글의 작성자와 현재 사용자가 동일한지 확인하여 권한을 체크할 수도 있습니다.
		if (user.getNickname().equals("admin")) {
			model.addAttribute("nickname", user.getNickname());
		} else if (!user.getNickname().equals(existingFAQ.getWriter()) && !user.getNickname().equals("admin")) {
			return "redirect:/error"; // 예를 들어 수정 권한이 없을 경우 오류 페이지로 리다이렉트
		}
		model.addAttribute("faq", existingFAQ);

		return "/FAQ/contactform_FAQ02";
	}

	@RequestMapping("/viewForm03")
	public String viewForm03(@RequestParam("id") int id, HttpSession session, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}

		// postId를 사용하여 데이터베이스에서 해당 글의 정보를 조회합니다.
		BoardDTO existingFAQ = faqDAO.getBoardById2(id);
		// 해당 글의 작성자와 현재 사용자가 동일한지 확인하여 권한을 체크할 수도 있습니다.
		if (user.getNickname().equals("admin")) {
			model.addAttribute("nickname", user.getNickname());
		} else if (!user.getNickname().equals(existingFAQ.getWriter()) && !user.getNickname().equals("admin")) {
			return "redirect:/error"; // 예를 들어 수정 권한이 없을 경우 오류 페이지로 리다이렉트
		}
		model.addAttribute("faq", existingFAQ);

		return "/FAQ/contactform_FAQ03";
	}

	@RequestMapping("/viewForm04")
	public String viewForm04(@RequestParam("id") int id, HttpSession session, Model model) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			return "redirect:/login";
		}

		// postId를 사용하여 데이터베이스에서 해당 글의 정보를 조회합니다.
		BoardDTO existingFAQ = faqDAO.getBoardById2(id);
		// 해당 글의 작성자와 현재 사용자가 동일한지 확인하여 권한을 체크할 수도 있습니다.
		if (user.getNickname().equals("admin")) {
			model.addAttribute("nickname", user.getNickname());
		} else if (!user.getNickname().equals(existingFAQ.getWriter()) && !user.getNickname().equals("admin")) {
			return "redirect:/error"; // 예를 들어 수정 권한이 없을 경우 오류 페이지로 리다이렉트
		}
		model.addAttribute("faq", existingFAQ);

		return "/FAQ/contactform_FAQ04";
	}
}
