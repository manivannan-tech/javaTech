
package com.example.webflux.repo;

import com.example.webflux.model.Employee;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeRepository {
    Mono<Employee> save(Employee e);
    Mono<Employee> findById(String id);
    Flux<Employee> findAll();
    Mono<Void> deleteById(String id);
}
