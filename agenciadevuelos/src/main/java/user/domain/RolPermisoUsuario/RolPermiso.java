package user.domain.RolPermisoUsuario;

public class RolPermiso {
private int idRol;
private int idPermiso;
private String nombrePermiso;
private String validacion;
private int idUsuario;

public RolPermiso() {


}
public RolPermiso(int idRol, int idPermiso, String nombrePermiso, String validacion,int idUsuario) {
    this.idRol = idRol;
    this.idPermiso = idPermiso;
    this.nombrePermiso = nombrePermiso;
    this.validacion = validacion;
    this.idPermiso = idPermiso;
   
}

public int getIdRol() {
    return idRol;
}
public void setIdRol(int idRol) {
    this.idRol = idRol;
}
public int getIdPermiso() {
    return idPermiso;
}
public void setIdPermiso(int idPermiso) {
    this.idPermiso = idPermiso;
}
public String getNombrePermiso() {
    return nombrePermiso;
}
public void setNombrePermiso(String nombrePermiso) {
    this.nombrePermiso = nombrePermiso;
}
public String getValidacion() {
    return validacion;
}
public void setValidacion(String validacion) {
    this.validacion = validacion;
}
public int getIdUsuario() {
    return idUsuario;
}
public void setIdUsuario(int nombreUsuario) {
    this.idUsuario = nombreUsuario;
}





}
