package ty.service;

import java.util.List;

import ty.entity.Message;

public interface MessageService {

	//显示某用户的所有消息
	List<Message> displayMessageList(int userid);
	
	//更改用户对某一条消息的已读状态
	boolean readMessage(int messageid);
	
	//为某用户添加一条消息
	boolean addMessage(Message message);
	
	//用户删除他的某条消息，不再显示
	boolean deleteMessage(int messageid);
}
