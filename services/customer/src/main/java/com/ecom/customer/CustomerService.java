package com.ecom.customer;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ecom.exception.CustomerNotFoundException;

import java.util.Objects;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public String createCustomer(CustomerRequest request){

        var mapped = Objects.requireNonNull(mapper.toCustomer(request), "Mapped customer must not be null");
        var customer = repository.save(mapped);

        return customer.getId();
    }
    public void updateCustomer(CustomerRequest request) {
        
        var customer = repository.findById(request.id())
                  .orElseThrow(() -> new CustomerNotFoundException(
                      String.format("No customer found :: No customer :: %s", request.id())
                  ));

                mergerCustomer(customer, request);
                repository.save(customer);
    }

    private void mergerCustomer(Customer customer, CustomerRequest request) {
        
        if (StringUtils.isNotBlank(request.firstname())) {
            customer.setFirstname(request.firstname());
        }

        if (StringUtils.isNotBlank(request.lastname())) {
            customer.setLastname(request.lastname());
        }

        if (StringUtils.isNotBlank(request.email())) {
            customer.setEmail(request.email());
        }

        if (request.address() != null) {
            customer.setAddress(request.address());
        }
    }
    

}


