package by.fin.repository;

import by.fin.repository.entity.Rate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatesRepository extends JpaRepository<Rate, Long> {
    List<Rate> findByCurrencyId(Long id);
}
