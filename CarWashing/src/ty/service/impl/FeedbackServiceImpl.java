package ty.service.impl;

import java.util.List;

import ty.dao.FeedbackDao;
import ty.dao.impl.FeedbackDaoImpl;
import ty.entity.Feedback;
import ty.service.FeedbackService;

public class FeedbackServiceImpl implements FeedbackService {

	FeedbackDao feedbackDao=new FeedbackDaoImpl();
	
	public boolean addFeedback(Feedback feedback) {
		// TODO Auto-generated method stub
		return feedbackDao.insertFeedback(feedback);
	}

	public List<Feedback> displayAllFeedback(int lastid, int pagesize) {
		// TODO Auto-generated method stub
		
		return feedbackDao.findAll(lastid, pagesize);
	}

}
