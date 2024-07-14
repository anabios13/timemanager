package com.example.timeTracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecordDTO {
    private Long id;
    private Long userId;
    private Long projectId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
}
