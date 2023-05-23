package by.fin.repository;

import by.fin.repository.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
    List<Currency> findByAbbreviation(String abbreviation);
}
