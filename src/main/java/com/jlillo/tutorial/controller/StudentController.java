package com.jlillo.tutorial.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jlillo.tutorial.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jlillo.tutorial.repository.StudentRepository;
import com.jlillo.tutorial.exception.StudentNotFoundException;
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

    @GetMapping("/hello")
    public String sayHello(@RequestParam(value = "name") String name) {
        return "Hello " + name + "!";
    }

    @GetMapping("/{studentId}")
    public Student getStudentbyId(@PathVariable long studentId) {
        LOGGER.info("Start getStudentbyId: " + studentId);
        Optional<Student> student = studentRepository.findById(studentId);
        if (!student.isPresent()) {
            throw new StudentNotFoundException("id-" + studentId);
        }

        return student.get();
    }

    @GetMapping()
    public List<Student> retrieveAllStudents() {
        return (List<Student>) studentRepository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable long id) {
        studentRepository.deleteById(id);
    }

    @PostMapping("")
    public ResponseEntity<Object> createStudent(@RequestBody Student student) {
        Student savedStudent = studentRepository.save(student);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedStudent.getId()).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

        Optional<Student> studentOptional = studentRepository.findById(id);

        if (!studentOptional.isPresent())
            return ResponseEntity.notFound().build();



        studentRepository.save(student);

        return ResponseEntity.noContent().build();
    }
}