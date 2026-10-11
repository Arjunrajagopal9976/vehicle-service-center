package com.example.vehicleservicecenter.repository;
import com.example.vehicleservicecenter.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
public interface VehicleRepository extends JpaRepository<Vehicle,Long> { boolean existsByRegistrationNumber(String registrationNumber); }
