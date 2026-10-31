package tn.esprit.autoloc.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.autoloc.Entities.CategorieVehicule;
import tn.esprit.autoloc.Entities.Client;
import tn.esprit.autoloc.Entities.StatutVehicule;
import tn.esprit.autoloc.Entities.Vehicule;

import java.util.List;

@Repository
public interface VehiculeRepo extends JpaRepository<Vehicule,Long> {
    List<Vehicule> findByMarque(String marque);
    List<Vehicule> findByCategorie(CategorieVehicule categorie);
    List<Vehicule> findByStatut(StatutVehicule statut);
    List<Vehicule> findByMarqueContainingIgnoreCase(String s);
    List<Vehicule> findByModeleStartingWithIgnoreCase(String s);
    List<Vehicule> findByTarifJournalierGreaterThan(double t);
    List<Vehicule> findByTarifJournalierLessThan(double t);
    List<Vehicule> findByTarifJournalierGreaterThanEqual(double t);
    List<Vehicule> findByMarqueAndStatut(String marque, StatutVehicule statut);
    List<Vehicule> findByCategorieOrCategorie(CategorieVehicule c1, CategorieVehicule c2);
    List<Vehicule> findByMarqueAndTarifJournalierLessThan(String marque, double t);
    List<Vehicule> findByStatutOrderByTarifJournalierAsc(StatutVehicule statut);
    List<Vehicule> findAllByOrderByTarifJournalierDesc();
}
