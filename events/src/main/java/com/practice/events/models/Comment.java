package com.practice.events.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="comments")
public class Comment {
    
//	ATTRIBUTES
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
	
	@Size(min=2, max=200, message="Message must be at least 2 characters!")
    private String message;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="event_id")
    private Event event;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;

//Constructors
	
	public Comment() {
	}
	
//PRESETS
	
	@PrePersist
	protected void onCreate(){
	    this.createdAt = new Date();
	}
	@PreUpdate
	protected void onUpdate(){
	    this.updatedAt = new Date();
	}

	
//METHODS
	
	// Id
	public Long getId() {
		return id;
	}

	// Message
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
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
	// Created
	public void setUser(User user) {
		this.user = user;
	}
	public void setEvent(Event event) {
		this.event = event;
	}

	// Event
	public Event getEvent() {
		return event;
	}
	public User getUser() {
		return user;
	}

	
}