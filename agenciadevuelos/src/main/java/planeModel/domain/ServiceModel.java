package planeModel.domain;

import java.util.ArrayList;

public interface ServiceModel {
    ArrayList <Model> getAllModels();
    Model getModelById(Integer id);
}
