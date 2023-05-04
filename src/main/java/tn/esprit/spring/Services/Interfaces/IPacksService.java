package tn.esprit.spring.Services.Interfaces;

import tn.esprit.spring.DAO.Entities.Packs;

import java.util.List;
import java.util.Map;

public interface IPacksService {

    Packs add(Packs a);
    Packs edit(Packs a);
    List<Packs> selectAll();
    Packs selectById(int idPack);
    void deleteById(int idPack);

    Packs toggleLike(Packs a);

    /*void delete(Packs a);
    List<Packs> addAll(List<Packs> list);
    void deleteAll(List<Packs> list);
    List<Packs> selectBytypepack (String type);
    int getLikesCountByPackage (int p);
    Packs getMostLikedPackage();
    Boolean createandaffect(int pr, Map<Integer, Integer> products);
     List<Packs> getRecommendedPacks(int userId);
     String getFavoriteTypePack(int userId);
     List<Packs> getPacksByTypePack(String typePack);*/
}
