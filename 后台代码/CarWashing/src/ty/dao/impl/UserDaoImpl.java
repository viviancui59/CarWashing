package ty.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;





import ty.dbutil.DBManager;
import ty.dao.UserDao;
import ty.entity.Car;
import ty.entity.User;

public class UserDaoImpl implements UserDao {
   
	private DBManager dbManager = new DBManager();
	@Override
	public User findUser(String phonenum, String password) {
		// TODO Auto-generated method stub
		String sql = "select * from user where User_phonenum=? and User_password=?"; 
		ResultSet rs = dbManager.execQuery(sql,phonenum,password);
		
		try {
			if(rs.next()){
				User user=new User();
				user.setUserid(rs.getInt(1));
				user.setPhonenum(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setType(rs.getInt(4));
				user.setName(rs.getString(5));
				user.setGender(rs.getString(6));
			    user.setAge(rs.getInt(7));
			    return user;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbManager.closeConnection();
		return null;
	}

	@Override
	public boolean insertUser(User user) {
		// TODO Auto-generated method stub

		String sql = "insert into user values(null, ? , ? , ? , ? , ? ,?)";
		
		return dbManager.execUpdate(sql,user.getPhonenum(),user.getPassword(),user.getType(),user.getName(),user.getGender(),user.getAge())>0;
	}

	@Override
	public boolean findUserPhonenum(String phonenum) {
		// TODO Auto-generated method stub
		String sql = "select * from user where User_phonenum=? "; 
		ResultSet rs = dbManager.execQuery(sql,phonenum);
		try {
			if(rs.next())
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	

	@Override
	public boolean updateUserPassword(User user) {
		// TODO Auto-generated method stub
		String sql = "update user set User_password=? where User_id=?";
		return dbManager.execUpdate(sql, user.getPassword(),user.getUserid())>0;
		
	}

	@Override
	public boolean insertCar(int carid,int userid) {
		// TODO Auto-generated method stub

		String sql = "insert into usercar values( ? , ? )";
		
		return dbManager.execUpdate(sql, userid,carid)>0;
	}

	@Override
	public boolean deleteCar(int carid,int userid) {
		// TODO Auto-generated method stub
		String sql = "delete from usercar where User_id=? and Car_id=?";
		String sqll = "delete from car where  Car_id=?";
        
         return (dbManager.execUpdate(sql, userid,carid)>0) &&(dbManager.execUpdate(sqll,carid)>0);
	}

	@Override
	public List<Car> findUserCar(List<Integer> list) {
		// TODO Auto-generated method stub
		List<Car> listcar=new ArrayList<Car>();
		for(Integer c:list)
		{
			String sql = "select * from car where  Car_id=?"; 
			ResultSet rs = dbManager.execQuery(sql,c);
			   
			try {
				while(rs.next()){
					Car car=new Car();
					
					car.setCarid(rs.getInt(1));
					
					String sqll = "select * from carbrand where  CarBrand_id=?"; 
					ResultSet rss = dbManager.execQuery(sqll,rs.getInt(2));
					if(rss.next())
					{
						car.setBrandimage(rss.getString(3));
						car.setBrandtype(rss.getString(2));
					}
					
					car.setPlate(rs.getString(3));
					
					listcar.add(car);
				   
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			dbManager.closeConnection();
		}
		
		return listcar;
	}

	@Override
	public User findUserById(int userid) {
		// TODO Auto-generated method stub
		String sql = "select * from user where User_id=? "; 
		ResultSet rs = dbManager.execQuery(sql,userid);
		
		try {
			if(rs.next()){
				User user=new User();
				user.setUserid(rs.getInt(1));
				user.setPhonenum(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setType(rs.getInt(4));
				user.setName(rs.getString(5));
				user.setGender(rs.getString(6));
			    user.setAge(rs.getInt(7));
			    return user;	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dbManager.closeConnection();
		return null;
	}

	@Override
	public List<Integer> findCarByUser(int userid) {
		// TODO Auto-generated method stub
		String sql = "select * from UserCar where User_id=?";
		List<Integer> list=new ArrayList<Integer>();
		ResultSet rs = dbManager.execQuery(sql,userid);
		int carid = 0;
		try {
			while (rs.next()) {
				carid=rs.getInt(2);
				list.add(carid);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			dbManager.closeConnection();

		}
		return list;
	}

	@Override
	public boolean updateUserInfo(User user) {
		// TODO Auto-generated method stub
		String sql = "update user set User_name=?,User_gender=?,User_age=? where User_id=?";
		return dbManager.execUpdate(sql, user.getName(),user.getGender(),user.getAge(),user.getUserid())>0;
		
	}

      
}
