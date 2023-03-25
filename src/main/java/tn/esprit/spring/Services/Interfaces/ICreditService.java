package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Credits;

import java.util.List;



public interface ICreditService {

    Credits add (Credits c );
    Credits edit (Credits c);
    List<Credits> selectAll();
    Credits selectById (int idCredit);
    void delete (Credits c);
    void deleteById (int idCredit);
    List<Credits> addAll (List<Credits> List);
    void deleteAll (List<Credits> list);
    List<Credits> GetCreditsByStatus (String status);
    boolean CreditExists (int accountNum);
    void predict (Credits c);
}
