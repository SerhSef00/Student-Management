package com.serhat.studentmanagement.controller;

import com.serhat.studentmanagement.dto.StudentDto;
import com.serhat.studentmanagement.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<StudentDto>> getAllStudents(){
        List<StudentDto> studentDto = studentService.getAllStudents();
        return new ResponseEntity<>(studentDto, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable("id") Long id){
        StudentDto studentDto = studentService.getStudentById(id);
        return new ResponseEntity<>(studentDto,HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<StudentDto> saveStudent(@RequestBody StudentDto studentDto){
        StudentDto studentDtos = studentService.saveStudent(studentDto);
        return new ResponseEntity<>(studentDtos,HttpStatus.CREATED);
    }
    @DeleteMapping("/students/{id}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable("id") Long id){

        Boolean status = studentService.deleteStudent(id);
        return new ResponseEntity<>(status,HttpStatus.ACCEPTED);

    }
    @PutMapping("/students/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable("id") Long id,
                                                    @RequestBody StudentDto studentDto){
        StudentDto dto = studentService.updateStudent(id,studentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
}
