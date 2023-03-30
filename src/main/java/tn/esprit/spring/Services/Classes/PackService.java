package tn.esprit.spring.Services.Classes;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import javafx.util.Pair;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Packs;
import tn.esprit.spring.DAO.Entities.Product;
import tn.esprit.spring.DAO.Repositories.PackRepository;
import tn.esprit.spring.DAO.Repositories.ProductRepository;
import tn.esprit.spring.Services.Interfaces.IPacksService;

import java.util.*;

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
    public Boolean createandaffect(int pr, Map<Integer, Integer> products) {
        Set<Product> prod=new HashSet<Product>();
        float price=0;
        for(Map.Entry<Integer, Integer>  idp:products.entrySet()) {
            System.out.println(idp.getKey());
            System.out.println(idp.getValue());
            Product product= productRepository.findById(idp.getKey()).orElse(null) ;
            int a=product.getQuantity();
            product.setQuantity(a-idp.getValue());
            if ( product.getQuantity()<=0 ){return false ;}
            else
            { prod.add(product);}

        }
        System.out.println(prod);
        Packs pro =packRepository.findByIdPack(pr);
        pro.setProduct_pack(prod);
        for (Product produit:prod) {

            price = price + produit.getPriceProduct() ;
        }
        pro.setPrice(price);
        packRepository.save(pro);
        return true;
    }

}
