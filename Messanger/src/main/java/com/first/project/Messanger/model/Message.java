package com.first.project.Messanger.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Message {
		
	private long id;
	private String message;
	private Date created;
	private String author;
	private Date lastmodified;
	
	public Message(){
	}
	
	public Message(long id,String message, String author,Date created, Date lastupdated){
		this.id=id;
		this.message=message;
		this.created=created;
		this.author=author;
		this.created=created;
		this.lastmodified = lastupdated;
	}
	public long getId() {
		return id;
	}
	public  void setId(long id) {
		this.id = id;
	}
	public String getMessage() {
		return 	message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author=author;
	}
	public Date getlastmodified() {
		return lastmodified;
	}
	/*public void setlastmodified(Date lastmodified) {
		this.lastmodified=lastmodified;
	}*/
}
