package controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class HomeDAOImpl implements HomeDAO {

	@Autowired
	public SqlSession sqlSession;
	
	
	@Override
	public List<HomeDTO> getTop5Record() {
		return sqlSession.selectList("getTop5Record");
	}

	@Override
	public List<HomeDTO> getTop5Recommend() {
		return sqlSession.selectList("getTop5Recommend");
	}

	
}
