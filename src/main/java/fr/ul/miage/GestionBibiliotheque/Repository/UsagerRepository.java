package fr.ul.miage.GestionBibiliotheque.Repository;

import fr.ul.miage.GestionBibiliotheque.Model.Usager;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsagerRepository  extends JpaRepository<Usager, UUID> {
    Optional<Usager> findByEmail(String email);
}
