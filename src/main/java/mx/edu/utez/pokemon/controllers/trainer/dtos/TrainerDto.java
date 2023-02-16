package mx.edu.utez.pokemon.controllers.trainer.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.pokemon.models.pokemon.Pokemon;
import mx.edu.utez.pokemon.models.trainer.Trainer;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class TrainerDto {
    private long id;
    private String name;
    private String team;

    public Trainer getTrainer(){
        return new Trainer(
                getId(),
                getName(),
                getTeam(),
                null
        );
    }
}
