package viaje.application;

import viaje.domain.ServiceFlightRepository;

public class EliminarVueloById {
    private final ServiceFlightRepository serviceFlightRepository;

    public EliminarVueloById(ServiceFlightRepository serviceFlightRepository) {
        this.serviceFlightRepository = serviceFlightRepository;
    }

    public void execute(int id) {
        serviceFlightRepository.EliminarVueloById(id);
        ;
    }

}
