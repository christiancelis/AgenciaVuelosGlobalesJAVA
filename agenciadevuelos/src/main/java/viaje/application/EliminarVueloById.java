package viaje.application;

import viaje.domain.ServiceFlight;

public class EliminarVueloById {
    private final ServiceFlight serviceFlightRepository;

    public EliminarVueloById(ServiceFlight serviceFlightRepository) {
        this.serviceFlightRepository = serviceFlightRepository;
    }

    public void execute(int id) {
        serviceFlightRepository.EliminarVueloById(id);
        ;
    }

}
