package com.example.timeTracker.mappers;

import com.example.timeTracker.DTO.RecordDTO;
import com.example.timeTracker.models.Record;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "project.id", target = "projectId")
    RecordDTO toDTO(Record record);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "projectId", target = "project.id")
    Record toEntity(RecordDTO recordDTO);
}
