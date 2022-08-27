package com.example.demo.modal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IncidentDto {
  private String id;
  private String codeName;
  private Location loc;
  private Boolean isResolve;
  private String officerId;
}
