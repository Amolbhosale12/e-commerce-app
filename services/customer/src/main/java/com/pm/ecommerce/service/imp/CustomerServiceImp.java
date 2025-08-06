package com.pm.ecommerce.service.imp;

import com.pm.ecommerce.common.constant.Constants;
import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.common.utils.ResultBuilder;
import com.pm.ecommerce.dto.AddressRequest;
import com.pm.ecommerce.dto.AddressResponse;
import com.pm.ecommerce.dto.CustomerRequest;
import com.pm.ecommerce.dto.CustomerResponse;
import com.pm.ecommerce.model.Address;
import com.pm.ecommerce.model.Customer;
import com.pm.ecommerce.repository.CustomerRepository;
import com.pm.ecommerce.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        log.info("CustomerServiceImp instantiated");
    }

    public static Customer toCustomer(CustomerRequest customerRequest) {
        return Customer
                .builder()
                .firstName(customerRequest.getFirstName())
                .lastName(customerRequest.getLastName())
                .email(customerRequest.getEmail())
                .address(toAddress(customerRequest.getAddressRequest()))
                .build();
    }

    public static Address toAddress(AddressRequest addressRequest) {
        return Address
                .builder()
                .street(addressRequest.getStreet())
                .houseNumber(addressRequest.getHouseNumber())
                .zipCode(addressRequest.getZipCode())
                .build();
    }

    public static CustomerResponse toCustomerResponse(Customer customer) {
        return CustomerResponse
                .builder()
                .id(customer.getId())
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .addressResponse(toAddressResponse(customer))
                .build();
    }

    public static AddressResponse toAddressResponse(Customer customer) {
        return AddressResponse
                .builder()
                .street(customer.getAddress().getStreet())
                .houseNumber(customer.getAddress().getHouseNumber())
                .zipCode(customer.getAddress().getZipCode()).build();
    }

    @Override
    public ResponseEntity<ResultDTO> create(CustomerRequest customerRequest) {

        //check duplication
        try {
            boolean response = customerRepository.existsByEmail(customerRequest.getEmail());
            if (response) {
                log.info("Customer already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(ResultBuilder.failure(Constants.EMAIL_ALREADY_EXIST.toString()));
            }
            customerRepository.save(toCustomer(customerRequest));
            log.info("customer created");
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(ResultBuilder.success(Constants.CUSTOMER_CREATED.toString()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultBuilder.failure(e.getMessage()));
        }

    }

    @Override
    public ResponseEntity<ResultDTO> update(String id, CustomerRequest customerRequest) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isEmpty()) {
                log.info("customer not found");
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResultBuilder.failure(Constants.CUSTOMER_NOT_FOUND.getValue()));
            }
            customerRepository.save(toCustomer(customerRequest));
            log.info("customer updated");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResultBuilder.success(Constants.CUSTOMER_UPDATED.toString()));

        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultBuilder.failure(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<ResultDTO> delete(String id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isEmpty()) {
                log.info("Customer not found with id {}", id);
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResultBuilder.failure(Constants.CUSTOMER_NOT_FOUND.toString()));
            }
            customerRepository.deleteById(id);
            log.info("Customer deleted");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResultBuilder.success(Constants.CUSTOMER_DELETED.getValue()));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultBuilder.failure(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<ResultDTO> getByCustomerId(String id) {
        try {
            Optional<Customer> customer = customerRepository.findById(id);
            if (customer.isEmpty()) {
                log.info("Customer not found id {}", id);
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResultBuilder.failure(Constants.CUSTOMER_NOT_FOUND.toString()));
            }
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResultBuilder.success(Constants.CUSTOMER_FETCH_SUCCESSFULLY.getValue(), toCustomerResponse(customer.get())));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ResultBuilder.failure(e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<ResultDTO> getAll() {
        try {
            List<Customer> list = customerRepository.findAll();
            if (list.isEmpty()) {
                log.info("Customer list is empty");
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ResultBuilder.failure(Constants.CUSTOMER_NOT_FOUND.toString()));
            }
            List<CustomerResponse> customerReponseList = list.stream().map(CustomerServiceImp::toCustomerResponse).toList();

            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(ResultBuilder.success(Constants.FETCH_ALL_CUSTOMERS_SUCCESSFULLY.getValue(), customerReponseList));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResultBuilder.failure(e.getMessage()));
        }
    }
}
