package com.devptl.school_service.service;


import com.devptl.school_service.entity.School;
import com.devptl.school_service.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

    public School addSchool(School school){
        return schoolRepository.save(school);
    }

    public List<School> fetchSchools(){
        return schoolRepository.findAll();
    }

    public School findById(int id){
        return schoolRepository.findById(id).orElse(null);
    }

}
