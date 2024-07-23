package Escala.application;

import Escala.domain.Escala;
import Escala.domain.ServiceEscala;

public class CreateEscala {
    private ServiceEscala serviceEscala;

    public CreateEscala(ServiceEscala serviceEscala) {
        this.serviceEscala = serviceEscala;
    }

    public void execute(Escala escala) {
        serviceEscala.guardarEscala(escala);
    }

}