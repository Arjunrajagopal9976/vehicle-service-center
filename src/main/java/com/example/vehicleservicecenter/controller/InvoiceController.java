package com.example.vehicleservicecenter.controller;
import com.example.vehicleservicecenter.dto.InvoiceResponse;
import com.example.vehicleservicecenter.service.InvoiceService;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/invoices")
public class InvoiceController {
 private final InvoiceService service; public InvoiceController(InvoiceService s){service=s;}
 @GetMapping("/{id}") public InvoiceResponse get(@PathVariable Long id){return service.get(id);}
}
