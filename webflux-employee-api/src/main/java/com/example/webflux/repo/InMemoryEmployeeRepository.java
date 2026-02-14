
package com.example.webflux.repo;

import com.example.webflux.model.Employee;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryEmployeeRepository implements EmployeeRepository {

    private final Map<String, Employee> db = new ConcurrentHashMap<>();

    @Override
    public Mono<Employee> save(Employee e) {
        String id = (e.id() == null || e.id().isBlank())
                ? UUID.randomUUID().toString()
                : e.id();

        Employee saved = new Employee(id, e.name(), e.role(), e.salary());
        db.put(id, saved);
        return Mono.just(saved);
    }

    @Override
    public Mono<Employee> findById(String id) {
        Employee e = db.get(id);
        return e == null ? Mono.empty() : Mono.just(e);
    }

    @Override
    public Flux<Employee> findAll() {
        return Flux.fromIterable(db.values());
    }

    @Override
    public Mono<Void> deleteById(String id) {
        db.remove(id);
        return Mono.empty();
    }
}
