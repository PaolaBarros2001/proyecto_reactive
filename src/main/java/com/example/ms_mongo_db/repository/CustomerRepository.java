package com.example.ms_mongo_db.repository;

import com.example.ms_mongo_db.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {
}

