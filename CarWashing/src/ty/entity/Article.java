package ty.entity;

import java.io.Serializable;

public class Article implements Serializable {

	private int articleid;
	private String title;
	private String content;
	private String author;
	private String time;
	public int getArticleid() {
		return articleid;
	}
	public void setArticleid(int articleid) {
		this.articleid = articleid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "Article [articleid=" + articleid + ", title=" + title
				+ ", content=" + content + ", author=" + author + ", time="
				+ time + "]";
	}
	
}
