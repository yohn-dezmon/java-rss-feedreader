package com.jdes.rssfeed.model;


import javax.persistence.*;

//import org.springframework.data.annotation.CreatedDate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;




@Entity
@Table(name = "article")
// this allows for date_added to work properly
@EntityListeners(AuditingEntityListener.class)
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	// I think (and hope) that the name parameter refers to what the 
	// column is named in the DB... but i really am not sure!
	@Column(name = "article_id")
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "body")
	private String body;
	
	@Column(name = "link")
	private String link;
	
	@Column(name = "guid")
	private String guid;
	
	// Not sure if the boolean -> tinyint(1) will translate... 
	@Column(name = "unread")
	private boolean unread = true;
	
	@ManyToOne(fetch = FetchType.LAZY)
	// does the name parameter refer to the spelling of the column in the MySQL DB or in the model?
	@JoinColumn(name = "source_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Source source;
	
	@Column(name = "date_added", columnDefinition="DATETIME")
	// does this do what I think it does? (mark the time that it is inserted?)
	private LocalDateTime dateAdded;
	
	@Column(name = "date_published", columnDefinition="DATETIME", updatable= false)
	private LocalDateTime datePublished;
	
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
    
    public String getBody() {
    	return body;
    }
    
    public void setBody(String body) {
    	this.body = body;
    }
    
    public String getLink() {
    	return link;
    }
    
    public void setLink(String link) {
    	this.link = link;
    }
    
    public String getGuid() {
    	return guid;
    }
    
    public void setGuid(String guid) {
    	this.guid = guid;
    }
    
	public boolean getUnread() {
		return unread;
	}
	
	public void setUnread(boolean unread) {
		this.unread = unread;
	}
	
	// Do I need a get/set for the foreign key? yes...
	
	public Source getSourceId() {
    	return source;
    }
    
	// I might need to change thise to source.id
	// hmmm I don't think so, the @JoinColumn should handle that! :D 
    public void setSourceId(Source source) {
    	this.source = source;
    }
	
	public LocalDateTime getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(LocalDateTime dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public LocalDateTime getDatePublished() {
		return datePublished;
	}
	
	public void setDatePublished(LocalDateTime datePublished) {
		this.datePublished = datePublished;
	}	
	

}
