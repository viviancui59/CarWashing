package ty.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ty.dao.MessageDao;
import ty.dbutil.DBManager;
import ty.entity.Message;

public class MessageDaoImpl implements MessageDao {

	private DBManager dbManager = new DBManager();
	@Override
	public boolean insertMessage(Message message) {

		String sql="insert into message values(null,?,?,?,now(),1)";//1代表未读消息
		
		return dbManager.execUpdate(sql,message.getUserid(),message.getType(),message.getContent())>0;
	
	}

	@Override
	public boolean deleteMessage(int messageid) {
		String sql = "delete from message where Message_id=?";
        return dbManager.execUpdate(sql, messageid)>0;
	}

	@Override
	public List<Message> findByUserId(int userid) {
		String sql ="select * from message where User_id=?";

		  ResultSet rs= dbManager.execQuery(sql,userid);
		   List<Message> list=new ArrayList<Message>();
		   try {
			while(rs.next()){
				 Message message=new Message();
				 message.setMessageid(rs.getInt(1));
				 message.setUserid(userid);
				 message.setType(rs.getInt(3));
				 message.setContent(rs.getString(4));
				 message.setTime(rs.getString(5));
				 message.setReadtag(rs.getInt(6));
				 
				 
				   list.add(message);
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
	public Message findByMessageId(int messageid) {
		String sql = "select * from message where Message_id=?";
	    ResultSet rs = dbManager.execQuery(sql,messageid);
	     try {
			if(rs.next()){
				 Message message=new Message();
				 message.setMessageid(messageid);
				 message.setUserid(rs.getInt(2));
				 message.setType(rs.getInt(3));
				 message.setContent(rs.getString(4));
				 message.setTime(rs.getString(5));
				 message.setReadtag(rs.getInt(6));
				 				 
				 return message;
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
	public boolean checkMessage(int messageid) {

		String sql="update message set Message_readtag=2 where 	Message_id=?";//2代表消息已读
		
		return dbManager.execUpdate(sql,messageid)>0;
	
	}

}
