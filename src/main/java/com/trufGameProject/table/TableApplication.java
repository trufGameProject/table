package com.trufGameProject.table;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@RestController
@SpringBootApplication
public class TableApplication {
	@Autowired
    JdbcTemplate jdbcTemplate;

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/table/")
	public String listTables() {
		String sql = "SELECT * FROM table";
    
		List<TableEntity> listTables = jdbcTemplate.query(sql,
					BeanPropertyRowMapper.newInstance(TableEntity.class));
		String JSONString = "{";
		for (TableEntity tables : listTables) {
			if (!JSONString.equals("{")) {
				JSONString += ", ";
			}
			JSONString += tables.toJSON();
		}
		JSONString += "}";
		return JSONString;
	}

	@GetMapping("/table/{id}")
	public String tableInfo(@PathVariable String id) {
		String sql = "SELECT * FROM table WHERE tableId = ?";
    	TableEntity table = jdbcTemplate.queryForObject(sql,
        BeanPropertyRowMapper.newInstance(TableEntity.class), id);
    	
		return table.toJSON();
	}

	@PostMapping("/table")
	public String createTable(@RequestParam String tableId, @RequestParam int tableStatus) {
		String sql = "INSERT INTO table (tableId, tableStatus) VALUES (?, ?)";
        int result = jdbcTemplate.update(sql, tableId, tableStatus);
        
        if (result > 0) {
            System.out.println("Insert successfully.");
        }
		return tableInfo(tableId);
	}

	public static void main(String[] args) {
		SpringApplication.run(TableApplication.class, args);
	}

} 
