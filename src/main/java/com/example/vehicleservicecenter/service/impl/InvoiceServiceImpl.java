package com.example.vehicleservicecenter.service.impl;
import com.example.vehicleservicecenter.dto.InvoiceResponse;
import com.example.vehicleservicecenter.exception.NotFoundException;
import com.example.vehicleservicecenter.repository.InvoiceRepository;
import com.example.vehicleservicecenter.service.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class InvoiceServiceImpl implements InvoiceService {
 private final InvoiceRepository repo;
 public InvoiceServiceImpl(InvoiceRepository repo){this.repo=repo;}
 @Transactional(readOnly=true) public InvoiceResponse get(Long id){var i=repo.findById(id).orElseThrow(()->new NotFoundException("Invoice not found: "+id));
 return new InvoiceResponse(i.getId(),i.getServiceRequest().getId(),i.getLaborCost(),i.getPartsCost(),i.getTotalAmount(),i.getIssuedAt());}
}
