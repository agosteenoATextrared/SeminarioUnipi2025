package it.extrared.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.extrared.entity.TransportEntity;
import it.extrared.enums.DestinationTypeEnum;
import it.extrared.enums.TransportTypeEnum;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransportOutputDTO {
    @JsonProperty
    private Long id;
    @JsonProperty
    private Integer distanceKm;
    @JsonProperty
    private DestinationTypeEnum destinationType;
    @JsonProperty
    private Boolean isUrgent;
    @JsonProperty
    private Integer weightKg;
    @JsonProperty
    private TransportTypeEnum transportType;
    @JsonProperty
    private Integer estimatedTimeH;
    @JsonProperty
    private Integer aircraftFleetNumber;
    @JsonProperty
    private Integer reservedWagons;
    @JsonProperty
    private Integer reservedContainers;

    public TransportOutputDTO(TransportEntity transportEntity) {
        this.id = transportEntity.id;
        this.distanceKm = transportEntity.getDistanceKm();
        this.destinationType = transportEntity.getDestinationType();
        this.isUrgent = transportEntity.getIsUrgent();
        this.weightKg = transportEntity.getWeightKg();
        this.transportType = transportEntity.getTransportType();
        this.estimatedTimeH = transportEntity.getEstimatedTimeH();
        this.aircraftFleetNumber = transportEntity.getAircraftFleetNumber();
        this.reservedWagons = transportEntity.getReservedWagons();
        this.reservedContainers = transportEntity.getReservedContainers();
    }
}
