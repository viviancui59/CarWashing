package ty.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import ty.dao.OrderListDao;
import ty.dbutil.DBManager;
import ty.entity.OrderList;

public class OrderListDaoImpl implements OrderListDao {

	
	private DBManager dbManager = new DBManager();

	@Override
	public boolean insertOrder(OrderList orderlist) {
   
		String sql="insert into orderlist values(?,?,?,?,?,?,?)";
		
		return dbManager.execUpdate(sql, orderlist.getOrderid(),orderlist.getCarid(),orderlist.getStoreid(),orderlist.getUserid(),orderlist.getTime(),orderlist.getPrice(),orderlist.getType())>0;
	
	}

	@Override
	public boolean cancelOrder(String orderid) {
		String sql = "delete from orderlist where Order_id=?";
        return dbManager.execUpdate(sql, orderid)>0;
	
	}

	public List<OrderList> findFutureOrder(int userid) {
		List<OrderList> list=new ArrayList<OrderList>();
		String sql = "select * from orderlist where User_id=? and Order_time>=now()";
	    ResultSet rs = dbManager.execQuery(sql,userid);
	     try {
	    	 while(rs.next()){
				 OrderList orderlist=new OrderList();
				 orderlist.setOrderid(rs.getString(1));
				 orderlist.setCarid(rs.getInt(2));
				 orderlist.setStoreid(rs.getInt(3));
				 orderlist.setUserid(userid);
				 orderlist.setTime(rs.getString(5));

				 orderlist.setPrice(rs.getFloat(6));
				 orderlist.setType(rs.getInt(7));
				 
				 				 
				 list.add(orderlist);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
	     dbManager.closeConnection();
		}
		return list;
	}

	public List<OrderList> findHistoryOrder(int userid) {
		List<OrderList> list=new ArrayList<OrderList>();
		String sql = "select * from orderlist where User_id=? and Order_time<now()";
	    ResultSet rs = dbManager.execQuery(sql,userid);
	     try {
	    	 while(rs.next()){
	    		 
				 OrderList orderlist=new OrderList();
				 orderlist.setOrderid(rs.getString(1));
				 orderlist.setCarid(rs.getInt(2));
				 orderlist.setStoreid(rs.getInt(3));
				 orderlist.setUserid(userid);
				 orderlist.setTime(rs.getString(5));

				 orderlist.setPrice(rs.getFloat(6));
				 orderlist.setType(rs.getInt(7));
				 
				 				 
				 list.add(orderlist);
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
	public List<OrderList> findAllHistory() {
		String sql ="select * from orderlist where Order_time<now()";

		  ResultSet rs= dbManager.execQuery(sql);
		   List<OrderList> list=new ArrayList<OrderList>();
		   try {
			while(rs.next()){
				 OrderList orderlist=new OrderList();
				 orderlist.setOrderid(rs.getString(1));
				 orderlist.setCarid(rs.getInt(2));
				 orderlist.setStoreid(rs.getInt(3));
				 orderlist.setUserid(rs.getInt(4));
				 orderlist.setTime(rs.getString(5));
				 orderlist.setPrice(rs.getFloat(6));
				 orderlist.setType(rs.getInt(7));
				 
				 
				   list.add(orderlist);
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
	public List<OrderList> findAllFuture() {
		String sql ="select * from orderlist where Order_time>now()";

		  ResultSet rs= dbManager.execQuery(sql);
		   List<OrderList> list=new ArrayList<OrderList>();
		   try {
			while(rs.next()){
				 OrderList orderlist=new OrderList();
				 orderlist.setOrderid(rs.getString(1));
				 orderlist.setCarid(rs.getInt(2));
				 orderlist.setStoreid(rs.getInt(3));
				 orderlist.setUserid(rs.getInt(4));
				 orderlist.setTime(rs.getString(5));
				 orderlist.setPrice(rs.getFloat(6));
				 orderlist.setType(rs.getInt(7));
				 
				 
				   list.add(orderlist);
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		     dbManager.closeConnection();
			}
		   
			return list;
	}

}
