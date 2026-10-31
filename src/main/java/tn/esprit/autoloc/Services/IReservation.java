package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Reservation;

import java.time.LocalDate;
import java.util.List;

public interface IReservation {
    List<Reservation> debutApres(LocalDate d);
    List<Reservation> finAvant(LocalDate d);

}
