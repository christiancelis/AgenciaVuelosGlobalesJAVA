package layover.application;

import layover.domain.Layover;
import layover.domain.ServiceLayover;

public class CreateLayover {
private ServiceLayover serviceLayover;

public CreateLayover(ServiceLayover serviceLayover) {
    this.serviceLayover = serviceLayover;
}
 public void execute(Layover layover) {
        serviceLayover.CreateLayover(layover);
    }

}
