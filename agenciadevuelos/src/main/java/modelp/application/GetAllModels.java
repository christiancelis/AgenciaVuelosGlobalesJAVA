package modelp.application;

import java.util.ArrayList;

import modelp.domain.Model;
import modelp.domain.ServiceModel;

public class GetAllModels {
    private final ServiceModel serviceModel;

    public GetAllModels(ServiceModel serviceModel) {
        this.serviceModel = serviceModel;
    }

    public ArrayList<Model> execute(){
        return serviceModel.getAllModels();
    }
}
