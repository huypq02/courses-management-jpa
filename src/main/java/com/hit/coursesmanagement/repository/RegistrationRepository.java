package com.hit.coursesmanagement.repository;

import com.hit.coursesmanagement.entity.RegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, Integer> {
    List<RegistrationEntity> findByStudentId(int studentId);
    List<RegistrationEntity> findByCourseId(int courseId);
}
