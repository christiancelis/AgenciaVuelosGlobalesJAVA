package planeModel.application;

import java.util.ArrayList;

import planeModel.domain.Model;
import planeModel.domain.ServiceModel;

public class GetAllModels {
    private final ServiceModel serviceModel;

    public GetAllModels(ServiceModel serviceModel) {
        this.serviceModel = serviceModel;
    }

    public ArrayList<Model> execute(){
        return serviceModel.getAllModels();
    }
}
