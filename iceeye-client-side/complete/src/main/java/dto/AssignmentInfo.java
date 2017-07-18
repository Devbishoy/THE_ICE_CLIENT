package dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AssignmentInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public long userId;
	
	public long id;
	
	public String title;
	
	public String body;
	
	public AssignmentInfo(long userId, long id, String title, String body) {
		super();
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.body = body;
	}	
	
	@Override
	public String toString() {
		return "AssignmentInfo [userId=" + userId + ", id=" + id + ", title=" + title + ", body=" + body + "]";
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	
}
