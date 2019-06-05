package com.jdes.rssfeed.model;


import javax.persistence.*;



import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;




@Entity
@Table(name = "article")
// this allows for date_added to work properly
@EntityListeners(AuditingEntityListener.class)
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	// the name parameter refers to the name of the column in the DB
	// the private int id refers to the name within the hibernate model
	@Column(name = "article_id")
	private int id;
	
	@Column(name = "title", columnDefinition="LONGTEXT")
	private String title;
	
	@Column(name = "body", columnDefinition="LONGTEXT")
	private String body;
	
	@Column(name = "link", columnDefinition="LONGTEXT")
	private String link;
	
	@Column(name = "guid", columnDefinition="LONGTEXT", unique= true)
	private String guid;
	
	@Column(name = "unread", columnDefinition="int default 1")
	private int unread;
	
	// foreign key!
	@ManyToOne(fetch = FetchType.LAZY)
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
    
	public int getUnread() {
		return unread;
	}
	
	public void setUnread(int unread) {
		this.unread = unread;
	}
	
	// Do I need a get/set for the foreign key? yes...
	
	public Source getSourceId() {
    	return source;
    }
    
	// I might need to change thise to source.id
	// I don't think so, the @JoinColumn should handle that! :D 
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
