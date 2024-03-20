package FAQ;

public class FAQDTO {
	
	String title, contents, board_title, writer, writingtime, answer;
	int id;
	
	public String getAnswer() {
		return answer;
	}

	
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}

	public String getWritingtime() {
		return writingtime;
	}

	public void setWritingtime(String writingtime) {
		this.writingtime = writingtime;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getBoard_title() {
		return board_title;
	}

	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	

}
