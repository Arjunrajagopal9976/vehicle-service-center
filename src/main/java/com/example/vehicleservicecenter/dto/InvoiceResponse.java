package com.example.vehicleservicecenter.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record InvoiceResponse(Long id, Long serviceRequestId, BigDecimal laborCost,
 BigDecimal partsCost, BigDecimal totalAmount, LocalDateTime issuedAt) {}
