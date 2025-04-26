package com.example.ms_mongo_db.controller;

import com.example.ms_mongo_db.DTO.CustomerDTO;
import com.example.ms_mongo_db.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@RestController
@RequestMapping("/v1/customers")
@Validated
public class CustomerController {

    private final ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    // 🔹 Obtener todos los clientes
    @GetMapping
    public Flux<CustomerDTO> getAll() {
        return customerService.findAll();
    }

    // 🔹 Obtener cliente por ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<CustomerDTO>> getById(@PathVariable String id) {
        return customerService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // 🔹 Crear nuevo cliente
    @PostMapping
    public Mono<ResponseEntity<CustomerDTO>> create(@RequestBody CustomerDTO dto) {
        return customerService.save(dto)
                .map(saved -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(saved));
    }

    // 🔹 Actualizar cliente
    @PutMapping("/{id}")
    public Mono<ResponseEntity<CustomerDTO>> update(@PathVariable String id,
                                                     @RequestBody CustomerDTO dto) {
        dto.setId(id);
        return customerService.save(dto)
                .map(updated -> ResponseEntity.ok(updated))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    // 🔹 Eliminar cliente
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return customerService.findById(id)
                .flatMap(existing -> customerService.deleteById(id)
                        .then(Mono.just(ResponseEntity.noContent().<Void>build())))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
