package com.example.vehicleservicecenter.repository;
import com.example.vehicleservicecenter.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerRepository extends JpaRepository<Customer,Long> {}
