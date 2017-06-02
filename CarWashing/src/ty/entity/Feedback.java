package ty.entity;

import java.io.Serializable;
public class Feedback {
   private int feedbackid;
   private int userid;
   private  String content;
   private  String time;
public int getFeedbackid() {
	return feedbackid;
}
public void setFeedbackid(int feedbackid) {
	this.feedbackid = feedbackid;
}
public int getUserid() {
	return userid;
}
public void setUserid(int userid) {
	this.userid = userid;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
@Override
public String toString() {
	return "Feedback [feedbackid=" + feedbackid + ", userid=" + userid
			+ ", content=" + content + ", time=" + time + "]";
}
   
   

}
