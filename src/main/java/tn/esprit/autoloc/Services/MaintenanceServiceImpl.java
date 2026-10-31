package tn.esprit.autoloc.Services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Entities.Maintenance;
import tn.esprit.autoloc.Repositories.MaintenanceRepo;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MaintenanceServiceImpl implements IMaintenance {
    private final MaintenanceRepo maintenanceRepo;
    @Override
    public List<Maintenance> finEntre(LocalDate d, LocalDate f) {
        return maintenanceRepo.findByDateFinBetween(d, f);
    }
}