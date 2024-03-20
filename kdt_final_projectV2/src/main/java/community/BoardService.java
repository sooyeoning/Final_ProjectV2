package community;

import java.util.HashMap;
import java.util.List;

import User.LikesDTO;
import travelspot.DTO.ReportDTO;

public interface BoardService {
	void createBoard(BoardDTO board);
	List<BoardDTO> getAllBoards(); // 모든 Board 목록을 가져오는 메서드
	List<BoardDTO> getTop10Boards(); // Top 10 Board 목록을 가져오는 메서드
	BoardDTO getBoardById(int boardId);
	void updateBoard(BoardDTO board);
	void deleteBoard(BoardDTO board);
	
	// 게시글 조회수 증가 메서드 추가
    void increaseViewCount(int boardId);
    
    // 최신순으로 게시글 가져오기
    List<BoardDTO> getNewestBoards();
    
    int insertComment(CommentsDTO commentsdto);
    List<CommentsDTO> getComments(int content_id);
	int deleteComments(int id);
	CommentsDTO getOneComment(int id);
	int updateComments(CommentsDTO commentsdto);
	int insertReport(travelspot.DTO.ReportDTO reportdto);
	List<String> selectUserId(int commentId);
	String selectReportedId(int id);

    LikesDTO getLikeByUserAndBoard(int userId, int boardId);
    void insertLikes(LikesDTO like);
    void cancelLikes(LikesDTO like);

    public void insertReport2(ReportDTO reportDTO);
    
    public String selectReportedId2(int id);
    
	public List<BoardDTO> searchBoard(HashMap<String, Object> map);
	
	public int searchBoardCnt(HashMap<String, Object> map);

}