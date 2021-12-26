package com.reactive.flux.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactive.flux.handler.CustomerHandler;
import com.reactive.flux.handler.CustomerStreamHandler;

@Configuration
public class RouterConfig {

	@Autowired
	private CustomerHandler customerHandler;

	@Autowired
	CustomerStreamHandler customerStreamHandler;

	@Bean
	public RouterFunction<ServerResponse> routerFuntion() {
		return RouterFunctions.route()
				.POST("/router/customer/save", customerHandler::persisteCustomer)
				.GET("/router/customers/{id}", customerHandler::loadCustomerById)
				.GET("/router/customers/stream", customerStreamHandler::loadCustomer)
				.GET("/router/customers", customerHandler::loadCustomer).build();
	}
}
