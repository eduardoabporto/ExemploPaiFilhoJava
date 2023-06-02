package org.example.controller;

import org.example.entity.Filho;
import org.example.service.FilhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/pais/{paiId}/filhos")
public class FilhoController {
    private final FilhoService filhoService;

    @Autowired
    public FilhoController(FilhoService filhoService) {
        this.filhoService = filhoService;
    }

    @GetMapping
    public ResponseEntity<List<Filho>> getFilhosByPaiId(@PathVariable Long paiId) {
        List<Filho> filhos = filhoService.getFilhosByPaiId(paiId);
        return ResponseEntity.ok(filhos);
    }

    @GetMapping("/{filhoId}")
    public ResponseEntity<Filho> getFilhoById(@PathVariable Long paiId, @PathVariable Long filhoId) {
        Filho filho = filhoService.getFilhoById(filhoId);
        if (filho != null && filho.getPai().getId().equals(paiId)) {
            return ResponseEntity.ok(filho);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Filho> createFilho(@PathVariable Long paiId, @RequestBody Filho filho) {
        Filho createdFilho = filhoService.createFilho(paiId, filho);
        return new ResponseEntity<>(createdFilho, HttpStatus.CREATED);
    }

    @PutMapping("/{filhoId}")
    public ResponseEntity<Filho> updateFilho(@PathVariable Long paiId, @PathVariable Long filhoId, @RequestBody Filho filho) {
        Filho updatedFilho = filhoService.updateFilho(paiId, filhoId, filho);
        if (updatedFilho != null) {
            return ResponseEntity.ok(updatedFilho);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{filhoId}")
    public ResponseEntity<Void> deleteFilho(@PathVariable Long paiId, @PathVariable Long filhoId) {
        boolean deleted = filhoService.deleteFilho(paiId, filhoId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
