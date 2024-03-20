package controller;

import java.util.List;

import community.BoardDTO;

public interface HomeDAO {
	List<HomeDTO> getTop5Record();
	List<HomeDTO> getTop5Recommend();
	
}
