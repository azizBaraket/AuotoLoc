package tn.esprit.autoloc.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.*;
import tn.esprit.autoloc.Repositories.AgenceRepo;
import tn.esprit.autoloc.Repositories.EquipementRepo;
import tn.esprit.autoloc.Repositories.VehiculeRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class VehiculeServiceImpl implements IVehicule{
    private final VehiculeRepo  vehiculeRepo;
    private final AgenceRepo agenceRepo;
    private final EquipementRepo equipementRepo;

    @Override
    public Vehicule ajouterVehicule(Vehicule vehicule) {
        return vehiculeRepo.save(vehicule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> afficherVehicules() {
        return vehiculeRepo.findAll();
    }

    @Override
    public Vehicule afficherVehicule(Long id) {
        return vehiculeRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Véhicule introuvable avec l'identifiant : " + id
                        ));
    }

    @Override
    public Vehicule modifierVehicule(Long id, Vehicule vehicule) {

        Vehicule vehiculeExistant = vehiculeRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Véhicule introuvable avec l'identifiant : " + id
                        ));

        vehiculeExistant.setImmatriculation(
                vehicule.getImmatriculation());
        vehiculeExistant.setMarque(vehicule.getMarque());
        vehiculeExistant.setModele(vehicule.getModele());
        vehiculeExistant.setCategorie(vehicule.getCategorie());
        vehiculeExistant.setTarifJournalier(
                vehicule.getTarifJournalier());
        vehiculeExistant.setStatut(vehicule.getStatut());

        return vehiculeRepo.save(vehiculeExistant);
    }

    @Override
    public void supprimerVehicule(Long id) {

        Vehicule vehicule = vehiculeRepo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Véhicule introuvable avec l'identifiant : " + id
                        ));
        vehiculeRepo.deleteById(id);

    }

    @Override
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence) {
        Vehicule vehicule = vehiculeRepo.findById(idVehicule)
                .orElseThrow();
        Agence agence = agenceRepo.findById(idAgence)
                .orElseThrow();
        vehicule.setAgence(agence);
        vehicule.setStatut(StatutVehicule.DISPONIBLE);
        return vehiculeRepo.save(vehicule);
    }


    @Override
    public  List<Vehicule> parMarque(String m) {
        return vehiculeRepo.findByMarque(m);
    }
    @Override
    public  List<Vehicule> parCategorie(CategorieVehicule c) {
        return vehiculeRepo.findByCategorie(c);
    }
    @Override
    public List<Vehicule> disponibles() {
        return vehiculeRepo.findByStatut(StatutVehicule.DISPONIBLE);
    }
    @Override
    public List<Vehicule> marqueContient(String s) {
        return vehiculeRepo.findByMarqueContainingIgnoreCase(s);
    }
    @Override
    public List<Vehicule> modeleCommencePar(String s) {
        return vehiculeRepo.findByModeleStartingWithIgnoreCase(s);
    }
    @Override
    public List<Vehicule> tarifSuperieurA(double t) {
        return vehiculeRepo.findByTarifJournalierGreaterThan(t);
    }
    @Override
    public List<Vehicule> tarifInferieurA(double t) {
        return vehiculeRepo.findByTarifJournalierLessThan(t);
    }
    @Override
    public List<Vehicule> tarifAuMoins(double t) {
        return vehiculeRepo.findByTarifJournalierGreaterThanEqual(t);
    }
    @Override
    public List<Vehicule> marqueEtStatut(String m, StatutVehicule s) {
        return vehiculeRepo.findByMarqueAndStatut(m, s);
    }
    @Override
    public List<Vehicule> suvOuBerline() {
        return vehiculeRepo.findByCategorieOrCategorie(CategorieVehicule.SUV, CategorieVehicule.BERLINE);
    }
    @Override
    public List<Vehicule> marqueEtTarifMax(String m, double t) {
        return vehiculeRepo.findByMarqueAndTarifJournalierLessThan(m, t);
    }
    @Override
    public List<Vehicule> disponiblesTriesCroissant() {
        return vehiculeRepo.findByStatutOrderByTarifJournalierAsc(StatutVehicule.DISPONIBLE);
    }
    @Override
    public List<Vehicule> tousTriesDecroissant() {
        return vehiculeRepo.findAllByOrderByTarifJournalierDesc();
    }
    }

