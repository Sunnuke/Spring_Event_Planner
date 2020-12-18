package com.practice.events.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practice.events.models.Comment;
import com.practice.events.repositories.CommentRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
//	CREATE
	public Comment createComment(Comment comment) {
		return commentRepository.save(comment);
	}
}
