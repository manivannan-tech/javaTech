package com.example.webflux.service;

import com.example.webflux.model.Employee;
import com.example.webflux.repo.EmployeeRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
public class EmployeeService {

    private final EmployeeRepository repo;

    public EmployeeService(EmployeeRepository repo) {
        this.repo = repo;
    }

    public Mono<Employee> create(Employee e) {
        return repo.save(e);
    }

    public Mono<Employee> get(String id) {
        return repo.findById(id);
    }

    public Flux<Employee> list() {
        return repo.findAll();
    }

    public Mono<Void> delete(String id) {
        return repo.deleteById(id);
    }

    /**
     * SSE Streaming demo:
     * Emits employees one-by-one with a small delay so you can visually observe incremental output
     * in Postman or a browser. (The delay is only for demo.)
     */
    public Flux<Employee> streamEmployees() {
        return repo.findAll()
                .delayElements(Duration.ofMillis(200));
    }

    /**
     * SSE Streaming demo:
     * Emits a heartbeat every 1s with current employee count.
     */
    public Flux<String> streamStats() {
        return Flux.interval(Duration.ofSeconds(1))
                .flatMap(tick -> repo.findAll().count()
                        .map(cnt -> "tick=" + tick + ", employees=" + cnt));
    }


}
