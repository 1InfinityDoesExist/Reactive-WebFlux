package com.reactive.flux.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.reactive.flux.bean.Customer;
import com.reactive.flux.repository.CustomerRepository;
import com.reactive.flux.service.CustomerService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Mono<Customer> saveCustomer(Customer customer) {
		log.info("----Persisting customer details in database.");
		return this.customerRepository.save(customer);
	}

	@Override
	public Mono<Customer> findCustomer(String id) {
		log.info("----Retrieving data from database: {}", id);
		return this.customerRepository.findById(id);
	}

	@Override
	public Flux<Customer> findAllCustomer() {
		log.info("-----Retrieving all the data from the database.");
		return this.customerRepository.findAll();
	}

	
}
