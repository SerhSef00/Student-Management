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
public class TeacherDto {
    private Long id;
    private String tFirstName;
    private String tLastName;
    private Set<Course> courses;
}
