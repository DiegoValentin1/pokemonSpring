package mx.edu.utez.pokemon.controllers.pokemon.dtos;

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
public class PokemonDto {
    private long id;
    private String name;
    private String type;
    private int power;
    private int health;
    private int damage;
    private int defense;
    private Trainer trainer;

    public Pokemon getPokemon(){
        return new Pokemon(
                getId(),
                getName(),
                getType(),
                getPower(),
                getHealth(),
                getDamage(),
                getDefense(),
                getTrainer()
        );
    }
}
