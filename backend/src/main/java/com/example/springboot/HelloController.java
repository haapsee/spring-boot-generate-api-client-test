package com.example.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Hello API", description = "Basic greeting endpoints")
public class HelloController {

	@Operation(summary = "Get a greeting", description = "Returns a simple greeting message from Spring Boot")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Successfully retrieved greeting")
	})
	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

}