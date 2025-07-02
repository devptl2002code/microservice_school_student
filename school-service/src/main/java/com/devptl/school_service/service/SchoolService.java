package com.devptl.school_service.service;


import com.devptl.school_service.dto.schoolDTO.SchoolRequest;
import com.devptl.school_service.dto.schoolDTO.SchoolResponse;

import java.util.List;

public interface SchoolService {


    SchoolResponse addSchool(SchoolRequest request);

    List<SchoolResponse> fetchSchools();

    void deleteById (Long schoolId);
}
