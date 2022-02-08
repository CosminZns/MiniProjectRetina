package com.retina.demo.model;

import com.opencsv.bean.CsvBindAndSplitByPosition;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Data;

import java.util.List;

@Data
public class NypdEvent {

    @CsvBindByPosition(position = 0)
    private String eventId;

    @CsvBindByPosition(position = 1)
    private String CMPLNT_FR_DT;

    @CsvBindByPosition(position = 2)
    private String CMPLNT_FR_TM;

    @CsvBindByPosition(position = 3)
    private String CMPLNT_TO_DT;

    @CsvBindByPosition(position = 4)
    private String CMPLNT_TO_TM;

    @CsvBindByPosition(position = 5)
    private String ADDR_PCT_CD;

    @CsvBindByPosition(position = 6)
    private String RPT_DT;

    @CsvBindByPosition(position = 7)
    private String KY_CD;

    @CsvBindByPosition(position = 8)
    private String OFNS_DESC;

    @CsvBindByPosition(position = 9)
    private String PD_CD;

    @CsvBindAndSplitByPosition(elementType = String.class, splitOn = ",", writeDelimiter = ",", position = 10)
    private List<String> PD_DESC;

    @CsvBindByPosition(position = 11)
    private String CRM_ATPT_CPTD_CD;

    @CsvBindByPosition(position = 12)
    private String LAW_CAT_CD;

    @CsvBindByPosition(position = 13)
    private String BORO_NM;

    @CsvBindByPosition(position = 14)
    private String LOC_OF_OCCUR_DESC;

    @CsvBindByPosition(position = 15)
    private String PREM_TYP_DESC;

    @CsvBindByPosition(position = 16)
    private String JURIS_DESC;

    @CsvBindByPosition(position = 17)
    private String PARKS_NM;

    @CsvBindByPosition(position = 18)
    private String HADEVELOPT;

    @CsvBindByPosition(position = 19)
    private String X_COORD_CD;

    @CsvBindByPosition(position = 20)
    private String Y_COORD_CD;

    @CsvBindByPosition(position = 21)
    private String Latitude;

    @CsvBindByPosition(position = 22)
    private String Longitude;

}



