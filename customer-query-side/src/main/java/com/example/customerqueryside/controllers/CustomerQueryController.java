package com.example.customerqueryside.controllers;

import com.example.customerqueryside.entities.Customer;
import com.example.customerqueryside.queries.GetAllCustomersQuery;
import com.example.customerqueryside.queries.GetCustomerByIdQuery;
import com.example.customerqueryside.repositories.CustomerRepository;
import com.example.customerqueryside.services.CustomerEventHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequestMapping("/query/customer")
@Slf4j
@RestController
@AllArgsConstructor
public class CustomerQueryController {
    public QueryGateway queryGateway;

    @GetMapping("/all")
    public CompletableFuture<List<Customer>> allCustomers() {
        return queryGateway.query(
                new GetAllCustomersQuery(),
                ResponseTypes.multipleInstancesOf(Customer.class)
        );
    }

    @GetMapping("/byId/{customerId}")
    public CompletableFuture<Customer> customerById(@PathVariable String customerId) {
        return queryGateway.query(
                new GetCustomerByIdQuery(customerId),
                ResponseTypes.instanceOf(Customer.class)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> excpetionHandler(Exception exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
