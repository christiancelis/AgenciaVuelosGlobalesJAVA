package user.domain;

public class User {
private int id;
private String usuario;
private String contraseña;
private int rolId;


public User() {


}
public User(int id, String usuario, String contraseña, int rolId) {
    this.id = id;
    this.usuario = usuario;
    this.contraseña = contraseña;
    this.rolId = rolId;
}
public int getId() {
    return id;
}
public void setId(int id) {
    this.id = id;
}
public String getUsuario() {
    return usuario;
}
public void setUsuario(String usuario) {
    this.usuario = usuario;
}
public String getContraseña() {
    return contraseña;
}
public void setContraseña(String contraseña) {
    this.contraseña = contraseña;
}
public int getRolId() {
    return rolId;
}
public void setRolId(int rolId) {
    this.rolId = rolId;
}
@Override
public String toString() {
    return "AuthUser [id=" + id + ", usuario=" + usuario + ", contraseña=" + contraseña + ", rolId=" + rolId + "]";
}
public User execute(int id2, String usuario2, String newContraseña, int rolId2) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'execute'");
}



}
