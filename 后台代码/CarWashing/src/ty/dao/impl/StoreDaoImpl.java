package ty.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ty.dao.StoreDao;
import ty.dbutil.DBManager;
import ty.entity.Store;

public class StoreDaoImpl implements StoreDao {

	private DBManager dbManager = new DBManager();
	
	
	public List<Store> findAllStore() {
		// TODO Auto-generated method stub
		String sql = "select * from Store";
		ResultSet rs = dbManager.execQuery(sql);
		List<Store> list = new ArrayList<Store>();
		try {
			
			while (rs.next()) {
				Store store = new Store();

				store.setStoreid(rs.getInt(1));
				store.setName(rs.getString(2));
				store.setImage(rs.getString(3));
				store.setAddress(rs.getString(4));
				store.setOpeningtime(rs.getString(5));
				store.setClosingtime(rs.getString(6));
				
				list.add(store);

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



	public boolean insertStore(Store store) {
		String sql = "insert into Store values(null,?,?,?,?,?)";

		String name=store.getName();
		String image=store.getImage();
		String address=store.getAddress();
		String openingtime=store.getOpeningtime();
		String closingtime=store.getClosingtime();
		boolean flag=(dbManager.execUpdate(sql,name,image,address,openingtime,closingtime)>0);
		dbManager.closeConnection();
		return flag;
	}

	
	public boolean deleteStore(int storeid) {
		// TODO Auto-generated method stub
		String sql = "delete from Store where Store_id = ?";
		boolean flag=(dbManager.execUpdate(sql,storeid)>0);
		dbManager.closeConnection();
		return flag;
	}

	
	public Store findByStoreId(int storeid) {
		// TODO Auto-generated method stub
		String sql = "select * from Store where Store_id=?";
		ResultSet rs = dbManager.execQuery(sql,storeid);
		Store store = null;
		try {
			if (rs.next()) {
				store = new Store();

				store.setStoreid(rs.getInt(1));
				store.setName(rs.getString(2));
				store.setImage(rs.getString(3));
				store.setAddress(rs.getString(4));
				store.setOpeningtime(rs.getString(5));
				store.setClosingtime(rs.getString(6));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			dbManager.closeConnection();

		}
		return store;
	}


}
