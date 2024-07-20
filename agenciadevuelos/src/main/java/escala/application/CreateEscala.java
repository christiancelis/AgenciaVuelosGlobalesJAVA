package escala.application;

import escala.domain.Escala;
import escala.domain.ServiceEscala;

public class CreateEscala {
    private ServiceEscala serviceEscala;

    public CreateEscala(ServiceEscala serviceEscala) {
        this.serviceEscala = serviceEscala;
    }

    public void execute(Escala escala) {
        serviceEscala.createEscala(escala);
    }
}