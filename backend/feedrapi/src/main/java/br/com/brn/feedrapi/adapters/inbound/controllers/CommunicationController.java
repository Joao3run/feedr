package br.com.brn.feedrapi.adapters.inbound.controllers;

import br.com.brn.feedrapi.application.domain.filters.CommunicationFilter;
import br.com.brn.feedrapi.application.domain.models.Communication;
import br.com.brn.feedrapi.application.ports.services.CommunicationServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/communications")
public class CommunicationController {

    private final CommunicationServicePort communicationService;

    public CommunicationController(CommunicationServicePort communicationService) {
        this.communicationService = communicationService;
    }

    @GetMapping
    public List<Communication> listCommunicationsByFilter(@RequestBody @Valid CommunicationFilter filter) {
        return communicationService.listCommunicationsByFilter(filter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Communication> findById(@PathVariable("id") Long id) {
        Communication consumer = communicationService.findById(id);
        if (!Objects.isNull(consumer)) {
            return new ResponseEntity<>(consumer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


}
