package user.infrastructure.in;

import java.util.Scanner;

import rol.application.GetAllRol;
import rol.domain.ServiceRol;
import rol.infrastructure.out.RolRepository;
import user.application.GetAllPermisos.GetAllPermisos;
import user.application.createUser.CreateUser;
import user.application.findUser.FindUser;
import user.application.updateUser.UpdateUser;
import user.domain.ServiceUser;
import user.infrastructure.out.UserRepository;

public class MenuUsuario {
private Scanner scanner = new Scanner(System.in);

public void Start(){

 ServiceUser serviceUser = new UserRepository();
        ServiceRol serviceRol = new RolRepository();
        GetAllRol getAllRol = new GetAllRol(serviceRol);
       CreateUser createUser = new CreateUser(serviceUser); 
       UpdateUser updateUser = new UpdateUser(serviceUser);
       FindUser findUser = new FindUser(serviceUser);
       GetAllPermisos getAllPermisos = new GetAllPermisos(serviceUser);
       UserController userController = new UserController(createUser, getAllRol,findUser, updateUser,getAllPermisos);
    while (true) {
        
        System.out.println("==============================");
        System.out.println("    GESTIÓN DE USUARIOS");
        System.out.println("==============================");
        System.out.println("1. CREAR USUARIO");
        System.out.println("2. ACTUALIZAR USUARIO");
        System.out.println("3. ELIMINAR USUARIO");
        System.out.println("4. BUSCAR USUARIO");
        System.out.println("5. SALIR");
        System.out.println("==============================");
        


        int choice = utils.Validation.leerNumero("Digita el numero: ", scanner);

        switch (choice) {
            case 1: 
        
            userController.createUser();
                break;
                case 2:
                userController.updateById();
                break;
                case 3:
                System.out.println("Aun en mantenimiento..");
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
