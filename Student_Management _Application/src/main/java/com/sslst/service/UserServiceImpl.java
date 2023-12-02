package com.sslst.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sslst.exception.CustomersException;
import com.sslst.model.Users;
import com.sslst.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public Users registerUser(Users user) {
		
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		
		user.setPassword(encodedPassword);
		user.setRole("ROLE_USER");
		Users newuser = userRepository.save(user);
		
		return newuser;
	}

	@Override
	public void removeSessionMessage() {
		
		HttpSession session = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest().getSession();
		
		session.removeAttribute("msg");
	}

	@Override
	public Users getCustomerById(int cid) throws CustomersException {

		Optional<Users> customer = userRepository.findById(cid);

		if (customer.isEmpty()) {
			throw new CustomersException("Customer doesn't exists with id " + cid);
		}

		return customer.get();
	}

	@Override
	public List<Users> getAllCustomers() throws CustomersException {

		List<Users> list = userRepository.findAll();

		if (list.isEmpty()) {
			throw new CustomersException("No customer found");
		}

		return list;
	}

	@Override
	public Users updateCustomer(int cid, Users customer) throws CustomersException {

		Optional<Users> customers = userRepository.findById(cid);

		if (customers.isEmpty()) {
			throw new CustomersException("Customer doesn't exists with id " + cid);
		}

		Users custo = customers.get();

		custo.setFirstname(customer.getFirstname());
		custo.setLastname(customer.getLastname());
		custo.setAge(customer.getAge());
		custo.setGender(customer.getGender());
		custo.setAddress(customer.getAddress());
		custo.setEmail(customer.getEmail());
		custo.setPhone(customer.getPhone());
		custo.setPassword(customer.getPassword());
	

		return userRepository.save(custo);
	}

	@Override
	public Users deleteCustomer(int cid) throws CustomersException {

		Optional<Users> customer = userRepository.findById(cid);

		if (customer.isEmpty()) {
			throw new CustomersException("No customer exists with id " + cid);
		}

		userRepository.delete(customer.get());

		return customer.get();
	}

}
