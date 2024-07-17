package user.infrastructure.in;

import java.util.ArrayList;
import java.util.Scanner;

import com.agenciadevuelosglobales.Menu.MenuAdmin;
import com.agenciadevuelosglobales.Menu.MenuPermisos;
import com.agenciadevuelosglobales.Menu.MenuTecnico;
import com.agenciadevuelosglobales.Menu.MenuVentas;

import rol.application.GetAllRol;
import rol.domain.Rol;
import user.application.GetAllPermisos.GetAllPermisos;
import user.application.createUser.CreateUser;
import user.application.findUser.FindUser;
import user.application.updateUser.UpdateUser;
import user.domain.User;
import user.infrastructure.out.UserRepository;

public class UserController {
    private final CreateUser createUser;
    private final GetAllRol getAllRol;
    private final FindUser findUser;
    private final UpdateUser updateUser;
    private final GetAllPermisos getAllPermisos;
    private final Scanner scanner = new Scanner(System.in);

    public UserController(CreateUser createUser, GetAllRol getAllRol, FindUser findUser, UpdateUser updateUser,GetAllPermisos getAllPermisos) {
        this.createUser = createUser;
        this.getAllRol = getAllRol;
        this.findUser = findUser;
        this.updateUser = updateUser;
        this.getAllPermisos = getAllPermisos;
    }

   

