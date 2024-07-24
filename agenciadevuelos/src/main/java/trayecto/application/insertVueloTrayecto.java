package trayecto.application;

import trayecto.domain.ServiceTrayecto;

public class insertVueloTrayecto {
private ServiceTrayecto serviceTrayecto;

public insertVueloTrayecto(ServiceTrayecto serviceTrayecto) {
    this.serviceTrayecto = serviceTrayecto;
}

public void execute(int idViaje, int idTrayecto) {
        serviceTrayecto.insertarVueloEscala(idViaje,idTrayecto);
    }


}
