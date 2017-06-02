package ty.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import ty.dao.CarDao;
import ty.dbutil.DBManager;
import ty.entity.Car;

public class CarDaoImpl implements CarDao{
	
	private DBManager dbManager = new DBManager();

	public Car findByCarId(int carid) {
		// TODO Auto-generated method stub
		String sql = "select * from Car where Car_id=?";
		ResultSet rs = dbManager.execQuery(sql,carid);
		Car car = null;
		try {
			while (rs.next()) {
				car = new Car();

				car.setCarid(rs.getInt(1));
				car.setCarbrandid(rs.getInt(2));
				car.setPlate(rs.getString(3));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			dbManager.closeConnection();

		}
		return car;
	}

	public boolean insertCar(Car car) {
		// TODO Auto-generated method stub
		
		String sql = "insert into Car values(null,?,?)";

		int carbrandid=car.getCarbrandid();
		String plate=car.getPlate();
		boolean flag=(dbManager.execUpdate(sql,carbrandid,plate)>0);
		dbManager.closeConnection();
		return flag;
	}

	public int findCarIdByPlate(String carplate) {
		// TODO Auto-generated method stub
		String sql = "select * from Car where Car_plate=?";
		ResultSet rs = dbManager.execQuery(sql,carplate);
		Car car = null;
		try {
			while (rs.next()) {
				car = new Car();

				car.setCarid(rs.getInt(1));
				car.setCarbrandid(rs.getInt(2));
				car.setPlate(rs.getString(3));
				return car.getCarid();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			dbManager.closeConnection();

		}
		return -1;
	}

}
