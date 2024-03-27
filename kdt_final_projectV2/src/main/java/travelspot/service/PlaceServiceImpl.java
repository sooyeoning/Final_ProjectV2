package travelspot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelspot.DTO.ContentsDTO;
import travelspot.DTO.PlaceContentsDTO;
import travelspot.DTO.PlaceDTO;
import travelspot.mapper.PlaceMapper;
import travelspot.model.CheckPlaceContentsExistsReq;
import travelspot.model.CheckPlaceExistsReq;
import travelspot.model.CheckUserLikesReq;
import travelspot.model.GetListReq;
import travelspot.model.GetListThemeReq;
import travelspot.model.GetPlaceReq;
import travelspot.model.InsertThemeDetailReq;
import travelspot.model.PostPlaceReq;
import travelspot.model.PostThemeBasicInfoReq;
import travelspot.model.UpdatePlaceReq;
import travelspot.model.UpdateThemeDetailReq;
import travelspot.model.UpdateThemePlaceReq;

@Service
public class PlaceServiceImpl {

	@Autowired
	PlaceMapper placemapper;

	public PlaceDTO selectPlace(int contentId) {
		return placemapper.selectPlace(contentId);
	}

	public List<PlaceDTO> listPlaces(GetListReq req) {
		return placemapper.listPlaces(req);
	}

	public int getTotalCnt(int areaCode) {
		return placemapper.getTotalCnt(areaCode);
	}

	public List<PlaceContentsDTO> listThemePlaces(GetListThemeReq req) {
		return placemapper.listThemePlaces(req);
	}

	public int getTotalThemeCnt(String theme) {
		return placemapper.getTotalThemeCnt(theme);
	}

	public void insertThemeBasicInfo(PostThemeBasicInfoReq postThemeBasicInfoReq) {
		placemapper.insertThemeBasicInfo(postThemeBasicInfoReq);
	}

	public void updateThemePlace(UpdateThemePlaceReq updateThemePlaceReq) {
		placemapper.updateThemePlace(updateThemePlaceReq);
	}

	public String selectPlaceId(CheckPlaceExistsReq checkPlaceExistsReq) {
		return placemapper.selectPlaceId(checkPlaceExistsReq);
	}

	public PlaceContentsDTO getPlaceContentThemeDetail(int contentId) {
		return placemapper.getPlaceContentThemeDetail(contentId);
	}

	public PlaceDTO getPlaceThemeDetail(int id) {
		return placemapper.getPlaceThemeDetail(id);
	}

	public void plusViewCount(int contentId) {
		placemapper.plusViewCount(contentId);
	}

	public void likePlace(int contentId) {
		placemapper.likePlace(contentId);
	}

	public Integer CheckPlaceLikes(CheckUserLikesReq req) {
		return placemapper.CheckPlaceLikes(req);
	}

	public void insertLikes(CheckUserLikesReq req) {
		placemapper.insertLikes(req);
	}

	public List<PlaceDTO> searchPlace(GetPlaceReq param) {
		return placemapper.searchPlace(param);
	}

	public int searchPlaceCnt(GetPlaceReq param) {
		return placemapper.searchPlaceCnt(param);
	}

	public List<PlaceContentsDTO> searchThemePlaces(GetPlaceReq param) {
		return placemapper.searchThemePlaces(param);
	}

	public int searchThemePlacesCnt(GetPlaceReq param) {
		return placemapper.searchThemePlacesCnt(param);
	}

	public void cancelLikes(CheckUserLikesReq req) {
		placemapper.cancelLikes(req);
	}

	public Integer cancelPlaceLike(int contentId) {
		return placemapper.cancelPlaceLike(contentId);
	}

	public String selectContentId(CheckPlaceContentsExistsReq checkPlaceContentsExistsReq) {
		return placemapper.selectContentId(checkPlaceContentsExistsReq);
	}

	public void copyTablePlace2(PlaceDTO placeDTO) {
		placemapper.copyTablePlace2(placeDTO);
	}

	public List<PlaceDTO> selectAllPlace() {
		return placemapper.selectAllPlace();
	}

	public List<ContentsDTO> selectAllContents() {
		return placemapper.selectAllContents();
	}

	public void updatePlace(UpdatePlaceReq updatePlaceReq) {
		placemapper.updatePlace(updatePlaceReq);
	}

	public int getContentTypeId(int contentId) {
		return placemapper.getContentTypeId(contentId);
	}

	public int updateThemeDetail(UpdateThemeDetailReq updateThemeDetailReq) {
		return placemapper.updateThemeDetail(updateThemeDetailReq);
	}

	public int insertThemeDetail(InsertThemeDetailReq insertThemeDetailReq) {
		return placemapper.insertThemeDetail(insertThemeDetailReq);
	}

	public int copyThemeDetail(ContentsDTO contentDTO2) {
		return placemapper.copyThemeDetail(contentDTO2);
	}

	public void insertPlaces(PostPlaceReq postPlaceReq) {
		placemapper.insertPlaces(postPlaceReq);
	}

	/*public int updateThemeDetail2(ContentsDTO contentsDTO2) {
		return placemapper.updateThemeDetail2(contentsDTO2);
	}

	public int insertThemeDetail2(ContentsDTO contentDTO2) {
		return placemapper.insertThemeDetail2(contentDTO2);
	}*/
	/*public void insertPlaces2(PlaceDTO placeDTO) {
	placemapper.insertPlaces2(placeDTO);
}

public void insertThemeBasicInfo2(PlaceDTO placeDTO) {
	placemapper.insertThemeBasicInfo2(placeDTO);
}

public void updateThemePlace2(PlaceDTO placeDTO) {
	placemapper.updateThemePlace2(placeDTO);
}*/

/*public String selectPlaceId2(CheckPlaceExistsReq checkPlaceExistsReq) {
	return placemapper.selectPlaceId2(checkPlaceExistsReq);
}

public String selectContentId2(int contentId) {
	return placemapper.selectContentId2(contentId);
}*/

}