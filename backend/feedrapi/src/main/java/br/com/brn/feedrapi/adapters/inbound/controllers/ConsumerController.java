package br.com.brn.feedrapi.adapters.inbound.controllers;

import br.com.brn.feedrapi.adapters.inbound.dtos.ConsumerDTO;
import br.com.brn.feedrapi.application.domain.filters.ConsumerFilter;
import br.com.brn.feedrapi.application.domain.models.Consumer;
import br.com.brn.feedrapi.application.ports.services.ConsumerServicePort;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/consumers")
public class ConsumerController {

    private final ConsumerServicePort consumerService;

    public ConsumerController(ConsumerServicePort consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping
    public List<Consumer> listConsumers(@RequestBody @Valid ConsumerFilter filter) {
        return consumerService.listConsumersByFilter(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consumer> findById(@PathVariable("id") Long id) {
        Consumer consumer = consumerService.findById(id);
        if (!Objects.isNull(consumer)) {
            return new ResponseEntity<>(consumer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public Consumer save(@RequestBody @Valid ConsumerDTO consumerDTO) {
        Consumer consumer = new Consumer();
        BeanUtils.copyProperties(consumerDTO, consumer);
        return consumerService.save(consumer);
    }

}
