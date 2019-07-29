package com.example.data.jdbc.service.impl;

import com.example.data.jdbc.common.utils.SpringUtils;
import com.example.data.jdbc.entity.Customer;
import com.example.data.jdbc.exception.ResourceNotFoundException;
import com.example.data.jdbc.repository.CustomerRepository;
import com.example.data.jdbc.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer saveOrUpdate(Customer customer) {
        if (customer.getId() == null) {
            return customerRepository.save(customer);
        }
        Customer dbCustomer = customerRepository.findById(customer.getId()).get();
        SpringUtils.copyPropertiesIgnoreNull(customer, dbCustomer);
        return customerRepository.save(dbCustomer);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("客户不存在"));
    }

    @Override
    public Page<Customer> pageQuery(Integer pageNum, Integer pageSize) {
        return customerRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
    }
}
