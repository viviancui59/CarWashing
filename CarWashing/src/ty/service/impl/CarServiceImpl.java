package ty.service.impl;

import java.util.ArrayList;
import java.util.List;

import ty.dao.CarBrandDao;
import ty.dao.impl.CarBrandDaoImpl;
import ty.entity.Car;
import ty.entity.CarBrand;
import ty.service.CarService;

public class CarServiceImpl implements CarService {

	CarBrandDao carBrandDao=new CarBrandDaoImpl();
	
	public List<Car> displayCarBrand(List<Car> carlist) {
		// TODO Auto-generated method stub
		for(Car car:carlist){
			CarBrand carBrand=carBrandDao.findByCarBrandId(car.getCarbrandid());
			car.setBrandimage(carBrand.getImage());
			car.setBrandtype(carBrand.getType());
		}
		return carlist;
	}



}
