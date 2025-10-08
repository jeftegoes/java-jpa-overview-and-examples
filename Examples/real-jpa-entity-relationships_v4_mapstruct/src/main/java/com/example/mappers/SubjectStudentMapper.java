package com.example.mappers;

import com.example.models.Student;
import com.example.models.SubjectStudent;
import com.example.responses.StudentResponse;
import com.example.responses.SubjectStudentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubjectStudentMapper {
    @Mapping(source = "student.subjectStudents", target = "student.subjectStudents", ignore = true)
    @Mapping(source = "subject.subjectStudents", target = "subject.subjectStudents", ignore = true)
    SubjectStudentResponse toSubjectStudentResponse(SubjectStudent subjectStudent);
}
