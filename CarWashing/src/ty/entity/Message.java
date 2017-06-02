package ty.entity;

import java.io.Serializable;

public class Message implements Serializable {

	private int messageid;
	private int userid;
	private int type;
	private String content;
	private String time;
	private int readtag;
	public int getMessageid() {
		return messageid;
	}
	public void setMessageid(int messageid) {
		this.messageid = messageid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public int getReadtag() {
		return readtag;
	}
	public void setReadtag(int readtag) {
		this.readtag = readtag;
	}
	@Override
	public String toString() {
		return "Message [messageid=" + messageid + ", userid=" + userid
				+ ", type=" + type + ", content=" + content + ", time=" + time
				+ ", readtag=" + readtag + "]";
	}
	
}
