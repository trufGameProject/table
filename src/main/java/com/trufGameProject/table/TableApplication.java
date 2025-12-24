package com.trufGameProject.table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TableApplication {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/table/")
	public String listTables() {
		return "Here will be the List of Tables";
	}

	@GetMapping("/table/{id}")
	public String tableInfo(@PathVariable String id) {
		return "Here will output the information of table at " + id;
	}

	@PostMapping("/table")
	public String createTable(@RequestParam String tableId, @RequestParam int tableStatus) {
		return "Creating: " + tableId + "Status: " + tableStatus;
	}
	public static void main(String[] args) {
		SpringApplication.run(TableApplication.class, args);
	}

} 
