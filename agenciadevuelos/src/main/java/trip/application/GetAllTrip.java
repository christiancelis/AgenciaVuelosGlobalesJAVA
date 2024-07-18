package trip.application;
import java.util.ArrayList;
import trip.domain.ServiceTrip;
import trip.domain.Trip;

public class GetAllTrip{
private final ServiceTrip serviceTrip;

public GetAllTrip(ServiceTrip serviceTrip) {
    this.serviceTrip = serviceTrip;
}
 public ArrayList<Trip> execute(){
        return serviceTrip.getAllTrip();
    } 
}
