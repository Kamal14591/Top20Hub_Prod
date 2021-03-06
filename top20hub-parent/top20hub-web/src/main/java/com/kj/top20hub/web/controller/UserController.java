package com.kj.top20hub.web.controller;

import java.net.URI;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kj.top20hub.Exception.UserNotFoundException;
import com.kj.top20hub.bo.ContactUsBO;
import com.kj.top20hub.bo.UserBO;
import com.kj.top20hub.dto.ContactUs;
import com.kj.top20hub.dto.User;
import com.kj.top20hub.dto.UserDetails;
import com.kj.top20hub.dto.UserInterest;

@RestController
@RequestMapping("/users")
public class UserController {

	private static final Logger log = Logger.getLogger(UserController.class);
	
	@Autowired
	private UserBO userService;

	@Autowired
	private ContactUsBO contactUsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@GetMapping(value = "/")
	public List<User> listUsers() {
		log.info("Get all users");
		List<User> users = userService.getAllUsers();
		return users;
	}

	@GetMapping(value = "/{id}")
	public User getUser(@PathVariable int id) {
		User user = userService.getUser(id);
		
		if(user == null)
			throw new UserNotFoundException("Id Not found");
		
		return user;
	}

	@PostMapping(value = "/")
	public ResponseEntity<Object> creatUser(@RequestBody User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		int result = userService.createUser(user);
		URI location =  ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(result).toUri();
		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/updateUser")
	public void updateUser(@RequestBody User user) {
	//	int id = user.getUser_id();
		userService.updateUser(user);
	}

	@DeleteMapping(value = "/{id}")
	public void deleteUser(@PathVariable("id") int id) {
		User user = userService.deleteUser(id);
		
		if(user == null)
			throw new UserNotFoundException("User not found");
		
	}

	@RequestMapping("/contactUs")
	public String contactUs(Model theModel) {
		ContactUs contactUs = new ContactUs();
		theModel.addAttribute("contactUs", contactUs);
		return "contact_page";
	}

	@RequestMapping(value = "/contactUsMessages")
	public String contactUsMessages() {

		return "contactUsMessages";
	}

	
	
	@GetMapping(value = "/contactus/messages")
	public List<ContactUs> contactUsMsg() {

		List<ContactUs> contactUs = contactUsService.getAllMessages();
		return contactUs;

	}
	
	@PostMapping("/saveContactUs")
	public int createContactUs(@RequestBody ContactUs contactUs) {
		int result = contactUsService.create(contactUs);

		return result;
	}
	
	
	@GetMapping("/getUsername")
	public String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		log.info("Current User is : " + username);

		if (username.equalsIgnoreCase("anonymousUser"))
			return "Guest";
		else
			return username;

	}

}
