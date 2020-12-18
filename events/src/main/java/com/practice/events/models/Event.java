package com.practice.events.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="events")
public class Event {
    
//	ATTRIBUTES
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Size(min=5, max=20, message="Event Name must be at least 2 characters!")
    private String name;
    
    private String date;
    
    @Size(min=5, max=20, message="Location Name must be at least 5 characters!")
    private String location;
    
    @Size(min=2, max=2, message="State must be abbreviated and 2 characters!")
    private String state;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User host;

	@OneToMany(mappedBy="event", fetch = FetchType.LAZY)
    private List<Comment> comments;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "joining", 
        joinColumns = @JoinColumn(name = "event_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> joiners;
    
//	Constructors
    
    public Event() {
    }
    
//	PRESETS
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }

//	METHODS
    
	// Id
	public Long getId() {
		return id;
	}

	// Name
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	// Date
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	// Location
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	// State
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	// Date/Time
	public Date getCreatedAt() {
		return createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}

// 	Relationship
  // One to Many
	// Creator
	public User getHost() {
		return host;
	}
	public void setHost(User host) {
		this.host = host;
	}

	// Comments
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

  // Many to Many
	// Joiners
	public List<User> getJoiners() {
		return joiners;
	}

	public void setJoiners(List<User> joiners) {
		this.joiners = joiners;
	}
    
}
