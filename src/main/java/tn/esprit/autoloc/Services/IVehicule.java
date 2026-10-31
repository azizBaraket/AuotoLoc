package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.CategorieVehicule;
import tn.esprit.autoloc.Entities.StatutVehicule;
import tn.esprit.autoloc.Entities.Vehicule;

import java.util.List;

public interface IVehicule {
    Vehicule ajouterVehicule(Vehicule vehicule);

    List<Vehicule> afficherVehicules();

    Vehicule afficherVehicule(Long id);

    Vehicule modifierVehicule(Long id, Vehicule vehicule);

    void supprimerVehicule(Long id);
    public Vehicule affecterVehicule(Long idVehicule, Long idAgence);

    List<Vehicule> parMarque(String marque);


    List<Vehicule> parCategorie(CategorieVehicule c);



    List<Vehicule> disponibles();


    List<Vehicule> marqueContient(String s);


    List<Vehicule> modeleCommencePar(String s);


    List<Vehicule> tarifSuperieurA(double t);


    List<Vehicule> tarifInferieurA(double t);


    List<Vehicule> tarifAuMoins(double t);


    List<Vehicule> marqueEtStatut(String marque, StatutVehicule statut);


    List<Vehicule> suvOuBerline();


    List<Vehicule> marqueEtTarifMax(String marque, double t);


    List<Vehicule> disponiblesTriesCroissant();


    List<Vehicule> tousTriesDecroissant();

}
