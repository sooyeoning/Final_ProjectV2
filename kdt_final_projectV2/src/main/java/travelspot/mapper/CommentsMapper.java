package travelspot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import travelspot.DTO.CommentsDTO;
import travelspot.DTO.CommentsUserDTO;
import travelspot.DTO.ReportDTO;
import travelspot.model.UpdateCommentReq;

@Mapper 	//매퍼 파일이야, @MapperScan 필요 
@Repository //객체 생성, @ComponentScan 필요
public interface CommentsMapper {
	 public List<CommentsDTO> getComments(int place_id);
	 public void insertComments(CommentsDTO CommentsDTO);
	 public void deleteComments(int id);
	 public CommentsDTO getOneContent(int id);
	 public void updateComments(UpdateCommentReq updateCommentReq);
	 public void insertReply(CommentsDTO CommentsDTO);
	 public List<CommentsUserDTO> getCommentsProfile(int place_id);
	 public void insertReport(ReportDTO ReportDTO);
	 public List<String> selectUserId(int commentId);
	 public String selectReportedId(int id);
}
