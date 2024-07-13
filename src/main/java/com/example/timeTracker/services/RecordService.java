package com.example.timeTracker.services;

import com.example.timeTracker.DTO.RecordDTO;
import com.example.timeTracker.mappers.RecordMapper;
import com.example.timeTracker.models.Record;
import com.example.timeTracker.repositories.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecordService {

    @Autowired
    private RecordRepository recordRepository;

    @Autowired
    private RecordMapper recordMapper;

    public List<RecordDTO> getAllRecords() {
        return recordRepository.findAll().stream()
                .map(recordMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<RecordDTO> getRecordById(Long id) {
        return recordRepository.findById(id)
                .map(recordMapper::toDTO);
    }

    public RecordDTO createRecord(RecordDTO recordDTO) {
        Record record = recordMapper.toEntity(recordDTO);
        return recordMapper.toDTO(recordRepository.save(record));
    }

    public Optional<RecordDTO> updateRecord(Long id, RecordDTO recordDTO) {
        return recordRepository.findById(id).map(existingRecord -> {
            existingRecord.setStartTime(recordDTO.getStartTime());
            existingRecord.setEndTime(recordDTO.getEndTime());
            // Assuming that userId and projectId are validated elsewhere
            existingRecord.setUser(recordMapper.toEntity(recordDTO).getUser());
            existingRecord.setProject(recordMapper.toEntity(recordDTO).getProject());
            return recordMapper.toDTO(recordRepository.save(existingRecord));
        });
    }

    public void deleteRecord(Long id) {
        recordRepository.deleteById(id);
    }
}
