package com.jdes.rssfeed.model;

import javax.persistence.*;


import java.time.LocalDateTime;




@Entity
@Table(name = "source")
public class Source {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "title", columnDefinition="LONGTEXT")
	private String title;
	
	@Column(name = "subtitle", columnDefinition="LONGTEXT")
	private String subtitle;
	
	@Column(name = "link", columnDefinition="LONGTEXT")
	private String link;
	
	@Column(name = "feed", columnDefinition="LONGTEXT")
	private String feed;
	
	@Column(name = "date_added", columnDefinition="DATETIME")
	private LocalDateTime dateAdded;
	
	
	public synchronized int getId() {
		return id;
	}
	public synchronized void setId(int id) {
		this.id = id;
	}
	
	public synchronized String getTitle() {
		return title;
	}
	
    public synchronized void setTitle(String title) {
		this.title = title;
	}
    
    public synchronized String getSubtitle() {
    	return subtitle;
    }
    
    public synchronized void setSubtitle(String subtitle) {
    	this.subtitle = subtitle;
    }
    
    public synchronized String getLink() {
    	return link;
    }
    
    public synchronized void setLink(String link) {
    	this.link = link;
    }
    
    public synchronized String getFeed() {
    	return feed;
    }
    
    public synchronized void setFeed(String feed) {
    	this.feed = feed;
    }
	
	
	public synchronized LocalDateTime getDateAdded() {
		return dateAdded;
	}
	
	public synchronized void setDateAdded(LocalDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	
}