package com.serhat.studentmanagement.service;

import com.serhat.studentmanagement.dto.CourseDto;
import com.serhat.studentmanagement.dto.StudentDto;
import com.serhat.studentmanagement.entity.Course;
import com.serhat.studentmanagement.entity.Student;
import com.serhat.studentmanagement.exception.CustomerNotFoundException;
import com.serhat.studentmanagement.repository.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;
    private RuntimeException CustomerNotFoundException;

    public CourseService(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    public CourseDto saveCourse(CourseDto courseDto) {
        Course course = modelMapper.map(courseDto,Course.class);
        return modelMapper.map(courseRepository.save(course), CourseDto.class);
    }

    public List<CourseDto> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<CourseDto> courseDtos = courses.stream().map(course -> modelMapper.map(course,CourseDto.class))
                .collect(Collectors.toList());
        return courseDtos;
    }

    public CourseDto getCoursesById(Long id) {
        Course course = courseRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Course Not Found" + id));
        return modelMapper.map(course,CourseDto.class);
    }

    public Boolean deleteCourses(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            courseRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public CourseDto updateCourses(Long id, CourseDto courseDto) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            course.get().setCourseName(courseDto.getCourseName());
//            course.get().setStudents(courseDto.getStudents());
            course.get().setTeacher(courseDto.getTeacher());
            return modelMapper.map(courseRepository.save(course.get()),CourseDto.class);
        }
        throw new CustomerNotFoundException("Course not found" + id);
    }
}
