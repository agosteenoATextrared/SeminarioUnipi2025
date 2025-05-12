package it.extrared.transport;

import it.extrared.dto.TransportInputDTO;
import it.extrared.entity.TransportEntity;

/** This interface describes a way of transporting goods and defines two methods:
    - "supports" is used to determine if a certain strategy is available based on input field
    - "enrich" is used to insert additional information into the entity
**/
public interface TransportationType {
    boolean supports(TransportInputDTO viaggio);
    void enrich(TransportEntity entity, TransportInputDTO input);
}
