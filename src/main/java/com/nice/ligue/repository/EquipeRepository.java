package com.nice.ligue.repository;

import com.nice.ligue.entities.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Equipe repository.
 */
@Repository
public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}