package tn.esprit.autoloc.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.Entities.CategorieVehicule;
import tn.esprit.autoloc.Entities.Employe;
import tn.esprit.autoloc.Entities.StatutVehicule;
import tn.esprit.autoloc.Entities.Vehicule;
import tn.esprit.autoloc.Services.IVehicule;
import tn.esprit.autoloc.Services.VehiculeServiceImpl;

import java.util.List;

@RestController
@Tag(name="Gestion Vehicule")
@RequestMapping("/api/Vehicule")
@RequiredArgsConstructor
public class VehiculeController {
    private final IVehicule iVehicule;
    @Operation(summary="ajouter vehicule dans la BD")
    @PostMapping("/addVehicule")
    public ResponseEntity<Vehicule> ajouter(
            @Valid @RequestBody Vehicule vehicule) {

        Vehicule nouveau =
                iVehicule.ajouterVehicule(vehicule);

        return ResponseEntity.status(
                HttpStatus.CREATED)
                .body(nouveau);
    }

    @GetMapping("/GetAll")
    public ResponseEntity<List<Vehicule>> afficherTous() {
        return ResponseEntity.ok(
                iVehicule.afficherVehicules());
    }

    @GetMapping("/GetById/{id}")
    public ResponseEntity<Vehicule> afficherParId(
            @Parameter(description ="l'identifiant du vehicule")
            @PathVariable Long id) {
        return ResponseEntity.ok(
                iVehicule.afficherVehicule(id));
    }

    @PutMapping("/PutById/{id}")
    public ResponseEntity<Vehicule> modifier(
            @PathVariable Long id,
            @Valid @RequestBody Vehicule vehicule) {

        return ResponseEntity.ok(
                iVehicule.modifierVehicule(id, vehicule));
    }

    @DeleteMapping("/Delete/{id}")
    public void supprimer(
            @PathVariable Long id) {

        iVehicule.supprimerVehicule(id);

    }
    @PutMapping("/affecterVehicule/{idVehicule}/{idAgence}")
    public Vehicule affecterVehicule(@PathVariable Long idVehicule,
                                  @PathVariable Long idAgence){
        return iVehicule.affecterVehicule(idVehicule,idAgence);
    }


    @GetMapping("/marque/{marque}")
    public List<Vehicule> parMarque(@PathVariable String marque) {
        return iVehicule.parMarque(marque);
    }


    @GetMapping("/categorie/{categorie}")
    public List<Vehicule> parCategorie(@PathVariable CategorieVehicule categorie) {
        return iVehicule.parCategorie(categorie);
    }

    @GetMapping("/disponibles")
    public List<Vehicule> disponibles() {
        return iVehicule.disponibles();
    }


    @GetMapping("/recherche/marque")
    public List<Vehicule> marqueContient(@RequestParam String q) {
        return iVehicule.marqueContient(q);
    }


    @GetMapping("/recherche/modele")
    public List<Vehicule> modeleCommencePar(@RequestParam String q) {
        return iVehicule.modeleCommencePar(q);
    }


    @GetMapping("/tarif/superieur")
    public List<Vehicule> sup(@RequestParam(defaultValue = "100") double valeur) {
        return iVehicule.tarifSuperieurA(valeur);
    }

    @GetMapping("/tarif/inferieur")
    public List<Vehicule> inf(@RequestParam(defaultValue = "50") double valeur) {
        return iVehicule.tarifInferieurA(valeur);
    }

    @GetMapping("/tarif/minimum")
    public List<Vehicule> min(@RequestParam(defaultValue = "80") double valeur) {
        return iVehicule.tarifAuMoins(valeur);
    }

    @GetMapping("/filtre/marque-statut")
    public List<Vehicule> marqueStatut(@RequestParam String marque, @RequestParam StatutVehicule statut) {
        return iVehicule.marqueEtStatut(marque, statut);
    }

    @GetMapping("/suv-ou-berline")
    public List<Vehicule> suvOuBerline() {
        return iVehicule.suvOuBerline();
    }

    @GetMapping("/filtre/marque-tarif")
    public List<Vehicule> marqueTarif(@RequestParam String marque, @RequestParam(defaultValue = "100") double max) {
        return iVehicule.marqueEtTarifMax(marque, max);
    }

    @GetMapping("/disponibles/tri-tarif-asc")
    public List<Vehicule> dispoAsc() {
        return iVehicule.disponiblesTriesCroissant();
    }

    @GetMapping("/tri-tarif-desc")
    public List<Vehicule> triDesc() {
        return iVehicule.tousTriesDecroissant();
    }






}
