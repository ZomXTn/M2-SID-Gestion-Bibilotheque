package fr.ul.miage.GestionBibiliotheque.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fr.ul.miage.GestionBibiliotheque.Model.Exemplaire;
import fr.ul.miage.GestionBibiliotheque.Model.Livre;
import fr.ul.miage.GestionBibiliotheque.Repository.LivreRepository;
import fr.ul.miage.GestionBibiliotheque.Service.ExemplaireService;
import fr.ul.miage.GestionBibiliotheque.Service.DTO.Livre.PostLivreDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping(value = "/livres")
public class LivreController {

    @Autowired
    LivreRepository livresRepository;

    @Autowired
    ExemplaireService exemplaireService;

    @GetMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Livre> getAllLives(){
        return this.livresRepository.findAll();
    }

    @GetMapping(value = "/{oeuvreID}/exemplaires")
    @ResponseStatus(value = HttpStatus.OK)
    public List<Exemplaire> getExemplaireOfLivre(@PathVariable("oeuvreID") UUID oeuvreID){
        return livresRepository.getReferenceById(oeuvreID).getListeExemplaires();
    }

    @PostMapping(value= "/")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Livre createLivre(@RequestBody @Valid PostLivreDTO livreDTO){
        Livre livre = livreDTO.toEntity();
        return this.livresRepository.save(livre);
    }

    @PostMapping(value = "/{oeuvreID}/exemplaire")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Exemplaire createNewExemplaire(@PathVariable("oeuvreID") UUID oeuvreID){
        return exemplaireService.createExemplaire(oeuvreID);
    }

    

    @DeleteMapping
    public ResponseEntity<String> deleteLivre(@RequestParam @NotBlank UUID id){
        this.livresRepository.deleteById(id);
        return ResponseEntity.ok("Livre supprimé avec succès");
    }

    
}
