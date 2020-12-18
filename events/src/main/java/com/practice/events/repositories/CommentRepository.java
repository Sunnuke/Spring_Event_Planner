package com.practice.events.repositories;

import org.springframework.data.repository.CrudRepository;

import com.practice.events.models.Comment;

public interface CommentRepository extends CrudRepository< Comment, Long> {
	
}
