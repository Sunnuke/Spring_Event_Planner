package com.practice.events.contollers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.practice.events.models.Event;
import com.practice.events.models.User;
import com.practice.events.services.EventService;
import com.practice.events.services.UserService;

@Controller
public class EventController {
	
	@Autowired
    private EventService eventService;
	@Autowired
    private UserService userService;
	
	// Dashboard
	@RequestMapping("/events")
	public String Dashboard(@ModelAttribute("event") Event event, Model model, HttpSession session) {
		if (session.getAttribute("id") == null) {
			return "redirect:/registration";
		}
		return "/event/dashboard.jsp";
	}
	
	// Create Event
	@RequestMapping(value = "/Event", method = RequestMethod.POST)
	public String newEvent(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			return "redirect:/registration";
		} else {
			eventService.createEvent(event, userService.findUserById((Long) session.getAttribute("id")));
			return "redirect:/events";
		}	
	}
	
	// Selected Event
	@RequestMapping("/events/{id}")
	public String showEvent(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("id") == null) {
			return "redirect:/registration";
		}
		Event event = eventService.findEvent(id);
		List<User> joiners = event.getJoiners();
		model.addAttribute("event", event);
		model.addAttribute("joiners", joiners);
		return "/event/event.jsp";
	}
	
	// Edit Event page
	@RequestMapping("/events/{id}/edit")
	public String editEvent(@PathVariable("id") Long id, Model model, HttpSession session) {
		if (session.getAttribute("id") == null) {
			return "redirect:/registration";
		}
		Event event = eventService.findEvent(id);
		model.addAttribute("event", event);
		return "/event/editEvent.jsp";
	}
	
	// Edit Event
	@RequestMapping(value = "/Edit/{id}", method = RequestMethod.PUT)
	public String edit(@Valid @ModelAttribute("event") Event event, BindingResult result, HttpSession session, @PathVariable("id") Long id) {
		if (result.hasErrors()) {
			return "redirect:/registration";
		} else {
			eventService.updateEvent(event, id);
			return "redirect:/events/" + id + "/edit";
		}	
	}
}
