package com.practice.events.contollers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.practice.events.models.Comment;
import com.practice.events.services.CommentService;
import com.practice.events.services.EventService;
import com.practice.events.services.UserService;

@Controller
public class CommentController {
	
	@Autowired
    private CommentService commentService;
	@Autowired
    private EventService eventService;
	@Autowired
    private UserService userService;
	
	// Create Comment
	@RequestMapping(value = "/Event/{id}", method = RequestMethod.POST)
	public String Comment(@Valid @ModelAttribute("comment") Comment comment, BindingResult result, HttpSession session, @PathVariable("id") Long id) {
		if (result.hasErrors()) {
			return "redirect:/registration";
		} else {
			comment.setUser(userService.findUserById((Long) session.getAttribute("id")));
			comment.setEvent(eventService.findEvent(id));
			commentService.createComment(comment);
			return "redirect:/events/" + id;
		}	
	}
}
