package ty.service;

import java.util.List;

import ty.entity.Feedback;

public interface FeedbackService {

	//某用户增加一条意见反馈
	boolean addFeedback(Feedback feedback);
	
	//为管理员显示全部意见反馈
	List<Feedback> displayAllFeedback(int lastid, int pagesize);
}
