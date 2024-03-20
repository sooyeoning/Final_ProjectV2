package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeDAO homeDAO;
	
	@Override
	public List<HomeDTO> getTop5Record() {
		return homeDAO.getTop5Record();
	}

	@Override
	public List<HomeDTO> getTop5Recommend() {
		return homeDAO.getTop5Recommend();
	}

}
