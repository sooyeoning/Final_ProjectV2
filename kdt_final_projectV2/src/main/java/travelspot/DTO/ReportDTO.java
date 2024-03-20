package travelspot.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReportDTO {

	private int id; // 신고번호
	private int commentId; // 신고당하는 댓글 번호
	private String reportedId;// 신고당한 사람 아이디
	private String userNickname; // 신고자 닉네임
	private String userId; // 신고자 아이디
	private String reportCategory; // 신고 카테고리 - String
	private String reportContents; // 신고 내용
	private String regDate; // 신고날짜
	private int contentId; // 신고당하는 댓글이 있는 게시글 번호
	private int boardId;

	public ReportDTO(int id, int commentId, String userNickname, String userId, String reportCategory,
			String reportContents, String regDate, int contentId, String reportedId, int boardId) {
		this.id = id;
		this.commentId = commentId;
		this.userNickname = userNickname;
		this.userId = userId;
		this.reportCategory = reportCategory;
		this.reportContents = reportContents;
		this.regDate = regDate;
		this.contentId = contentId;
		this.reportedId = reportedId;
		this.boardId = boardId;
	}

}