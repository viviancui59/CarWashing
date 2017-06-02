package ty.dao;

import ty.entity.Car;

public interface CarDao {

	//通过carid查找car实体
	Car findByCarId(int carid);
	
	//添加车辆信息 返回是否成功
	boolean insertCar(Car car);
	
	//通过车牌号找carid
	int findCarIdByPlate(String carplate);
	
}
