package com.retina.demo.service;

import com.retina.demo.dto.DeleteAndCreateDto;
import com.retina.demo.dto.TotalGroupedOffensesDto;
import com.retina.demo.dto.TotalNumberOfEventsDto;
import com.retina.demo.exception.EventException;
import com.retina.demo.model.IllegalEvent;
import com.retina.demo.repository.IllegalEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("EventDatabaseService")
public class EventDatabaseServiceImpl implements EventService {

    @Autowired
    private IllegalEventRepository repository;


    @Override
    public TotalNumberOfEventsDto getTotalNumberOfEvents() {
        return new TotalNumberOfEventsDto(repository.findAll().size());
    }

    @Override
    public List<TotalGroupedOffensesDto> getTotalNumberOfEventsGroupByKyCd() {
        List<IllegalEvent> events = repository.findAll();
        List<Integer> classificationCodes = new ArrayList<>();
        events.subList(1, events.size()).forEach(event -> {
            classificationCodes.add(event.getClassificationCode());
        });
        Map<Integer, Long> results =
                classificationCodes.stream()
                        .collect(Collectors
                                .groupingBy(Function.identity(), Collectors.counting()));

        List<TotalGroupedOffensesDto> offenses = new ArrayList<>();
        results.forEach((key, value) -> {
            TotalGroupedOffensesDto totalGroupedOffensesDto = new TotalGroupedOffensesDto();
            totalGroupedOffensesDto.setOffenseCode(key);
            totalGroupedOffensesDto.setTotalNumberOfEvents(value);
            offenses.add(totalGroupedOffensesDto);
        });
        return offenses;
    }

    @Override
    public DeleteAndCreateDto deleteEvent(Long id) throws EventException {
        if (id == null) {
            throw new EventException("Id it's not present");
        }
        Optional<IllegalEvent> event = repository.findByEventId(id);
        DeleteAndCreateDto deleteAndCreateDto = new DeleteAndCreateDto();
        if (event.isPresent()) {
            repository.deleteById(event.get().getId());
            deleteAndCreateDto.setSuccess(true);
        } else {
            deleteAndCreateDto.setSuccess(false);
        }
        return deleteAndCreateDto;
    }

    @Override
    public DeleteAndCreateDto createEvent(Long id, Integer kyCd) {
        if (id == null || kyCd == null) {
            throw new EventException("Id or classification code not present");
        }
        Optional<IllegalEvent> event = repository.findByEventId(id);
        DeleteAndCreateDto deleteAndCreateDto = new DeleteAndCreateDto();
        IllegalEvent illegalEvent = new IllegalEvent();
        if (event.isPresent()) {
            deleteAndCreateDto.setSuccess(false);
        } else {
            illegalEvent.setEventId(id);
            illegalEvent.setClassificationCode(kyCd);
            repository.save(illegalEvent);
            deleteAndCreateDto.setSuccess(true);
        }
        return deleteAndCreateDto;
    }

}
