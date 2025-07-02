package com.devptl.student_service.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "students")
public class Student {
    @Id
    private String id;
    private String name;
    private int age;
    private String gender;
    private Integer schoolId;

    private int rollNumber;
    private String standard;      // e.g., "10th", "5th", etc.
    private String section;       // e.g., "A", "B", etc.

    private String profileImageUrl;  // For AWS S3 integration
}
