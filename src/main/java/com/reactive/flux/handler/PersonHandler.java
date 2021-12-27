package com.reactive.flux.handler;

import java.time.Duration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonHandler {

	private static final Logger log = LoggerFactory.getLogger(PersonHandler.class);

	public static final Map<Long, Map<String, Object>> PERSON_DATA;
	private static final Map<Long, Map<String, Object>> HOBBY_DATA;

	private static final Mono<ServerResponse> NOT_FOUND = ServerResponse.notFound().build();

	static {
		PERSON_DATA = new HashMap<>();
		addPerson(1L, "Amanda");
		addPerson(2L, "Brittany");
		addPerson(3L, "Christopher");
		addPerson(4L, "Elizabeth");
		addPerson(5L, "Hannah");
		addPerson(6L, "Joshua");
		addPerson(7L, "Kayla");
		addPerson(8L, "Lauren");
		addPerson(9L, "Matthew");
		addPerson(10L, "Megan");
	}

	static {
		HOBBY_DATA = new HashMap<>();
		addHobby(1L, "Travel");
		addHobby(2L, "Coffee Roasting");
		addHobby(3L, "Puzzles");
		addHobby(4L, "3D Printing");
		addHobby(5L, "Skiing");
		addHobby(6L, "Yoga");
		addHobby(7L, "Scuba Diving");
		addHobby(8L, "Shopping");
		addHobby(9L, "Tai Chi");
		addHobby(10L, "Kombucha brewing");
	}

	private static void addPerson(Long id, String name) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("id", id);
		map.put("name", name);
		PERSON_DATA.put(id, map);
	}

	private static void addHobby(Long personId, String hobby) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("personId", personId);
		map.put("hobby", hobby);
		HOBBY_DATA.put(personId, map);
	}

	public Mono<ServerResponse> getPersons(ServerRequest request) {
		return ServerResponse.ok().bodyValue(PERSON_DATA.values());
	}

	public Mono<ServerResponse> getPerson(ServerRequest request) {
		Long personId = Long.parseLong(request.pathVariable("id"));
		log.info("-----personId : {}", personId);
		Map<String, Object> personObject = PERSON_DATA.get(personId);

		return personObject != null ? ServerResponse.ok().bodyValue(personObject) : NOT_FOUND;
	}

	public Mono<ServerResponse> getPersonHobbies(ServerRequest request) {
		Long personId = Long.parseLong(request.pathVariable("id"));
		log.info("-----personId : {}", personId);
		Map<String, Object> personObject = HOBBY_DATA.get(personId);

		return personObject != null ? ServerResponse.ok().bodyValue(personObject) : NOT_FOUND;
	}

	public Mono<ServerResponse> getPersonEvents(ServerRequest request) {
		return ServerResponse.ok().body(BodyInserters.fromServerSentEvents(
				Flux.interval(Duration.ofSeconds(2))
				.map(i -> PERSON_DATA.get((i % 10) + 1)).map(data -> ServerSentEvent.builder(data).build())));
	}
}
