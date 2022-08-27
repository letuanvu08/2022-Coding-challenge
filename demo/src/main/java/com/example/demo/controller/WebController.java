package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class WebController {

  @GetMapping("/api/v1/state")
  public ResponseEntity<?> getMessage(){
    return ResponseEntity.ok("dsfdsg");
  }

}
