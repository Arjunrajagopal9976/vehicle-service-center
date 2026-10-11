package com.example.vehicleservicecenter.repository;
import com.example.vehicleservicecenter.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface InvoiceRepository extends JpaRepository<Invoice,Long> { Optional<Invoice> findByServiceRequestId(Long serviceRequestId); }
