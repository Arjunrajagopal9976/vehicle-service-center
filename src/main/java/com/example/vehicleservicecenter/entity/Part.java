package com.example.vehicleservicecenter.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="parts")
public class Part {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,length=120) private String name;
    @Column(name="part_number",nullable=false,unique=true,length=60) private String partNumber;
    @Column(name="unit_price",nullable=false,precision=12,scale=2) private BigDecimal unitPrice;
    @Column(name="stock_quantity",nullable=false) private int stockQuantity;
    public Long getId(){return id;} public String getName(){return name;} public void setName(String v){name=v;}
    public String getPartNumber(){return partNumber;} public void setPartNumber(String v){partNumber=v;}
    public BigDecimal getUnitPrice(){return unitPrice;} public void setUnitPrice(BigDecimal v){unitPrice=v;}
    public int getStockQuantity(){return stockQuantity;} public void setStockQuantity(int v){stockQuantity=v;}
}
