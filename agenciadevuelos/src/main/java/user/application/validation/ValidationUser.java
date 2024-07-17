package user.application.validation;

import user.domain.ServiceUser;

public class ValidationUser{
private ServiceUser serviceUser;

public ValidationUser(ServiceUser serviceUser) {
    this.serviceUser = serviceUser;
}

 public void execute(int id,String contraseña) {
        serviceUser.ValidationUser(id,contraseña);
    }
}