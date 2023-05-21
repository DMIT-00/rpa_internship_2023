package by.fin.service;

import by.fin.repository.entity.Weekend;

import java.time.LocalDate;
import java.util.List;

public interface WeekendService {
    List<Weekend> findAll();
    List<LocalDate> findAllWeekendDatesBetween(LocalDate start, LocalDate end);
}
