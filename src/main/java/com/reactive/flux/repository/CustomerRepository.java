package com.reactive.flux.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.reactive.flux.bean.Customer;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

}
