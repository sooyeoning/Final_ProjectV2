package travelspot.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import travelspot.DTO.ContentsDTO;
import travelspot.DTO.PlaceContentsDTO;
import travelspot.DTO.PlaceDTO;
import travelspot.mapper.PlaceMapper;

@Service
public class PlaceServiceImpl {

	@Autowired
	PlaceMapper placemapper;

	public PlaceDTO selectPlace(int contentId) {
		return placemapper.selectPlace(contentId);
	}

	public List<PlaceDTO> listPlaces(HashMap<String, Object> param) {
		return placemapper.listPlaces(param);
	}

	public int getTotalCnt(int areaCode) {
		return placemapper.getTotalCnt(areaCode);
	}

	public List<PlaceContentsDTO> listThemePlaces(HashMap<String, Object> param) {
		return placemapper.listThemePlaces(param);
	}

	public int getTotalThemeCnt(String theme) {
		return placemapper.getTotalThemeCnt(theme);
	}

	public void insertThemeBasicInfo(PlaceDTO placeDTO) {
		placemapper.insertThemeBasicInfo(placeDTO);
	}

	public void updateThemePlace(PlaceDTO placeDTO) {
		placemapper.updateThemePlace(placeDTO);
	}

	public String selectPlaceId(int contentId) {
		return placemapper.selectPlaceId(contentId);
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

	public Integer CheckPlaceLikes(HashMap<String, Integer> map) {
		return placemapper.CheckPlaceLikes(map);
	}

	public void insertLikes(HashMap<String, Integer> map) {
		placemapper.insertLikes(map);
	}

	public List<PlaceDTO> searchPlace(HashMap<String, Object> map) {
		return placemapper.searchPlace(map);
	}

	public int searchPlaceCnt(HashMap<String, Object> map) {
		return placemapper.searchPlaceCnt(map);
	}

	public List<PlaceContentsDTO> searchThemePlaces(HashMap<String, Object> map) {
		return placemapper.searchThemePlaces(map);
	}

	public int searchThemePlacesCnt(HashMap<String, Object> map) {
		return placemapper.searchThemePlacesCnt(map);
	}

	public void cancelLikes(HashMap<String, Integer> map) {
		placemapper.cancelLikes(map);
	}

	public Integer cancelPlaceLike(int contentId) {
		return placemapper.cancelPlaceLike(contentId);
	}

	public String selectContentId(int contentId) {
		return placemapper.selectContentId(contentId);
	}

	public void insertPlaces2(PlaceDTO placeDTO) {
		placemapper.insertPlaces2(placeDTO);
	}

	public void insertThemeBasicInfo2(PlaceDTO placeDTO) {
		placemapper.insertThemeBasicInfo2(placeDTO);
	}

	public void updateThemePlace2(PlaceDTO placeDTO) {
		placemapper.updateThemePlace2(placeDTO);
	}

	public String selectPlaceId2(int contentId) {
		return placemapper.selectPlaceId2(contentId);
	}

	public String selectContentId2(int contentId) {
		return placemapper.selectContentId2(contentId);
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

	public void updatePlace2(PlaceDTO placeDTO) {
		placemapper.updatePlace2(placeDTO);
	}

	public int getContentTypeId(int contentId) {
		return placemapper.getContentTypeId(contentId);
	}

	public int updateThemeDetail(ContentsDTO contentsDTO2) {
		return placemapper.updateThemeDetail(contentsDTO2);
	}

	public int insertThemeDetail(ContentsDTO contentDTO2) {
		return placemapper.insertThemeDetail(contentDTO2);
	}

	public int copyThemeDetail(ContentsDTO contentDTO2) {
		return placemapper.copyThemeDetail(contentDTO2);
	}

	public void insertPlaces(PlaceDTO placeDTO) {
		placemapper.insertPlaces(placeDTO);
	}

	public int updateThemeDetail2(ContentsDTO contentsDTO2) {
		return placemapper.updateThemeDetail2(contentsDTO2);
	}

	public int insertThemeDetail2(ContentsDTO contentDTO2) {
		return placemapper.insertThemeDetail2(contentDTO2);
	}

}