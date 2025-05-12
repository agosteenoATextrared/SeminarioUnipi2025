package it.extrared.transport;

import it.extrared.dto.TransportInputDTO;
import it.extrared.entity.TransportEntity;
import it.extrared.enums.DestinationTypeEnum;
import it.extrared.enums.TransportTypeEnum;
import it.extrared.utils.TravelUtil;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TrainStrategy implements TransportationType {

    @Override
    public boolean supports(TransportInputDTO transportInput) {
        return transportInput.getDistanceKm() < 3500 || !transportInput.getDestinationType().equals(DestinationTypeEnum.INTERNATIONAL_EXTRA_EU);
    }

    @Override
    public void enrich(TransportEntity entity, TransportInputDTO input) {

        //The number of reserved train wagons is calculated by dividing the total weight by the maximum wagon weight in kg
        entity.setReservedWagons(
            //#SeminarioUnipi2025: DA IMPLEMENTARE
            // maxWeight è "cablato" dentro al codice. Questa è una bad practice, spostarlo in una property
            TravelUtil.calculateCarrier(input.getWeightKg(), 30000) 
        );

        //The estimated time in hours is calculated by dividing the kilometers of travel by the average speed
        entity.setEstimatedTimeH(
            
            //#SeminarioUnipi2025: DA IMPLEMENTARE
            // avgSpeed è "cablato" dentro al codice. Questa è una bad practice, spostarlo in una property
            TravelUtil.calculateTravelTimeH(input.getDistanceKm(),130)
        );
        entity.setTransportType(TransportTypeEnum.TRAIN);
    }
}
