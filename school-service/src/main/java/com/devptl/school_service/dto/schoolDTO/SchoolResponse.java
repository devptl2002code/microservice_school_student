package com.devptl.school_service.dto.schoolDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SchoolResponse {

    private Long schoolId;
    private String schoolName;
    private String locationName;
    private String principalName;
    private String contactNumber;
    private String email;
    private String schoolBoard; // e.g., CBSE, GSEB, ICSE
    private Integer totalClasses;
    private Timestamp createdOn;
    private Timestamp updatedOn;

}
