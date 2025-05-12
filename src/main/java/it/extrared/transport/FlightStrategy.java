package it.extrared.transport;

import it.extrared.dto.TransportInputDTO;
import it.extrared.entity.TransportEntity;
import it.extrared.enums.TransportTypeEnum;
import it.extrared.utils.TravelUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FlightStrategy implements TransportationType {

    @Override
    public boolean supports(TransportInputDTO transportInput) {
        return transportInput.getIsUrgent() || transportInput.getDistanceKm() > 1000;
    }

    @Override
    public void enrich(TransportEntity entity, TransportInputDTO input) {

        //The number of aircraft required to make up the fleet is calculated by dividing the total weight
        // by the maximum weight that can be carried by the individual aircraft
        entity.setAircraftFleetNumber(
            //#SeminarioUnipi2025: DA IMPLEMENTARE
            // maxWeight è "cablato" dentro al codice. Questa è una bad practice, spostarlo in una property
            TravelUtil.calculateCarrier(input.getWeightKg(), 40000)
        );

        //The estimated time in hours is calculated by dividing the kilometers of travel by the average speed
        entity.setEstimatedTimeH(
            //#SeminarioUnipi2025: DA IMPLEMENTARE
            // avgSpeed è "cablato" dentro al codice. Questa è una bad practice, spostarlo in una property
            TravelUtil.calculateTravelTimeH(input.getDistanceKm(),640)
        );
        entity.setTransportType(TransportTypeEnum.CARGO_FLIGHT);
    }
}
