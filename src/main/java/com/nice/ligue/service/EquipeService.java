package com.nice.ligue.service;

import com.nice.ligue.dto.EquipeDTO;
import com.nice.ligue.dto.JoueurDTO;
import com.nice.ligue.entities.Equipe;
import com.nice.ligue.entities.Joueur;
import com.nice.ligue.repository.EquipeRepository;
import com.nice.ligue.repository.JoueurRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The type Equipe service.
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class EquipeService {


    private final EquipeRepository equipeRepository;

    private final  JoueurRepository joueurRepository;

    /**
     * Gets equipes.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the equipes
     */
    public Page<EquipeDTO> getEquipes(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize,
                Sort.by("name")
                        .and(Sort.by("acronym"))
                        .and(Sort.by("budget")));
        Page<Equipe> equipees = equipeRepository.findAll(pageable);
        log.info("getEquipes: Les données ont été récupérées avec succès.");
        return equipees.map(EquipeDTO::from);
    }

    /**
     * Add equipe equipe dto.
     *
     * @param equipe the equipe
     * @return the equipe dto
     */
    public EquipeDTO addEquipe(EquipeDTO equipe) {
        if (equipe == null) {
            return null;
        }
        List<JoueurDTO> joueurDTOList = equipe.getJoueursDTO();
        equipe.setJoueursDTO(new ArrayList<>());
        Equipe equipe1 = EquipeDTO.to(equipe);
        Equipe equipe2 = equipeRepository.save(equipe1);
        // Vérifier si l'équipe a des joueurs associés
        if (joueurDTOList != null && !joueurDTOList.isEmpty()) {
            // Associer l'équipe aux joueurs
            for (JoueurDTO joueurDTO : joueurDTOList) {
                Joueur joueur = JoueurDTO.to(joueurDTO);
                joueur.setEquipe(equipe2);
                Joueur joueur1 = joueurRepository.save(joueur);
                equipe2.getJoueurs().add(joueur1);
            }
        }
        log.info("addEquipe: Les données ont été ajoutées avec succès.");
        return EquipeDTO.from(equipe2);


    }

    /**
     * Add joueur joueur.
     *
     * @param equipeId the equipe id
     * @param joueur   the joueur
     * @return the joueur
     */
    public Joueur addJoueur(Long equipeId, Joueur joueur) {
        // Récupérer l'équipe par son identifiant
        Equipe equipe = equipeRepository.findById(equipeId)
                .orElseThrow(() -> new NoSuchElementException("Equipe not found with id " + equipeId));

        // Associer le joueur à l'équipe
        joueur.setEquipe(equipe);
        log.info("addJoueur: Les données ont été ajoutées avec succès.");
        return joueurRepository.save(joueur);
    }

    /**
     * Gets equipe by id.
     *
     * @param equipeId the equipe id
     * @return the equipe by id
     */
    public Equipe getEquipeById(Long equipeId) {
        log.info("getEquipeById: Les données ont été récupérées avec succès..");
        return equipeRepository.findById(equipeId)
                .orElseThrow(() -> new NoSuchElementException("equipeId not found with id " + equipeId));
    }
}