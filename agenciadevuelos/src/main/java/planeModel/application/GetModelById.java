package planeModel.application;

import planeModel.domain.Model;
import planeModel.domain.ServiceModel;

public class GetModelById {
 private final ServiceModel serviceModel;

    public GetModelById(ServiceModel serviceModel) {
        this.serviceModel = serviceModel;
    }

    public Model execute(int id){
        return serviceModel.getModelById(id);
    }
}
