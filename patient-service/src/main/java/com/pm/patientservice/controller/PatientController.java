package com.pm.patientservice.controller;

import com.pm.patientservice.dto.PatientRequestDTO;
import com.pm.patientservice.dto.PatientResponseDTO;
import com.pm.patientservice.dto.validators.CreatePatientValidationGroup;
import com.pm.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "API for managing Patients")
@AllArgsConstructor
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Get Patients")
    public List<PatientResponseDTO> getPatients() {
        return patientService.getPatients();
    }

    @PostMapping
    @Operation(summary = "Create a new Patient")
    public PatientResponseDTO createPatient(
            @Validated({Default.class, CreatePatientValidationGroup.class})
            @RequestBody PatientRequestDTO patientRequestDTO) {
        return patientService.createPatient(
                patientRequestDTO);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a new Patient")
    public PatientResponseDTO updatePatient(@PathVariable UUID id,
    @Validated @RequestBody PatientRequestDTO patientRequestDTO) {
        return patientService.updatePatient(id, patientRequestDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Patient")
    public void deletePatient(@PathVariable UUID id) {
        patientService.deletePatient(id);
    }
}
