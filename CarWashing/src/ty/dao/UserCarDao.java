package ty.dao;

import java.util.List;

public interface UserCarDao {

	//通过userid查找carid
	List<Integer> findCarByUser(int userid);
	
	//添加user-car联系
	boolean insertUserCar(int userid,int carid);
	
	//删除user-car联系
	boolean deleteUserCar(int userid,int carid);
}
