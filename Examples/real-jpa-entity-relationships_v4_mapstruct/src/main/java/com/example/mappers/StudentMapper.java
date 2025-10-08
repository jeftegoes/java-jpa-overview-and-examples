package com.example.mappers;

import com.example.models.Student;
import com.example.responses.StudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {SubjectStudentMapper.class})
public interface StudentMapper {
    StudentResponse toStudentResponse(Student student);
}
