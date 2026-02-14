
package com.example.webflux.controller;

import com.example.webflux.model.Employee;
import com.example.webflux.repo.EmployeeRepository;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeRepository repo;

    public EmployeeController(EmployeeRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public Mono<Employee> create(@RequestBody Employee e) {
        return repo.save(e);
    }

    @GetMapping("/{id}")
    public Mono<Employee> get(@PathVariable String id) {
        return repo.findById(id);
    }

    @GetMapping
    public Flux<Employee> list() {
        return repo.findAll();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return repo.deleteById(id);
    }
}
