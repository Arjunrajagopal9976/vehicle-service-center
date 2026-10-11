package com.example.vehicleservicecenter.entity;

import jakarta.persistence.*;

@Entity
@Table(name="mechanics")
public class Mechanic {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=100) private String name;
    @Column(nullable=false,length=20) private String phone;
    @Column(nullable=false,length=100) private String specialization;
    @Column(nullable=false) private boolean available=true;
    public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
    public String getPhone(){return phone;} public void setPhone(String v){phone=v;}
    public String getSpecialization(){return specialization;} public void setSpecialization(String v){specialization=v;}
    public boolean isAvailable(){return available;} public void setAvailable(boolean v){available=v;}
}
