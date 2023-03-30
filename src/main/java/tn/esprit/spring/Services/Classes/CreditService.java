package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Credits;
import tn.esprit.spring.DAO.Entities.CsvClass;
import tn.esprit.spring.DAO.Entities.Packs;
import tn.esprit.spring.DAO.Entities.User;
import tn.esprit.spring.DAO.Repositories.CreditsRepository;
import java.io.BufferedReader;
import tn.esprit.spring.DAO.Repositories.UserRepository;

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
    public Double predict(int idCredit) throws IOException {

        //prepare client features to be predicted
        Credits credit= creditsRepository.getReferenceById(idCredit);
        List<Integer> features = new ArrayList<>();
        features.add(credit.getCheckingAccount());
        features.add(credit.getDuration());
        features.add(credit.getCreditHistory());
        features.add(credit.getPurpose());
        features.add(credit.getBondsStatus());
        features.add(credit.getEmploymentYears());
        features.add(credit.getInstallmentRate());
        features.add(credit.getStatusAndSex());
        features.add(credit.getGurantOrCoapplicant());
        features.add(credit.getResidenceSince());
        features.add(credit.getProperty());
        features.add(credit.getAge());
        features.add(credit.getOtherPlans());
        features.add(credit.getHousing());
        features.add(credit.getNumOfExistingCredits());
        features.add(credit.getJob());

        //load model weights from the csv file
        CsvClass csv = new CsvClass();
        String line= "";
        BufferedReader br= new BufferedReader(new FileReader("src/main/resources/theta.csv"));
        line= br.readLine();
        String[] data= line.split(",");
        csv.setWeights(Arrays.stream(data)
                .map(Double::parseDouble)
                .collect(Collectors.toList()));

        //predict using sigmoid function
        Double z = 0.0;
        for (int i = 0; i < features.size(); i++) {
            z = csv.weights.get(i) * features.get(i);
        }
        double prediction= 1.0 / (1.0 + Math.exp(-z));
        return prediction;


    }
}
