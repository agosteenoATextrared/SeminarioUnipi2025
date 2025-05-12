package it.extrared.transport;

import it.extrared.dto.TransportInputDTO;
import it.extrared.entity.TransportEntity;
import it.extrared.enums.TransportTypeEnum;
import it.extrared.utils.TravelUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShipStrategy implements TransportationType {

    @Override
    public boolean supports(TransportInputDTO transportInput) {
        return transportInput.getWeightKg() > 2250000 && !transportInput.getIsUrgent();
    }

    @Override
    public void enrich(TransportEntity entity, TransportInputDTO input) {

        //The number of reserved containers is calculated by dividing the total weight by the maximum container weight in kg
        entity.setReservedContainers(
            //#SeminarioUnipi2025: DA IMPLEMENTARE
            // maxWeight è "cablato" dentro al codice. Questa è una bad practice, spostarlo in una property
            TravelUtil.calculateCarrier(input.getWeightKg(), 50000)
        );

        //The estimated time in hours is calculated by dividing the kilometers of travel by the average speed
        entity.setEstimatedTimeH(
            //#SeminarioUnipi2025: DA IMPLEMENTARE
            // avgSpeed è "cablato" dentro al codice. Questa è una bad practice, spostarlo in una property
            TravelUtil.calculateTravelTimeH(input.getDistanceKm(),30)
        );
        entity.setTransportType(TransportTypeEnum.CARGO_SHIP);
    }
}
