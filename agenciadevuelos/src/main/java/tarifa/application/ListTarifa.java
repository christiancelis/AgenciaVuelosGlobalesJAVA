package tarifa.application;

import java.util.ArrayList;
import tarifa.domain.ServiceTarifa;
import tarifa.domain.Tarifa;

public class ListTarifa {
    private ServiceTarifa serviceTarifa;

    public ListTarifa(ServiceTarifa serviceTarifa) {
        this.serviceTarifa = serviceTarifa;
    }

    public ArrayList<Tarifa> execute() {
        return serviceTarifa.ListTarifa();
    }
}
