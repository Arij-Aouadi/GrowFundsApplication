package tn.esprit.spring.Services.Interfaces;

import io.swagger.v3.core.util.Json;
import tn.esprit.spring.DAO.Entities.Amortisement;
import tn.esprit.spring.DAO.Entities.Credits;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
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

    List<Credits> clientCredits(int accountNUm);

    float calculeMonthlyPayment(int credit);

    void export(HttpServletResponse response,int idCredit) throws IOException;

    List<Amortisement> amortisement(int idCredit);


}
