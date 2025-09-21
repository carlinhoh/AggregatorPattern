package com.eis1dev.aggregator.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Map;

public record HydrateRequest(
        @NotBlank String useCase,
        @NotNull  Map<String, Object> ids
) {}