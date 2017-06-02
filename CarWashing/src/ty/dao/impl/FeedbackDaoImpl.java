package ty.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import ty.dao.FeedbackDao;
import ty.dbutil.DBManager;
import ty.entity.Feedback;

public class FeedbackDaoImpl implements FeedbackDao {

	private DBManager dbManager = new DBManager();

	@Override
	public boolean insertFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		String sql = "insert into feedback values(null, ? , ? , now() )";
		
		return dbManager.execUpdate(sql, feedback.getUserid(),feedback.getContent())>0;
	}

	@Override
	public List<Feedback> findAll(int lastid, int pagesize) {
		// TODO Auto-generated method stub
		String sql = "select * from feedback where  commentid>? limit 0,?";

		  ResultSet rs= dbManager.execQuery(sql,lastid,pagesize);
		   List<Feedback> list=new ArrayList<Feedback>();
		   try {
			while(rs.next()){
				Feedback feedback =new Feedback();
				feedback.setFeedbackid((rs.getInt(1)));
				feedback.setUserid(rs.getInt(2));
				feedback.setContent(rs.getString(3));
				feedback.setTime(rs.getString(4));
				
				
			    list.add(feedback);
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
