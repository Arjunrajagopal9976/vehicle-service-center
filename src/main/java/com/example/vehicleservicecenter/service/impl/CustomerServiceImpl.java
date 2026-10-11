package com.example.vehicleservicecenter.service.impl;

import com.example.vehicleservicecenter.dto.*;
import com.example.vehicleservicecenter.entity.*;
import com.example.vehicleservicecenter.exception.NotFoundException;
import com.example.vehicleservicecenter.repository.CustomerRepository;
import com.example.vehicleservicecenter.service.CustomerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

@Service
public class CustomerServiceImpl implements CustomerService {
 private final CustomerRepository repo;
 public CustomerServiceImpl(CustomerRepository repo){this.repo=repo;}
 @Transactional public CustomerResponse create(CustomerCreateRequest r){
  Customer c=new Customer(); c.setName(r.name().trim()); c.setEmail(r.email().trim()); c.setPhone(r.phone().trim()); return response(repo.save(c));
 }
 @Transactional(readOnly=true) public List<CustomerResponse> all(){return repo.findAll().stream().map(this::response).toList();}
 @Transactional(readOnly=true) public CustomerResponse get(Long id){return response(repo.findById(id).orElseThrow(()->new NotFoundException("Customer not found: "+id)));}
 private CustomerResponse response(Customer c){return new CustomerResponse(c.getId(),c.getName(),c.getEmail(),c.getPhone(),
  c.getVehicles().stream().map(v->new CustomerResponse.VehicleSummary(v.getId(),v.getRegistrationNumber(),v.getMake(),v.getModel(),v.getYear())).toList());}
}
