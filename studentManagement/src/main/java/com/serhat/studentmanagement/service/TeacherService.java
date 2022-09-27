package com.serhat.studentmanagement.service;


import com.serhat.studentmanagement.dto.TeacherDto;

import com.serhat.studentmanagement.entity.Teacher;

import com.serhat.studentmanagement.exception.TeacherNotFoundException;
import com.serhat.studentmanagement.repository.TeacherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    private final ModelMapper modelMapper;
    private final TeacherRepository teacherRepository;
    private RuntimeException TeacherNotFoundException;

    public TeacherService(ModelMapper modelMapper, TeacherRepository teacherRepository) {
        this.modelMapper = modelMapper;
        this.teacherRepository = teacherRepository;
    }

    public TeacherDto saveTeacher(TeacherDto teacherDto) {
        Teacher teacher = modelMapper.map(teacherDto,Teacher.class);
        return modelMapper.map(teacherRepository.save(teacher), TeacherDto.class);
    }

    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDto> teacherDtos = teachers.stream().map(teacher -> modelMapper.map(teacher,TeacherDto.class))
                .collect(Collectors.toList());
        return teacherDtos;
    }

    public TeacherDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(()-> new  TeacherNotFoundException("teacher not found" + id));
        return modelMapper.map(teacher,TeacherDto.class);
    }

    public Boolean deleteTeacher(Long id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            teacherRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public TeacherDto updateTeacher(Long id, TeacherDto teacherDto) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        if (teacher.isPresent()) {
            teacher.get().setTFirstName(teacherDto.getTFirstName());
            teacher.get().setTLastName(teacherDto.getTLastName());
            teacher.get().setCourses(teacherDto.getCourses());
            return modelMapper.map(teacherRepository.save(teacher.get()),TeacherDto.class);
        }
        throw new TeacherNotFoundException("Teacher Not Found" + id);
    }
}
