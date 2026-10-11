package com.example.vehicleservicecenter.entity;

import com.example.vehicleservicecenter.enums.JobStatus;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Entity
@Table(name="service_requests")
public class ServiceRequest extends AuditedEntity {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="vehicle_id",nullable=false) private Vehicle vehicle;
    @ManyToOne(fetch=FetchType.LAZY) @JoinColumn(name="mechanic_id") private Mechanic mechanic;
    @Column(nullable=false,length=1000) private String description;
    @Enumerated(EnumType.STRING) @Column(nullable=false,length=40) private JobStatus status=JobStatus.BOOKED;
    @Column(name="scheduled_at",nullable=false) private LocalDateTime scheduledAt;
    @Column(length=2000) private String notes;
    @ManyToMany
    @JoinTable(name="service_request_parts",
        joinColumns=@JoinColumn(name="service_request_id"),
        inverseJoinColumns=@JoinColumn(name="part_id"))
    @MapKeyJoinColumn(name="part_id")
    private Map<Long, Part> parts = new LinkedHashMap<>();
    @ElementCollection
    @CollectionTable(name="service_request_part_quantities", joinColumns=@JoinColumn(name="service_request_id"))
    @MapKeyColumn(name="part_id")
    @Column(name="quantity")
    private Map<Long,Integer> partQuantities = new LinkedHashMap<>();

    public Long getId(){return id;} public Vehicle getVehicle(){return vehicle;} public void setVehicle(Vehicle v){vehicle=v;}
    public Mechanic getMechanic(){return mechanic;} public void setMechanic(Mechanic v){mechanic=v;}
    public String getDescription(){return description;} public void setDescription(String v){description=v;}
    public JobStatus getStatus(){return status;} public void setStatus(JobStatus v){status=v;}
    public LocalDateTime getScheduledAt(){return scheduledAt;} public void setScheduledAt(LocalDateTime v){scheduledAt=v;}
    public String getNotes(){return notes;} public void setNotes(String v){notes=v;}
    public Map<Long,Part> getParts(){return parts;} public Map<Long,Integer> getPartQuantities(){return partQuantities;}
}
