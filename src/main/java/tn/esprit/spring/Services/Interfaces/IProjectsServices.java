package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Projects;

import java.util.List;

public interface IProjectsServices {
    Projects add(Projects p);
    Projects edit(Projects p);
    List<Projects> selectAll();
    Projects selectById(int idProjects);
    void deleteById(int idProjects);
    void delete(Projects p);
    List<Projects> addAll(List<Projects> list);
    void deleteAll(List<Projects> list);

}
