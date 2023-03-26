package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Packs;
import tn.esprit.spring.DAO.Entities.Product;
import tn.esprit.spring.DAO.Repositories.PackRepository;
import tn.esprit.spring.DAO.Repositories.ProductRepository;
import tn.esprit.spring.Services.Interfaces.IPacksService;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class PackService implements IPacksService {
    private PackRepository packRepository ;
  private ProductRepository productRepository;

    @Override
    public Packs add(Packs a) {
        return packRepository.save(a);
    }

    @Override
    public Packs edit(Packs a) {
        return packRepository.save(a);
    }

    @Override
    public List<Packs> selectAll() {
        return packRepository.findAll();
    }

    @Override
    public Packs selectById(int idPack) {
        return packRepository.findById(idPack).get();
    }

    @Override
    public void deleteById(int idPack) {
        packRepository.deleteById(idPack);
    }

    @Override
    public void delete(Packs a) {
        packRepository.delete(a);

    }


    @Override
    public List<Packs> addAll(List<Packs> list) {
        return packRepository.saveAll(list);
    }

    @Override
    public void deleteAll(List<Packs> list) {
        packRepository.deleteAll(list);

    }

    @Override
    public List<Packs> selectBytypepack(String type) {
        return packRepository.findByTypepack(type);
    }

    @Override
    public int getLikesCountByPackage(int p) {
        return packRepository.countLikesByPackageId(p);
    }

    @Override
    public Packs getMostLikedPackage() {
        List<Packs> packages = packRepository.findAll();
        packages.sort(Comparator.comparingInt(p -> p.getLikedByUsers().size()));
        System.out.println(packages.get(packages.size() - 1));
        return packages.get(packages.size() - 1);

    }

    @Override
    public Packs createandaffect(int pr, List<Integer> p) {
        Set<Product> prod=new HashSet<Product>();
        float price=0;
        for(int idp:p) {
            Product product= productRepository.findById(idp).orElse(null) ;
            prod.add(product);

        }
        Packs pro =packRepository.findByIdPack(pr);
        pro.setProduct_pack(prod);
        for (Product produit:prod) {

            price = price + produit.getPriceProduct() ;
        }
        pro.setPrice(price);

        return packRepository.save(pro);
    }

}
