package rol.application;

import java.util.ArrayList;

import rol.domain.Rol;
import rol.domain.ServiceRol;


public class GetAllRol {
private final ServiceRol serviceRol;

public GetAllRol(ServiceRol serviceRol) {
    this.serviceRol = serviceRol;
}
 public ArrayList<Rol> execute(){
        return serviceRol.getAllRol();
    } 

}
