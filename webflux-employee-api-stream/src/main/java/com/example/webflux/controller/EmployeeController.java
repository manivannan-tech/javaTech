package com.example.webflux.controller;

import com.example.webflux.model.Employee;
import com.example.webflux.service.EmployeeService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    // ---- Normal JSON APIs (Angular HttpClient typically consumes these) ----

    @PostMapping
    public Mono<Employee> create(@RequestBody Employee e) {
        return service.create(e);
    }

    @GetMapping("/{id}")
    public Mono<Employee> get(@PathVariable String id) {
        return service.get(id);
    }

    @GetMapping
    public Flux<Employee> list() {
        return service.list();
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id) {
        return service.delete(id);
    }

    // ---- Streaming APIs (SSE) ----
    // In Postman: use GET and watch the response keep appending events.

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Employee> streamEmployees() {
        return service.streamEmployees();
    }

    @GetMapping(value = "/stream/stats", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamStats() {
        return service.streamStats();
    }

    @GetMapping(value="/notifications/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamNotifications() {
        return Flux.interval(Duration.ofSeconds(2))
                .map(i -> "New notification #" + i + " at " + java.time.Instant.now());
    }
}
