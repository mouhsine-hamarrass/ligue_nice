package com.nice.ligue.dto;

import com.nice.ligue.entities.Equipe;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Equipe dto.
 */
@Data
@Accessors(chain = true)
public class EquipeDTO {

    @NotNull
    private String name;
    @NotNull
    private String acronym;
    private List<JoueurDTO> joueursDTO;
    @NotNull
    private Double budget;


    /**
     * From equipe dto.
     *
     * @param model the model
     * @return the equipe dto
     */
    public static EquipeDTO from(Equipe model) {
        if (model == null) {
            return null;
        }

        return new EquipeDTO()
                .setBudget(model.getBudget())
                .setAcronym(model.getAcronym())
                .setName(model.getName())
                .setJoueursDTO(model.getJoueurs().stream().map(JoueurDTO::from).collect(Collectors.toList()));
    }

    /**
     * To equipe.
     *
     * @param dto the dto
     * @return the equipe
     */
    public static Equipe to(EquipeDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Equipe()
                .setAcronym(dto.getAcronym())
                .setName(dto.getName())
                .setBudget(dto.getBudget())
                .setJoueurs(dto.joueursDTO.stream().map(JoueurDTO::to).collect(Collectors.toList()));
    }


}