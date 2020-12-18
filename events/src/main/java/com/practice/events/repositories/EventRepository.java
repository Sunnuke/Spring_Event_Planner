package com.practice.events.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.practice.events.models.Event;


public interface EventRepository extends CrudRepository<Event, Long> {
	List<Event> findAll();
	List<Event> findByStateContaining(String state);
}
