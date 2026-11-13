package tn.esprit.autoloc.Scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import tn.esprit.autoloc.Services.ReservationServiceImpl;

@Component
@RequiredArgsConstructor
public class ReservationScheduler {
    private final ReservationServiceImpl reservationService;

    @Scheduled(fixedRate = 60000)
    public void verifierReservations() {
        reservationService.terminerReservationsExpirees();
    }
}