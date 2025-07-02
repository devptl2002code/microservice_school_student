package com.devptl.school_service.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "school")
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long schoolId;

    @Column(name = "school_name", unique = true)
    private String schoolName;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "principal_name")
    private String principalName;

    // Optional additions
    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "school_board")
    private String schoolBoard; // e.g., CBSE, GSEB, ICSE

    @Column(name = "total_classes")
    private Integer totalClasses;

    @CreationTimestamp
    @Column(updatable = false, name = "created_on")
    private Timestamp createdOn;

    @UpdateTimestamp
    @Column(name = "updated_on")
    private Timestamp updatedOn;


}
