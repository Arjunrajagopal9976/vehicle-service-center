package com.example.vehicleservicecenter.controller;
import com.example.vehicleservicecenter.dto.*;
import com.example.vehicleservicecenter.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/vehicles")
public class VehicleController {
 private final VehicleService service; public VehicleController(VehicleService s){service=s;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public CustomerResponse.VehicleSummary create(@Valid @RequestBody VehicleCreateRequest r){return service.create(r);}
 @GetMapping("/{id}/history") public VehicleService.ServiceRequestResponseHistory history(@PathVariable Long id){return service.history(id);}
}
