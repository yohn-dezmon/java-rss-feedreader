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
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
    public void setTitle(String title) {
		this.title = title;
	}
    
    public String getSubtitle() {
    	return subtitle;
    }
    
    public void setSubtitle(String subtitle) {
    	this.subtitle = subtitle;
    }
    
    public String getLink() {
    	return link;
    }
    
    public void setLink(String link) {
    	this.link = link;
    }
    
    public String getFeed() {
    	return feed;
    }
    
    public void setFeed(String feed) {
    	this.feed = feed;
    }
	
	
	public LocalDateTime getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(LocalDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	
}