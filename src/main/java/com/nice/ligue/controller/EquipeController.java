package com.nice.ligue.controller;

import com.nice.ligue.dto.EquipeDTO;
import com.nice.ligue.entities.Equipe;
import com.nice.ligue.entities.Joueur;
import com.nice.ligue.service.EquipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * The type Equipe controller.
 */
@Slf4j
@RestController
@RequestMapping("/api/equipes")
@RequiredArgsConstructor
public class EquipeController {

    private final EquipeService equipeService;

    /**
     * Gets equipes.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @return the equipes
     */
    @GetMapping("/getEquipes")
    public ResponseEntity<Page<EquipeDTO>> getEquipes(@RequestParam(defaultValue = "0") int pageNo,
                                                      @RequestParam(defaultValue = "10") int pageSize) {

        Page<EquipeDTO> equipes = equipeService.getEquipes(pageNo, pageSize);
        return ResponseEntity.ok().body(equipes);


    }


    /**
     * Add equipe response entity.
     *
     * @param equipe the equipe
     * @return the response entity
     */
    @PostMapping("/addEquipe")
    public ResponseEntity<EquipeDTO> addEquipe(@RequestBody EquipeDTO equipe) {
        EquipeDTO addedEquipe = equipeService.addEquipe(equipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEquipe);
    }

    /**
     * Gets equipe by id.
     *
     * @param equipeId the equipe id
     * @return the equipe by id
     */
    @GetMapping("/getEquipeById/{id}")
    public ResponseEntity<Equipe> getEquipeById(@PathVariable("id") Long equipeId) {
        Equipe equipe = equipeService.getEquipeById(equipeId);
        return ResponseEntity.ok().body(equipe);
    }

    /**
     * Add joueur to equipe response entity.
     *
     * @param equipeId the equipe id
     * @param joueur   the joueur
     * @return the response entity
     */
    @PostMapping("/addJoueurToEquipe/{id}")
    public ResponseEntity<Joueur> addJoueurToEquipe(@PathVariable("id") Long equipeId,
                                                    @RequestBody Joueur joueur) {
        Joueur addedJoueur = equipeService.addJoueur(equipeId, joueur);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedJoueur);
    }

}
