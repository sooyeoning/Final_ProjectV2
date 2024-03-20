package FAQ;

import java.util.List;

import community.BoardDTO;

public interface FAQDAO {
	void insertArticle(FAQDTO faqDTO);

	List<FAQDTO> selectAllFAQs(String writer);

	List<FAQDTO> selectAllFAQsForAdmin(String writer);

	BoardDTO answer(int id);

	void deleteArticleById(int id);

	void updateFAQ(BoardDTO dto);

	BoardDTO getBoardById2(int id);

	public List<FAQDTO> selectFAQListForAdmin(int startIdx, int count);

	public int selectTotalFAQCount();

}
