package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Project;
import tn.esprit.spring.DAO.Entities.Revenue;
import tn.esprit.spring.DAO.Entities.User;

import java.util.List;

public interface IProjectsServices {

    public List<Project> getProjectsForFounder(User u) ;
    Project selectById(int idProjects);


    Project addRevenue(Revenue r, int idProject);

    Project add(Project p,User u);

    List<Project> selectAll();


    Project edit(Project p);

    /*


    List<Project> selectAll();
   ;
    void deleteById(int idProjects);
    void delete(Project p);
    List<Project> addAll(List<Project> list);
    void deleteAll(List<Project> list);
    //List<Projects> Get_projects_by_User(Long idUser) ;
    public List<Project> Searchprojects(String ch) ;
    public List<Project> getProjectsByInvestor(User investor) ;


     Project suggestInvestorsForProject(User investor);*/

}
