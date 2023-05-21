package by.fin.repository;


import by.fin.repository.entity.Weekend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WeekendsRepository extends JpaRepository<Weekend, Long> {
    List<Weekend> findByIsDayOffTrueAndCalendarDateBetween(LocalDate calendarDateStart, LocalDate calendarDateEnd);
}
