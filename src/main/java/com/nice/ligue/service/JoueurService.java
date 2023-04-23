package com.nice.ligue.service;

import com.nice.ligue.dto.JoueurDTO;
import com.nice.ligue.entities.Joueur;
import com.nice.ligue.repository.JoueurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

/**
 * The type Joueur service.
 */
@Slf4j
@Service
public class JoueurService {

    @Autowired
    private JoueurRepository joueurRepository;

    /**
     * Gets joueurs.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @param sortBy   the sort by
     * @return the joueurs
     */
    public Page<JoueurDTO> getJoueurs(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Joueur> joueurs = joueurRepository.findAll(pageable);
        log.info("getJoueurs: Les données ont été récupérées avec succès.");
        return joueurs.map(JoueurDTO::from);
    }

    /**
     * Gets joueur by id.
     *
     * @param joueurId the joueur id
     * @return the joueur by id
     */
    public Joueur getJoueurById(Long joueurId) {
        log.info("getJoueurById: Les données ont été récupérées avec succès.");
        return joueurRepository.findById(joueurId)
                .orElseThrow(() -> new NoSuchElementException("Joueur not found with id " + joueurId));
    }

    /**
     * Update joueur joueur dto.
     *
     * @param joueurId  the joueur id
     * @param joueurDTO the joueur dto
     * @return the joueur dto
     */
    public JoueurDTO updateJoueur(Long joueurId, JoueurDTO joueurDTO) {
        Joueur existingJoueur = joueurRepository.findById(joueurId)
                .orElseThrow(() -> new NoSuchElementException("Joueur not found with id " + joueurId));

        existingJoueur.setName(joueurDTO.getName());
        existingJoueur.setPosition(joueurDTO.getPosition());
        log.info("updateJoueur: Les données ont été modifiées avec succès.");
        return JoueurDTO.from(joueurRepository.save(existingJoueur));
    }

    /**
     * Delete joueur.
     *
     * @param joueurId the joueur id
     */
    public void deleteJoueur(Long joueurId) {
        log.info("updateJoueur: Les données ont été supprimées avec succès.");
        joueurRepository.deleteById(joueurId);
    }
}
