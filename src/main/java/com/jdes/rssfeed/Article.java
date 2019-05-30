package com.jdes.rssfeed;


import javax.persistence.*;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;



@Entity
@Table(name = "article")
// this allows for date_added to work properly
@EntityListeners(AuditingEntityListener.class)
public class Article {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id")
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
	
	@ManyToOne
	@JoinColumn(name = "source_id")
	private Source source;
	
	@Column(name = "date_added", columnDefinition="DATETIME", nullable = false, updatable = false)
	// does this do what I think it does? (mark the time that it is inserted?)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate 
	private Date dateAdded;
	
	@Column(name = "date_published", columnDefinition="DATETIME")
	private Date datePublished;
	
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
	
	// Do I need a get/set for the foreign key?
	
	public Date getDateAdded() {
		return dateAdded;
	}
	
	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}
	
	public Date getDatePublished() {
		return datePublished;
	}
	
	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}
	
	
	
	
	

}
