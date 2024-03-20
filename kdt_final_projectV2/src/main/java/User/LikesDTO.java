package User;

import org.springframework.stereotype.Component;

import travelspot.DTO.PlaceDTO;

@Component
public class LikesDTO {
	public int id;
	public int user_id;
	public int like_check;
	public int place_id;
	public int board_id;

	public PlaceDTO placeDTO;
	public LikesDTO() {

	}

	public LikesDTO(int id, int user_id, int like_check, int place_id, int board_id) {
		this.id = id;
		this.user_id = user_id;
		this.like_check = like_check;
		this.place_id = place_id;
		this.board_id = board_id;
	}
	
	

	public PlaceDTO getPlaceDTO() {
		return placeDTO;
	}

	public void setPlaceDTO(PlaceDTO placeDTO) {
		this.placeDTO = placeDTO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getLike_check() {
		return like_check;
	}

	public void setLike_check(int like_check) {
		this.like_check = like_check;
	}

	public int getPlace_id() {
		return place_id;
	}

	public void setPlace_id(int place_id) {
		this.place_id = place_id;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	@Override
	public String toString() {
		return "LikesDTO [id=" + id + ", user_id=" + user_id + ", like_check=" + like_check + ", place_id=" + place_id
				+ ", board_id=" + board_id + "]";
	}

	
}
