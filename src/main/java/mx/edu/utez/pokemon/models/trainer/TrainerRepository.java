package mx.edu.utez.pokemon.models.trainer;

import mx.edu.utez.pokemon.models.pokemon.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByName(String name);
}
