package it.extrared;

import it.extrared.dto.TransportInputDTO;
import it.extrared.dto.TransportOutputDTO;
import it.extrared.entity.TransportEntity;
import it.extrared.transport.TransportationType;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

// Define the base path for the REST service
@Path("/transport")
public class TransportService {

    // Inject every available transport strategies.
    @Inject
    @Any
    Instance<TransportationType> transportStrategies;

    // Method to create a new transport strategy. Accepts TransportInputDTO in input as JSON
    @POST
    @Transactional
    public Response createTransportStrategy(TransportInputDTO input) {

        // Create a new transport entity based on the provided input
        TransportEntity entity = new TransportEntity(input);

        // Select the appropriate transport strategy based on "supports" method. If none is available respond with 400
        TransportationType strategy = transportStrategies.stream()
                .filter(s -> s.supports(input))
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("No strategy found", 400));

        strategy.enrich(entity, input);

        // Persist the entity in the database and reply
        entity.persist();
        return Response.status(201).entity(new TransportOutputDTO(entity)).build();
    }

    // Method to get a transport entity by ID
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") Long id) {
        // Find the transport entity by ID
        TransportEntity entity = (TransportEntity) TransportEntity.findByIdOptional(id)
                .orElseThrow(() -> new WebApplicationException("Not found", 404));

        // Return the found entity
        return Response.ok().entity(new TransportOutputDTO(entity)).build();
    }

    // Method to delete a transport entity by ID
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        // Delete the transport entity by ID
        boolean deleted = TransportEntity.deleteById(id);
        if (!deleted){
            throw new WebApplicationException("Not found", 404);
        }

        // Return a no-content response
        return Response.noContent().build();
    }

    // Method to update a transport entity by ID. Accepts TransportInputDTO in input as JSON
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, TransportInputDTO input) {
        // Find the transport entity by ID
        TransportEntity entity = TransportEntity.findById(id);
        if (entity == null){
            throw new WebApplicationException("Not found", 404);
        }

        // Update the fields of the transport entity
        entity.setDistanceKm(input.getDistanceKm());
        entity.setDestinationType(input.getDestinationType());
        entity.setIsUrgent(input.getIsUrgent());
        entity.setWeightKg(input.getWeightKg());

        // Select the appropriate transport strategy based on "supports" method. If none is available respond with 400
        TransportationType strategy = transportStrategies.stream()
                .filter(s -> s.supports(input))
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("No strategy found", 400));

        strategy.enrich(entity, input);

        // Return the updated entity
        return Response.ok().entity(new TransportOutputDTO(entity)).build();
    }
}
