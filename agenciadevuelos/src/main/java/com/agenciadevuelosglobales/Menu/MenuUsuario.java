package com.agenciadevuelosglobales.Menu;

import java.util.Scanner;

import rol.application.GetAllRol;
import rol.domain.ServiceRol;
import rol.infrastructure.out.RolRepository;
import user.application.GetAllPermisos.GetAllPermisos;
import user.application.createUser.CreateUser;
import user.application.findUser.FindUser;
import user.application.updateUser.UpdateUser;
import user.application.validation.ValidationUser;
import user.domain.ServiceUser;
import user.domain.RolPermisoUsuario.RolPermiso;
import user.infrastructure.in.UserController;
import user.infrastructure.out.UserRepository;

public class MenuUsuario {
private Scanner scanner = new Scanner(System.in);

public void Start(){

 ServiceUser serviceUser = new UserRepository();
        ServiceRol serviceRol = new RolRepository();
        GetAllRol getAllRol = new GetAllRol(serviceRol);
       CreateUser createUser = new CreateUser(serviceUser); 
       UpdateUser updateUser = new UpdateUser(serviceUser);
       RolPermiso rolPermiso = new RolPermiso();
       GetAllPermisos getAllPermisos = new GetAllPermisos(serviceUser);
       ValidationUser validationUser = new ValidationUser(serviceUser);
       FindUser findUser = new FindUser(serviceUser);
       UserController userController = new UserController(createUser, getAllRol,findUser, updateUser,getAllPermisos);
    while (true) {
        
        System.out.println("1. Crear usuario");
        System.out.println("2. Actualizar usuario");
        System.out.println("3. Eliminar usuario");
        System.out.println("4. Buscar usuario");
        System.out.println("5. Salir");


        int choice = utils.Validation.leerNumero("Digita el numero: ", scanner);

        switch (choice) {
            case 1: 
        
            userController.createUser();
                break;
                case 2:
                userController.updateById();
                break;
                case 3:
                
                break;
                case 4:
                userController.findUserById();
                break;
                case 5:
                System.out.println("Saliendo..");
                return;
            default:
                throw new AssertionError();
        }

    }
}

}
