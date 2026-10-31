package tn.esprit.autoloc.Services;

import tn.esprit.autoloc.Entities.Maintenance;

import java.time.LocalDate;
import java.util.List;

public interface IMaintenance {
    List<Maintenance> finEntre(LocalDate debut, LocalDate fin);
}
