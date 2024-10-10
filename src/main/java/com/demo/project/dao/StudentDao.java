package com.demo.project.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.project.model.Student;

@Repository
public interface StudentDao extends JpaRepository<Student,Long>{
	
}