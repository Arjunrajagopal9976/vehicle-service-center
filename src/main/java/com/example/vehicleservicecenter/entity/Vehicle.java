package com.example.vehicleservicecenter.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicles")
public class Vehicle {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
    @Column(name="registration_number", nullable=false, unique=true, length=30) private String registrationNumber;
    @Column(nullable=false, length=80) private String make;
    @Column(nullable=false, length=80) private String model;
    @Column(name="vehicle_year", nullable=false) private int year;
    @Column(name="created_at", nullable=false, updatable=false) private LocalDateTime createdAt;
    @OneToMany(mappedBy="vehicle") private List<ServiceRequest> serviceRequests = new ArrayList<>();
    @PrePersist void onCreate(){createdAt=LocalDateTime.now();}
    public Long getId(){return id;} public Customer getCustomer(){return customer;} public void setCustomer(Customer v){customer=v;}
    public String getRegistrationNumber(){return registrationNumber;} public void setRegistrationNumber(String v){registrationNumber=v;}
    public String getMake(){return make;} public void setMake(String v){make=v;}
    public String getModel(){return model;} public void setModel(String v){model=v;}
    public int getYear(){return year;} public void setYear(int v){year=v;}
    public LocalDateTime getCreatedAt(){return createdAt;} public List<ServiceRequest> getServiceRequests(){return serviceRequests;}
}
