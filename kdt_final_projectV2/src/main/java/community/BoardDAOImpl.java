package community;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import User.LikesDTO;
import travelspot.DTO.ReportDTO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertBoard(BoardDTO board) {
		sqlSession.insert("insertBoard", board);
	}

	@Override
	public List<BoardDTO> getAllBoards() {
		return sqlSession.selectList("getAllBoards"); // SQL 매핑된 쿼리를 실행하여 Board 목록을 가져옴
	}

	@Override
	public List<BoardDTO> getTop10Boards() {
		return sqlSession.selectList("getTop10Boards"); // SQL 매핑된 쿼리를 실행하여 Top 10 Board 목록을 가져옴
	}

	@Override
	public BoardDTO getBoardById(int boardId) {
		return sqlSession.selectOne("getBoardById", boardId);
	}

	@Override
	public void updateBoard(BoardDTO board) {
		sqlSession.update("updateBoard", board);

	}

	@Override
	public void deleteBoard(BoardDTO board) {
		sqlSession.delete("deleteBoard", board);
	}

	@Override
	public void increaseViewCount(int boardId) {
		sqlSession.update("increaseViewCount", boardId);
	}

	@Override
	public List<BoardDTO> getNewestBoards() {
		return sqlSession.selectList("getNewestBoards");
	}

	@Override
	public int insertComment(CommentsDTO commentsdto) {
		return sqlSession.insert("insertComment", commentsdto);
	}

	@Override
	public List<CommentsDTO> getComments(int content_id) {
		return sqlSession.selectList("board.spring.mybatis.BoardDAO.getComments", content_id);
	}

	@Override
	public int deleteComments(int id) {
		return sqlSession.delete("board.spring.mybatis.BoardDAO.deleteComments", id);
	}

	@Override
	public CommentsDTO getOneComment(int id) {
		return sqlSession.selectOne("board.spring.mybatis.BoardDAO.getOneComment", id);
	}

	@Override
	public int updateComments(CommentsDTO commentsdto) {
		return sqlSession.update("board.spring.mybatis.BoardDAO.updateComments", commentsdto);
	}

	@Override
	public int insertReport(travelspot.DTO.ReportDTO reportdto) {
		return sqlSession.insert("board.spring.mybatis.BoardDAO.insertReport", reportdto);
	}

	@Override
	public List<String> selectUserId(int commentId) {
		return sqlSession.selectList("board.spring.mybatis.BoardDAO.selectUserId", commentId);
	}

	@Override
	public String selectReportedId(int id) {
		return sqlSession.selectOne("board.spring.mybatis.BoardDAO.selectReportedId", id);
	}

	public LikesDTO getLikeByUserAndBoard(int userId, int boardId) {
		return sqlSession.selectOne("getLikeByUserAndBoard", new LikesDTO(userId, 0, 0, 0, boardId));
	}

	@Override
	public void insertLikes(LikesDTO like) {
		sqlSession.insert("insertLikes", like);
	}

	@Override
	public void cancelLikes(LikesDTO like) {
		sqlSession.update("cancelLikes", like);
	}

	@Override
	public List<String> selectByUserId(int boardId) {
		return sqlSession.selectList("selectByUserId", boardId);
	}

	@Override
	public void insertReport2(ReportDTO reportDTO) {
		sqlSession.insert("insertReport2", reportDTO);
	}

	@Override
	public String selectReportedId2(int id) {
		return sqlSession.selectOne("selectReportedId2", id);
	}
	@Override
	public List<BoardDTO> searchBoard(HashMap<String, Object> map) {
	    return sqlSession.selectList("searchBoard", map);
	}
	@Override
	public int searchBoardCnt(HashMap<String, Object> map) {
		return sqlSession.selectOne("searchBoardCnt",map);
	}
	

}