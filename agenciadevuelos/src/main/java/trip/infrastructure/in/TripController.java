package trip.infrastructure.in;
import java.util.ArrayList;
import trip.application.GetAllTrip;
import trip.domain.Trip;


public class TripController {
private final GetAllTrip getAllTrip ;

public TripController(GetAllTrip getAllTrip) {
    this.getAllTrip =  getAllTrip;
}


public void ListAllTrip(){
    ArrayList<Trip> listTrip = getAllTrip.execute();
    for (Trip trip : listTrip) {
        System.out.println("estos son los viajes: "+  trip);
    }
}


}
