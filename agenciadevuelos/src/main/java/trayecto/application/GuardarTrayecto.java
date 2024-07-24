package trayecto.application;

import trayecto.domain.Trayecto;
import trayecto.domain.ServiceTrayecto;

public class GuardarTrayecto {
    private ServiceTrayecto serviceTrayecto;

    public GuardarTrayecto(ServiceTrayecto serviceTrayecto) {
        this.serviceTrayecto = serviceTrayecto;
    }

    public int execute(Trayecto trayecto) {
       return serviceTrayecto.GuardarTrayecto(trayecto);

        
    }

}