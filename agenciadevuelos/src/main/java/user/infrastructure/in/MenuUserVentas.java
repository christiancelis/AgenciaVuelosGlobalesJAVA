package user.infrastructure.in;

import java.util.Scanner;

import com.agenciadevuelosglobales.Menu.GenerarPermisos;

import rol.application.GetAllRol;
import rol.domain.ServiceRol;
import rol.infrastructure.out.RolRepository;
import user.application.GetAllPermisos.GetAllPermisos;
import user.application.createUser.CreateUser;
import user.application.findUser.FindUser;
import user.application.updateUser.UpdateUser;
import user.domain.ServiceUser;
import user.infrastructure.out.UserRepository;

public class MenuUserVentas {
    ServiceUser serviceUser = new UserRepository();
    //User
    ServiceRol serviceRol = new RolRepository();
    GetAllRol getAllRol = new GetAllRol(serviceRol);
    CreateUser createUser = new CreateUser(serviceUser);
    UpdateUser updateUser = new UpdateUser(serviceUser);
    FindUser findUser = new FindUser(serviceUser);
    GetAllPermisos getAllPermisos = new GetAllPermisos(serviceUser);
    UserController userController = new UserController(createUser, getAllRol, findUser, updateUser, getAllPermisos);

    private Scanner scanner = new Scanner(System.in);
    GenerarPermisos menuPermisos = new GenerarPermisos();
    public void Start(int rolId, int idUsu) {
        while (true) {
            System.out.println("==============================");
            System.out.println("       MENÚ VENTAS");
            System.out.println("==============================");
            System.out.println("1. Gestionar clientes");
            System.out.println("2. Gestionar reservas");
            System.out.println("3. Salir");
            System.out.println("==============================");

          

            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    String cliente = "cliente";
                    menuPermisos.getAllPermiso(cliente, rolId, idUsu);
                    menuUserCliente();  // Llama al submenú de clientes
                    break;

                case 2:
                    String reserva = "reserva";
                    menuPermisos.getAllPermiso(reserva, rolId, idUsu);
                    menuUserReserva();  // Llama al submenú de reservas
                    break;

                case 3:
                System.out.println("saliendo...");
                return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuUserCliente() {
        while (true) {
        
            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Información del cliente ...");
                    userController.findUserById();
                    break;

                case 2:
                    System.out.println("Registrar cliente..");
                    userController.createUser();
                    break;

                case 3:
                    System.out.println("Actualizando información del cliente...");
                    userController.updateById();
                    break;

                case 4:
                System.out.println("saliendo...");
                return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }

    public void menuUserReserva() {
        while (true) {
          
            int choice = utils.Validation.leerNumero("Digite la opción: ", scanner);

            switch (choice) {
                case 1:
                    System.out.println("Crear reserva de vuelo ...");
                    break;

                case 2:
                    System.out.println("Consultar reserva de vuelo");
                    break;

                case 3:
                    System.out.println("Eliminar reserva de vuelo..");
                    break;

                case 4:
                System.out.println("saliendo...");
                return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
                    break;
            }
        }
    }
}
