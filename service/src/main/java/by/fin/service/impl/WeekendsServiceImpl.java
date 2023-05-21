package by.fin.service.impl;

import by.fin.repository.WeekendsRepository;
import by.fin.repository.entity.Weekend;
import by.fin.service.WeekendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WeekendsServiceImpl implements WeekendService {
    private final WeekendsRepository weekendsRepository;

    @Override
    public List<Weekend> findAll() {
        return weekendsRepository.findAll();
    }

    @Override
    public List<LocalDate> findAllWeekendDatesBetween(LocalDate start, LocalDate end) {
        List<Weekend> weekends = weekendsRepository.findByIsDayOffTrueAndCalendarDateBetween(start, end);

        return weekends.stream().map(Weekend::getCalendarDate).toList();
    }
}
