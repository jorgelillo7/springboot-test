package com.jlillo.tutorial.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.jlillo.tutorial.model.Student;

import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    private Student givenStudent(String name, String passport) {
        Student student = new Student(name, passport);
        entityManager.persist(student);
        entityManager.flush();
        return student;
    }

    @Test
    public void whenFindById() {
        Student student = givenStudent("pepe", "545454545");
        Student found = studentRepository.findById(student.getId()).get();
        assertThat(student.getId()).isEqualTo(found.getId());
    }

    @Test
    public void whenDeleteById() {
        Student student = givenStudent("pepe", "545454545");
        Student found = studentRepository.findById(student.getId()).get();
        assertThat(student.getId()).isEqualTo(found.getId());

        studentRepository.deleteById(student.getId());
        Optional<Student> notFound = studentRepository.findById(student.getId());
        System.out.println(notFound.isPresent());
        assertThat(notFound.isPresent()).isEqualTo(false);


    }

}