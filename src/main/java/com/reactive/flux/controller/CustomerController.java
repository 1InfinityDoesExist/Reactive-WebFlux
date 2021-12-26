package com.reactive.flux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reactive.flux.bean.Customer;
import com.reactive.flux.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping(value = "/save")
	public Mono<Customer> saveCustomerUsingPOST(@RequestBody Customer customer) {
		return this.customerService.saveCustomer(customer);
	}

	@GetMapping("/{id}")
	public Mono<Customer> findCustomerUsingGET(@PathVariable String id) {
		return this.customerService.findCustomer(id);
	}

	@GetMapping(value = "/get", produces = "text/event-stream")
	public Flux<Customer> getAllCustomerUsingGET() {
		return this.customerService.findAllCustomer();
	}

}
