package by.fin.service;

import by.fin.repository.entity.Rate;

import java.util.List;

public interface RatesService {
    List<Rate> findRatesByCurrencyId(Long id);
}
