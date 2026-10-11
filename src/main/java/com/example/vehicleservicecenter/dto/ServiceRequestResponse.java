package com.example.vehicleservicecenter.dto;

import com.example.vehicleservicecenter.enums.JobStatus;
import java.time.LocalDateTime;
import java.util.List;

public record ServiceRequestResponse(Long id, Long vehicleId, String registrationNumber,
 Long mechanicId, String mechanicName, String description, JobStatus status,
 LocalDateTime scheduledAt, String notes, LocalDateTime createdAt,
 List<PartLine> parts) {
 public record PartLine(Long partId, String name, int quantity, String unitPrice) {}
}
