package com.retina.demo.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class IllegalEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "eventId")
    private Long eventId;

    @Column(name = "startDateOfEvent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startOfEvent;

    @Column(name = "endDateOfEvent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endOfEvent;

    @Column(name = "reportedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedDate;

    @Column(name = "classificationCode")
    private Integer classificationCode;

    @Column(name = "descriptionOfOffense")
    private String descriptionOfOffense;

    @Column(name = "internalClassificationCode")
    private Integer internalClassificationCode;

    @Column(name = "descriptionOfInternalClassification")
    private String descriptionOfInternalClassification;

    @Column(name = "indicatorOfCrime")
    private String indicatorOfCrime;

    @Column(name = "levelOfOffense")
    private String levelOfOffense;

    @Column(name = "responsibleJurisdiction")
    private String responsibleJurisdiction;

    @Column(name = "nameOfBorough")
    private String nameOfBorough;

    @Column(name = "precinctOfIncident")
    private Integer precinctOfIncident;

    @Column(name = "locationOfOccurrence")
    private String locationOfOccurrence;

    @Column(name = "descriptionOfPremises")
    private String descriptionOfPremises;

    @Column(name = "nameOfNycPark")
    private String nameOfNycPark;

    @Column(name = "nameOfNychaHousing")
    private String nameOfNychaHousing;

    @Column(name = "xCoordinate")
    private Long xCoordinate;

    @Column(name = "YCoordinate")
    private Long YCoordinate;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    //This should be List<ClassificationCodeDescription>
    @Column(name = "descriptionOfClassificationCode")
    private String descriptionOfClassificationCode;

}
