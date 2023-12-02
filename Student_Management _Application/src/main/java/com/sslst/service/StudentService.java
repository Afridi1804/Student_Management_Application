package com.sslst.service;

import java.util.List;

import com.sslst.exception.CustomersException;
import com.sslst.model.Students;
import com.sslst.model.Users;

public interface StudentService {

	public Students registerStudent(Students students);
	
	public Students getStudentById(int sid) throws CustomersException;

	public List<Students> getAllStudent() throws CustomersException;

	public Students updateStudent(int sid, Students students) throws CustomersException;

	public Students deleteStudent(int sid) throws CustomersException;
	
}
