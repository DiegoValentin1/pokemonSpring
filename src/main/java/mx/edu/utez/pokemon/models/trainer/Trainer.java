package mx.edu.utez.pokemon.models.trainer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.pokemon.models.pokemon.Pokemon;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "trainers")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, length = 150)
    private String name;
    @Column(nullable = false, length = 150)
    private String team;
    @OneToMany(mappedBy = "trainer")
    @JsonIgnore
    private List<Pokemon> pokemons;
}
