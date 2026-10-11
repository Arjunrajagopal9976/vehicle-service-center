package com.example.vehicleservicecenter.service;

import com.example.vehicleservicecenter.dto.*;
import com.example.vehicleservicecenter.enums.JobStatus;
import com.example.vehicleservicecenter.entity.ServiceRequest;
import java.math.BigDecimal;
import java.util.List;

public interface ServiceRequestService {
 ServiceRequestResponse book(ServiceBookingRequest request);
 ServiceRequestResponse get(Long id);
 List<ServiceRequestResponse> all(JobStatus status);
 ServiceRequestResponse updateStatus(Long id,StatusUpdateRequest request);
 ServiceRequestResponse assignMechanic(Long id,Long mechanicId);
 ServiceRequestResponse addPart(Long id,Long partId,int quantity);
 InvoiceResponse invoice(Long id,BigDecimal laborCost);
 ServiceRequestResponse toResponse(ServiceRequest request);
}
