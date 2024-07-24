package user.application.GetAllPermisos;

import java.util.ArrayList;

import user.domain.ServiceUser;
import user.domain.RolPermisoUsuario.RolPermiso;

public class GetAllPermisos {
private ServiceUser serviceUser;

public GetAllPermisos(ServiceUser serviceUser) {
    this.serviceUser = serviceUser;
}

public ArrayList <RolPermiso> execute(String validacion,int idRol,int idUsuario){
        return serviceUser.getAllPermiso(validacion,idRol,idUsuario);
    } 
}
