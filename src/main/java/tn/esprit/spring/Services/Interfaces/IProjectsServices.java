package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Projects;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;

public interface IProjectsServices {
    Projects add(Projects p,Long id);
    Projects edit(Projects p);
    List<Projects> selectAll();
    Projects selectById(int idProjects);
    void deleteById(int idProjects);
    void delete(Projects p);
    List<Projects> addAll(List<Projects> list);
    void deleteAll(List<Projects> list);
    //List<Projects> Get_projects_by_User(Long idUser) ;
    public List<Projects> Searchprojects(String ch) ;
    public List<Projects> getProjectsByInvestor(User investor) ;


     Projects suggestInvestorsForProject(User investor);}
