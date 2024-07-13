package com.example.timeTracker.mappers;

import com.example.timeTracker.DTO.ProjectDTO;
import com.example.timeTracker.models.Project;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectDTO toDTO(Project project);
    Project toEntity(ProjectDTO projectDTO);
}

