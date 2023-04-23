package com.nice.ligue.dto;

import com.nice.ligue.entities.Joueur;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Joueur dto.
 */
@Accessors(chain = true)
@Data
public class JoueurDTO {
    @NotNull
    private String name;
    @NotNull
    private String position;

    /**
     * From joueur dto.
     *
     * @param model the model
     * @return the joueur dto
     */
    public static JoueurDTO from(Joueur model) {
        if (model == null) {
            return null;
        }
        return new JoueurDTO()
                .setName(model.getName())
                .setPosition(model.getPosition());
    }

    /**
     * To joueur.
     *
     * @param dto the dto
     * @return the joueur
     */
    public static Joueur to(JoueurDTO dto) {
        if (dto == null) {
            return null;
        }
        Joueur joueur = new Joueur();
        joueur.setName(dto.name);
        joueur.setPosition(dto.getPosition());
        return joueur;
    }








}