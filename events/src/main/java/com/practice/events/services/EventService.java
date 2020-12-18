package com.practice.events.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.events.models.Comment;
import com.practice.events.models.Event;
import com.practice.events.models.User;
import com.practice.events.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;
	
//	RETRIEVE
	public List<Event> allEvent(){
		return eventRepository.findAll();
	}
	
	
	public Event findEvent(Long id) {
		Optional<Event> optionalEvent = eventRepository.findById(id);
		if (optionalEvent.isPresent()) {
			return optionalEvent.get();
		}
		else {
			return null;
		}

	}
	
	// Event Comments
	public List<Comment> eventComments(Event event, Long id) {
		List<Comment> comments = event.getComments();
		return comments;
	}
	
//	CREATE
	public Event createEvent(Event event, User user) {
		event.setHost(user);
		return eventRepository.save(event);
		
	}
	
//	UPDATE
	public Event updateEvent(Event event, Long id) {
		Event eventOg = findEvent(id);
		eventOg.setName(event.getName());
		eventOg.setDate(event.getDate());
		eventOg.setLocation(event.getLocation());
		eventOg.setState(event.getState());
		eventRepository.save(eventOg);
		return eventOg;
	}
	
// Join Event
	public Event addJoiner(User user, Long id) {
		Event event = findEvent(id);
		List<User> joiners = event.getJoiners();
		joiners.add(user);
		event.setJoiners(joiners);
		return eventRepository.save(event);
	}
	
// Add Comment
	public Event addComment(Comment comment, Long id) {
		Event event = findEvent(id);
		List<Comment> comments = event.getComments();
		comments.add(comment);
		event.setComments(comments);
		return eventRepository.save(event);
	}
	
// delete event
	public void deleteEvent(Long id) {
		eventRepository.deleteById(id);
	}

}
