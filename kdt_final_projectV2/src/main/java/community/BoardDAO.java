package community;

import java.util.HashMap;
import java.util.List;

import User.LikesDTO;
import travelspot.DTO.ReportDTO;

public interface BoardDAO {
	void insertBoard(BoardDTO board);

	List<BoardDTO> getAllBoards(); // 모든 Board 목록을 가져오는 메서드

	List<BoardDTO> getTop10Boards(); // Top 10 Board 목록을 가져오는 메서드

	BoardDTO getBoardById(int boardId);

	void updateBoard(BoardDTO board);

	void deleteBoard(BoardDTO board);

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

	// 좋아요 테이블 정보 저장
	void insertLikes(LikesDTO like);

	// 좋아요 테이블 정보 삭제
	void cancelLikes(LikesDTO like);

	// 좋아요 여부 확인
	LikesDTO getLikeByUserAndBoard(int userId, int boardId);

	public void insertReport2(ReportDTO ReportDTO);

	public List<String> selectByUserId(int boardId);

	public String selectReportedId2(int id);
	
	public List<BoardDTO> searchBoard(HashMap<String, Object> map);
	public int searchBoardCnt(HashMap<String, Object> map);
}