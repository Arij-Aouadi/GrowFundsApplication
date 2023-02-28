package tn.esprit.spring.Services.Classes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.DAO.Entities.Packs;
import tn.esprit.spring.DAO.Repositories.PackRepository;
import tn.esprit.spring.Services.Interfaces.IPacksService;

import java.util.List;

@AllArgsConstructor
@Service
public class PackService implements IPacksService {
    private PackRepository packRepository ;


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
}
