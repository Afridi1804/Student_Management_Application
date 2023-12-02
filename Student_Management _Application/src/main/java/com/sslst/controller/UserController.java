package com.sslst.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sslst.exception.CustomersException;
import com.sslst.model.Students;
import com.sslst.model.Users;
import com.sslst.repository.StudentRepository;
import com.sslst.repository.UserRepository;
import com.sslst.service.StudentService;
import com.sslst.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
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
		
		return "profile";
		
	}
	

	
	@GetMapping("/getCustomerById/{cid}") // ✅ Working
	public ResponseEntity<Users> getCustomerById(@PathVariable int cid) throws CustomersException {

		Users cat = userService.getCustomerById(cid);
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	@PutMapping("/updateCustomer/{cid}") // ✅ Working
	public ResponseEntity<Users> updateCustomer(@PathVariable int cid, @RequestBody Users customer)
			throws CustomersException {
		Users cat = userService.updateCustomer(cid, customer);
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	@DeleteMapping("/deleteCustomer/{cid}")
	public ResponseEntity<Users> deleteCustomer(@PathVariable int cid) throws CustomersException {
		Users cat = userService.deleteCustomer(cid);
		return new ResponseEntity<>(cat, HttpStatus.OK);

	}

	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Users>> getAllCustomers() throws CustomersException {
		List<Users> list = userService.getAllCustomers();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}
	
	
	@PostMapping("/registerStudent") // ✅ Working
	public String registerCustomer(@ModelAttribute Students students) throws CustomersException {
//
//		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
//
//		customer.setRole("ROLE_" + customer.getRole().toUpperCase());

		Students cat = studentService.registerStudent(students);

		return "redirect:/user/home";
	}
	
}
