package com.example.vehicleservicecenter.dto;

import com.example.vehicleservicecenter.enums.JobStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record StatusUpdateRequest(@NotNull JobStatus status, @Size(max=2000) String notes) {}
