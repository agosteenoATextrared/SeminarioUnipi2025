package it.extrared.dto;

import it.extrared.enums.DestinationTypeEnum;

public class TransportInputDTO {

    private int distanceKm;
    private DestinationTypeEnum destinationType;
    private boolean isUrgent;
    private int weightKg;

    //Getters and setters
    public int getDistanceKm() {
        return distanceKm;
    }
    public void setDistanceKm(int distanceKm) {
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

    public int getWeightKg() {
        return weightKg;
    }
    public void setWeightKg(int weightKg) {
        this.weightKg = weightKg;
    }
}
