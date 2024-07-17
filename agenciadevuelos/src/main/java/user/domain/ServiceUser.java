package user.domain;

import java.util.ArrayList;

import user.domain.RolPermisoUsuario.RolPermiso;


public interface ServiceUser {
    User FindUserById(int id);
    void CreateUser(User user);
    User UpdateUserById(int id,String user,String contraseña,int rolnumber);
User ValidationUser(int id,String contraseña);
ArrayList <RolPermiso> getAllPermiso(String validacion, int idRol,int idUsuario);
}
