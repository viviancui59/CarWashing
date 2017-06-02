package ty.service;

import java.util.List;

import ty.entity.Car;

public interface CarService {

	//显示某用户的车辆信息，通过carid list返回带有brand信息的car list
	List<Car> displayCarBrand(List<Car> carlist);
	
}
