package ty.service;

import java.util.List;

import ty.entity.Car;
import ty.entity.User;


public interface UserService {

	User login(String phonenum,String password);
	User register(User user);
	User findUserid(int userid);
	
    boolean checkUserPhoneNum(String phonenum);//检测用户手机是否已经注册	
    boolean updateUserInfo(User user);
    boolean updateUserPassword(User user);

    boolean insertCar(Car car,int  userid);
    boolean deleteCar(int carid,int  userid);
    
    List<Car> findUserCar(int userid);

}
