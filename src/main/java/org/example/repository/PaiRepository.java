package org.example.repository;

import org.example.entity.Pai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaiRepository extends JpaRepository<Pai, Long> {
}
