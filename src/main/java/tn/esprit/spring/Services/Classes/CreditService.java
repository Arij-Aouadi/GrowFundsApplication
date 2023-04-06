package tn.esprit.spring.Services.Classes;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.Packs;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.CreditsRepository;
import java.io.BufferedReader;
import tn.esprit.spring.DAO.Repositories.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreditService implements tn.esprit.spring.Services.Interfaces.ICreditService {


    private CreditsRepository creditsRepository;
    private UserRepository userRepository;
    @Override
    public Credits add(Credits c){
        return  creditsRepository.save(c);
    }


    @Override
    public Credits edit(Credits c) {
        return creditsRepository.save(c);
    }

    @Override
    public List<Credits> selectAll() {
        return creditsRepository.findAll();
    }

    @Override
    public Credits selectById(int idCredit) {
        return creditsRepository.findById(idCredit).get();
    }

    @Override
    public void delete(Credits c) {
        creditsRepository.delete(c);

    }

    @Override
    public void deleteById(int idCredit) {
        creditsRepository.deleteById(idCredit);
    }

    @Override
    public List<Credits> addAll(List<Credits> list) {
        return creditsRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<Credits> list) {
        creditsRepository.deleteAll(list);
    }

    @Override
    public List<Credits> GetCreditsByStatus(String status) {
        return creditsRepository.findCreditsByStatus(status);
    }

    @Override
    public boolean CreditExists(int accountNum) {
        return creditsRepository.existsCreditsByAccount_AccountNum(accountNum);
    }

    @Override
    public float calculeMonthlyPayment(int idcredit) {

        Credits credit = creditsRepository.findCreditsByIdCredit(idcredit);

        float mensuelIntrestRate=credit.getInterestRate()/12;

        float mensuelPayment =(float) ((credit.getAmount()*mensuelIntrestRate)/(1-(Math.pow((1+mensuelIntrestRate),-credit.getDuration()))));

        return mensuelPayment;
    }

    @Override
    public void export(HttpServletResponse response,int idCredit) throws IOException {
        Credits credits= creditsRepository.findCreditsByIdCredit(idCredit);
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle= FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

        Paragraph paragraph= new Paragraph("Tableau d''Amortissement "+String.valueOf(idCredit),fontTitle);
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(paragraph);
        document.close();
    }


}
