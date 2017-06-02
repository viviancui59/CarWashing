package ty.dao;

import java.util.List;

import ty.entity.Car;
import ty.entity.User;

public interface UserDao {
	 //根据用户名和密码查找用户
	User findUser(String phonenum, String password);
	 //根据用户id
		User findUserById(int userid);
	//插入新用户 
	boolean insertUser(User user);
	//检测重复
    boolean findUserPhonenum(String phonenum); //因为userid是主键，所以若重复数据库会插入不成功
    
    //更新
    boolean updateUserPassword(User user);
    boolean updateUserInfo(User user);
    //增加车型
    boolean insertCar(int carid,int userid);
    //删除车型
    boolean deleteCar(int carid,int userid);
    //查找所有车型
    List<Car>  findUserCar(List<Integer> list);
    
    List<Integer> findCarByUser(int userid);
}
