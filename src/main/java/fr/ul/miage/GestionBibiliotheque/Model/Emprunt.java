package fr.ul.miage.GestionBibiliotheque.Model;

import fr.ul.miage.GestionBibiliotheque.Model.Usager;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@DiscriminatorValue("EMPRUNT")
public class Emprunt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Date dateDebut;
    private Date dateFin;
    @ManyToOne
    @JoinColumn(name = "usager_id")
    @JsonIgnore
    private Usager usager;
    @ManyToOne
    @JoinColumn(name = "exemplaire_id")
    private Exemplaire exemplaire;
}