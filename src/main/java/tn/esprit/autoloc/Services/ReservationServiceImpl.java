package tn.esprit.autoloc.Services;

import com.jetbrains.exported.JBRApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Reservation;
import tn.esprit.autoloc.Entities.StatutReservation;
import tn.esprit.autoloc.Repositories.ReservationRepo;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
//@Transactional
public class ReservationServiceImpl implements IReservation {
    private final ReservationRepo reservationRepo;
    @Override
    public List<Reservation> debutApres(LocalDate d) {
        return reservationRepo.findByDateDebutAfter(d);
    }
    @Override
    public List<Reservation> finAvant(LocalDate d) {
        return reservationRepo.findByDateFinBefore(d);
    }
    @Transactional
    public void terminerReservationsExpirees() {
        List<Reservation> reservations =
                reservationRepo.findByStatutAndDateFinBefore(
                        StatutReservation.CONFIRMEE,
                        LocalDate.now()
                );
        for (Reservation reservation : reservations) {
            reservation.setStatut(StatutReservation.TERMINEE);
        }
        reservationRepo.saveAll(reservations);
    }
}
