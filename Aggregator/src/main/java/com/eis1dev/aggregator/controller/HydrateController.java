package com.eis1dev.aggregator.controller;

import com.eis1dev.aggregator.application.HydrateServiceApplication;
import com.eis1dev.aggregator.model.HydrateRequest;
import com.eis1dev.aggregator.model.HydrateResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hydrate")
public class HydrateController {

    private final HydrateServiceApplication service;

    public HydrateController(HydrateServiceApplication service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<HydrateResponse> hydrate(@Valid @RequestBody HydrateRequest req,
                                                   @RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(service.run(req, headers));
    }
}