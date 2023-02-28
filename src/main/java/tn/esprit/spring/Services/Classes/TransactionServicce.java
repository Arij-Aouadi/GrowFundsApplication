package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Transactions;
import tn.esprit.spring.DAO.Repositories.TransactionRepository;
import tn.esprit.spring.Services.Interfaces.ITransactionService;

import java.util.List;

@AllArgsConstructor //injection par constructeur
@Service

public class TransactionServicce implements ITransactionService {
    private TransactionRepository transactionRepository ;
    @Override
    public Transactions add(Transactions t) {
        return transactionRepository.save(t);
    }

    @Override
    public Transactions edit(Transactions t) {
        return transactionRepository.save(t);
    }

    @Override
    public List<Transactions> selectAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Transactions selectById(int idTransactions) {
        return transactionRepository.findById(idTransactions).get();

    }

    @Override
    public void deleteById(int idTransactions) {
        transactionRepository.deleteById(idTransactions);
    }

    @Override
    public void delete(Transactions t) {
        transactionRepository.delete(t);

    }

    @Override
    public List<Transactions> addAll(List<Transactions> list) {
        return transactionRepository.saveAll(list);
    }
    //************************Supprimer toutes les Transaction****************
    @Override
    public void deleteAll(List<Transactions> list) {
        transactionRepository.deleteAll(list);}



    //***************************** Retourner les transactions liee a un Rib Donnee ************
    @Override
    public List<Transactions> selectByRibsource(long ribsource) {
        return transactionRepository.findByRibsource(ribsource);
    }




}