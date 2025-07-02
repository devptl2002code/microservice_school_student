package com.devptl.school_service.service.impl;



import com.devptl.school_service.dto.schoolDTO.SchoolRequest;
import com.devptl.school_service.dto.schoolDTO.SchoolResponse;
import com.devptl.school_service.entity.School;
import com.devptl.school_service.repository.SchoolRepository;

import com.devptl.school_service.service.Impl.SchoolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


/*Unit testing is done by using JUnit and Mockito */
@SpringBootTest
public class SchoolServiceImplTest {

    @InjectMocks // injects mocked dependencies into this real service class
    private SchoolServiceImpl schoolService;

    @Mock // create mock object of SchoolRepository
    private SchoolRepository schoolRepository;

    private SchoolRequest mockRequest;
    private School mockSchool;


    /* Run before each test to set up common mock data */
    @BeforeEach
    void setUp() {

        // to use mock data we need to first initialize
        // mocks annotated with @Mock and @InjectMocks
        MockitoAnnotations.openMocks(this);

        // create dummy request for addSchool()
        mockRequest = new SchoolRequest(
                "Springfield High",
                "New York",
                "John Doe",
                "9876543210",
                "springfield@school.com",
                "CBSE",
                12
        );

        // create dummy school entity object
        // which pretend to be like fetch from db
        mockSchool = School.builder()
                .schoolId(1L)
                .schoolName(mockRequest.getSchoolName())
                .locationName(mockRequest.getLocationName())
                .principalName(mockRequest.getPrincipalName())
                .contactNumber(mockRequest.getContactNumber())
                .email(mockRequest.getEmail())
                .schoolBoard(mockRequest.getSchoolBoard())
                .totalClasses(mockRequest.getTotalClasses())
                .build();

    }


    /* Test Case: addSchool() should save and return school if no duplicate exists */
    @Test
    void addSchool_shouldReturnResponse_whenSchoolSavedSuccessfully(){

        // mock behavior : school name does not exist already
        when(schoolRepository.findBySchoolName(mockRequest.getSchoolName()))
                .thenReturn(Optional.empty());

        // Mock behavior: repository saves and returns the saved entity
        when(schoolRepository.save(any(School.class))).thenReturn(mockSchool);

        // Act: call the service method
        SchoolResponse response = schoolService.addSchool(mockRequest);

        // Assert: validate output
        assertNotNull(response);
        assertEquals(mockRequest.getSchoolName(), response.getSchoolName());

        // verify the repository methods where called once
        verify(schoolRepository, times(1)).findBySchoolName(mockRequest.getSchoolName());
        verify(schoolRepository, times(1)).save(any(School.class));

    }

    /**
     * Test: addSchool() should throw exception if school name already exists
     */
    @Test
    void addSchool_shouldThrowException_whenDuplicateSchoolName() {
        // Mock behavior: school already exists
        when(schoolRepository.findBySchoolName(mockRequest.getSchoolName()))
                .thenReturn(Optional.of(mockSchool));

        // Assert that IllegalArgumentException is thrown
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                schoolService.addSchool(mockRequest)
        );

        assertTrue(exception.getMessage().contains("already created"));

        // Verify that save() was never called
        verify(schoolRepository, times(1)).findBySchoolName(mockRequest.getSchoolName());
        verify(schoolRepository, times(0)).save(any(School.class));
    }

    /**
     * Test: fetchSchools() should return a list of SchoolResponse
     */
    @Test
    void fetchSchools_shouldReturnListOfSchoolResponse() {
        // Mock behavior: repository returns 1 school
        when(schoolRepository.findAll()).thenReturn(List.of(mockSchool));

        // Act
        List<SchoolResponse> schools = schoolService.fetchSchools();

        // Assert
        assertEquals(1, schools.size());
        assertEquals(mockSchool.getSchoolName(), schools.get(0).getSchoolName());

        // Verify
        verify(schoolRepository, times(1)).findAll();
    }

    /**
     * Test: deleteById() should delete school when it exists
     */
    @Test
    void deleteById_shouldDelete_whenSchoolExists() {
        // Mock: school exists in DB
        when(schoolRepository.findBySchoolId(1L)).thenReturn(Optional.of(mockSchool));

        // Act
        schoolService.deleteById(1L);

        // Assert: no exception expected, just verify behavior
        verify(schoolRepository, times(1)).findBySchoolId(1L);
        verify(schoolRepository, times(1)).delete(mockSchool);
    }


    /**
     * Test: deleteById() should throw if school is not found
     */
    @Test
    void deleteById_shouldThrow_whenSchoolNotFound() {
        // Mock: no school found
        when(schoolRepository.findBySchoolId(99L)).thenReturn(Optional.empty());

        // Act + Assert: should throw exception
        Exception exception = assertThrows(RuntimeException.class, () ->
                schoolService.deleteById(99L)
        );

        assertTrue(exception.getMessage().contains("School not found"));

        // Verify save was not called
        verify(schoolRepository, times(1)).findBySchoolId(99L);
        verify(schoolRepository, times(0)).delete(any());
    }


}
