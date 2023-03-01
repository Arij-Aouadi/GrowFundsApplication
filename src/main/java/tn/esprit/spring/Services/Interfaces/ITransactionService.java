package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Transactions;

import java.util.List;

public interface ITransactionService {
    Transactions add(Transactions t);
    Transactions edit(Transactions t);
    List<Transactions> selectAll();
    Transactions selectById(int idTransactions);
    void deleteById(int idTransactions);
    void delete(Transactions t );
    List<Transactions> addAll(List<Transactions> list);
    void deleteAll(List<Transactions> list);
    List<Transactions> selectByRibsource(long ribsource);
}