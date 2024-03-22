package travelspot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import travelspot.DTO.ContentsDTO;
import travelspot.DTO.PlaceContentsDTO;
import travelspot.DTO.PlaceDTO;
import travelspot.model.CheckUserLikesReq;
import travelspot.model.GetListReq;
import travelspot.model.GetListThemeReq;
import travelspot.model.GetPlaceReq;

@Mapper // 매퍼 파일이야, @MapperScan 필요
@Repository // 객체 생성, @ComponentScan 필요
public interface PlaceMapper {
	 public void insertPlaces(PlaceDTO placeDTO);
	 public List<PlaceDTO> listPlaces(GetListReq req);
	 public PlaceDTO selectPlace(int contentId);
	 public void plusViewCount(int contentId); //조회수 증가
	 public String selectPlaceId(int contentId);
	 public String selectContentId(int contentId);//contents 테이블 정보 유무 조회
	 public int getTotalCnt(int areaCode);
	 public int getTotalThemeCnt(String theme);
	 public void insertThemeBasicInfo(PlaceDTO placeDTO);
	 public void updateThemePlace(PlaceDTO placeDTO);
	 public List<PlaceContentsDTO> listThemePlaces(GetListThemeReq req);
	 public PlaceContentsDTO getPlaceContentThemeDetail(int contentId);
	 public PlaceDTO getPlaceThemeDetail(int id);
	 public void likePlace(int contentId); //관광지 찜하기
	 public Integer CheckPlaceLikes(CheckUserLikesReq req);//관광지 찜 체크
	 public void insertLikes(CheckUserLikesReq req);
	 public List<PlaceDTO> searchPlace(GetPlaceReq param); //검색 관광지 리스트
	 public int searchPlaceCnt(GetPlaceReq param);
	 public List<PlaceContentsDTO> searchThemePlaces(GetPlaceReq param);//테마검색
	 public int searchThemePlacesCnt(GetPlaceReq param);
	 public void cancelLikes(CheckUserLikesReq req);
	 public Integer cancelPlaceLike(int contentId);
	 
	 //
	 public void insertPlaces2(PlaceDTO placeDTO);
	 public void insertThemeBasicInfo2(PlaceDTO placeDTO);
	 public void updateThemePlace2(PlaceDTO placeDTO);
	
	 public String selectPlaceId2(int contentId);
	 public String selectContentId2(int contentId);//contents 테이블 정보 유무 조회
	 public void copyTablePlace2(PlaceDTO placeDTO);
	 public List<PlaceDTO> selectAllPlace();
	 public List<ContentsDTO> selectAllContents();
	 public void updatePlace2(PlaceDTO placeDTO);

	 public int getContentTypeId(int contentId);
	 //
	 public int updateThemeDetail(ContentsDTO contentsDTO2); 
	 public int insertThemeDetail(ContentsDTO contentDTO2);
	 public int updateThemeDetail2(ContentsDTO contentsDTO2); 
	 public int insertThemeDetail2(ContentsDTO contentDTO2);
	 public int copyThemeDetail(ContentsDTO contentDTO2);

}
	