package com.tweetapp.mytweetapp.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "tweet")
public class Tweet {

	@Id
	private String id;
	private String description;
	private String userId;
	//private Date createdOn;

	public Tweet() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
//
//	public Date getCreatedOn() {
//		return createdOn;
//	}
//
//	public void setCreatedOn(Date createdOn) {
//		this.createdOn = createdOn;
//	}

//	@Override
//	public String toString() {
//		return "Tweet [id=" + id + ", description=" + description + ", userId=" + userId + ", createdOn=" + createdOn
//				+ "]";
//	}
}
