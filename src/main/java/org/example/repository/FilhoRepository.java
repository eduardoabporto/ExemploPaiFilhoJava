package org.example.repository;

import org.example.entity.Filho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilhoRepository extends JpaRepository<Filho, Long> {
    List<Filho> findByPaiId(Long paiId);
}
