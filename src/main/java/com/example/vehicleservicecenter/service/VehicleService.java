package com.example.vehicleservicecenter.service;
import com.example.vehicleservicecenter.dto.*;
public interface VehicleService { CustomerResponse.VehicleSummary create(VehicleCreateRequest request); ServiceRequestResponseHistory history(Long vehicleId); record ServiceRequestResponseHistory(Long vehicleId,String registrationNumber,java.util.List<ServiceRequestResponse> jobs){} }
