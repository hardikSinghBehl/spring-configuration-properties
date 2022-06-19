package com.behl.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

	@GetMapping("/v1/ping")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<Map<String, String>> healthCheckHandler() {
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "pong"));
	}

}
