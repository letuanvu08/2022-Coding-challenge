package com.example.demo.modal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IncidentOccurred {
  private String type;
  private String incidentId;
  private String codeName;
  private Location loc;
}
