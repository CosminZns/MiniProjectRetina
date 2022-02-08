package com.retina.demo.dto;


import com.sun.istack.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class PostEventDto implements Serializable {

    @NotNull
    private Long eventId;
    @NotNull
    private Integer kyCd;
}
