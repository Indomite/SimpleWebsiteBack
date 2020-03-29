package com.homyit.entity;

import java.sql.Timestamp;

public class MessageEntity {
    private int messageid;
    private String content;
    private String author;
    private Timestamp create_time;
    public int getMessageid() {
        return messageid;
    }
    public void setMessageid(int messageid) {
        this.messageid = messageid;
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
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
}