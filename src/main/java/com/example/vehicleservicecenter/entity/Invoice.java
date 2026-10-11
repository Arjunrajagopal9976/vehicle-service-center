package com.example.vehicleservicecenter.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="invoices")
public class Invoice {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @OneToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="service_request_id",nullable=false,unique=true)
    private ServiceRequest serviceRequest;
    @Column(name="labor_cost",nullable=false,precision=12,scale=2) private BigDecimal laborCost;
    @Column(name="parts_cost",nullable=false,precision=12,scale=2) private BigDecimal partsCost;
    @Column(name="total_amount",nullable=false,precision=12,scale=2) private BigDecimal totalAmount;
    @Column(name="issued_at",nullable=false) private LocalDateTime issuedAt;
    @PrePersist void onCreate(){issuedAt=LocalDateTime.now();}
    public Long getId(){return id;} public ServiceRequest getServiceRequest(){return serviceRequest;} public void setServiceRequest(ServiceRequest v){serviceRequest=v;}
    public BigDecimal getLaborCost(){return laborCost;} public void setLaborCost(BigDecimal v){laborCost=v;}
    public BigDecimal getPartsCost(){return partsCost;} public void setPartsCost(BigDecimal v){partsCost=v;}
    public BigDecimal getTotalAmount(){return totalAmount;} public void setTotalAmount(BigDecimal v){totalAmount=v;}
    public LocalDateTime getIssuedAt(){return issuedAt;}
}
