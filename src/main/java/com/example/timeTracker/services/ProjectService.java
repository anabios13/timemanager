package com.example.timeTracker.services;

import com.example.timeTracker.DTO.ProjectDTO;
import com.example.timeTracker.mappers.ProjectMapper;
import com.example.timeTracker.models.Project;
import com.example.timeTracker.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMapper projectMapper;

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProjectDTO> getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(projectMapper::toDTO);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectMapper.toEntity(projectDTO);
        return projectMapper.toDTO(projectRepository.save(project));
    }

    public Optional<ProjectDTO> updateProject(Long id, ProjectDTO projectDTO) {
        return projectRepository.findById(id).map(existingProject -> {
            existingProject.setName(projectDTO.getName());
            existingProject.setDescription(projectDTO.getDescription());
            return projectMapper.toDTO(projectRepository.save(existingProject));
        });
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
