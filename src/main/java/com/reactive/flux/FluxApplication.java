package com.reactive.flux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

import com.reactive.flux.bean.Customer;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class FluxApplication {

	public static void main(String[] args) {
		SpringApplication.run(FluxApplication.class, args);
		FluxApplication.thridPartyAPICall();
	}

	public static void thridPartyAPICall() {
		WebClient client = WebClient.create("http://localhost:8080/");

		Flux<Customer> flux = client.get().uri("customers/get").retrieve().bodyToFlux(Customer.class);
		flux.doOnNext(System.out::println).blockLast();
	}

}
