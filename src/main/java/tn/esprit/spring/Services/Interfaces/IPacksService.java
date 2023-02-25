package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Packs;

import java.util.List;

public interface IPacksService {

    Packs add(Packs a);
    Packs edit(Packs a);
    List<Packs> selectAll();
    Packs selectById(int idPack);
    void deleteById(int idPack);
    void delete(Packs a);
    List<Packs> addAll(List<Packs> list);
    void deleteAll(List<Packs> list);
}