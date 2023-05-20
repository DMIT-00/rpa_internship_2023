package by.fin.service.impl;

import by.fin.repository.WeekendsRepository;
import by.fin.repository.entity.Weekend;
import by.fin.service.WeekendService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
