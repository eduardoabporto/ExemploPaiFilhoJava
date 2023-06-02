package org.example.controller;
import org.example.entity.Pai;
import org.example.service.PaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pais")
public class PaiController {
    private final PaiService paiService;

    @Autowired
    public PaiController(PaiService paiService) {
        this.paiService = paiService;
    }

    @GetMapping
    public ResponseEntity<List<Pai>> getAllPais() {
        List<Pai> pais = paiService.getAllPais();
        return ResponseEntity.ok(pais);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pai> getPaiById(@PathVariable Long id) {
        Pai pai = paiService.getPaiById(id);
        if (pai != null) {
            return ResponseEntity.ok(pai);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Pai> createPai(@RequestBody Pai pai) {
        Pai createdPai = paiService.createPai(pai);
        return new ResponseEntity<>(createdPai, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pai> updatePai(@PathVariable Long id, @RequestBody Pai pai) {
        Pai updatedPai = paiService.updatePai(id, pai);
        if (updatedPai != null) {
            return ResponseEntity.ok(updatedPai);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePai(@PathVariable Long id) {
        boolean deleted = paiService.deletePai(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


