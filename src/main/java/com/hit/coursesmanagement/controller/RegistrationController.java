package com.hit.coursesmanagement.controller;

import com.hit.coursesmanagement.entity.CourseEntity;
import com.hit.coursesmanagement.entity.RegistrationEntity;
import com.hit.coursesmanagement.entity.StudentEntity;
import com.hit.coursesmanagement.repository.CourseRepository;
import com.hit.coursesmanagement.repository.RegistrationRepository;
import com.hit.coursesmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class RegistrationController {
    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/students/{studentId}/courses")
    public ResponseEntity<?> registerCourses(@PathVariable int studentId, @RequestBody List<Integer> courseIds) {
        StudentEntity student = studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student not found"));
        List<CourseEntity> courses = courseRepository.findAllById(courseIds);

        for (CourseEntity course : courses) {
            RegistrationEntity registration = new RegistrationEntity();
            registration.setStudent(student);
            registration.setCourse(course);
            registration.setRegistrationDate(LocalDate.now());
            registrationRepository.save(registration);
        }

        return ResponseEntity.ok("Courses registered successfully!");
    }

    @GetMapping("/students/{studentId}/courses")
    public ResponseEntity<?> getCoursesByStudent(@PathVariable int studentId) {
        List<RegistrationEntity> registrations = registrationRepository.findByStudentId(studentId);
        List<CourseEntity> courses = registrations.stream()
                .map(RegistrationEntity::getCourse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(courses);
    }

    @GetMapping("/courses/{courseId}/students")
    public ResponseEntity<?> getStudentsByCourse(@PathVariable int courseId) {
        List<RegistrationEntity> registrations = registrationRepository.findByCourseId(courseId);
        List<StudentEntity> students = registrations.stream()
                .map(RegistrationEntity::getStudent)
                .collect(Collectors.toList());

        return ResponseEntity.ok(students);
    }
}