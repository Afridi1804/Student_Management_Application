package com.sslst.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sslst.model.Users;
import com.sslst.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	
	@Autowired
	private UserRepository userRepository;
	
	@ModelAttribute
	public void CommonUser(Principal p,Model m) {
		
		if(p != null) {
			
			String email = p.getName();
			
			Users user = userRepository.findByEmail(email);
			
			m.addAttribute("user", user);
		}
		
		
	}
	
	@GetMapping("/profile")
	public String profile() {
		
		return "admin_profile";
		
	}
	
	
	@GetMapping("/home")
	public String home() {
		
		return "home";
		
	}
	
}
