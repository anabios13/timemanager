package com.example.timeTracker.controllers;

import com.example.timeTracker.DTO.RecordDTO;
import com.example.timeTracker.services.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @PostMapping
    public ResponseEntity<RecordDTO> createRecord(@RequestBody RecordDTO recordDTO) {
        RecordDTO createdRecord = recordService.createRecord(recordDTO);
        return ResponseEntity.ok(createdRecord);
    }

    @GetMapping
    public ResponseEntity<List<RecordDTO>> getAllRecords() {
        List<RecordDTO> records = recordService.getAllRecords();
        return ResponseEntity.ok(records);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecordDTO> getRecordById(@PathVariable Long id) {
        RecordDTO record = recordService.getRecordById(id).orElse(null);
        return ResponseEntity.ok(record);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecordDTO> updateRecord(@PathVariable Long id, @RequestBody RecordDTO recordDTO) {
        RecordDTO updatedRecord = recordService.updateRecord(id, recordDTO).orElse(null);
        return ResponseEntity.ok(updatedRecord);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteRecord(id);
        return ResponseEntity.noContent().build();
    }
}

