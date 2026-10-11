package com.example.vehicleservicecenter.controller;
import com.example.vehicleservicecenter.dto.*;
import com.example.vehicleservicecenter.enums.JobStatus;
import com.example.vehicleservicecenter.service.ServiceRequestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
@RestController @RequestMapping("/api/service-requests")
public class ServiceRequestController {
 private final ServiceRequestService service; public ServiceRequestController(ServiceRequestService s){service=s;}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public ServiceRequestResponse book(@Valid @RequestBody ServiceBookingRequest r){return service.book(r);}
 @GetMapping public List<ServiceRequestResponse> all(@RequestParam(required=false) JobStatus status){return service.all(status);}
 @GetMapping("/{id}") public ServiceRequestResponse get(@PathVariable Long id){return service.get(id);}
 @PatchMapping("/{id}/status") public ServiceRequestResponse status(@PathVariable Long id,@Valid @RequestBody StatusUpdateRequest r){return service.updateStatus(id,r);}
 @PatchMapping("/{id}/mechanic/{mechanicId}") public ServiceRequestResponse assign(@PathVariable Long id,@PathVariable Long mechanicId){return service.assignMechanic(id,mechanicId);}
 @PostMapping("/{id}/parts/{partId}") public ServiceRequestResponse addPart(@PathVariable Long id,@PathVariable Long partId,@RequestParam(defaultValue="1") int quantity){return service.addPart(id,partId,quantity);}
 @PostMapping("/{id}/invoice") public InvoiceResponse invoice(@PathVariable Long id,@RequestParam BigDecimal laborCost){return service.invoice(id,laborCost);}
}
