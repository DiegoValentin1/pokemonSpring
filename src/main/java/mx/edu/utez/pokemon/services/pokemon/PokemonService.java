package mx.edu.utez.pokemon.services.pokemon;

import mx.edu.utez.pokemon.models.pokemon.Pokemon;
import mx.edu.utez.pokemon.models.pokemon.PokemonRepository;
import mx.edu.utez.pokemon.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PokemonService {
    @Autowired
    private PokemonRepository repository;

    @Transactional(readOnly = true)
    public Response<List<Pokemon>> getAll(){
        return new Response<>(
                this.repository.findAll(),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public Response<Pokemon>getOne(Long id){
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
                "Pokemón no encontrado"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Pokemon> insert(Pokemon pokemon){
        Optional<Pokemon> exists=this.repository.findByName(pokemon.getName());
        if (exists.isPresent())
            return new Response<>(
                    null,
                    true,
                    400,
                    "El pokemon ya se encuentra registrado"
            );
        return new Response<>(
                this.repository.saveAndFlush(pokemon),
                false,
                200,
                "Pokemón registrado"
        );

    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Pokemon> update(Pokemon pokemon, long id){
        if (!this.repository.existsById(id))
            return new Response<>(
                    null,
                    true,
                    400,
                    "El pokemón no se encontró"
            );


        return new Response<>(
                this.repository.saveAndFlush(pokemon),
                false,
                200,
                "Pokemón actualizado correctamente"
        );

    }

    @Transactional(rollbackFor = {SQLException.class})
    public Response<Boolean> delete(Long id){
        if (!this.repository.existsById(id))
            return new Response<>(
                    null,
                    true,
                    400,
                    "El pokemón no se encontró"
            );
        this.repository.deleteById(id);
        return new Response<>(
                null,
                false,
                200,
                "Pokemón eliminado correctamente"
        );

    }
}
