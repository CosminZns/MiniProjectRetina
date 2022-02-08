package com.retina.demo.service;


import com.retina.demo.dto.DeleteAndCreateDto;
import com.retina.demo.dto.TotalGroupedOffensesDto;
import com.retina.demo.dto.TotalNumberOfEventsDto;
import com.retina.demo.exception.EventException;
import com.retina.demo.model.NypdEvent;
import com.retina.demo.utils.FileReaderWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service("EventFileService")
public class EventFileServiceImpl implements EventService {


    @Autowired
    private ApplicationContext context;

    @Autowired
    private FileReaderWriter fileReaderWriter;


    @Override
    public TotalNumberOfEventsDto getTotalNumberOfEvents() {
        List<NypdEvent> nypdEvents = (List<NypdEvent>) context.getBean("eventList");
        return new TotalNumberOfEventsDto(nypdEvents.size() - 1);
    }

    @Override
    public List<TotalGroupedOffensesDto> getTotalNumberOfEventsGroupByKyCd() {
        List<NypdEvent> events = (List<NypdEvent>) context.getBean("eventList");
        List<Integer> classificationCodes = new ArrayList<>();
        events.subList(1, events.size()).forEach(event -> {
            classificationCodes.add(Integer.valueOf(event.getKY_CD()));
        });
        Map<Integer, Long> results =
                classificationCodes.stream().collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()));

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
    public DeleteAndCreateDto deleteEvent(Long id) {
        if (id == null) {
            throw new EventException("Id it's not present");
        }
        List<NypdEvent> events = (List<NypdEvent>) context.getBean("eventList");
        DeleteAndCreateDto deleteAndCreateDto = new DeleteAndCreateDto();
        Optional<NypdEvent> optionalNypdEvent =
                events.subList(1, events.size())
                        .stream()
                        .filter(event -> event.getEventId().equals(String.valueOf(id)))
                        .findAny();
        if (optionalNypdEvent.isPresent()) {
            events.remove(optionalNypdEvent.get());
            fileReaderWriter.writeToCsv(events);
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
        List<NypdEvent> events = (List<NypdEvent>) context.getBean("eventList");
        NypdEvent nypdEvent = new NypdEvent();
        DeleteAndCreateDto deleteAndCreateDto = new DeleteAndCreateDto();
        long count = events.stream().filter(event21 -> event21.getEventId().equals(String.valueOf(id))).count();
        if (count != 0) {
            deleteAndCreateDto.setSuccess(false);
        } else {
            deleteAndCreateDto.setSuccess(true);
            nypdEvent.setEventId(String.valueOf(id));
            nypdEvent.setKY_CD(kyCd.toString());
            events.add(nypdEvent);
            fileReaderWriter.writeToCsv(events);
        }
        return deleteAndCreateDto;
    }

}
