package it.extrared.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import it.extrared.enums.DestinationTypeEnum;
import it.extrared.dto.TransportInputDTO;
import it.extrared.enums.TransportTypeEnum;
import jakarta.persistence.Entity;

@Entity
public class TransportEntity extends PanacheEntity {

    private Integer distanceKm;
    private DestinationTypeEnum destinationType;
    private Boolean isUrgent;
    private Integer weightKg;

    //Calculated based on input
    private TransportTypeEnum transportType;
    private Integer estimatedTimeH;

    // Specific fields for each transport type
    private Integer aircraftFleetNumber;
    private Integer reservedWagons;
    private Integer reservedContainers;


    //Constructors
    public TransportEntity(TransportInputDTO transportInputDTO) {
        this.distanceKm = transportInputDTO.getDistanceKm();
        this.destinationType = transportInputDTO.getDestinationType();
        this.isUrgent = transportInputDTO.getIsUrgent();
        this.weightKg = transportInputDTO.getWeightKg();
    }
    public TransportEntity() {}



    //Getters and setters
    public Integer getDistanceKm() {
        return distanceKm;
    }
    public void setDistanceKm(Integer distanceKm) {
        this.distanceKm = distanceKm;
    }

    public DestinationTypeEnum getDestinationType() {
        return destinationType;
    }
    public void setDestinationType(DestinationTypeEnum destinationType) {
        this.destinationType = destinationType;
    }

    public boolean getIsUrgent() {
        return isUrgent;
    }
    public void setIsUrgent(boolean urgent) {
        this.isUrgent = urgent;
    }

    public Integer getWeightKg() {
        return weightKg;
    }
    public void setWeightKg(Integer weightKg) {
        this.weightKg = weightKg;
    }

    public TransportTypeEnum getTransportType() {
        return transportType;
    }
    public void setTransportType(TransportTypeEnum transportType) {
        this.transportType = transportType;
    }

    public Integer getEstimatedTimeH() {
        return estimatedTimeH;
    }
    public void setEstimatedTimeH(Integer estimatedTimeH) {
        this.estimatedTimeH = estimatedTimeH;
    }

    public Integer getAircraftFleetNumber() {
        return aircraftFleetNumber;
    }
    public void setAircraftFleetNumber(Integer aircraftFleetNumber) {
        this.aircraftFleetNumber = aircraftFleetNumber;
    }

    public Integer getReservedWagons() {
        return reservedWagons;
    }
    public void setReservedWagons(Integer reservedWagons) {
        this.reservedWagons = reservedWagons;
    }

    public Integer getReservedContainers() {
        return reservedContainers;
    }
    public void setReservedContainers(Integer reservedContainers) {
        this.reservedContainers = reservedContainers;
    }
}
