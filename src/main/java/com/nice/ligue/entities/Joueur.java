package com.nice.ligue.entities;

import lombok.experimental.Accessors;

import javax.persistence.*;

/**
 * The type Joueur.
 */
@Entity
@Accessors(chain = true)
public class Joueur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String position;

    @ManyToOne
    @JoinColumn(name = "equipe_id", nullable = false)
    private Equipe equipe;

    /**
     * Instantiates a new Joueur.
     *
     * @param id       the id
     * @param name     the name
     * @param position the position
     * @param equipe   the equipe
     */
    public Joueur(Long id, String name, String position, Equipe equipe) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.equipe = equipe;
    }

    /**
     * Instantiates a new Joueur.
     *
     * @param id       the id
     * @param name     the name
     * @param position the position
     */
    public Joueur(long id, String name, String position) {
        this.id = id;
        this.name = name;
        this.position = position;
    }

    /**
     * Instantiates a new Joueur.
     */
    public Joueur() {
    }

    /**
     * Instantiates a new Joueur.
     *
     * @param name     the name
     * @param position the position
     */
    public Joueur(String name, String position) {
        this.name = name;
        this.position = position;
    }
    // Getters et Setters

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * Gets equipe.
     *
     * @return the equipe
     */
    public Equipe getEquipe() {
        return equipe;
    }

    /**
     * Sets equipe.
     *
     * @param equipe the equipe
     */
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }
}