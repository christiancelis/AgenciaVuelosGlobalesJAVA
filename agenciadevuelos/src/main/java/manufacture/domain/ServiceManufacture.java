package manufacture.domain;

import java.util.ArrayList;

public interface ServiceManufacture {
    ArrayList <Manufacture> getAllManufactures();
    Manufacture getManufactureById(Integer id);
}
