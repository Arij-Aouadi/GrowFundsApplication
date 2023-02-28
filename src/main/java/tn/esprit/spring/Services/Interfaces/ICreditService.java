package tn.esprit.spring.Services.interfaces;

import tn.esprit.spring.DAO.Entities.Credits;

import java.util.List;

public interface ICreditService {

    Credits add(Credits c);
    Credits edit(Credits c);
    List<Credits> selectAll();
    Credits selectById(int idCredit);
    void delete(Credits c);
    void deleteById(int idCredit);
    List<Credits> addAll(List<Credits> List);
    void deleteAll(List<Credits> list);
}
