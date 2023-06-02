package org.example.service;

import org.example.entity.Filho;
import org.example.entity.Pai;
import org.example.repository.FilhoRepository;
import org.example.repository.PaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilhoService {
    private final FilhoRepository filhoRepository;
    private final PaiRepository paiRepository;

    @Autowired
    public FilhoService(FilhoRepository filhoRepository, PaiRepository paiRepository) {
        this.filhoRepository = filhoRepository;
        this.paiRepository = paiRepository;
    }

    public List<Filho> getFilhosByPaiId(Long paiId) {
        Pai pai = paiRepository.findById(paiId).orElse(null);
        if (pai != null) {
            return pai.getFilhos();
        }
        return null;
    }

    public Filho getFilhoById(Long filhoId) {
        return filhoRepository.findById(filhoId).orElse(null);
    }

    public Filho createFilho(Long paiId, Filho filho) {
        Pai pai = paiRepository.findById(paiId).orElse(null);
        if (pai != null) {
            filho.setPai(pai);
            return filhoRepository.save(filho);
        }
        return null;
    }

    public Filho updateFilho(Long paiId, Long filhoId, Filho filho) {
        Pai pai = paiRepository.findById(paiId).orElse(null);
        Filho existingFilho = filhoRepository.findById(filhoId).orElse(null);
        if (pai != null && existingFilho != null && existingFilho.getPai().equals(pai)) {
            existingFilho.setNome(filho.getNome());
            existingFilho.setIdade(filho.getIdade());
            // Atualize outros campos do filho conforme necessário

            return filhoRepository.save(existingFilho);
        }
        return null;
    }

    public boolean deleteFilho(Long paiId, Long filhoId) {
        Pai pai = paiRepository.findById(paiId).orElse(null);
        Filho filho = filhoRepository.findById(filhoId).orElse(null);
        if (pai != null && filho != null && filho.getPai().equals(pai)) {
            filhoRepository.delete(filho);
            return true;
        }
        return false;
    }
}
