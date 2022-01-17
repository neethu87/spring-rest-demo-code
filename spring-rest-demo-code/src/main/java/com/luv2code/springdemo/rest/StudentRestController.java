package com.luv2code.springdemo.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luv2code.springdemo.entity.Student;

@RestController
@RequestMapping("/api")
public class StudentRestController {
	private List<Student> studList;
	
	//define @PostConstruct to load the student data --- 	//only once when the bean is constructed
	@PostConstruct
	public void loadData() {
		studList = new ArrayList<>();
		studList.add(new Student("Jerin","Chacko"));
		studList.add(new Student("Aaren","Jerin"));
		studList.add(new Student("Nia","Jerin"));
	}
	@GetMapping("/students")
	public List<Student> getStudents(){
	//just return it as the list is populated when  the bean is created thru @PostConstruct
	return studList;
}
	@GetMapping("students/{studentId}")
	public Student getStudent(@PathVariable int studentId) {
		
		if((studentId >= studList.size()) || ( studentId < 0)) {
			throw new StudentNotFoundException("Student Id not Found");
		}
		//here to keep it simple, we will use index as student id
		Student s = studList.get(studentId);		
		return s;
	}
	
}
