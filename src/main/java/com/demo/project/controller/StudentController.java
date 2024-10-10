package com.demo.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.model.Student;
import java.util.List;
import java.util.Optional;

import com.demo.project.dao.StudentDao;


@RestController
@RequestMapping("api")
public class StudentController{
	@Autowired
	private StudentDao studentDao;
	
	@GetMapping("students")
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> students = studentDao.findAll();
		return new ResponseEntity<>(students,HttpStatus.OK);
	}
	
	@GetMapping("students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id){
		Optional<Student> student = studentDao.findById(id);
		return student.isPresent()? new ResponseEntity<>(student.get(),HttpStatus.OK):new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("students")
	public ResponseEntity<Student> addNewStudent(@RequestBody Student student){
		Student savedStudent = studentDao.save(student);
		return new ResponseEntity<>(savedStudent,HttpStatus.OK);
	}
	
	@PutMapping("students/{id}")
	public ResponseEntity<Student> updateStudentById(@PathVariable Long id , @RequestBody Student student) {
		Optional<Student> optionalStudent = studentDao.findById(id);
		if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();
            
            // Update the fields of the existing student with the details from the request body
            existingStudent.setName(student.getName());
            existingStudent.setDob(student.getDob());
            existingStudent.setPhone(student.getPhone());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setCourseName(student.getCourseName());
            
            // Save the updated student back to the database
            Student updatedStudent = studentDao.save(existingStudent);
            
            // Return the updated student
            return new ResponseEntity<>(updatedStudent,HttpStatus.OK);
        }
		// If the student with the given id doesn't exist, return 404 Not Found
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("students/{id}")
	public ResponseEntity<Student> deleteStudentById(@PathVariable Long id) {
		Optional<Student> optionalStudent = studentDao.findById(id);
		if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            
            //delete the student
            studentDao.deleteById(id);
          
            // Return the deleted student
            return new ResponseEntity<>(student,HttpStatus.OK);
        }
		// If the student with the given id doesn't exist, return 404 Not Found
		return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
	}
	
	
}