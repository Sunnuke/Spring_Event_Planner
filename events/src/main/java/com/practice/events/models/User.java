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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name="users")
public class User {
    
//	ATTRIBUTES
	
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Size(min=2, max=20, message="First Name must be at least 2 characters!")
    private String firstName;
    
    @Size(min=2, max=20, message="Last Name must be at least 2 characters!")
    private String lastName;
    
    @Size(min=5, max=20, message="Location Name must be at least 5 characters!")
    private String location;
    
    @Size(min=2, max=2, message="State must be abbreviated and 2 characters!")
    private String state;
    
	@Email(message="Email must be valid!")
    private String email;
    
    @Size(min=5, message="Password must be greater than 5 characters!")
    private String password;
    
    @Transient
    private String passwordConfirmation;
    
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;
    
    @OneToMany(mappedBy="host", fetch = FetchType.LAZY)
    private List<Event> events;
    
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Comment> comments;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "joining", 
        joinColumns = @JoinColumn(name = "user_id"), 
        inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    private List<Event> joining;
    
//	Constructors

	public User() {
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

    //  First Name
    public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Last Name
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
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
    
    // Email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	// Password
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	// Password Confirmation
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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
	// Events
	public List<Event> getEvents() {
		return events;
	}
	public void setEvents(List<Event> events) {
		this.events = events;
	}
	
	// Comments
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
  // Many to Many
	// Joining
	public List<Event> getJoining() {
		return joining;
	}
	public void setJoining(List<Event> joining) {
		this.joining = joining;
	}

	

	
}

