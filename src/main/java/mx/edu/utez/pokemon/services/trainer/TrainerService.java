package mx.edu.utez.pokemon.services.trainer;

import mx.edu.utez.pokemon.models.pokemon.Pokemon;
import mx.edu.utez.pokemon.models.trainer.Trainer;
import mx.edu.utez.pokemon.models.trainer.TrainerRepository;
import mx.edu.utez.pokemon.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TrainerService {
    @Autowired
    private TrainerRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Trainer>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public Response<Trainer>getOne(Long id){
        if (this.repository.existsById(id)){
            return new Response<>(
                    this.repository.findById(id).get(),
                    false,
                    200,
                    "OK"
            );
        }
        return new Response<>(
                null,
                true,
                400,
                "Entrenador no encontrado"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Trainer> insert(Trainer trainer){
        Optional<Trainer> exists=this.repository.findByName(trainer.getName());
        if (exists.isPresent())
            return new Response<>(
                    null,
                    true,
                    400,
                    "El entrenador ya se encuentra registrado"
            );
        return new Response<>(
                this.repository.saveAndFlush(trainer),
                false,
                200,
                "Entrenador registrado"
        );

    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Trainer> update(Trainer trainer, long id){
        if (!this.repository.existsById(id))
            return new Response<>(
                    null,
                    true,
                    400,
                    "El entrenador no se encontró"
            );


        return new Response<>(
                this.repository.saveAndFlush(trainer),
                false,
                200,
                "Entrenador actualizado correctamente"
        );

    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> delete(Long id){
        if (!this.repository.existsById(id))
            return new Response<>(
                    null,
                    true,
                    400,
                    "El entrenador no se encontró"
            );
        this.repository.deleteById(id);
        return new Response<>(
                null,
                false,
                200,
                "Entrenador eliminado correctamente"
        );

    }
}
