package com.example.data.jdbc.service;

import com.example.data.jdbc.entity.Customer;
import org.springframework.data.domain.Page;

public interface CustomerService {

    Customer saveOrUpdate(Customer customer);

    void deleteById(Long id);

    Customer findById(Long id);

    Page<Customer> pageQuery(Integer pageNum, Integer pageSize);
}
