package com.serhat.studentmanagement.service;

import com.serhat.studentmanagement.dto.StudentDto;
import com.serhat.studentmanagement.entity.Student;
import com.serhat.studentmanagement.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.mockito.Mockito.mock;

public class StudentServiceTest {
    private StudentService studentService;
    private StudentRepository studentRepository;
    private  ModelMapper modelMapper;

    Calculator calculator = new Calculator();


    @BeforeEach
    public void setUp(){
        studentRepository = mock(StudentRepository.class);

    }

    @Test
    public void testFindByStudentId_whenStudentIdExists_shouldReturnStudent(){
        Student student = new Student(1L,"name","surname",12,"qwerty@",null);

        Mockito.when(studentRepository.findById(1L)).thenReturn(Optional.of(student));

        StudentDto result = studentService.getStudentById(2L);

        Assertions.assertEquals(result,student);
    }

    @Test
    public void addNumbers(){
        int numberOne = 1;
        int numberTwo = 6;
        int result = calculator.add(numberOne,numberTwo);
        int expected = 7;
        Assertions.assertEquals(7,7);
    }

    class Calculator {
        int add(int a, int b){
            return a+b;
        }
    }

}