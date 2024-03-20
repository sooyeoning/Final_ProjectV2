package travelspot.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentsDTO {
	private int id; 			//댓글 고유 번호
	private String contents; 	//댓글 내용
	private String writingtime; //댓글 등록시간
	private String writer;		//댓글 작성자
	private String target_id;	//게시글 작성자
	private int ref_group;		//게시글 고유 번호
	private int comment_group;	//대댓글 작성시 댓글의 아이디
	private int content_id;		//커뮤니티 게시글 고유 번호
	private int place_id;		//여행지 게시글 고유 번호
	
	public CommentsDTO(int id, String contents, String writingtime, String target_id, int ref_group, int comment_group,
			int content_id, String writer, int place_id) {
		this.id = id;
		this.contents = contents;
		this.writingtime = writingtime;
		this.target_id = target_id;
		this.ref_group = ref_group;
		this.comment_group = comment_group;
		this.content_id = content_id;
		this.writer = writer;
		this.place_id = place_id;
	}
	
}
