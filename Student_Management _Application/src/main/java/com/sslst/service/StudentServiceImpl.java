package com.sslst.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sslst.exception.CustomersException;
import com.sslst.model.Students;
import com.sslst.model.Users;
import com.sslst.repository.StudentRepository;
import com.sslst.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	public StudentRepository studentRepository;
	
	@Override
	public Students registerStudent(Students student) {
		
//		String encodedPassword = passwordEncoder.encode(user.getPassword());
		
//		user.setPassword(encodedPassword);
//		user.setRole("ROLE_USER");
		Students newuser = studentRepository.save(student);
		
		return newuser;
	}

//	@Override
//	public void removeSessionMessage() {
//		
//		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
//		
//		session.removeAttribute("msg");
//	}

	@Override
	public Students getStudentById(int cid) throws CustomersException {

		Optional<Students> customer = studentRepository.findById(cid);

		if (customer.isEmpty()) {
			throw new CustomersException("Customer doesn't exists with id " + cid);
		}

		return customer.get();
	}

	@Override
	public List<Students> getAllStudent() throws CustomersException {

		List<Students> list = studentRepository.findAll();

		if (list.isEmpty()) {
			throw new CustomersException("No customer found");
		}

		return list;
	}

	@Override
	public Students updateStudent(int cid, Students student) throws CustomersException {

		Optional<Students> customers = studentRepository.findById(cid);

		if (customers.isEmpty()) {
			throw new CustomersException("Customer doesn't exists with id " + cid);
		}

		Students custo = customers.get();

		custo.setFirstname(student.getFirstname());
		custo.setLastname(student.getLastname());
		custo.setAge(student.getAge());
		custo.setGender(student.getGender());
		custo.setAddress(student.getAddress());
		custo.setEmail(student.getEmail());
		custo.setPhone(student.getPhone());
		custo.setPassword(student.getPassword());
	

		return studentRepository.save(custo);
	}

	@Override
	public Students deleteStudent(int cid) throws CustomersException {

		Optional<Students> customer = studentRepository.findById(cid);

		if (customer.isEmpty()) {
			throw new CustomersException("No customer exists with id " + cid);
		}

		studentRepository.delete(customer.get());

		return customer.get();
	}
	
}
