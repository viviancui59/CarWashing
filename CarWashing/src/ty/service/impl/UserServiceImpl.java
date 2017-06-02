package ty.service.impl;


import java.util.List;

import ty.dao.CarDao;
import ty.dao.UserDao;
import ty.dao.impl.CarDaoImpl;
import ty.dao.impl.UserDaoImpl;
import ty.entity.Car;
import ty.entity.User;
import ty.service.UserService;

public class UserServiceImpl implements UserService {

	private UserDao userDao=new UserDaoImpl();
	private CarDao carDao=new CarDaoImpl();
	@Override
	public User login(String phonenum, String password) {
		User user=userDao.findUser(phonenum, password);
		if(user!=null){
				return user;
			}
		return null;
	}

	@Override
	public User register(User user) {
		if(userDao.insertUser(user)){
			user=userDao.findUser(user.getPhonenum(), user.getPassword());
			return user;
		}

		return null;
	}

	@Override
	public User findUserid(int userid) {
		User user=userDao.findUserById(userid);
		return user;
	}

	@Override
	public boolean checkUserPhoneNum(String phonenum) {
		boolean usercheck=userDao.findUserPhonenum(phonenum);
		return usercheck;
	}

	@Override
	public boolean updateUserPassword(User user) {
		if(userDao.updateUserPassword(user)){
			return true;
		}
		return false;
	}


	@Override
	public boolean insertCar(Car car,int  userid) {
		// TODO Auto-generated method stub
		int carid=carDao.findCarIdByPlate(car.getPlate());
		if(carid>=0)
			{
			
			return userDao.insertCar(carid,   userid);
			
			}
		else
		{
			carDao.insertCar(car);
			return userDao.insertCar(carid,  userid);
		}
		
		
	}

	@Override
	public boolean deleteCar(int carid,int  userid) {
		// TODO Auto-generated method stub
		
		return userDao.deleteCar(carid, userid);
		
	}

	@Override
	public boolean updateUserInfo(User user) {
		if(userDao.updateUserInfo(user)){
			return true;
		}
		return false;
	}

	@Override
	public List<Car> findUserCar(int userid) {
		// TODO Auto-generated method stub
		return	userDao.findUserCar(userDao.findCarByUser(userid));
	
	}


}
