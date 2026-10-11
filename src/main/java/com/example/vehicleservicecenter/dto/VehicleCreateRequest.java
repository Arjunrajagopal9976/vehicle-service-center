package com.example.vehicleservicecenter.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record VehicleCreateRequest(@NotNull Long customerId,
 @NotBlank @Size(max=30) String registrationNumber,
 @NotBlank @Size(max=80) String make, @NotBlank @Size(max=80) String model,
 @Min(1950) int year) {}
