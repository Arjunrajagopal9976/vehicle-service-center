package com.example.vehicleservicecenter.service;

import com.example.vehicleservicecenter.enums.JobStatus;
import com.example.vehicleservicecenter.model.JobWorkflow;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JobWorkflowTest {
 @Test void bookedCanMoveToInspection(){assertTrue(JobWorkflow.TRANSITIONS.get(JobStatus.BOOKED).contains(JobStatus.INSPECTION));}
 @Test void completedCannotMoveToAnotherState(){assertTrue(JobWorkflow.TRANSITIONS.get(JobStatus.COMPLETED).isEmpty());}
}
