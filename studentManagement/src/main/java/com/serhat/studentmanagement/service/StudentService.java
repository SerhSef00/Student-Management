package com.serhat.studentmanagement.service;

import com.serhat.studentmanagement.dto.StudentDto;
import com.serhat.studentmanagement.entity.Student;
import com.serhat.studentmanagement.exception.StudentNotFoundException;
import com.serhat.studentmanagement.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;
    private RuntimeException StudentNotFoundException;

    public StudentService(StudentRepository studentRepository, ModelMapper modelMapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = modelMapper;
    }

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = students.stream().map(student -> modelMapper.map(student,StudentDto.class))
                .collect(Collectors.toList());
        return studentDtos;

    }


    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(()-> new StudentNotFoundException("student not found" + id));
        return modelMapper.map(student,StudentDto.class);
    }


    public StudentDto saveStudent(StudentDto studentDto) {
        Student student = modelMapper.map(studentDto,Student.class);
        return modelMapper.map(studentRepository.save(student),StudentDto.class);
    }

    public Boolean deleteStudent(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            student.get().setStudentNumber(studentDto.getStudentNumber());
            student.get().setEmail(studentDto.getEmail());
            student.get().setFirstName(studentDto.getFirstName());
            student.get().setLastName(studentDto.getLastName());
            student.get().setCourses(studentDto.getCourses());
            return modelMapper.map(studentRepository.save(student.get()),StudentDto.class);
        }
        throw new StudentNotFoundException("student not found" + id);
    }
}
