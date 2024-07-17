package user.application.createUser;
import user.domain.User;

import user.domain.ServiceUser;

public class CreateUser {
private ServiceUser serviceUser;

public CreateUser(ServiceUser serviceUser) {
    this.serviceUser = serviceUser;
}

 public void execute(User user) {
        serviceUser.CreateUser(user);
    }

}
