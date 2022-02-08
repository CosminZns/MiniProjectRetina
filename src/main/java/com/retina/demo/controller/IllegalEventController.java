package com.retina.demo.controller;


import com.retina.demo.service.EventService;
import com.retina.demo.dto.DeleteAndCreateDto;
import com.retina.demo.dto.PostEventDto;
import com.retina.demo.dto.TotalGroupedOffensesDto;
import com.retina.demo.dto.TotalNumberOfEventsDto;
import com.retina.demo.exception.EventException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/dataset")
public class IllegalEventController {

    //Change qualifier to "EventDatabaseService" to use the db

    @Autowired
    @Qualifier("EventDatabaseService")
    private EventService eventService;

    
    @GetMapping(value = "/stats/total", produces = MediaType.APPLICATION_JSON_VALUE)
    public TotalNumberOfEventsDto getTotalNumberOfEvents() {
        return eventService.getTotalNumberOfEvents();
    }

    @GetMapping(value = "/stats/offenses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TotalGroupedOffensesDto> getTotalEventsGroupByClassificationCode() {
        return eventService.getTotalNumberOfEventsGroupByKyCd();
    }

    @DeleteMapping(value = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeleteAndCreateDto deleteEvent(@RequestParam Long id) throws EventException {
        return eventService.deleteEvent(id);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public DeleteAndCreateDto createEvent(@RequestBody PostEventDto postEventDto) {
        return eventService.createEvent(postEventDto.getEventId(), postEventDto.getKyCd());
    }
}
