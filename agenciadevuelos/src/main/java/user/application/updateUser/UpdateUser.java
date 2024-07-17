package user.application.updateUser;

import user.domain.ServiceUser;
import user.domain.User;

public class UpdateUser {
private final ServiceUser serviceUser;

public UpdateUser(ServiceUser serviceUser) {
    this.serviceUser = serviceUser;
}

public User execute(int id,String user,String contraseña,int rolnumber) {
        return serviceUser.UpdateUserById(id,user,contraseña, rolnumber);
    }

}
