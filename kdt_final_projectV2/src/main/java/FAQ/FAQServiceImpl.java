package FAQ;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import community.BoardDTO;

@Service

public class FAQServiceImpl implements FAQService {

	@Autowired
	private FAQDAO faqDAO;

	@Override
	public void createArticle(FAQDTO faqDTO) {
		faqDAO.insertArticle(faqDTO);
	}

	@Override
	@Transactional
	public List<FAQDTO> selectAllFAQs(String writer) {
		return faqDAO.selectAllFAQs(writer);
	}
	
	@Override
	@Transactional
	public List<FAQDTO> selectAllFAQsForAdmin(String writer) {
		return faqDAO.selectAllFAQsForAdmin(writer);
	}
	
	@Override
	@Transactional
	public BoardDTO answer(int id) {
		return faqDAO.answer(id);
	}
	
	@Override
	@Transactional
    public void deleteArticleById(int id) {
        // DAO를 사용하여 게시물 삭제하기
        faqDAO.deleteArticleById(id);
    }
    
	@Override
    @Transactional
    public void updateFAQ(BoardDTO dto) {
        // 업데이트 기능
        faqDAO.updateFAQ(dto);
    }
	
	@Override
    public BoardDTO getBoardById2(int id) {
		return faqDAO.getBoardById2(id);
    }

    public List<FAQDTO> getFAQListForAdmin(int startIdx, int count) {
        return faqDAO.selectFAQListForAdmin(startIdx, count);
    }

    public int getTotalFAQCount() {
        return faqDAO.selectTotalFAQCount();
    }
}
