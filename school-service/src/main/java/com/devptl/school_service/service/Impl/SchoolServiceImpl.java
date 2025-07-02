package com.devptl.school_service.service.Impl;

import com.devptl.school_service.dto.schoolDTO.SchoolRequest;
import com.devptl.school_service.dto.schoolDTO.SchoolResponse;
import com.devptl.school_service.entity.School;
import com.devptl.school_service.repository.SchoolRepository;
import com.devptl.school_service.service.SchoolService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

    @Override
    public SchoolResponse addSchool(SchoolRequest request) {
        log.debug("Adding new school: {}", request.getSchoolName());

        // FIX: If school already exists, throw exception
        boolean exists = schoolRepository.findBySchoolName(request.getSchoolName()).isPresent();
        if (exists) {
            throw new IllegalArgumentException("School is already created with name: " + request.getSchoolName());
        }

        School school = covertToEntity(request);
        school = schoolRepository.save(school);
        log.info("School saved successfully with ID: {}", school.getSchoolId());

        return convertToDTO(school);
    }



    private SchoolResponse convertToDTO(School school) {
        return SchoolResponse.builder()
                .schoolId(school.getSchoolId())
                .schoolName(school.getSchoolName())
                .locationName(school.getLocationName())
                .principalName(school.getPrincipalName())
                .contactNumber(school.getContactNumber())
                .email(school.getEmail())
                .schoolBoard(school.getSchoolBoard())
                .totalClasses(school.getTotalClasses())
                .createdOn(school.getCreatedOn())
                .updatedOn(school.getUpdatedOn())
                .build();
    }

    /* Helper to covert DTO -> Modal class */
    private School covertToEntity(SchoolRequest request) {
        return School.builder()
                .schoolName(request.getSchoolName())
                .locationName(request.getLocationName())
                .principalName(request.getPrincipalName())
                .contactNumber(request.getContactNumber())
                .email(request.getEmail())
                .schoolBoard(Strings.isNotEmpty(request.getSchoolBoard()) ? request.getSchoolBoard() : "GSEB")
                .totalClasses(request.getTotalClasses())
                .build();
    }

    @Override
    public List<SchoolResponse> fetchSchools() {

        return schoolRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long schoolId) {

        School existingSchool = schoolRepository.findBySchoolId(schoolId)
                .orElseThrow(() -> new RuntimeException("School not found with id: " + schoolId));
        schoolRepository.delete(existingSchool);

    }

}
