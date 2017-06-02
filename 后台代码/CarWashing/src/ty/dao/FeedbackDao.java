package ty.dao;

import java.util.List;

import ty.entity.Car;
import ty.entity.Feedback;
import ty.entity.User;

public interface FeedbackDao {
   
	 
		//插入新用户 
		boolean insertFeedback(Feedback feedback);
		
		//返回所有用户反馈意见
		List<Feedback> findAll(int lastid,int pagesize);
		//返回特定的反馈意见
	 //  Feedback	  findByFeedbackId(int feedbackid);
	   
}
