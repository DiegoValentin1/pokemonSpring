package mx.edu.utez.pokemon.controllers.trainer;

import mx.edu.utez.pokemon.controllers.pokemon.dtos.PokemonDto;
import mx.edu.utez.pokemon.controllers.trainer.dtos.TrainerDto;
import mx.edu.utez.pokemon.models.pokemon.Pokemon;
import mx.edu.utez.pokemon.models.trainer.Trainer;
import mx.edu.utez.pokemon.services.pokemon.PokemonService;
import mx.edu.utez.pokemon.services.trainer.TrainerService;
import mx.edu.utez.pokemon.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api-pokemon/trainer")
@CrossOrigin(origins = {"*"})
public class TrainerController {
    @Autowired
    private TrainerService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Trainer>>> getAll(){
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Response<Trainer>>getOne(@PathVariable long id ){
        return new ResponseEntity<>(
                this.service.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Response<Trainer>>insert(
            @RequestBody TrainerDto trainer
    ){
        return new ResponseEntity<>(
                this.service.insert(trainer.getTrainer()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Trainer>>update(@PathVariable long id ,@RequestBody TrainerDto trainer){

        return new ResponseEntity<>(
                this.service.update(trainer.getTrainer(),id),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Trainer>>delete(@PathVariable long id){
        this.service.delete(id);
        return new ResponseEntity<>(
                null,
                HttpStatus.CREATED
        );
    }
}
