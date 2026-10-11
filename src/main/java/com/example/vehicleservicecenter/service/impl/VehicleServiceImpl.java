package com.example.vehicleservicecenter.service.impl;

import com.example.vehicleservicecenter.dto.*;
import com.example.vehicleservicecenter.entity.*;
import com.example.vehicleservicecenter.exception.*;
import com.example.vehicleservicecenter.repository.*;
import com.example.vehicleservicecenter.service.VehicleService;
import com.example.vehicleservicecenter.service.ServiceRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VehicleServiceImpl implements VehicleService {
 private final VehicleRepository vehicles; private final CustomerRepository customers; private final ServiceRequestRepository requests; private final ServiceRequestService requestService;
 public VehicleServiceImpl(VehicleRepository v,CustomerRepository c,ServiceRequestRepository r,ServiceRequestService s){vehicles=v;customers=c;requests=r;requestService=s;}
 @Transactional public CustomerResponse.VehicleSummary create(VehicleCreateRequest r){
  Customer c=customers.findById(r.customerId()).orElseThrow(()->new NotFoundException("Customer not found: "+r.customerId()));
  if(vehicles.existsByRegistrationNumber(r.registrationNumber()))throw new BadRequestException("Registration number already exists");
  Vehicle v=new Vehicle();v.setCustomer(c);v.setRegistrationNumber(r.registrationNumber().trim().toUpperCase());v.setMake(r.make().trim());v.setModel(r.model().trim());v.setYear(r.year());
  v=vehicles.save(v);return new CustomerResponse.VehicleSummary(v.getId(),v.getRegistrationNumber(),v.getMake(),v.getModel(),v.getYear());
 }
 @Transactional(readOnly=true) public ServiceRequestResponseHistory history(Long id){
  Vehicle v=vehicles.findById(id).orElseThrow(()->new NotFoundException("Vehicle not found: "+id));
  return new ServiceRequestResponseHistory(id,v.getRegistrationNumber(),requests.findByVehicleIdOrderByCreatedAtDesc(id).stream().map(requestService::toResponse).toList());
 }
}
