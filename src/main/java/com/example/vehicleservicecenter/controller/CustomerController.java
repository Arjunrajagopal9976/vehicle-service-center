package com.example.vehicleservicecenter.controller;
import com.example.vehicleservicecenter.dto.*;
import com.example.vehicleservicecenter.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/customers")
public class CustomerController {
 private final CustomerService service; public CustomerController(CustomerService s){service=s;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public CustomerResponse create(@Valid @RequestBody CustomerCreateRequest r){return service.create(r);}
 @GetMapping public List<CustomerResponse> all(){return service.all();}
 @GetMapping("/{id}") public CustomerResponse get(@PathVariable Long id){return service.get(id);}
}
