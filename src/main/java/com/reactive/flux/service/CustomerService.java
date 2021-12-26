package com.reactive.flux.service;

import org.springframework.stereotype.Service;

import com.reactive.flux.bean.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface CustomerService {

	public Mono<Customer> saveCustomer(Customer customer);

	public Mono<Customer> findCustomer(String id);

	public Flux<Customer> findAllCustomer();

}
