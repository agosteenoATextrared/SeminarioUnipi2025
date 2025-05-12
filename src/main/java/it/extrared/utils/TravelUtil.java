package it.extrared.utils;

public class TravelUtil {

    public static Integer calculateTravelTimeH(Integer distanceKm, Integer avgSpeed){
        return Math.round((float) distanceKm / avgSpeed);
    }

    public static Integer calculateCarrier(Integer weightKg, Integer maxWeight){
        return Math.ceilDiv(weightKg, maxWeight);
    }
}
