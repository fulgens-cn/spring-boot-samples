package com.example.data.jdbc.web.api;

import com.example.data.jdbc.common.ServerResponse;
import com.example.data.jdbc.entity.Customer;
import com.example.data.jdbc.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/{id}")
    public ServerResponse<Customer> findById(@PathVariable Long id) {
        return ServerResponse.success(customerService.findById(id));
    }

    @PostMapping("")
    public ServerResponse<Customer> saveCustomer(@RequestBody Customer customer) {
        return ServerResponse.success(customerService.saveOrUpdate(customer));
    }

    @DeleteMapping("/{id}")
    public ServerResponse deleteById(@PathVariable Long id) {
        customerService.deleteById(id);
        return ServerResponse.success();
    }

    @PutMapping("/{id}")
    public ServerResponse<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customer.setId(id);
        return ServerResponse.success(customerService.saveOrUpdate(customer));
    }
}
