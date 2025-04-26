package com.example.ms_mongo_db.service;

import com.example.ms_mongo_db.DTO.CustomerDTO;
import com.example.ms_mongo_db.model.Customer;
import com.example.ms_mongo_db.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Flux<CustomerDTO> findAll() {
        return repository.findAll()
                .map(this::toDTO);
    }

    @Override
    public Mono<CustomerDTO> findById(String id) {
        return repository.findById(id)
                .map(this::toDTO);
    }

    @Override
    public Mono<CustomerDTO> save(CustomerDTO dto) {
        Customer customer = toEntity(dto);
        return repository.save(customer)
                .map(this::toDTO);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return repository.deleteById(id);
    }

    // 🔄 Métodos de conversión DTO <-> Entity

    private CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setEmail(customer.getEmail());
        return dto;
    }

    private Customer toEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        return customer;
    }
}


