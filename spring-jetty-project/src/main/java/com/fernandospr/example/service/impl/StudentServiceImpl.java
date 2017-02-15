package com.fernandospr.example.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fernandospr.example.dao.StudentDao;
import com.fernandospr.example.exceptions.ResourceAlreadyExistsException;
import com.fernandospr.example.exceptions.ResourceNotFoundException;
import com.fernandospr.example.model.Student;
import com.fernandospr.example.model.Student2;
import com.fernandospr.example.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentDao;

	@Override
	public List<Student> findAll() {
		List<Student> students = new ArrayList<>();
		Student student = new Student("Pham Van Lap");
		student.setId("1");
		students.add(student);
		student = new Student("Vo Thu Thao");
		student.setId("1");
		students.add(student);
//		return studentDao.findAll();
		return students;
	}
	
	@Override
	public List<Student2> findAll2() {
		List<Student2> students = new ArrayList<>();
		Student2 student = new Student2(1, "Pham Van Lap");
		students.add(student);
		student = new Student2(2, "Vo Thu Thao");
		students.add(student);
		student = new Student2(3, "Mai Phuong Thuy");
		students.add(student);
//		return studentDao.findAll();
		return students;
	}

	@Override
	public Student find(String id) {
		Student student = studentDao.find(id);
		if (student == null) {
			throw new ResourceNotFoundException("Student with id " + id + " not found");
		}
		return student;
	}
	
	@Override
	public boolean exists(String id) {
		return studentDao.find(id) != null;
	}

	@Override
	public Student save(Student student) throws ResourceAlreadyExistsException {
		if (this.studentDao.find(student.getId()) != null) {
			throw new ResourceAlreadyExistsException("Student with id " + student.getId() + " already exists");
		}
		
		return this.studentDao.save(student);
	}

	@Override
	public Student update(String id, Student newStudent) {
    	
    	if (!id.equals(newStudent.getId()) && this.exists(newStudent.getId())) {
    		throw new ResourceAlreadyExistsException("Student with id " + newStudent.getId().toString() + " already exists");
    	}
		
		return this.studentDao.update(id, newStudent);
	}

	@Override
	public void delete(Student student) {
		this.studentDao.delete(student);
	}
}
