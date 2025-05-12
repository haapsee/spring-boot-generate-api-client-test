package com.example.application;

import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.api.HelloApiApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

/**
 * A simple console application that uses the client module to query the backend.
 */
public class ConsoleApplication {
    private static final Logger logger = LoggerFactory.getLogger(ConsoleApplication.class);
    private static final String DEFAULT_BASE_PATH = "http://localhost:8080";
    
    public static void main(String[] args) {
        System.out.println("Spring Boot Console Application");
        System.out.println("==============================");
        
        // Configure API client
        ApiClient apiClient = Configuration.getDefaultApiClient();
        apiClient.setBasePath(DEFAULT_BASE_PATH);
        
        // Create API instance
        HelloApiApi helloApi = new HelloApiApi(apiClient);
        
        // Interactive console
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            
            while (running) {
                System.out.println("\nAvailable commands:");
                System.out.println("1. Get greeting");
                System.out.println("0. Exit");
                System.out.print("\nEnter command: ");
                
                String input = scanner.nextLine().trim();
                
                switch (input) {
                    case "1":
                        getGreeting(helloApi);
                        break;
                    case "0":
                        running = false;
                        System.out.println("Exiting application...");
                        break;
                    default:
                        System.out.println("Unknown command. Please try again.");
                }
            }
        }
    }

    private static void getGreeting(HelloApiApi helloApi) {
        try {
            String greeting = helloApi.index();
            System.out.println("Greeting from Spring Boot: " + greeting);
        } catch (ApiException e) {
            logger.error("Error occurred while fetching greeting: {}", e.getMessage());
            System.out.println("Failed to fetch greeting. Please check the server status.");
        }
    }
    
}