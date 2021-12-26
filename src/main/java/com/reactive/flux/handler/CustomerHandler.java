package com.reactive.flux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.flux.bean.Customer;
import com.reactive.flux.service.CustomerService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CustomerHandler {

	@Autowired
	private CustomerService customerService;

	public Mono<ServerResponse> loadCustomer(ServerRequest request) {

		Flux<Customer> findAllCustomer = customerService.findAllCustomer();
		return ServerResponse.ok().body(findAllCustomer, Customer.class);
	}

	public Mono<ServerResponse> loadCustomerById(ServerRequest request) {

		Mono<Customer> findAllCustomer = customerService.findCustomer(request.pathVariable("id"));
		return ServerResponse.ok().body(findAllCustomer, Customer.class);
	}

	public Mono<ServerResponse> persisteCustomer(ServerRequest request) {
		log.info("Payload : {}", request.bodyToMono(Customer.class));

		// assume
		Mono<Customer> persistedCustomer = customerService.findAllCustomer().take(1).single();
		return ServerResponse.ok().body(persistedCustomer, Customer.class);
	}
}