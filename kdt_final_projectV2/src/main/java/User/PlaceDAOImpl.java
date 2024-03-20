package User;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import travelspot.DTO.PlaceDTO;

@Repository
public class PlaceDAOImpl implements PlaceDAO {
	@Autowired
	public SqlSession sqlSession;

	@Override
	public PlaceDTO getPlaceById(int place_id) {
		return sqlSession.selectOne("getPlaceById",place_id);
	}
	
	
}
