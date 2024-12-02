package com.hit.coursesmanagement.controller;

import com.hit.coursesmanagement.dto.CourseDto;
import com.hit.coursesmanagement.dto.StudentDto;
import com.hit.coursesmanagement.entity.CourseEntity;
import com.hit.coursesmanagement.entity.StudentEntity;
import com.hit.coursesmanagement.repository.CourseRepository;
import com.hit.coursesmanagement.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public ResponseEntity<?> getCoursesByDuration(@RequestParam int durationGreaterThan) {
        List<CourseEntity> courses = courseRepository.findAll();
//        LocalDate date = LocalDate.of(2025, 1, 1);
//        System.out.println(ChronoUnit.DAYS.between(date, LocalDate.now()));

        List<CourseDto> filteredCourses = courses.stream()
                .filter(course -> ChronoUnit.DAYS.between(LocalDate.now(), course.getDuration()) > durationGreaterThan)
                .map(course -> {
                    CourseDto courseDto = new CourseDto();
                    courseDto.setId(course.getId());
                    courseDto.setName(course.getTitle());
                    courseDto.setDuration(course.getDuration().toString());
                    return courseDto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(filteredCourses);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getCoursesCount() {
        long count = courseRepository.count();
        return ResponseEntity.ok(count);
    }
}
