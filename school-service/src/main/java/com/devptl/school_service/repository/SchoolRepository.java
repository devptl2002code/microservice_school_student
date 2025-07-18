package com.devptl.school_service.repository;


import com.devptl.school_service.entity.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, Integer> {
    Optional<School> findBySchoolId (Long schoolId);
    Optional<School> findBySchoolName(String schoolName);
}
