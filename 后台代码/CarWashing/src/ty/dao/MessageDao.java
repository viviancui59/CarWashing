package ty.dao;

import java.util.List;

import ty.entity.Message;
import ty.entity.OrderList;


public interface MessageDao {

	boolean insertMessage(Message message);//添加一条消息
	boolean deleteMessage(int messageid);//用户删除消息
	
	List<Message> findByUserId(int userid);//在用户界面中显示出发给这个用户的所有消息
	
	Message findByMessageId(int messageid);//根据messageid返回一条消息的所有信息
	boolean checkMessage(int messageid);
}
