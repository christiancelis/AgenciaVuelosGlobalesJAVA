package user.application.findUser;

import user.domain.ServiceUser;
import user.domain.User;

public class FindUser {
private ServiceUser serviceUser;

public FindUser(ServiceUser serviceUser) {
    this.serviceUser = serviceUser;
}

public User execute(int id) {
        return serviceUser.FindUserById(id);
    }
}
