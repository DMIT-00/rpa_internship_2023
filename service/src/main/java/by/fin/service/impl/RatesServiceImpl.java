package by.fin.service.impl;

import by.fin.repository.RatesRepository;
import by.fin.repository.entity.Rate;
import by.fin.service.RatesService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RatesServiceImpl implements RatesService {
    private final RatesRepository ratesRepository;

    @Override
    public List<Rate> findRatesByCurrencyId(Long id) {
        return ratesRepository.findByCurrencyId(id);
    }
}
