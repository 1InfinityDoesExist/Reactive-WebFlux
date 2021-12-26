package com.reactive.flux.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

@EnableReactiveMongoRepositories
public class MongoConfig extends AbstractReactiveMongoConfiguration {

	@Value("${spring.data.mongodb.uri}")
	private String mongoUrl;

	@Override
	public MongoClient reactiveMongoClient() {
		return MongoClients.create(mongoUrl);
	}

	@Override
	protected String getDatabaseName() {
		return "reactive_flux";
	}

}
