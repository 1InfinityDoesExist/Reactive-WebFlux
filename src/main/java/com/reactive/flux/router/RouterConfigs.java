package com.reactive.flux.router;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

import com.reactive.flux.handler.PersonHandler;

import reactor.core.publisher.Mono;

@Configuration
public class RouterConfigs {

	@Autowired
	private PersonHandler personHandler;

	@Bean
	public RouterFunction<?> routes() {

		return RouterFunctions.route()
				.GET("/persons", personHandler::getPersons)
				.GET("/person/{id}", personHandler::getPerson)
				.GET("/person/{id}/hobby", personHandler::getPersonHobbies)
				.GET("/persons/events", personHandler::getPersonEvents)
				.filter((request, next) -> {
					Duration delay = request.queryParam("delay").map(s -> Duration.ofSeconds(Long.parseLong(s)))
							.orElse(Duration.ZERO);
					return delay.isZero() ? next.handle(request)
							: Mono.delay(delay).flatMap(aLong -> next.handle(request));
				}).build();
	}

}
