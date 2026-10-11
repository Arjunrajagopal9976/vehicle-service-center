package com.example.vehicleservicecenter.model;

import com.example.vehicleservicecenter.enums.JobStatus;
import java.util.Map;
import java.util.Set;

public final class JobWorkflow {
 private JobWorkflow(){}
 public static final Map<JobStatus, Set<JobStatus>> TRANSITIONS = Map.of(
  JobStatus.BOOKED,Set.of(JobStatus.INSPECTION,JobStatus.CANCELLED),
  JobStatus.INSPECTION,Set.of(JobStatus.IN_PROGRESS,JobStatus.CANCELLED),
  JobStatus.IN_PROGRESS,Set.of(JobStatus.WAITING_FOR_PARTS,JobStatus.COMPLETED,JobStatus.CANCELLED),
  JobStatus.WAITING_FOR_PARTS,Set.of(JobStatus.IN_PROGRESS,JobStatus.CANCELLED),
  JobStatus.COMPLETED,Set.of(), JobStatus.CANCELLED,Set.of());
}
