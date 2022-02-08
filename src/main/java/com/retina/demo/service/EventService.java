package com.retina.demo.service;


import com.retina.demo.dto.DeleteAndCreateDto;
import com.retina.demo.dto.TotalGroupedOffensesDto;
import com.retina.demo.dto.TotalNumberOfEventsDto;

import java.util.List;

public interface EventService {

    TotalNumberOfEventsDto getTotalNumberOfEvents();

    List<TotalGroupedOffensesDto> getTotalNumberOfEventsGroupByKyCd();

    DeleteAndCreateDto deleteEvent(Long id);

    DeleteAndCreateDto createEvent(Long id, Integer kyCd);

}
