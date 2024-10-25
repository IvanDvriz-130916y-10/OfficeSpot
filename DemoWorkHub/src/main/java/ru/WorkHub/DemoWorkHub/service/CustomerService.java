package ru.WorkHub.DemoWorkHub.service;

import ru.WorkHub.DemoWorkHub.dto.CustomerDto;
import ru.WorkHub.DemoWorkHub.entity.Customer;

import java.util.List;

public interface CustomerService {

    void saveCustomer(CustomerDto customerDto);

    Customer findCustomerByEmail(String email);

    List<CustomerDto> findAllCustomers();
}