package user.infrastructure.in;
import rol.application.GetAllRol;
import rol.domain.ServiceRol;
import rol.infrastructure.out.RolRepository;
import user.application.GetAllPermisos.GetAllPermisos;
import user.application.createUser.CreateUser;
import user.application.findUser.FindUser;
import user.application.updateUser.UpdateUser;


import user.domain.ServiceUser;

import user.infrastructure.out.UserRepository;

public class UserMenu {

    public void MenuUser() throws Exception {
        ServiceUser serviceUser = new UserRepository();
        
        ServiceRol serviceRol = new RolRepository();
       
        GetAllRol getAllRol = new GetAllRol(serviceRol);
        CreateUser createUser = new CreateUser(serviceUser);
        UpdateUser updateUser = new UpdateUser(serviceUser);
        FindUser findUser = new FindUser(serviceUser);
        GetAllPermisos getAllPermisos = new GetAllPermisos(serviceUser);
        UserController userController = new UserController(createUser, getAllRol, findUser, updateUser, getAllPermisos);

        userController.ValidationAccount();

    }





}
