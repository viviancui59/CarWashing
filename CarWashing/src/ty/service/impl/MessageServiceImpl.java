package ty.service.impl;

import java.util.List;

import ty.dao.MessageDao;
import ty.dao.impl.MessageDaoImpl;
import ty.entity.Message;
import ty.service.MessageService;

public class MessageServiceImpl implements MessageService {

	private MessageDao messageDao = new MessageDaoImpl();
	
	public List<Message> displayMessageList(int userid) {
		// TODO Auto-generated method stub
		return messageDao.findByUserId(userid);
	}

	public boolean readMessage(int messageid) {
		// TODO Auto-generated method stub
		return messageDao.checkMessage(messageid);
	}

	public boolean addMessage(Message message) {
		// TODO Auto-generated method stub
		return messageDao.insertMessage(message);
	}

	public boolean deleteMessage(int messageid) {
		// TODO Auto-generated method stub
		return messageDao.deleteMessage(messageid);
	}

}
