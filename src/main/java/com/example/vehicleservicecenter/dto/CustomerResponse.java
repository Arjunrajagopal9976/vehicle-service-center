package com.example.vehicleservicecenter.dto;

import java.util.List;

public record CustomerResponse(Long id, String name, String email, String phone, List<VehicleSummary> vehicles) {
    public record VehicleSummary(Long id, String registrationNumber, String make, String model, int year) {}
}
