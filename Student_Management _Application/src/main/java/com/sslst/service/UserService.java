package com.sslst.service;

import com.sslst.exception.CustomersException;
import com.sslst.model.Users;
import java.util.*;

public interface UserService {

	public Users registerUser(Users user);
	
	public void removeSessionMessage();
	
	public Users getCustomerById(int cid) throws CustomersException;

	public List<Users> getAllCustomers() throws CustomersException;

	public Users updateCustomer(int cid, Users customer) throws CustomersException;

	public Users deleteCustomer(int cid) throws CustomersException;

	
}
