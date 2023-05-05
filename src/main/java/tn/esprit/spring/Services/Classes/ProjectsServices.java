package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Project;
import tn.esprit.spring.DAO.Entities.Revenue;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Entities.TypeInvestor;
import tn.esprit.spring.DAO.Repositories.ProjectsRepository;
import tn.esprit.spring.DAO.Repositories.RevenueRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;
import tn.esprit.spring.Services.Interfaces.IProjectsServices;

import java.util.*;

@Service
@AllArgsConstructor
public class ProjectsServices implements IProjectsServices {
    private ProjectsRepository projectsRepository;
    private RevenueRepository revenueRepository;
    private UserService userService;
    private  Smsservice smsservice;

    @Override
    public List<Project> getProjectsForFounder(User u) {
        return projectsRepository.findByFounder(u);
    }
    @Override
    public Project selectById(int idProjects) {
        return projectsRepository.findById(idProjects).get();
    }

    @Override
    public Project addRevenue(Revenue r, int idProject) {
        Project p =projectsRepository.findById(idProject).get();
        revenueRepository.save(r);
        p.getRevenues().add(r);
        projectsRepository.save(p);
        return p;

    }
    @Override
    public Project add(Project p ,User u) {
        p.setFounder(u);
        String phoneNumber = u.getTheuserNumber();
        String message = "Hello , your project " + p.getName() + " has been added successfully and you will get our answer as soon as possible.";
        smsservice.sendSms(phoneNumber, message);
        projectsRepository.save(p);
        return p;
    }

    @Override
    public List<Project> selectAll() {
        return projectsRepository.findAll();
    }

    @Override
    public Project edit(Project p) {
        return projectsRepository.save(p);
    }

    /*@Override
    public Project add(Project p , Long id) {
        projectsRepository.save(p);
        User user = userRepository.getReferenceById(id);
        p.setUser(user);
        String phoneNumber = user.getTheuserNumber();
        String message = "Hello , your project " + p.getName() + " has been added successfully.";

        smsservice.sendSms(phoneNumber, message);

        return p;
    }







    @Override
    public void deleteById(int idProjects) {
        projectsRepository.deleteById(idProjects);

    }

    @Override
    public void delete(Project p) {
        projectsRepository.delete(p);
    }

    @Override
    public List<Project> addAll(List<Project> list) {
        return projectsRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<Project> list) {
        projectsRepository.deleteAll(list);
    }


    public List<Project> Searchprojects(String ch) {
        List<Project> matchingPosts = new ArrayList<>();
        for (Project projects : projectsRepository.findAll()) {
            if (projects.getName().contains(ch) || projects.getType().contains(ch)) {
                matchingPosts.add(projects);
            }
        }
        return matchingPosts;
    }

    @Override
    public List<Project> getProjectsByInvestor(User investor) {
        return projectsRepository.findByInvestor(investor);
    }

 @Override
    public Project suggestInvestorsForProject(User investor) {


        List<Project> investments = projectsRepository.findByInvestor(investor);
        Map<TypeInvestor, Long> investmentTypeCounts = new HashMap<>();

        for (Project investment : investments) {
            TypeInvestor type = investment.getLeTypeInvestor();
            Long count = investmentTypeCounts.get(type);
            System.out.println(type);
            System.out.println(count);

            if (count == null) {
                count = 0L;
            }
            investmentTypeCounts.put(type, count + 1);
        }
        TypeInvestor favoriteTypeInvestement = null;
        Long maxCount = 0L;
        for (Map.Entry<TypeInvestor, Long> entry : investmentTypeCounts.entrySet()) {
            TypeInvestor type = entry.getKey();
            Long count = entry.getValue();
            if (count > maxCount) {
                favoriteTypeInvestement = type;
                maxCount = count;
            }
        }

        List<Project> allProjects = projectsRepository.findAll();

        List<Project> favoriteProjects = new ArrayList<>();

        for (Project project : allProjects) {
            if (project.getLeTypeInvestor().equals(favoriteTypeInvestement)) {
                favoriteProjects.add(project);
            }
        }
        int index = new Random().nextInt(favoriteProjects.size());
        return favoriteProjects.get(index);
    }*/
}



