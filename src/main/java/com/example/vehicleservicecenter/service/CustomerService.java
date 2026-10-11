package com.example.vehicleservicecenter.service;

import com.example.vehicleservicecenter.dto.*;
import java.util.List;

public interface CustomerService {
 CustomerResponse create(CustomerCreateRequest request);
 List<CustomerResponse> all();
 CustomerResponse get(Long id);
}
