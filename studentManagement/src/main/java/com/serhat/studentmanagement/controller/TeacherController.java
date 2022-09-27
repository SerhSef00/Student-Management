package com.serhat.studentmanagement.controller;

import com.serhat.studentmanagement.dto.StudentDto;
import com.serhat.studentmanagement.dto.TeacherDto;
import com.serhat.studentmanagement.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TeacherController {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/teachers")
    public ResponseEntity<TeacherDto> saveTeacher(@RequestBody TeacherDto teacherDto) {
        TeacherDto teacherDto1 = teacherService.saveTeacher(teacherDto);
        return new ResponseEntity<>(teacherDto1, HttpStatus.CREATED);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> teacherDtos = teacherService.getAllTeachers();
        return new ResponseEntity<>(teacherDtos, HttpStatus.OK);
    }

    @GetMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(@PathVariable("id") Long id) {
        TeacherDto teacherDto = teacherService.getTeacherById(id);
        return new ResponseEntity<>(teacherDto, HttpStatus.OK);
    }

    @DeleteMapping("/teachers/{id}")
    public ResponseEntity<Boolean> deleteTeacher(@PathVariable("id") Long id) {

        Boolean status = teacherService.deleteTeacher(id);
        return new ResponseEntity<>(status, HttpStatus.ACCEPTED);

    }

    @PutMapping("/teachers/{id}")
    public ResponseEntity<TeacherDto> updateTeacher(@PathVariable("id") Long id,
                                                    @RequestBody TeacherDto teacherDto) {
        TeacherDto dto = teacherService.updateTeacher(id, teacherDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}