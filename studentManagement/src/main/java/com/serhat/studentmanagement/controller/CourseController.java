package com.serhat.studentmanagement.controller;

import com.serhat.studentmanagement.dto.CourseDto;
import com.serhat.studentmanagement.dto.StudentDto;
import com.serhat.studentmanagement.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping("/courses")
    public ResponseEntity<CourseDto> saveCourse(@RequestBody CourseDto courseDto) {
        CourseDto courseDtos = courseService.saveCourse(courseDto);
        return new ResponseEntity<>(courseDtos, HttpStatus.CREATED);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        List<CourseDto> courseDtos = courseService.getAllCourses();
        return new ResponseEntity<>(courseDtos, HttpStatus.OK);
    }

    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDto> getCoursesById(@PathVariable("id") Long id) {
        CourseDto courseDto = courseService.getCoursesById(id);
        return new ResponseEntity<>(courseDto, HttpStatus.OK);
    }

    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Boolean> deleteCourses(@PathVariable("id") Long id) {

        Boolean status = courseService.deleteCourses(id);
        return new ResponseEntity<>(status, HttpStatus.ACCEPTED);

    }

    @PutMapping("/courses/{id}")
    public ResponseEntity<CourseDto> updateCourses(@PathVariable("id") Long id,
                                                   @RequestBody CourseDto courseDto) {
        CourseDto dto = courseService.updateCourses(id, courseDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
