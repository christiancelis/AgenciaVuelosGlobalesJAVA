package customer.application;

import customer.domain.Customer;
import customer.domain.ServiceCustomer;

public class CreateCustomer {
    private final ServiceCustomer serviceCustomer;

    public CreateCustomer(ServiceCustomer serviceCustomer) {
        this.serviceCustomer = serviceCustomer;
    }

    public void execute(Customer customer) {
        serviceCustomer.CreateCustomer(customer);
    }

}
