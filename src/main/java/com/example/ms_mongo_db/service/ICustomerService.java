package com.example.ms_mongo_db.service;

import com.example.ms_mongo_db.DTO.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {
    Flux<CustomerDTO> findAll();
    Mono<CustomerDTO> findById(String id);
    Mono<CustomerDTO> save(CustomerDTO customerDTO);
    Mono<Void> deleteById(String id);
}