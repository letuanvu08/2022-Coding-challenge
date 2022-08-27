package com.example.demo.modal;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OfficerDto {
  private String id;
  private String badgeName;
  private Location loc;
}
