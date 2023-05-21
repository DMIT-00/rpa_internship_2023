package by.fin.repository;

import by.fin.repository.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RatesRepository extends JpaRepository<Rate, Long> {
    List<Rate> findByCurrency_Id(Long id);
    List<Rate> findByCurrency_IdAndDateBetween(Long id, LocalDate dateStart, LocalDate dateEnd);
}
