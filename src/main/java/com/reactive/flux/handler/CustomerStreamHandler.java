package com.reactive.flux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.flux.bean.Customer;
import com.reactive.flux.service.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

	@Autowired
	private CustomerService customerService;

	public Mono<ServerResponse> loadCustomer(ServerRequest request) {

		Flux<Customer> customerStream = customerService.findAllCustomer();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customerStream, Customer.class);
	}
}
