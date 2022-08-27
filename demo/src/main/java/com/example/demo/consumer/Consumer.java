package com.example.demo.consumer;

import com.example.demo.modal.*;
import com.example.demo.utils.GsonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Component
@Slf4j
@RequiredArgsConstructor
public class Consumer {

  private static List<IncidentDto> bufferIncident = new ArrayList<>();
  private static List<OfficerDto> Officer = new ArrayList<>();

  @RabbitListener(queues = "events")
  @RabbitHandler
  public void onMessage(Message message) {
    try {
      String messageBody = new String(message.getBody(), StandardCharsets.UTF_8);
      MessageDto messageDto = GsonUtils.fromJsonString(messageBody, MessageDto.class);
      if(messageDto.getType() == "IncidentOccurred"){
        handleIncidentOccurred(messageBody);
      }
      if(messageDto.getType() == "IncidentResolved"){

      }
      log.info("onMessage = {}", GsonUtils.toJsonString(messageDto));
    } catch (Exception e) {
      log.error("onMessage Exception {}", e.getMessage(), e);
    }
  }

  private void handleIncidentOccurred(String messageBody ){
    IncidentOccurred incidentOccurred = GsonUtils.fromJsonString(messageBody, IncidentOccurred.class);
    IncidentDto incidentDto = IncidentDto.builder()
        .codeName(incidentOccurred.getCodeName())
        .id(incidentOccurred.getIncidentId())
        .isResolve(false)
        .loc(incidentOccurred.getLoc())
        .build();
    bufferIncident.add(incidentDto);
  }

  private void handleIncidentResolve(String messageBody){
    IncidentResolved incidentOccurred = GsonUtils.fromJsonString(messageBody, IncidentResolved.class);
    Optional<IncidentDto> incidentDto1 = bufferIncident.stream().filter(incidentDto -> incidentDto.getId().equals(incidentOccurred.getIncidentId())).findFirst();
    if(incidentDto1.isPresent())
    incidentDto1.get().setIsResolve(true);
  }

//
//  @RabbitListener(queues = "events")
//  @RabbitHandler
//  public void onMessage(IncidentResolved message) {
//    try {
//      log.info("onMessage = {}", message);
//    } catch (Exception e) {
//      log.error("onMessage Exception {}", e.getMessage(), e);
//    }
//  }
//
//  @RabbitListener(queues = "events")
//  @RabbitHandler
//  public void onMessage(OfficerGoesOffline message) {
//    try {
//      log.info("onMessage = {}", GsonUtils.toJsonString(message));
//    } catch (Exception e) {
//      log.error("onMessage Exception {}", e.getMessage(), e);
//    }
//  }
//
//  @RabbitListener(queues = "events")
//  @RabbitHandler
//  public void onMessage(OfficerGoesOnline message) {
//    try {
//      log.info("onMessage = {}", GsonUtils.toJsonString(message));
//    } catch (Exception e) {
//      log.error("onMessage Exception {}", e.getMessage(), e);
//    }
//  }
//
//  @RabbitListener(queues = "events")
//  @RabbitHandler
//  public void onMessage(OfficerLoacationUploaded message) {
//    try {
//      log.info("onMessage = {}", message);
//    } catch (Exception e) {
//      log.error("onMessage Exception {}", e.getMessage(), e);
//    }
//  }
}
