package com.retina.demo.utils;


import com.retina.demo.model.IllegalEvent;
import com.retina.demo.model.NypdEvent;
import com.retina.demo.repository.IllegalEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class DatabaseWriter {


    @Autowired
    private ApplicationContext context;


    @Autowired
    private IllegalEventRepository repository;

    public void writeToDatabase() {
        List<NypdEvent> events = (List<NypdEvent>) context.getBean("eventList");
        List<IllegalEvent> eventList = new ArrayList<>();
        events.subList(1, events.size()).forEach(event -> {
            IllegalEvent illegalEvent = new IllegalEvent();
            illegalEvent.setDescriptionOfClassificationCode(event.getPD_DESC().toString());
            illegalEvent.setEventId(Long.valueOf(event.getEventId()));
            illegalEvent.setStartOfEvent(buildDateAndTime(event.getCMPLNT_FR_DT(), event.getCMPLNT_FR_TM()));
            illegalEvent.setEndOfEvent(buildDateAndTime(event.getCMPLNT_TO_DT(), event.getCMPLNT_TO_TM()));
            illegalEvent.setPrecinctOfIncident(event.getADDR_PCT_CD().isEmpty() ? null : Integer.valueOf(event.getADDR_PCT_CD()));
            illegalEvent.setReportedDate(buildDate(event.getRPT_DT()));
            illegalEvent.setClassificationCode(Integer.valueOf(event.getKY_CD()));
            illegalEvent.setDescriptionOfOffense(event.getOFNS_DESC());
            illegalEvent.setInternalClassificationCode(event.getPD_CD().isEmpty() ? null : Integer.valueOf(event.getPD_CD()));
            illegalEvent.setIndicatorOfCrime(event.getCRM_ATPT_CPTD_CD());
            illegalEvent.setLevelOfOffense(event.getLAW_CAT_CD());
            illegalEvent.setNameOfBorough(event.getBORO_NM().isEmpty() ? null : event.getBORO_NM());
            illegalEvent.setLocationOfOccurrence(event.getLOC_OF_OCCUR_DESC().isEmpty() ? null : event.getLOC_OF_OCCUR_DESC());
            illegalEvent.setDescriptionOfPremises(event.getPREM_TYP_DESC().isEmpty() ? null : event.getPREM_TYP_DESC());
            illegalEvent.setResponsibleJurisdiction(event.getJURIS_DESC());
            illegalEvent.setNameOfNycPark(event.getPARKS_NM().isEmpty() ? null : event.getPARKS_NM());
            illegalEvent.setNameOfNychaHousing(event.getHADEVELOPT().isEmpty() ? null : event.getHADEVELOPT());
            illegalEvent.setXCoordinate(event.getX_COORD_CD().equals("") ? null : Long.valueOf(event.getX_COORD_CD()));
            illegalEvent.setYCoordinate(event.getY_COORD_CD().equals("") ? null : Long.valueOf(event.getY_COORD_CD()));
            illegalEvent.setLatitude(event.getLatitude().equals("") ? null : Double.valueOf(event.getLatitude()));
            illegalEvent.setLongitude(event.getLongitude().equals("") ? null : Double.valueOf(event.getLongitude()));
            eventList.add(illegalEvent);
        });
        repository.saveAll(eventList);
    }

    private Date buildDateAndTime(String date, String time) {
        if (!date.isEmpty() && !time.isEmpty()) {
            return Date.from(LocalDateTime
                    .parse(date + " " + time, DateTimeFormatter.ofPattern("M/d/yyyy H:mm:ss"))
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
        }
        return null;
    }

    private Date buildDate(String date) {
        if (!date.isEmpty()) {
            try {
                return new SimpleDateFormat("M/d/yyyy").parse(date);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;
    }

}
