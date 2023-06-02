package org.example.service;

import org.example.entity.Filho;
import org.example.entity.Pai;
import org.example.repository.FilhoRepository;
import org.example.repository.PaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaiService {
    private final PaiRepository paiRepository;
    private final FilhoRepository filhoRepository;

    @Autowired
    public PaiService(PaiRepository paiRepository, FilhoRepository filhoRepository) {
        this.paiRepository = paiRepository;
        this.filhoRepository = filhoRepository;
    }

    public List<Pai> getAllPais() {
        return paiRepository.findAll();
    }

    public Pai getPaiById(Long id) {
        return paiRepository.findById(id).orElse(null);
    }

    public Pai createPai(Pai pai) {
        return paiRepository.save(pai);
    }

    public Pai updatePai(Long id, Pai pai) {
        Pai existingPai = paiRepository.findById(id).orElse(null);
        if (existingPai != null) {
            existingPai.setNome(pai.getNome());
            // Atualize outros campos do pai conforme necessário

            return paiRepository.save(existingPai);
        }
        return null;
    }

    public boolean deletePai(Long id) {
        if (paiRepository.existsById(id)) {
            paiRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Filho> getFilhosByPaiId(Long paiId) {
        Pai pai = paiRepository.findById(paiId).orElse(null);
        if (pai != null) {
            return pai.getFilhos();
        }
        return null;
    }

    public Filho createFilho(Long paiId, Filho filho) {
        Pai pai = paiRepository.findById(paiId).orElse(null);
        if (pai != null) {
            filho.setPai(pai);
            return filhoRepository.save(filho);
        }
        return null;
    }

    public boolean deleteFilho(Long paiId, Long filhoId) {
        Pai pai = paiRepository.findById(paiId).orElse(null);
        if (pai != null) {
            Filho filho = filhoRepository.findById(filhoId).orElse(null);
            if (filho != null && filho.getPai().equals(pai)) {
                filhoRepository.deleteById(filhoId);
                return true;
            }
        }
        return false;
    }
}
