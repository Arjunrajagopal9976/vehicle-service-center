package com.example.vehicleservicecenter.mapper;

import com.example.vehicleservicecenter.dto.ServiceRequestResponse;
import com.example.vehicleservicecenter.entity.ServiceRequest;
import org.springframework.stereotype.Component;

@Component
public class ServiceRequestMapper {
    public ServiceRequestResponse toResponse(ServiceRequest request) {
        return new ServiceRequestResponse(request.getId(), request.getVehicle().getId(),
            request.getVehicle().getRegistrationNumber(),
            request.getMechanic() == null ? null : request.getMechanic().getId(),
            request.getMechanic() == null ? null : request.getMechanic().getName(),
            request.getDescription(), request.getStatus(), request.getScheduledAt(),
            request.getNotes(), request.getCreatedAt(),
            request.getParts().entrySet().stream().map(e ->
                new ServiceRequestResponse.PartLine(e.getKey(), e.getValue().getName(),
                    request.getPartQuantities().getOrDefault(e.getKey(), 1),
                    e.getValue().getUnitPrice().toPlainString())).toList());
    }
}
