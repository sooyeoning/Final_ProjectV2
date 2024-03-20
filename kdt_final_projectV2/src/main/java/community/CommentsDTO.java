package community;

import java.util.List;

import org.springframework.stereotype.Component;

import User.UserDTO;

public class CommentsDTO {
	int id; 			//댓글 고유 번호
	String contents; 	//댓글 내용
	String writingtime; //댓글 등록시간
	String writer;		//댓글 작성자
	String target_id;	//게시글 작성자
	int ref_group;		//게시글 고유 번호
	int comment_group;	//대댓글 작성시 댓글의 아이디
	int content_id;		//커뮤니티 게시글 고유 번호
	int place_id;		//여행지 게시글 고유 번호
	
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
	
	public CommentsDTO() { //기본 생성자
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getWritingtime() {
		return writingtime;
	}
	public void setWritingtime(String writingtime) {
		this.writingtime = writingtime;
	}
	public String getTarget_id() {
		return target_id;
	}
	public void setTarget_id(String target_id) {
		this.target_id = target_id;
	}
	public int getRef_group() {
		return ref_group;
	}
	public void setRef_group(int ref_group) {
		this.ref_group = ref_group;
	}
	public int getComment_group() {
		return comment_group;
	}
	public void setComment_group(int comment_group) {
		this.comment_group = comment_group;
	}
	public int getContent_id() {
		return content_id;
	}
	public void setContent_id(int content_id) {
		this.content_id = content_id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public int getPlace_id() {
		return place_id;
	}
	public void setPlace_id(int place_id) {
		this.place_id = place_id;
	}
	
	
}
