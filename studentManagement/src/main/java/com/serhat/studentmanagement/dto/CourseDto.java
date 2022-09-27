package com.serhat.studentmanagement.dto;

import com.serhat.studentmanagement.entity.Student;
import com.serhat.studentmanagement.entity.Teacher;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseDto {
    private Long id;
    private String courseName;
    private Teacher teacher;
//    private Set<Student> students;
}
