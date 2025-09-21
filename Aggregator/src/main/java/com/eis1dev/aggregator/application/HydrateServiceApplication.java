package com.eis1dev.aggregator.application;
import com.eis1dev.aggregator.model.HydrateRequest;
import com.eis1dev.aggregator.model.HydrateResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.ProducerTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class HydrateServiceApplication {

    private final ProducerTemplate template;
    private final ObjectMapper mapper;

    public HydrateServiceApplication(ProducerTemplate template, ObjectMapper mapper) {
        this.template = template;
        this.mapper = mapper;
    }

    public HydrateResponse run(HydrateRequest req, HttpHeaders incoming) {
        Map<String, Object> headers = new HashMap<>(req.ids());
        String route = "direct:" + req.useCase();
        String json = template.requestBodyAndHeaders(route, null, headers, String.class);
        try {
            Map<String,Object> map = mapper.readValue(json, new TypeReference<>() {});
            return new HydrateResponse(map);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to parse aggregation result", e);
        }
    }
}

