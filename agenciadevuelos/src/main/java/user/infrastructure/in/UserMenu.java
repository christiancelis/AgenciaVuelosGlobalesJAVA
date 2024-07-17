package user.infrastructure.in;

import com.agenciadevuelosglobales.Menu.MenuManager;

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
import user.infrastructure.out.UserRepository;

public class UserMenu {

    public void MenuUser() {
        ServiceUser serviceUser = new UserRepository();
        
        ServiceRol serviceRol = new RolRepository();
       
        GetAllRol getAllRol = new GetAllRol(serviceRol);
        CreateUser createUser = new CreateUser(serviceUser);
        UpdateUser updateUser = new UpdateUser(serviceUser);
        RolPermiso rolPermiso = new RolPermiso();
        GetAllPermisos getAllPermisos = new GetAllPermisos(serviceUser);
        ValidationUser validationUser = new ValidationUser(serviceUser);
        FindUser findUser = new FindUser(serviceUser);
        UserController userController = new UserController(createUser, getAllRol, findUser, updateUser, getAllPermisos);

        userController.ValidationAccount();

    }
}
