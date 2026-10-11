package com.example.vehicleservicecenter.controller;

import com.example.vehicleservicecenter.dto.ServiceRequestResponse;
import com.example.vehicleservicecenter.enums.JobStatus;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ResponseEntityTest {
 @Test void responseContainsWorkflowState(){
  var response=new ServiceRequestResponse(1L,2L,"TN38AB1234",null,null,"Oil service",JobStatus.BOOKED,
   LocalDateTime.now().plusDays(1),null,LocalDateTime.now(),List.of());
  assertEquals(JobStatus.BOOKED,response.status());
  assertEquals("TN38AB1234",response.registrationNumber());
 }
}
