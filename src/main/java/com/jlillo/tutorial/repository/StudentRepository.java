package com.jlillo.tutorial.repository;


import com.jlillo.tutorial.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{

}
