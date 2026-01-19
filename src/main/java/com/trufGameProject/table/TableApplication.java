package com.trufGameProject.table;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;

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
		try {
			String sql = "SELECT * FROM myTable";
		
			List<TableEntity> listTables = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TableEntity.class));
			String JSONString = "[";
			for (TableEntity tables : listTables) {
				if (!JSONString.equals("[")) {JSONString += ", ";}
				JSONString += tables.toJSON();
			}
			JSONString += "]";
			return JSONString;
		}
		catch (Exception e) {
			Error error = new Error(Instant.now().toString(), 400,"Error", 0);
			return error.toJSON();
		}
	}

	@GetMapping("/table/{id}")
	public String tableInfo(@PathVariable String id) {
		try {
			String sql = "SELECT * FROM myTable WHERE tableId = ?";
			TableEntity table = jdbcTemplate.queryForObject(sql,
			BeanPropertyRowMapper.newInstance(TableEntity.class), id);
			
			return table.toJSON();
		}
		catch (Exception e) {
			Error error = new Error(Instant.now().toString(), 404,"Table not found", 2);
			return error.toJSON();
		}
	}

	@GetMapping("/table/type/{tableStatus}")
	public String getTableofStatus(@PathVariable int tableStatus) {
		try {
			if (tableStatus < 0 || tableStatus > 4) {throw new IndexOutOfBoundsException();}
			String sql = "SELECT * FROM myTable WHERE tableStatus = ?";
			TableEntity table = jdbcTemplate.queryForObject(sql,
			BeanPropertyRowMapper.newInstance(TableEntity.class), tableStatus);
			
			return table.toJSON();
		}
		catch (IndexOutOfBoundsException eout) {
			Error error = new Error(Instant.now().toString(), 422,"Table status out of range (0-4)", 4);
			return error.toJSON();
		}
		catch (Exception e) {
			Error error = new Error(Instant.now().toString(), 404,"Error", 0);
			return error.toJSON();
		}
	}

	@PostMapping("/table")
	public String createTable(@RequestBody TableInfo table) {
		try {
			String sql = "INSERT INTO myTable (tableId, tableStatus) VALUES (?, ?)";
			int result = jdbcTemplate.update(sql, table.getTableId(), table.getTableStatus());
			
			if (result > 0) { System.out.println("Insert successfully.");}
			return tableInfo(table.getTableId());
		}
		catch (Exception e) {
			Error error = new Error(Instant.now().toString(), 404,"Error", 0);
			return error.toJSON();
		}
	}

	@DeleteMapping("/table/{id}")
	public ResponseEntity<?> tableDelete(@PathVariable String id) {
		String sql = "DELETE FROM myTable WHERE tableId = ?";
    	int delete = jdbcTemplate.update(sql, id);
		if (delete == 0) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(Instant.now().toString(), 404,"Table not found", 2));}
		else {return ResponseEntity.ok(new Error(Instant.now().toString(), 200, "Successful deletion", 1));}
	}

	@PatchMapping("/table/{id}/position/{position}") 
	public ResponseEntity<?> positionUpdate(@PathVariable String id, @PathVariable String position, @RequestBody PId pid) {
		String sql = "UPDATE myTable SET ";
		if (position.equals("North")) {sql += "North = ? WHERE tableId = ?";}
		else if (position.equals("West")) {sql += "West = ? WHERE tableId = ?";}
		else if (position.equals("South")) {sql += "South = ? WHERE tableId = ?";}
		else if (position.equals("East")) {sql += "East = ? WHERE tableId = ?";}
		else {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(Instant.now().toString(), 404,"Invalid position. Please enter North, West, South, or East", 3));}
		int update = jdbcTemplate.update(sql, pid.getPid(), id);
		if (update == 0) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(Instant.now().toString(), 404,"Table not found", 2));}
		return ResponseEntity.ok(new Error(Instant.now().toString(), 200, "Successful input", 1));
	}

	@PatchMapping("/table/{id}/status/{tableStatus}") 
	public ResponseEntity<?> statusUpdate(@PathVariable String id, @PathVariable int tableStatus) {
		if (tableStatus > 4 || tableStatus < 0) {return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT).body(new Error(Instant.now().toString(), 422,"Table status out of range (0-4)", 4));}
		String sql = "UPDATE myTable SET tableStatus = ? WHERE tableId = ?";
        int update = jdbcTemplate.update(sql, tableStatus, id);
		if (update == 0) {return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error(Instant.now().toString(), 404,"Table not found", 5));}
		return ResponseEntity.ok(new Error(Instant.now().toString(), 200, "Successful update", 1));
	}

	public static void main(String[] args) {
		SpringApplication.run(TableApplication.class, args);
	}

} 
