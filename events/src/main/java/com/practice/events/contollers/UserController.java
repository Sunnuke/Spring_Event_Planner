package com.practice.events.contollers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.practice.events.models.User;
import com.practice.events.services.UserService;

@Controller
public class UserController {

	@Autowired
    private UserService userService;
	
	// Displays Forms to Register or Login
    @RequestMapping("/registration")
    public String registerForm(@ModelAttribute("user") User user) {
        return "/log/registrationPage.jsp";
    }
    @RequestMapping("/login")
    public String login() {
        return "/log/loginPage.jsp";
    }
    
    // Processing routes for Registering or Logging in
    @RequestMapping(value="/registration", method=RequestMethod.POST)
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session, RedirectAttributes redirect) {
    	// if result has errors, return the registration page (don't worry about validations just now)
    	System.out.println(result);
    	if (result.hasErrors()) {
    		List<String> errors = new ArrayList<String>();
        	errors.add(userService.validating(user, result));
        	for (Object object : result.getAllErrors()) {
        		FieldError fieldError = (FieldError) object;
    	        if(fieldError.getDefaultMessage() != null) {
    	        	errors.add(fieldError.getDefaultMessage());
    	        }
        	}
    		redirect.addFlashAttribute("errors", errors);
			return "redirect:/registration";
		} 
    	// else, save the user in the database, save the user id in session, and redirect them to the /home route
    	else {
    		User account = userService.registerUser(user);
    		Long id = account.getId();
    		session.setAttribute("id", id);
			return "redirect:/home";
		}	
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session, RedirectAttributes redirect) {
        // if the user is authenticated, save their user id in session
    	if (userService.authenticateUser(email, password)) {
    		User user = userService.findByEmail(email);
    		Long id = user.getId();
    		session.setAttribute("id", id);
			return "redirect:/home";
		}
        // else, add error messages and return the login page
    	else {
    		redirect.addFlashAttribute("errors", "User Name or Password is incorrect!");
    		return "redirect:/login";
    	}
    }
    
    // Displays a success page informing the user that they are logged in
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        // get user from session, save them in the model and return the home page
    	Long id = (Long) session.getAttribute("id");
    	User user = userService.findUserById(id);
    	model.addAttribute("user", user);
    	return "/log/homePage.jsp";
    }
    
    // Closes user's session
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        // invalidate session
    	session.invalidate();
        // redirect to login page
    	return "redirect:/login";
    }
}
