package com.pm.ecommerce.controller;


import com.pm.ecommerce.common.dto.ResultDTO;
import com.pm.ecommerce.dto.CustomerRequest;
import com.pm.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<ResultDTO> create(@Valid @RequestBody CustomerRequest customerRequest) {
        return customerService.create(customerRequest);
    }

    @PutMapping
    public ResponseEntity<ResultDTO> update(@Valid @RequestBody CustomerRequest customerRequest, String id) {
        return customerService.update(id, customerRequest);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResultDTO> delete(@PathVariable(name = "id") String id) {
        return customerService.delete(id);
    }

    @GetMapping
    public ResponseEntity<ResultDTO> getAll() {
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> getById(@PathVariable(name = "id") String id) {
        return customerService.getByCustomerId(id);
    }

}