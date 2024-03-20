package travelspot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelspot.DTO.CommentsDTO;
import travelspot.DTO.CommentsUserDTO;
import travelspot.DTO.ReportDTO;
import travelspot.mapper.CommentsMapper;
import travelspot.model.UpdateCommentReq;

@Service
public class CommentsServiceImpl{

	@Autowired
	CommentsMapper commentsmapper;

	public List<CommentsDTO> getComments(int place_id) {
		return commentsmapper.getComments(place_id);
	}

	public void insertComments(CommentsDTO CommentsDTO) {
		commentsmapper.insertComments(CommentsDTO);
	}

	public void deleteComments(int id) {
		commentsmapper.deleteComments(id);
	}

	 public CommentsDTO getOneContent(int id) {
		return commentsmapper.getOneContent(id);
	}

	public void updateComments(UpdateCommentReq updateCommentReq) {
		commentsmapper.updateComments(updateCommentReq);
	}

	public void insertReply(CommentsDTO CommentsDTO) {
		commentsmapper.insertReply(CommentsDTO);
	}

	public List<CommentsUserDTO> getCommentsProfile(int place_id){
		return commentsmapper.getCommentsProfile(place_id) ;
	}

	public void insertReport(ReportDTO ReportDTO) {
		commentsmapper.insertReport(ReportDTO);
	}

	 public List<String> selectUserId(int commentId){
		return commentsmapper.selectUserId(commentId);
	}

	public String selectReportedId(int id) {
		return commentsmapper.selectReportedId(id);
	}
	

	


	


}
