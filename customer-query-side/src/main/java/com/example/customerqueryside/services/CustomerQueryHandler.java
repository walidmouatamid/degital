package com.example.customerqueryside.services;

import com.example.customerqueryside.entities.Customer;
import com.example.customerqueryside.queries.GetAllCustomersQuery;
import com.example.customerqueryside.queries.GetCustomerByIdQuery;
import com.example.customerqueryside.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class CustomerQueryHandler {

    public CustomerRepository customerRepository;

    @QueryHandler
    public List<Customer> customerList(GetAllCustomersQuery query){
        return customerRepository.findAll();
    }

    @QueryHandler
    public Customer customerById(GetCustomerByIdQuery query){
        return customerRepository.findById(query.getCustomerId())
                .orElseThrow(()-> new RuntimeException("Customer not found ..."));
    }
}
