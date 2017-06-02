package ty.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ty.dao.GoodsDao;
import ty.dbutil.DBManager;
import ty.entity.Goods;
import ty.entity.Store;

public class GoodsDaoImpl implements GoodsDao {

	private DBManager dbManager = new DBManager();

	public boolean insertGoods(Goods goods) {
		// TODO Auto-generated method stub
		
		String sql = "insert into Goods values(null,?,?,?,?,?)";
		
		String name=goods.getName();
		double price=goods.getPrice();
		int type=goods.getType();
		String image=goods.getImage();
		String introduction=goods.getIntroduction();
		
		boolean flag=(dbManager.execUpdate(sql,name,price,type,image,introduction)>0);
		dbManager.closeConnection();
		return flag;
	}

	public boolean deleteGoods(int goodsid) {
		// TODO Auto-generated method stub
		String sql = "delete from Goods where Goods_id = ?";
		boolean flag=(dbManager.execUpdate(sql,goodsid)>0);
		dbManager.closeConnection();
		return flag;
	}

	public boolean updateGoods(Goods goods) {
		// TODO Auto-generated method stub
		String sql = "update Goods set Goods_name = ? ,Goods_price = ? , Goods_type = ? ,Goods_image= ? ,Goods_introduction= ? where Goods_id = ? ";
		
		int goodsid=goods.getGoodsid();
		String name=goods.getName();
		double price=goods.getPrice();
		int type=goods.getType();
		String image=goods.getImage();
		String introduction=goods.getIntroduction();
		
		boolean flag=(dbManager.execUpdate(sql,name,price,type,image,introduction,goodsid)>0);
		dbManager.closeConnection();
		return flag;
	}

	public List<Goods> findAllGoods() {
		// TODO Auto-generated method stub
		String sql = "select * from Goods";
		ResultSet rs = dbManager.execQuery(sql);
		List<Goods> list = new ArrayList<Goods>();
		try {
			
			while (rs.next()) {
				
				Goods goods = new Goods();

				goods.setGoodsid(rs.getInt(1));
				goods.setName(rs.getString(2));
				goods.setPrice(rs.getDouble(3));
				goods.setType(rs.getInt(4));
				goods.setImage(rs.getString(5));
				goods.setIntroduction(rs.getString(6));
				
				list.add(goods);

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

	public List<Goods> findByGoodsType(int type) {
		// TODO Auto-generated method stub
		String sql = "select * from Goods where Goods_type=?";
		ResultSet rs = dbManager.execQuery(sql,type);
		List<Goods> list = new ArrayList<Goods>();
		try {
			
			while (rs.next()) {
				
				Goods goods = new Goods();

				goods.setGoodsid(rs.getInt(1));
				goods.setName(rs.getString(2));
				goods.setPrice(rs.getDouble(3));
				goods.setType(rs.getInt(4));
				goods.setImage(rs.getString(5));
				goods.setIntroduction(rs.getString(6));
				
				list.add(goods);

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

}
