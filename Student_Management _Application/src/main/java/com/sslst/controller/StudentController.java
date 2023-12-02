package com.sslst.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import com.sslst.service.StudentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping("/hello")
	public String testHandler() {
		return "Welcome to Spring Security";
	}
	
	
//	@PostMapping("/registerStudent") // ✅ Working
//	public String registerCustomer(@ModelAttribute Students students) throws CustomersException {
////
////		customer.setPassword(passwordEncoder.encode(customer.getPassword()));
////
////		customer.setRole("ROLE_" + customer.getRole().toUpperCase());
//
//		Students cat = studentService.registerStudent(students);
//
//		return "redirect:/user/home";
//	}

	@GetMapping("/getStudentById/{cid}") // ✅ Working
	public ResponseEntity<Students> getCustomerById(@PathVariable int cid) throws CustomersException {

		Students cat = studentService.getStudentById(cid);
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	@PutMapping("/updateStudent/{cid}") // ✅ Working
	public ResponseEntity<Students> updateCustomer(@PathVariable int cid, @RequestBody Students customer)
			throws CustomersException {
		Students cat = studentService.updateStudent(cid, customer);
		return new ResponseEntity<>(cat, HttpStatus.OK);
	}

	@DeleteMapping("/deleteStudent/{cid}")
	public ResponseEntity<Students> deleteCustomer(@PathVariable int cid) throws CustomersException {
		Students cat = studentService.deleteStudent(cid);
		return new ResponseEntity<>(cat, HttpStatus.OK);

	}

//	@GetMapping("/getCustomerByname/{name}")
//	public ResponseEntity<List<Students>> getCustomerByname(@PathVariable String name) throws CustomersException {
//
//		List<Students> cat = customerService.getCustomerByname(name);
//		return new ResponseEntity<>(cat, HttpStatus.OK);
//
//	}

	@GetMapping("/getAllStudent")
	public ResponseEntity<List<Students>> getAllCustomers() throws CustomersException {
		List<Students> list = studentService.getAllStudent();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	
}
