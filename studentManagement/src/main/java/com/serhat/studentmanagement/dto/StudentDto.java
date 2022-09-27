package com.serhat.studentmanagement.dto;

import com.serhat.studentmanagement.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private int studentNumber;
    private String email;
    private Set<Course> courses;
}
