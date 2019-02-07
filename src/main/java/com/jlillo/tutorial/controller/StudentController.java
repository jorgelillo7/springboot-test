package com.jlillo.tutorial.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jlillo.tutorial.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jlillo.tutorial.repository.StudentRepository;
import com.jlillo.tutorial.exception.NotFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final static  Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/{id}")
    public Student getStudentbyId(@PathVariable long id) {
        LOGGER.info(String.format("Start getStudentbyId: %s", id));
        Optional<Student> student = studentRepository.findById(id);
        if (!student.isPresent()) {
            throw new NotFoundException(String.format("Student with id: %s not found", id));
        }

        return student.get();
    }

    @GetMapping()
    public List<Student> retrieveAllStudents() {
        LOGGER.info("Start retrieveAllStudents");
        return studentRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        LOGGER.info(String.format("Start deleteStudent: %s", id));
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent()){
            throw new NotFoundException(String.format("Student with id: %s not found", id));
        }
        studentRepository.deleteById(id);
    }

    @PostMapping("")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        LOGGER.info("Start createStudent");
        Student savedStudent = studentRepository.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {
        LOGGER.info(String.format("Start updateStudent: %s", id));
        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent()){
            throw new NotFoundException(String.format("Student with id: %s not found", id));
        }

        studentRepository.save(student);

        return ResponseEntity.noContent().build();
    }
}