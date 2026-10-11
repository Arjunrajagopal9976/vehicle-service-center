package com.example.vehicleservicecenter.repository;
import com.example.vehicleservicecenter.entity.ServiceRequest;
import com.example.vehicleservicecenter.enums.JobStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest,Long> {
 List<ServiceRequest> findByStatus(JobStatus status);
 List<ServiceRequest> findByVehicleIdOrderByCreatedAtDesc(Long vehicleId);
}
