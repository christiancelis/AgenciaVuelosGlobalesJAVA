package modelp.application;

import modelp.domain.Model;
import modelp.domain.ServiceModel;

public class GetModelById {
 private final ServiceModel serviceModel;

    public GetModelById(ServiceModel serviceModel) {
        this.serviceModel = serviceModel;
    }

    public Model execute(int id){
        return serviceModel.getModelById(id);
    }
}
