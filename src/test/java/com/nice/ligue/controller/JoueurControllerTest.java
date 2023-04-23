package com.nice.ligue.controller;

import com.nice.ligue.common.CommonTest;
import com.nice.ligue.dto.JoueurDTO;
import com.nice.ligue.entities.Joueur;
import com.nice.ligue.repository.JoueurRepository;
import com.nice.ligue.service.JoueurService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class JoueurControllerTest extends CommonTest {

    @InjectMocks
    private JoueurService joueurService;

    @Mock
    private JoueurRepository joueurRepository;

    @Test
    public void testGetAllJoueurs() {
        // Préparation des données de test
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(new Joueur(1L, "Joueur1", "Attaquant"));
        joueurs.add(new Joueur(2L, "Joueur2", "Milieu"));
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id"));
        PageImpl<Joueur> pageJoueurs = new PageImpl<>(joueurs);
        Mockito.when(joueurRepository.findAll(pageable)).thenReturn(pageJoueurs);

        // Appel de la méthode à tester
        List<JoueurDTO> result = joueurService.getJoueurs(0, 10, "id").getContent().stream()
                .collect(Collectors.toList());

        // Vérification des résultats
        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals("Joueur1", result.get(0).getName());
        Assertions.assertEquals("Attaquant", result.get(0).getPosition());
        Assertions.assertEquals("Joueur2", result.get(1).getName());
        Assertions.assertEquals("Milieu", result.get(1).getPosition());
    }

    @Test
    public void testGetJoueurById() {
        // Préparation des données de test
        Mockito.when(joueurRepository.findById(1L)).thenReturn(Optional.of(JoueurDTO.to(new JoueurDTO().setName("Joueur1").setPosition("Attaquant"))));

        // Appel de la méthode à tester
        Joueur result = joueurService.getJoueurById(1L);

        // Vérification des résultats
        Assertions.assertEquals("Joueur1", result.getName());
        Assertions.assertEquals("Attaquant", result.getPosition());
    }

}