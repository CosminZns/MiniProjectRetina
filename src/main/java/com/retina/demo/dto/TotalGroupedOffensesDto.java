package com.retina.demo.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class TotalGroupedOffensesDto implements Serializable {

    private Integer offenseCode;
    private Long totalNumberOfEvents;
}
