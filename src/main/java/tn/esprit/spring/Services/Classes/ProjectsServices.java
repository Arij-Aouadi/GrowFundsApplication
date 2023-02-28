package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Projects;
import tn.esprit.spring.DAO.Repositories.ProjectsRepository;
import tn.esprit.spring.Services.interfaces.IProjectsServices;

import java.util.List;
@Service
@AllArgsConstructor
public class ProjectsServices implements IProjectsServices {
    private ProjectsRepository projectsRepository;
    @Override
    public Projects add(Projects p) {
        return projectsRepository.save(p) ;
    }

    @Override
    public Projects edit(Projects p) {
        return projectsRepository.save(p) ;
    }

    @Override
    public List<Projects> selectAll() {
        return projectsRepository.findAll();
    }

    @Override
    public Projects selectById(int idProjects) {
        return projectsRepository.findById(idProjects).get();
    }

    @Override
    public void deleteById(int idProjects) {
        projectsRepository.deleteById(idProjects);

    }

    @Override
    public void delete(Projects p) {
        projectsRepository.delete(p);
    }

    @Override
    public List<Projects> addAll(List<Projects> list) {
        return projectsRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<Projects> list) {
        projectsRepository.deleteAll(list);
    }
}