    public void createUser() {
        try {
            User user = new User();
            System.out.println("---------------------------");
            System.out.println("\t Creación de usuario");
            System.out.println("---------------------------");
            int id = utils.Validation.leerNumero("Ingrese la cedula: ", scanner);

            String usuario = utils.Validation.leerdato("Ingrese el usuario: ", scanner);

            System.out.println("Ingresa la contraseña: ");
            String contraseña = utils.Validation.leerdato("Ingrese la contraseña: ", scanner);

            ArrayList<Rol> listRol = getAllRol.execute();

            String leftAlignFormat = "| %-3d | %-20s |%n";
            System.out.format("+-----+----------------------+%n");
            System.out.format("| ID  | Nombre               |%n");
            System.out.format("+-----+----------------------+%n");
            for (Rol rol : listRol) {
                System.out.format(leftAlignFormat, rol.getId(), rol.getNombre());
            }
            System.out.format("+-----+----------------------+%n");

            System.out.println("\nIngresa el rol: ");
            int rol = utils.Validation.leerNumero("Dijite el id del rol", scanner);

            boolean usuarioExistente = verificarUsuarioExistente(usuario);

            if (usuarioExistente) {
                System.out.println("Error: El usuario ya existe en el sistema.");
            } else {
                user.setId(id);
                user.setUsuario(usuario);
                user.setContraseña(contraseña);
                user.setRolId(rol);

                createUser.execute(user);

                // Imprimir la información del usuario creado en formato tabla
                System.out.println("Usuario creado exitosamente:");
                String userTableFormat = "| %-15s | %-20s |%n";
                System.out.format("+-----------------+----------------------+%n");
                System.out.format("| Campo           | Valor                |%n");
                System.out.format("+-----------------+----------------------+%n");
                System.out.format(userTableFormat, "ID", user.getId());
                System.out.format(userTableFormat, "Usuario", user.getUsuario());
                System.out.format(userTableFormat, "Contraseña", user.getContraseña());
                System.out.format(userTableFormat, "Rol ID", user.getRolId());
                System.out.format("+-----------------+----------------------+%n");
            }
        } catch (Exception e) {
            System.err.println("Error al crear el usuario: " + e.getMessage());
        }
    }
    public void findUserById() {
        try {
            int id = utils.Validation.leerNumero("Ingrese el id del usuario a buscar: ", scanner);
            User user = findUser.execute(id);

            if (user != null) {
                System.out.println("Usuario encontrado:");
                System.out.println("Cedula: " + user.getId());
                System.out.println("Usuario: " + user.getUsuario());
                System.out.println("Rol: " + user.getRolId());
            } else {
                System.out.println("No se encontró ningún usuario con el ID: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al buscar el usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void updateById() {
        try {
            int id = utils.Validation.leerNumero("Ingrese la cedula a actualizar: ", scanner);
            // scanner.nextInt();

            User user = findUser.execute(id);
            System.out.println(findUser.execute(id));
            if (user != null) {
                System.out.println("Usuario encontrado:");
                System.out.println("ID: " + user.getId());
                System.out.println("Usuario: " + user.getUsuario());
                System.out.println("Rol ID: " + user.getRolId());

                System.out.println("¿Qué deseas modificar del usuario?");
                System.out.println("1. Usuario");
                System.out.println("2. Contraseña");
                System.out.println("3. Rol");
                int choice = utils.Validation.leerNumero("Eliga la opcion: ", scanner);

                User updatedUser = null;
                switch (choice) {
                    case 1:
                        String newUser = utils.Validation.leerdato("Ingrese el nuevo nombre de usuario: ", scanner);
                        updatedUser = updateUser.execute(id, newUser, user.getContraseña(), user.getRolId());
                        if (updatedUser != null) {
                            System.out.println("Usuario actualizado a: " + updatedUser.getUsuario());
                        } else {
                            System.out.println("Error al actualizar el nombre.");
                        }
                        break;
                    case 2:
                        String newContraseña = utils.Validation.leerdato("Ingrese la nueva contraseña: ", scanner);
                        updatedUser = updateUser.execute(id, user.getUsuario(), newContraseña, user.getRolId());
                        if (updatedUser != null) {
                            System.out.println("Contraseña actualizada a: " + updatedUser.getContraseña());
                        } else {
                            System.out.println("Error al actualizar la contraseña.");
                        }
                        break;
                    case 3:
                        ArrayList<Rol> listRol = getAllRol.execute();
                        String leftAlignFormat = "| %-3d | %-20s |%n";
                        System.out.format("+-----+----------------------+%n");
                        System.out.format("| ID  | Nombre               |%n");
                        System.out.format("+-----+----------------------+%n");
                        for (Rol rol : listRol) {
                            System.out.format(leftAlignFormat, rol.getId(), rol.getNombre());
                        }
                        System.out.format("+-----+----------------------+%n");

                        int newRol = utils.Validation.leerNumero("Ingrese el nuevo rol :", scanner);
                        updatedUser = updateUser.execute(id, user.getUsuario(), user.getContraseña(), newRol);
                        if (updatedUser != null) {
                            System.out.println("Rol actualizado a: " + updatedUser.getRolId());
                        } else {
                            System.out.println("Error al actualizar el rol.");
                        }
                        break;
                    default:
                        System.out.println("Opción no válida.");
                        break;
                }
            } else {
                System.out.println("No se encontró ningún usuario con la cédula: " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar el usuario: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void ValidationAccount() {
        try {
            while (true) {
                System.out.println("\n==============================");
                System.out.println("       INICIO DE SESION       ");
                System.out.println("==============================\n");
    
                int userId = utils.Validation.leerNumero("Ingresa la cedula: ", scanner);
                String contraseña = utils.Validation.leerdato("Ingrese la contraseña: ", scanner);
    
                User user = findUser.execute(userId);
                MenuPermisos menuPermisos = new MenuPermisos();
                
                if (user != null && user.getContraseña().equals(contraseña)) {
                    int rolid = user.getRolId();
                    int idUsu = user.getId();

    
                    System.out.println("\n==============================");
                    System.out.println("        INICIO EXITOSO        ");
                    System.out.println("==============================\n");
    
                    switch (rolid) {
                        case 1:
                        MenuAdmin menuAdmin = new MenuAdmin();
                            System.out.println("ROL: ADMINISTRADOR");
                        menuAdmin.Start(rolid,idUsu);

                         
                            break;
                        case 2:
                            MenuVentas menuVentas = new MenuVentas(); 
                            menuVentas.Start(rolid, idUsu);
                            System.out.println("ROL: AREA DE VENTAS");
                            
                            break;
                        case 3:
                            System.out.println("ROL: TECNICO");
                            MenuTecnico menuTecnico = new MenuTecnico();
                            menuTecnico.Start(rolid,idUsu);
                            break;
                        case 4:
                        
                            System.out.println("ROL: CLIENTE");
                            break;
                        default:
                            System.out.println("ROL: DESCONOCIDO");
                            break;
                    }
                } else {
                    System.out.println("\n==============================");
                    System.out.println("    USUARIO O CONTRASEÑA      ");
                    System.out.println("         INCORRECTOS          ");
                    System.out.println("==============================\n");
                }
            }
        } catch (Exception e) {
            System.err.println("Error al validar el usuario: " + e.getMessage());
        }
    }

    // public ArrayList<RolPermiso> getAllPermiso(String validacion,int rolId) {
    //     ArrayList<RolPermiso> permisos = getAllPermisos.execute(validacion,rolId);
        
    //     // Ejemplo de mensaje para verificar ejecución
    //     System.out.println("Permisos obtenidos correctamente:");
    //     int num = 1;  // Inicializamos num en 1 para que comience desde 1
    //     for (RolPermiso rolPermiso : permisos) {
            
    //         if (rolPermiso.getValidacion().contains(validacion) || rolPermiso.getIdRol() == rolId) {
    //             System.out.println(num++ + ". " + rolPermiso.getNombrePermiso());
    //         }
    //         // Aquí puedes agregar más lógica si es necesario
    //     }
        
    //     return permisos;
    // }
    

    private boolean verificarUsuarioExistente(String usuario) {
        return UserRepository.existsByUsername(usuario);
    }
}