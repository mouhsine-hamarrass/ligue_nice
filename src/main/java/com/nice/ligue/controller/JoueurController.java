package com.nice.ligue.controller;

import com.nice.ligue.dto.JoueurDTO;
import com.nice.ligue.service.JoueurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The type Joueur controller.
 */
@RestController
@RequestMapping("/api/joueurs")
public class JoueurController {

    @Autowired
    private JoueurService joueurService;

    /**
     * Gets joueurs.
     *
     * @param pageNo   the page no
     * @param pageSize the page size
     * @param sortBy   the sort by
     * @return the joueurs
     */
// Endpoint pour récupérer la liste de joueurs paginée et triée
    @GetMapping("/getJoueurs")
    public ResponseEntity<Page<JoueurDTO>> getJoueurs(@RequestParam(defaultValue = "0") int pageNo,
                                                      @RequestParam(defaultValue = "10") int pageSize,
                                                      @RequestParam(defaultValue = "id") String sortBy) {

        Page<JoueurDTO> joueurs = joueurService.getJoueurs(pageNo, pageSize, sortBy);
        return ResponseEntity.ok().body(joueurs);
    }

    /**
     * Update joueur response entity.
     *
     * @param joueurId  the joueur id
     * @param joueurDTO the joueur dto
     * @return the response entity
     */
// Endpoint pour mettre à jour les informations d'un joueur par son identifiant
    @PutMapping("updateJoueur/{id}")
    public ResponseEntity<JoueurDTO> updateJoueur(@PathVariable("id") Long joueurId,
                                                  @RequestBody JoueurDTO joueurDTO) {
        JoueurDTO updatedJoueur = joueurService.updateJoueur(joueurId, joueurDTO);
        return ResponseEntity.ok().body(updatedJoueur);
    }

    /**
     * Delete joueur response entity.
     *
     * @param joueurId the joueur id
     * @return the response entity
     */
// Endpoint pour supprimer un joueur par son identifiant
    @DeleteMapping("deleteJoueur/{id}")
    public ResponseEntity<String> deleteJoueur(@PathVariable("id") Long joueurId) {
        joueurService.deleteJoueur(joueurId);
        return ResponseEntity.ok().body("Joueur with id " + joueurId + " has been deleted");
    }
}
