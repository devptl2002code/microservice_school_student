package com.devptl.school_service.controller;

import com.devptl.school_service.dto.error.ErrorResponse;
import com.devptl.school_service.dto.schoolDTO.SchoolRequest;
import com.devptl.school_service.dto.schoolDTO.SchoolResponse;
import com.devptl.school_service.service.SchoolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "School", description = "API for managing school records")
public class SchoolController {

    private final SchoolService schoolService;

    @Operation(
            summary = "Create a new school",
            description = "Creates a new school entity and returns the created object",
            responses = {
                    @ApiResponse(responseCode = "200", description = "School successfully created",
                            content = @Content(schema = @Schema(implementation = SchoolResponse.class))),
                    @ApiResponse(responseCode = "400", description = "School already exists",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
                    @ApiResponse(responseCode = "500", description = "Server error",
                            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
            }
    )
    @PostMapping("/school")
    public ResponseEntity<?> addSchool(@RequestBody SchoolRequest request) {
        try {
            SchoolResponse response = schoolService.addSchool(request);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            log.warn("Validation failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(e.getMessage(), LocalDateTime.now()));
        } catch (Exception e) {
            log.error("Failed to create school", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to create school", LocalDateTime.now()));
        }
    }

    @Operation(summary = "Fetch all schools", description = "Returns a list of all registered schools")
    @GetMapping("/schools")
    public ResponseEntity<List<SchoolResponse>> getAllSchools() {
        log.debug("Fetching all schools");
        List<SchoolResponse> schools = schoolService.fetchSchools();
        return ResponseEntity.ok(schools);
    }

    @Operation(summary = "Delete school by ID", description = "Deletes the school for a given ID")
    @DeleteMapping("/school/{id}")
    public ResponseEntity<?> deleteSchool(@PathVariable Long id) {
        try {
            log.debug("Attempting to delete school with ID: {}", id);
            schoolService.deleteById(id);
            log.info("Successfully deleted school with ID: {}", id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            log.warn("Delete failed: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(e.getMessage(), LocalDateTime.now()));
        } catch (Exception e) {
            log.error("Internal error deleting school", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to delete school", LocalDateTime.now()));
        }
    }

    @Operation(summary = "Find school by ID", description = "Returns a single school based on ID")
    @GetMapping("/school/{id}")
    public ResponseEntity<?> getSchoolById(@PathVariable Long id) {
        try {
            log.debug("Fetching school with ID: {}", id);
            SchoolResponse school = schoolService.fetchSchools().stream()
                    .filter(s -> s.getSchoolId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("School not found with ID: " + id));
            return ResponseEntity.ok(school);
        } catch (RuntimeException e) {
            log.warn("School not found: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse(e.getMessage(), LocalDateTime.now()));
        } catch (Exception e) {
            log.error("Failed to fetch school", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Failed to fetch school", LocalDateTime.now()));
        }
    }
}
