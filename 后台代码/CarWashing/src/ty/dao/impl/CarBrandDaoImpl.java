package ty.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ty.dao.CarBrandDao;
import ty.dbutil.DBManager;
import ty.entity.CarBrand;

public class CarBrandDaoImpl implements CarBrandDao {

	private DBManager dbManager = new DBManager();
	@Override
	public List<CarBrand> findAllCarBrand() {
		String sql ="select * from carbrand";

		  ResultSet rs= dbManager.execQuery(sql);
		   List<CarBrand> list=new ArrayList<CarBrand>();
		   try {
			while(rs.next()){
				CarBrand carbrand=new CarBrand();
				carbrand.setCarbrandid(rs.getInt(1));
				carbrand.setType(rs.getString(2));
				carbrand.setImage(rs.getString(3));

				   list.add(carbrand);
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		     dbManager.closeConnection();
			}
		   
			return list;
	}

	@Override
	public CarBrand findByCarBrandId(int carbrandid) {
		String sql = "select * from carbrand where CarBrand_id=?";
	    ResultSet rs = dbManager.execQuery(sql,carbrandid);
	     try {
			if(rs.next()){
				CarBrand carbrand=new CarBrand();
				carbrand.setCarbrandid(carbrandid);
				carbrand.setType(rs.getString(2));
				carbrand.setImage(rs.getString(3));
				
				 				 
				 return carbrand;
			 }else{
				 return null;
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
	     dbManager.closeConnection();
		}
		return null;
	}

	@Override
	public CarBrand findByCarBrandtype(String txtBrand) {
		// TODO Auto-generated method stub
		String sql = "select * from carbrand where CarBrand_type=?";
	    ResultSet rs = dbManager.execQuery(sql,txtBrand);
	     try {
			if(rs.next()){
				CarBrand carbrand=new CarBrand();
				carbrand.setCarbrandid(rs.getInt(1));
				carbrand.setType(rs.getString(2));
				carbrand.setImage(rs.getString(3));
				
				 				 
				 return carbrand;
			 }else{
				 return null;
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
	     dbManager.closeConnection();
		}
		return null;
	}

}
