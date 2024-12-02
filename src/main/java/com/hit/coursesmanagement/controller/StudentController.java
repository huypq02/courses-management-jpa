package com.hit.coursesmanagement.controller;

import com.hit.coursesmanagement.dto.StudentDto;
import com.hit.coursesmanagement.entity.StudentEntity;
import com.hit.coursesmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/students")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @PostMapping
    public ResponseEntity<?> addUser (@RequestBody StudentEntity studentEntity){
        studentRepository.save(studentEntity);

        return ResponseEntity.ok("Student added successfully!");
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents(){
        List<StudentEntity> students = studentRepository.findAll();
        List<StudentDto> studentDtos = new ArrayList<>();
        for (StudentEntity student : students){
            StudentDto studentDto = new StudentDto();
            studentDto.setId(student.getId());
            studentDto.setName(student.getName());
            studentDto.setEmail(student.getEmail());
            studentDto.setAge(student.getAge());

            studentDtos.add(studentDto);
        }

        return ResponseEntity.ok(studentDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable int id){
        StudentEntity student = studentRepository.findById(id).get();
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setName(student.getName());
        studentDto.setEmail(student.getEmail());
        studentDto.setAge(student.getAge());

        return ResponseEntity.ok(studentDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable int id, @RequestBody StudentEntity studentEntity){
        StudentEntity student = studentRepository.findById(id).get();
        if (studentEntity.getName() != null){
            student.setName(studentEntity.getName());
        }
        if (student.getEmail() != null){
            student.setEmail(studentEntity.getEmail());
        }
        if (studentEntity.getAge() > 0){
            student.setAge(studentEntity.getAge());
        }

        studentRepository.save(student);

        return ResponseEntity.ok("Student updated successfully!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id){
        studentRepository.deleteById(id);

        return ResponseEntity.ok("Student deleted successfully!");
    }
}