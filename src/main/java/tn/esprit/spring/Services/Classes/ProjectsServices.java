package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Investtisment;
import tn.esprit.spring.DAO.Entities.Projects;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Entities.leTypeInvestor;
import tn.esprit.spring.DAO.Repositories.InvesttismentRepository;
import tn.esprit.spring.DAO.Repositories.ProjectsRepository;
import tn.esprit.spring.DAO.Repositories.UserRepository;
import tn.esprit.spring.Services.Interfaces.IProjectsServices;

import java.util.*;

@Service
@AllArgsConstructor
public class ProjectsServices implements IProjectsServices {
    private ProjectsRepository projectsRepository;
    private UserRepository userRepository;
    @Autowired
    private  Smsservice smsservice;

    @Override
    public Projects add(Projects p , Long id) {
        projectsRepository.save(p);
        User user = userRepository.getReferenceById(id);
        p.setUser(user);
        String phoneNumber = user.getTheuserNumber();
        String message = "Hello , your project " + p.getName() + " has been added successfully.";

        smsservice.sendSms(phoneNumber, message);

        return p;
    }

    @Override
    public Projects edit(Projects p) {
        return projectsRepository.save(p);
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


    public List<Projects> Searchprojects(String ch) {
        List<Projects> matchingPosts = new ArrayList<>();
        for (Projects projects : projectsRepository.findAll()) {
            if (projects.getName().contains(ch) || projects.getType().contains(ch)) {
                matchingPosts.add(projects);
            }
        }
        return matchingPosts;
    }

    @Override
    public List<Projects> getProjectsByInvestor(User investor) {
        return projectsRepository.findByInvestor(investor);
    }

 @Override
    public Projects suggestInvestorsForProject(User investor) {


        List<Projects> investments = projectsRepository.findByInvestor(investor);
        Map<leTypeInvestor, Long> investmentTypeCounts = new HashMap<>();

        for (Projects investment : investments) {
            leTypeInvestor type = investment.getLeTypeInvestor();
            Long count = investmentTypeCounts.get(type);
            System.out.println(type);
            System.out.println(count);

            if (count == null) {
                count = 0L;
            }
            investmentTypeCounts.put(type, count + 1);
        }
        leTypeInvestor favoriteTypeInvestement = null;
        Long maxCount = 0L;
        for (Map.Entry<leTypeInvestor, Long> entry : investmentTypeCounts.entrySet()) {
            leTypeInvestor type = entry.getKey();
            Long count = entry.getValue();
            if (count > maxCount) {
                favoriteTypeInvestement = type;
                maxCount = count;
            }
        }

        List<Projects> allProjects = projectsRepository.findAll();

        List<Projects> favoriteProjects = new ArrayList<>();

        for (Projects project : allProjects) {
            if (project.getLeTypeInvestor().equals(favoriteTypeInvestement)) {
                favoriteProjects.add(project);
            }
        }
        int index = new Random().nextInt(favoriteProjects.size());
        return favoriteProjects.get(index);
    }
}



