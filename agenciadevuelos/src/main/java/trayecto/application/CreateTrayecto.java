package trayecto.application;

import trayecto.domain.Trayecto;
import trayecto.domain.ServiceTrayecto;

public class CreateTrayecto {
    private ServiceTrayecto serviceTrayecto;

    public CreateTrayecto(ServiceTrayecto serviceTrayecto) {
        this.serviceTrayecto = serviceTrayecto;
    }

    public void execute(Trayecto trayecto) {
        serviceTrayecto.guardarTrayecto(trayecto);
    }

}