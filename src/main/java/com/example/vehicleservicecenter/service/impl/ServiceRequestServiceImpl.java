package com.example.vehicleservicecenter.service.impl;

import com.example.vehicleservicecenter.dto.*;
import com.example.vehicleservicecenter.entity.*;
import com.example.vehicleservicecenter.enums.JobStatus;
import com.example.vehicleservicecenter.exception.*;
import com.example.vehicleservicecenter.repository.*;
import com.example.vehicleservicecenter.service.ServiceRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.*;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {
 private final ServiceRequestRepository requests; private final VehicleRepository vehicles; private final MechanicRepository mechanics;
 private final PartRepository parts; private final InvoiceRepository invoices;
 public ServiceRequestServiceImpl(ServiceRequestRepository r,VehicleRepository v,MechanicRepository m,PartRepository p,InvoiceRepository i){requests=r;vehicles=v;mechanics=m;parts=p;invoices=i;}
 @Transactional public ServiceRequestResponse book(ServiceBookingRequest r){
  Vehicle v=vehicles.findById(r.vehicleId()).orElseThrow(()->new NotFoundException("Vehicle not found: "+r.vehicleId()));
  ServiceRequest s=new ServiceRequest();s.setVehicle(v);s.setDescription(r.description().trim());s.setScheduledAt(r.scheduledAt());s.setStatus(JobStatus.BOOKED);return toResponse(requests.save(s));
 }
 @Transactional(readOnly=true) public ServiceRequestResponse get(Long id){return toResponse(find(id));}
 @Transactional(readOnly=true) public List<ServiceRequestResponse> all(JobStatus status){return (status==null?requests.findAll():requests.findByStatus(status)).stream().map(this::toResponse).toList();}
 @Transactional public ServiceRequestResponse updateStatus(Long id,StatusUpdateRequest r){
  ServiceRequest s=find(id);if(!allowed(s.getStatus(),r.status()))throw new BadRequestException("Invalid status transition: "+s.getStatus()+" -> "+r.status());
  s.setStatus(r.status());if(r.notes()!=null&&!r.notes().isBlank())s.setNotes(r.notes().trim());return toResponse(requests.save(s));
 }
 private boolean allowed(JobStatus from,JobStatus to){
  if(from==to)return true;if(to==JobStatus.CANCELLED)return from!=JobStatus.COMPLETED&&from!=JobStatus.CANCELLED;
  return switch(from){case BOOKED->to==JobStatus.INSPECTION;case INSPECTION->to==JobStatus.IN_PROGRESS;
   case IN_PROGRESS->to==JobStatus.WAITING_FOR_PARTS||to==JobStatus.COMPLETED;
   case WAITING_FOR_PARTS->to==JobStatus.IN_PROGRESS;case COMPLETED,CANCELLED->false;};
 }
 @Transactional public ServiceRequestResponse assignMechanic(Long id,Long mechanicId){
  ServiceRequest s=find(id);Mechanic m=mechanics.findById(mechanicId).orElseThrow(()->new NotFoundException("Mechanic not found: "+mechanicId));
  if(!m.isAvailable())throw new BadRequestException("Mechanic is not available");s.setMechanic(m);return toResponse(requests.save(s));
 }
 @Transactional public ServiceRequestResponse addPart(Long id,Long partId,int quantity){
  if(quantity<1)throw new BadRequestException("Quantity must be at least 1");
  ServiceRequest s=find(id);Part p=parts.findById(partId).orElseThrow(()->new NotFoundException("Part not found: "+partId));
  int old=s.getPartQuantities().getOrDefault(partId,0);if(p.getStockQuantity()<quantity)throw new BadRequestException("Insufficient part stock");
  p.setStockQuantity(p.getStockQuantity()-quantity);s.getParts().put(partId,p);s.getPartQuantities().put(partId,old+quantity);
  parts.save(p);return toResponse(requests.save(s));
 }
 @Transactional public InvoiceResponse invoice(Long id,BigDecimal laborCost){
  if(laborCost==null||laborCost.signum()<0)throw new BadRequestException("Labor cost cannot be negative");
  ServiceRequest s=find(id);if(s.getStatus()!=JobStatus.COMPLETED)throw new BadRequestException("Complete the service job before invoicing");
  if(invoices.findByServiceRequestId(id).isPresent())throw new BadRequestException("Invoice already exists for this service request");
  BigDecimal partCost=BigDecimal.ZERO;
  for(var e:s.getParts().entrySet()){Part p=e.getValue();partCost=partCost.add(p.getUnitPrice().multiply(BigDecimal.valueOf(s.getPartQuantities().getOrDefault(e.getKey(),1))));}
  Invoice inv=new Invoice();inv.setServiceRequest(s);inv.setLaborCost(laborCost);inv.setPartsCost(partCost);inv.setTotalAmount(laborCost.add(partCost));inv=invoices.save(inv);
  return new InvoiceResponse(inv.getId(),id,inv.getLaborCost(),inv.getPartsCost(),inv.getTotalAmount(),inv.getIssuedAt());
 }
 @Transactional(readOnly=true) public ServiceRequestResponse toResponse(ServiceRequest s){
  return new ServiceRequestResponse(s.getId(),s.getVehicle().getId(),s.getVehicle().getRegistrationNumber(),
   s.getMechanic()==null?null:s.getMechanic().getId(),s.getMechanic()==null?null:s.getMechanic().getName(),
   s.getDescription(),s.getStatus(),s.getScheduledAt(),s.getNotes(),s.getCreatedAt(),
   s.getParts().entrySet().stream().map(e->new ServiceRequestResponse.PartLine(e.getKey(),e.getValue().getName(),s.getPartQuantities().getOrDefault(e.getKey(),1),e.getValue().getUnitPrice().toPlainString())).toList());
 }
 private ServiceRequest find(Long id){return requests.findById(id).orElseThrow(()->new NotFoundException("Service request not found: "+id));}
}
