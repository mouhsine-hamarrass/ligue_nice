package com.nice.ligue.repository;

import com.nice.ligue.entities.Joueur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Joueur repository.
 */
@Repository
public interface JoueurRepository extends JpaRepository<Joueur, Long> {
}