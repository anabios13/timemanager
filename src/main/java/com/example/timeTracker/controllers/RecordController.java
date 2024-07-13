package com.example.timeTracker.controllers;

import com.example.timeTracker.DTO.RecordDTO;
import com.example.timeTracker.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<RecordDTO> createRecord(@RequestBody RecordDTO recordDTO) {
        RecordDTO createdRecord = recordService.createRecord(recordDTO);
        return ResponseEntity.ok(createdRecord);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<List<RecordDTO>> getAllRecords() {
        List<RecordDTO> records = recordService.getAllRecords();
        return ResponseEntity.ok(records);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{id}")
    public ResponseEntity<RecordDTO> getRecordById(@PathVariable Long id) {
        RecordDTO record = recordService.getRecordById(id).orElse(null);
        return ResponseEntity.ok(record);
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}")
    public ResponseEntity<RecordDTO> updateRecord(@PathVariable Long id, @RequestBody RecordDTO recordDTO) {
        RecordDTO updatedRecord = recordService.updateRecord(id, recordDTO).orElse(null);
        return ResponseEntity.ok(updatedRecord);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}

