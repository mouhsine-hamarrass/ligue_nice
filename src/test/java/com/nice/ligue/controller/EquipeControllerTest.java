package com.nice.ligue.controller;

import com.nice.ligue.common.CommonTest;
import com.nice.ligue.dto.EquipeDTO;
import com.nice.ligue.dto.JoueurDTO;
import com.nice.ligue.entities.Equipe;
import com.nice.ligue.entities.Joueur;
import com.nice.ligue.repository.EquipeRepository;
import com.nice.ligue.repository.JoueurRepository;
import com.nice.ligue.service.EquipeService;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class EquipeControllerTest extends CommonTest {

    @InjectMocks
    private EquipeService equipeService;

    @Mock
    private EquipeRepository equipeRepository;

    @Mock
    private JoueurRepository joueurRepository;

    @Test
    public void testGetEquipes() {
        // Création de données de test
        List<EquipeDTO> equipeDTOList = new ArrayList<>();
        equipeDTOList.add(new EquipeDTO().setName("Equipe1").setAcronym("E1").setJoueursDTO(Arrays.asList(new JoueurDTO().setName("joueur11").setPosition("Attaquant"), new JoueurDTO().setName("joueur12").setPosition("defenseur"))).setBudget(1000000d));
        equipeDTOList.add(new EquipeDTO().setName("Equipe2").setAcronym("E2").setJoueursDTO(Arrays.asList(new JoueurDTO().setName("joueur13").setPosition("milieu"), new JoueurDTO().setName("joueur14").setPosition("arrièresGauches"))).setBudget(2000000d));
        Pageable pageable = PageRequest.of(0, 10, Sort.by("name").and(Sort.by("acronym")).and(Sort.by("budget")));
        PageImpl<Equipe> pageEquipes = new PageImpl(equipeDTOList.stream().map(EquipeDTO::to).collect(Collectors.toList()));

        Mockito.when(equipeRepository.findAll(pageable)).thenReturn(pageEquipes);

        // Appel de la méthode à tester
        List<EquipeDTO> result = new ArrayList<>(equipeService.getEquipes(0, 10).getContent());

        // Vérification des résultats
        Assertions.assertEquals(2, pageEquipes.getTotalElements());
        Assertions.assertEquals("Equipe1", result.get(0).getName());
        Assertions.assertEquals("Equipe2", result.get(1).getName());
        Assertions.assertEquals("joueur11", result.get(0).getJoueursDTO().get(0).getName());
        Assertions.assertEquals("joueur14", result.get(1).getJoueursDTO().get(1).getName());
    }

    @Test
    public void testAddEquipe() {
        // Création de données de test
        Equipe equipe = new Equipe(1L, "Equipe1", "E1", new ArrayList<>(), 1000000d);

        // Mocking de la méthode du repository
        Mockito.when(equipeRepository.save(Mockito.any(Equipe.class))).thenReturn(equipe);
        List<JoueurDTO> joueurDTOList = new ArrayList<>();
        joueurDTOList.add(new JoueurDTO().setName("joueur14").setPosition("arrièresGauches"));
        equipe.setJoueurs(joueurDTOList.stream().map(JoueurDTO::to).collect(Collectors.toList()));
        // Appel de la méthode du service à tester
        EquipeDTO result = equipeService.addEquipe(EquipeDTO.from(equipe));

        // Vérification des résultats
        Assertions.assertEquals("Equipe1", result.getName());
        Assertions.assertEquals("E1", result.getAcronym());
        Assertions.assertEquals(1000000L, result.getBudget().longValue());
        Assertions.assertEquals("joueur14", result.getJoueursDTO().get(0).getName());
    }

    @Test
    public void testGetEquipeById() {
        // Création de données de test
        Equipe equipe = new Equipe(1L, "Equipe1", "E1", new ArrayList<>(), 1000000d);

        // Mocking de la méthode du repository
        Mockito.when(equipeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(equipe));

        // Appel de la méthode du service à tester
        Equipe result = equipeService.getEquipeById(1L);

        // Vérification des résultats
        Assertions.assertEquals("Equipe1", result.getName());
        Assertions.assertEquals("E1", result.getAcronym());
        Assertions.assertEquals(1000000L, result.getBudget().longValue());
    }

    @Test
    public void testAddJoueur() {
        // Création de données de test
        Equipe equipe = new Equipe(1L, "Equipe1", "E1", new ArrayList<>(), 1000000d);
        Joueur joueur = new Joueur(1L, "Joueur1", "Attaquant");

        // Mocking de la méthode du repository
        Mockito.when(equipeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(equipe));
        Mockito.when(joueurRepository.save(Mockito.any(Joueur.class))).thenReturn(joueur);

        // Appel de la méthode du service à tester
        Joueur result = equipeService.addJoueur(1L, joueur);

        // Vérification des résultats
        Assertions.assertEquals("Joueur1", result.getName());
        Assertions.assertEquals("Attaquant", result.getPosition());
    }
}