package ty.dao;

import java.util.List;

import ty.entity.CarBrand;

public interface CarBrandDao {

	List<CarBrand> findAllCarBrand();//查找所有的车品牌
	
	CarBrand findByCarBrandId(int carbrandid);//通过carbrandid查找对应的车辆信息
	
	CarBrand findByCarBrandtype(String txtBrand);//通过carbrandtype查找对应的车辆信息
}
