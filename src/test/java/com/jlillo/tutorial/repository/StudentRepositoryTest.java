package com.jlillo.tutorial.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.not;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import com.jlillo.tutorial.model.Student;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private StudentRepository studentRepository;

    private static final Long DUMMY_ID = 100000L;
    private static final String UPDATE_NAME = "updateName";

    //remove before each test the 2 mock student on h2 database
    @Before
    public void before() {
        Student mockStudent1 = entityManager.find(Student.class, 10001L);
        Student mockStudent2 = entityManager.find(Student.class, 10002L);
        entityManager.remove(mockStudent1);
        entityManager.remove(mockStudent2);
    }

    private Student givenStudent(String name, String passport) {
        Student student = new Student(name, passport);
        entityManager.persist(student);
        entityManager.flush();
        return student;
    }
        
    @Test
    public void whenFindByIdReturnStudent() {
        Student student = givenStudent("pepe", "545454545");
        Student studentFound = studentRepository.findById(student.getId()).get();
        assertThat(student.getId()).isEqualTo(studentFound.getId());
    }

    @Test
    public void whenFindByIdNotReturnStudent() {
        Optional<Student> studentFound = studentRepository.findById(DUMMY_ID);
        assertThat(studentFound.isEmpty()).isEqualTo(true);
    }

    @Test
    public void whenFindAllReturnStudents() {
        givenStudent("jorge", "123");
        givenStudent("alberto", "456");
        givenStudent("luis", "789");

        List<Student> students = studentRepository.findAll();
        assertThat(students.size()).isEqualTo(3);
    }

    @Test
    public void whenFindAllReturnZeroStudents() {
        List<Student> students = studentRepository.findAll();
        assertThat(students.size()).isEqualTo(0);
    }

    @Test
    public void whenDeleteByIdOk() {
        Student student = givenStudent("pepe", "545454545");
        Student found = studentRepository.findById(student.getId()).get();
        assertThat(student.getId()).isEqualTo(found.getId());

        studentRepository.deleteById(student.getId());
        Optional<Student> notFound = studentRepository.findById(student.getId());
        System.out.println(notFound.isPresent());
        assertThat(notFound.isPresent()).isEqualTo(false);
    }

    @Test
    public void whenDeleteByIdKo() {
        givenStudent("pepe", "545454545");
        List<Student> students = studentRepository.findAll();
        assertThat(students.size()).isEqualTo(1);
        try{
            studentRepository.deleteById(DUMMY_ID);
        } catch (Exception err){
            System.out.println(err);
        }


        students = studentRepository.findAll();

        assertThat(students.size()).isEqualTo(1);
    }

    @Test
    public void whenCreateStudentOk() {
        List<Student> students = studentRepository.findAll();
        assertThat(students.size()).isEqualTo(0);
        Student student = new Student("pepe", "12345");
        studentRepository.save(student);
        students = studentRepository.findAll();
        assertThat(students.size()).isEqualTo(1);
    }

    @Test
    public void whenUpdateStudent() {
        Student student = givenStudent("pepe", "545454545");
        Student studentFound = studentRepository.findById(student.getId()).get();
        studentFound.setName("updateName");
        studentRepository.save(studentFound);
        Student updateStudent = studentRepository.findById(studentFound.getId()).get();

        assertThat(updateStudent.getName()).isEqualTo(UPDATE_NAME);
    }

}